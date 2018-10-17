package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.AlumnoApiController;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.entities.Asignatura;
import es.upm.miw.apaw.http.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AlumnosPracticasIT extends AlumnosITSupport {

    @Test
    void testCreatePractica() {
        createPractica(createAlumno(null), "APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW);
    }

    @Test
    void testCreatePracticaWithPracticaDtoNull() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS)
                .expandPath(alumnoId).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePracticaWithAlumnoIdNotFoundException() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS).expandPath("s5FdeGf54D").body(PracticaDto.builder("APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW).build()).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("ALUMNO"));
    }

    @Test
    void testReadAllPracticasAlumno() {
        HttpResponse response = readAllPracticasAlumno(createAlumnoWithPracticas());
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
        assertTrue(((Set<PracticaDto>) response.getBody()).size() == 4);
    }

    @Test
    void testReadAllPracticasAlumnoWithAlumnoIdNotFound() {
        HttpResponse response = readAllPracticasAlumno("s5FdeGf54D");
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
        assertTrue(((Set<PracticaDto>) response.getBody()).size() == 0);
    }

    @Test
    void testUpdateNotaPractica() {
        String alumnoId = createAlumno(null);
        String practicaId = createPractica(alumnoId, "APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW);
        HttpResponse response = updateNotaPractica(alumnoId, practicaId, 8);
        assertEquals(HttpStatus.OK, response.getStatus());

        response = readAllPracticasAlumno(alumnoId);
        PracticaDto practicaDto = ((Set<PracticaDto>) response.getBody()).iterator().next();
        assertNotNull(practicaDto);
        assertEquals(Integer.valueOf(8), practicaDto.getNota());
    }

    @Test
    void testUpdateNotaPracticaWithNotaNull() {
        String alumnoId = createAlumno(null);
        String practicaId = createPractica(alumnoId, "APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW);
        HttpException exception = assertThrows(HttpException.class, () -> updateNotaPractica(alumnoId, practicaId, null));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOTA"));
    }

    @Test
    void testUpdateNotaPracticaNotFound() {
        String alumnoId = createAlumno(null);
        HttpException exception = assertThrows(HttpException.class, () -> updateNotaPractica(alumnoId, "s5FdeGf54D", 8));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("PRACTICA"));
    }

    @Test
    void testUpdateNotaPracticaAlumnoNotFound() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).expandPath("s5FdeGf54D").path(AlumnoApiController.PRACTICAS).path(AlumnoApiController.ID_ID).expandPath("s5FdeGf54D").path(AlumnoApiController.NOTA).body(8).patch();
        HttpException exception = assertThrows(HttpException.class, () -> updateNotaPractica("s5FdeGf54D", "s5FdeGf54D", 8));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("ALUMNO"));
    }

}
