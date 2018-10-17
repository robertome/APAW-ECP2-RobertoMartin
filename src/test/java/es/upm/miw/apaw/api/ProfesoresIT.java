package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.ProfesorApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesoresIT extends ITSupport{

    @Test
    void testCreateProfesor() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto("Nombre", "Apellido1 Apellido2")).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());
    }

    @Test
    void testProfesorInvalidRequest() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateProfesorWithoutProfesorDto() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateProfesorWithProfesorDtoNombreNull() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto(null, null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOMBRE IS MISSING"));
    }

    @Test
    void testCreateProfesorWithProfesorDtoNombreEmpty() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto("", "")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("NOMBRE IS EMPTY"));
    }

    @Test
    void testCreateProfesorWithProfesorDtoApellidosNull() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto("Nombre", null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("APELLIDOS IS MISSING"));
    }

    @Test
    void testCreateProfesorWithProfesorDtoApellidosEmpty() {
        HttpRequest request = HttpRequest.builder(ProfesorApiController.PROFESORES).body(new ProfesorDto("Nombre", "   ")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getMessage().contains("APELLIDOS IS EMPTY"));
    }
}
