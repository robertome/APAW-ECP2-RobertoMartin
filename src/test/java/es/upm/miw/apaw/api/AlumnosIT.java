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
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Alumno", "Apellido1 Apellido2", createProfesor())).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
    }

    String createProfesor() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto("Profesor", "Apellido1 Apellido2")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateAlumnoWithAlumnoDtoIdProfesorNull() {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Alumno", "Apellido1 Apellido2")).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
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


}
