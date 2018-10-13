package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.businessControllers.AlumnoBusinessController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public class AlumnoApiController {

    public static final String ALUMNOS = "/alumnoes";

    private final AlumnoBusinessController alumnoBusinessController = new AlumnoBusinessController();

    public String create(AlumnoDto alumnoDto) {
        validateNotNull(alumnoDto, "alumnoDto");
        validateNotNull(alumnoDto.getNombre(), "alumnoDto.nombre");
        validateNotEmpty(alumnoDto.getNombre(), "alumnoDto.nombre");
        validateNotNull(alumnoDto.getApellidos(), "alumnoDto.apellidos");
        validateNotEmpty(alumnoDto.getApellidos(), "alumnoDto.apellidos");
        validateNotEmpty(alumnoDto.getProfesorId(), "alumnoDto.profesorId");

        return this.alumnoBusinessController.create(alumnoDto);
    }

    private void validateNotNull(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

    private void validateNotEmpty(String property, String message) {
        if (property != null && property.trim().isEmpty()) {
            throw new ArgumentNotValidException(message + " is empty");
        }
    }

}
