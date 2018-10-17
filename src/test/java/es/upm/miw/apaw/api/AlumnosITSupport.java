package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.AlumnoApiController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.entities.Asignatura;
import es.upm.miw.apaw.http.Client;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpResponse;
import es.upm.miw.apaw.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AlumnosITSupport {

    protected String createAlumno(String profesorId) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).body(new AlumnoDto("Alumno", "Apellido1 Apellido2", profesorId)).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());

        return (String) response.getBody();
    }

    protected String createPractica(String alumnoId, String nombrePractica, Asignatura asignatura) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS)
                .expandPath(alumnoId).body(PracticaDto.builder(nombrePractica, asignatura).build()).post();
        HttpResponse response = new Client().submit(request);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getBody());

        return (String) response.getBody();
    }

    protected String createAlumnoWithPracticas() {
        String alumnoId = createAlumno(null);
        createPractica(alumnoId, "APAW. ECP1. Patrones de Diseño", Asignatura.APAW);
        createPractica(alumnoId, "APAW. ECP2. Arquitecturas y Patrones Web", Asignatura.APAW);
        createPractica(alumnoId, "IWVG. Trabajo práctico: Web 2.0", Asignatura.IWVG);
        createPractica(alumnoId, "IWVG. Software Colaborativo", Asignatura.IWVG);

        return alumnoId;
    }

    protected HttpResponse readAllPracticasAlumno(String alumnoId) {
        HttpRequest request = HttpRequest.builder(AlumnoApiController.ALUMNOS).path(AlumnoApiController.ID_ID).path(AlumnoApiController.PRACTICAS).expandPath(alumnoId).get();
        return new Client().submit(request);
    }
}
