package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.AlumnoApiController;
import es.upm.miw.apaw.api.apiControllers.ProfesorApiController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.http.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlumnosIT {

    @Test
    void testCreateAlumnoWithAlumnoDtoIdProfesor() {
        createAlumno(createProfesor());
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoIdProfesorNull() {
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
    void testCreateAlumnoWithAlumnoDtoIdProfesorEmpty() {
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
    void testUpdateAlumnoWithAlumnoDtoIdProfesor() {
        String profesorId = createProfesor();
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("AlumnoUpdated", "Apellido1 Apellido2 Updated", profesorId)).put();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoIdProfesorNull() {
        String profesorId = createProfesor();
        String alumnoId = createAlumno(null);
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath(alumnoId).body(new AlumnoDto("AlumnoUpdated", "Apellido1 Apellido2 Updated", profesorId)).put();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateAlumnoWithAlumnoDtoIdProfesorEmpty() {
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
    void testUpdateUserNotFoundException() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID)
                .expandPath("s5FdeGf54D").body(new AlumnoDto("Nombre", "Apellidos")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

}
