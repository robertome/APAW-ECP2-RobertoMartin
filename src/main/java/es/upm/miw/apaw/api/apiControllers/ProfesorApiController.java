package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.businessControllers.ProfesorBusinessController;
import es.upm.miw.apaw.api.dtos.ProfesorDto;

public class ProfesorApiController extends ApiControllerSupport {

    public static final String PROFESORES = "/profesores";

    private final ProfesorBusinessController profesorBusinessController = new ProfesorBusinessController();

    public String create(ProfesorDto profesorDto) {
        validateNotNull(profesorDto, "profesorDto");
        validateNotNull(profesorDto.getNombre(), "profesorDto.nombre");
        validateNotEmpty(profesorDto.getNombre(), "profesorDto.nombre");
        validateNotNull(profesorDto.getApellidos(), "profesorDto.apellidos");
        validateNotEmpty(profesorDto.getApellidos(), "profesorDto.apellidos");

        return this.profesorBusinessController.create(profesorDto);
    }

}
