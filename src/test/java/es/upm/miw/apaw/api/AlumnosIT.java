package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.AlumnoApiController;
import es.upm.miw.apaw.api.apiControllers.ProfesorApiController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.api.entities.Asignatura;
import es.upm.miw.apaw.http.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AlumnosIT {

    @Test
    void testCreateAlumnoWithAlumnoDtoProfesorId() {
        createAlumno(createProfesor());
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoProfesorIdNull() {
        createAlumno(null);
    }

    private String createAlumno(String profesorId) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Alumno", "Apellido1 Apellido2", profesorId)).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());

        return (String) response.getBody();
    }

    private String createProfesor() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto("Profesor", "Apellido1 Apellido2")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoProfesorIdEmpty() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Alumno", "Apellido1 Apellido2", "")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("PROFESORID IS EMPTY"));
    }

    @Test
    void testAlumnoInvalidRequest() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlumnoWithoutAlumnoDto() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoNombreNull() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto(null, null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOMBRE IS MISSING"));
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoNombreEmpty() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("", "")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOMBRE IS EMPTY"));
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoApellidosNull() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Nombre", null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("APELLIDOS IS MISSING"));
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoApellidosEmpty() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Nombre", "   ")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("APELLIDOS IS EMPTY"));
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoProfesorId() {
        String profesorId = createProfesor();
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("AlumnoUpdated", "Apellido1 Apellido2 Updated", profesorId)).put();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoProfesorIdNull() {
        String profesorId = createProfesor();
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("AlumnoUpdated", "Apellido1 Apellido2 Updated", profesorId)).put();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoProfesorIdEmpty() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("AlumnoUpdated", "Apellido1 Apellido2 Updated", "")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("PROFESORID IS EMPTY"));
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoNombreNull() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto(null, null)).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOMBRE IS MISSING"));
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoNombreEmpty() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("", "")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOMBRE IS EMPTY"));
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoApellidosNull() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("Nombre", null)).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("APELLIDOS IS MISSING"));
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoApellidosEmpty() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("Nombre", "   ")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("APELLIDOS IS EMPTY"));
    }

    @Test
    void testUpdateAlumnoWithoutAlumnoDto() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(null).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateAlumnoNotFoundException() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath("s5FdeGf54D").body(new AlumnoDto("Nombre", "Apellidos")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoProfesorIdNotFoundException() {
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("Alumno Updated", "Apellido1 Apellido2 Updated", "s5FdeGf54D")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("PROFESOR"));
    }


    @Test
    void testCreatePractica() {
        createPractica(createAlumno(null), "APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW);
    }

    private void createPractica(String alumnoId, String nombrePractica, Asignatura asignatura) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS)
                .expandPath(alumnoId).body(PracticaDto.builder(nombrePractica, asignatura).build()).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
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
        createAlumnoWithPracticas();
    }

    String createAlumnoWithPracticas() {
        String alumnoId = createAlumno(null);
        createPractica(alumnoId, "APAW. ECP1. Patrones de Diseño", Asignatura.APAW);
        createPractica(alumnoId, "APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW);
        createPractica(alumnoId, "IWVG. Trabajo práctico: Web 2.0", Asignatura.IWVG);
        createPractica(alumnoId, "IWVG. Software Colaborativo", Asignatura.IWVG);

        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS).expandPath(alumnoId).get();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
        assertTrue(((Set<PracticaDto>) response.getBody()).size() == 4);

        return alumnoId;
    }

    @Test
    void testReadAllPracticasAlumnoWithAlumnoIdNotFound() {
        readAllPracticasAlumnoWithAlumnoIdNotFound("s5FdeGf54D");
    }

    void readAllPracticasAlumnoWithAlumnoIdNotFound(String alumnoId) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS).expandPath(alumnoId).get();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());

        assertTrue(((Set<PracticaDto>) response.getBody()).size() == 0);
    }

    @Test
    void testDeleteAlumno() {
        String alumnoId = createAlumnoWithPracticas();
        deleteAlumno(alumnoId);
        readAllPracticasAlumnoWithAlumnoIdNotFound(alumnoId);
    }

    @Test
    void testDeleteAlumnoNotFound() {
        deleteAlumno("s5FdeGf54D");
    }

    void deleteAlumno(String alumnoId) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).expandPath(alumnoId).delete();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

}
