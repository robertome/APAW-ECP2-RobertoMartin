package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.businessControllers.AlumnoBusinessController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;

public class AlumnoApiController extends ApiControllerSupport {

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

}
