package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.businessControllers.ProfesorBusinessController;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public class ProfesorApiController {

    public static final String PROFESORES = "/profesores";

    private ProfesorBusinessController profesorBusinessController = new ProfesorBusinessController();

    public String create(ProfesorDto profesorDto) {
        validateNotNull(profesorDto, "profesorDto");
        validateNotNull(profesorDto.getNombre(), "profesorDto.nombre");
        validateNotEmpty(profesorDto.getNombre(), "profesorDto.nombre");
        validateNotNull(profesorDto.getApellidos(), "profesorDto.apellidos");
        validateNotEmpty(profesorDto.getApellidos(), "profesorDto.apellidos");

        return this.profesorBusinessController.create(profesorDto);
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
