package es.upm.miw.apaw.api.apiControllers;

import es.upm.miw.apaw.api.businessControllers.AlumnoBusinessController;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.AlumnoWithMediaDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

import java.util.List;
import java.util.Set;

public class AlumnoApiController extends ApiControllerSupport {

    public static final String ALUMNOS = "/alumnos";
    public static final String ID_ID = "/{id}";
    public static final String PRACTICAS = "/practicas";
    public static final String NOTA = "/nota";
    public static final String SEARCH = "/search";

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

    public Set<PracticaDto> readAllPracticas(String alumnoId) {
        return alumnoBusinessController.readAllPracticas(alumnoId);
    }

    public void delete(String alumnoId) {
        alumnoBusinessController.delete(alumnoId);
    }

    public void updateNotaPractica(String alumnoId, String practicaId, Integer nota) {
        validateNotNull(nota, "nota");
        validateRange(nota, 0, 10, "nota");
        alumnoBusinessController.updateNotaPractica(alumnoId, practicaId, nota);
    }

    private void validateRange(Integer nota, int i, int i1, String message) {
        if (nota < 0 || nota > 10) {
            throw new ArgumentNotValidException(message + " MUST be between 0 and 10");
        }
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

    public List<AlumnoWithMediaDto> find(String query) {
        validateNotNull(query, "query param q");
        validateNotEmpty(query, "query param q");
        String[] queryArray = query.split(":>=");
        if (!"average".equals(queryArray[0])) {
            throw new ArgumentNotValidException("query param q is incorrect, missing 'average:>='");
        }

        return alumnoBusinessController.findByAverageGreaterThanEqual(Double.valueOf(queryArray[1]));
    }
}
