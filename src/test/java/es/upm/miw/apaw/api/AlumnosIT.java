package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.AlumnoApiController;
import es.upm.miw.apaw.api.apiControllers.ProfesorApiController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.AlumnoWithMediaDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.api.entities.Asignatura;
import es.upm.miw.apaw.http.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AlumnosIT extends AlumnosITSupport {

    @Test
    void testCreateAlumnoWithAlumnoDtoProfesorId() {
        createAlumno(createProfesor());
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoProfesorIdNull() {
        createAlumno(null);
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
    void testDeleteAlumno() {
        String alumnoId = createAlumnoWithPracticas();
        deleteAlumno(alumnoId);
        HttpResponse response = readAllPracticasAlumno(alumnoId);
        assertTrue(((Set<PracticaDto>) response.getBody()).size() == 0);
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

    @Test
    void testSearchAverage() {
        String alumnoId = createAlumno("Alumno1", "Apellidos Alumno 1", null);
        updateNotaPractica(alumnoId, createPractica(alumnoId, "APAW. Practica1", Asignatura.APAW), 3);
        updateNotaPractica(alumnoId, createPractica(alumnoId, "APAW. Practica2", Asignatura.APAW), 3);
        updateNotaPractica(alumnoId, createPractica(alumnoId, "FEM. Practica1", Asignatura.FEM), 3);

        alumnoId = createAlumno("Alumno2", "Apellidos Alumno 2", null);
        updateNotaPractica(alumnoId, createPractica(alumnoId, "APAW. Practica1", Asignatura.APAW), 7);
        updateNotaPractica(alumnoId, createPractica(alumnoId, "APAW. Practica2", Asignatura.APAW), 7);
        updateNotaPractica(alumnoId, createPractica(alumnoId, "FEM. Practica1", Asignatura.FEM), 7);

        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.SEARCH)
                .param("q", "average:>=5").get();
        List<AlumnoWithMediaDto> alumnos = (List<AlumnoWithMediaDto>) new Client().submit(request).getBody();
        assertEquals(1, alumnos.size());
        AlumnoWithMediaDto alumnoWithMediaDto = alumnos.get(0);
        assertEquals(Double.valueOf(7), alumnoWithMediaDto.getMedia());
    }

    @Test
    void testSearchAverageWithoutParamQ() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.SEARCH)
                .param("error", "average:>=7").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testSearchAverageParamError() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.SEARCH)
                .param("q", "error:>=7").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
