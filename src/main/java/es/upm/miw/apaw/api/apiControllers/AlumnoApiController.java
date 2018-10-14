package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.businessControllers.AlumnoBusinessController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;

public class AlumnoApiController extends ApiControllerSupport {

    public static final String ALUMNOS = "/alumnos";
    public static final String ID_ID = "/{id}";
    public static final String PRACTICAS = "/practicas";

    private final AlumnoBusinessController alumnoBusinessController = new AlumnoBusinessController();

    public String create(AlumnoDto alumnoDto) {
        validateAlumnoDto(alumnoDto);

        return alumnoBusinessController.create(alumnoDto);
    }

    public void update(String id, AlumnoDto alumnoDto) {
        validateAlumnoDto(alumnoDto);

        alumnoBusinessController.update(id, alumnoDto);
    }

    public String createPractica(String alumnoId, PracticaDto practicaDto) {
        validatePracticaDto(practicaDto);

        return alumnoBusinessController.createPractica(alumnoId, practicaDto);
    }

    private void validateAlumnoDto(AlumnoDto alumnoDto) {
        validateNotNull(alumnoDto, "alumnoDto");
        validateNotNull(alumnoDto.getNombre(), "alumnoDto.nombre");
        validateNotEmpty(alumnoDto.getNombre(), "alumnoDto.nombre");
        validateNotNull(alumnoDto.getApellidos(), "alumnoDto.apellidos");
        validateNotEmpty(alumnoDto.getApellidos(), "alumnoDto.apellidos");
        validateNotEmpty(alumnoDto.getProfesorId(), "alumnoDto.profesorId");
    }

    private void validatePracticaDto(PracticaDto practicaDto) {
        validateNotNull(practicaDto, "practicaDto");
        validateNotNull(practicaDto.getNombre(), "practicaDto.nombre");
        validateNotEmpty(practicaDto.getNombre(), "practicaDto.nombre");
        validateNotNull(practicaDto.getAsignatura(), "pracitaDto.asignatura");
    }


}
