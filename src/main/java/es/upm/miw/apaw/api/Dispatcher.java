package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.AlumnoApiController;
import es.upm.miw.apaw.api.apiControllers.ProfesorApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.AlumnoDto;
import es.upm.miw.apaw.api.dtos.PracticaDto;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;
import es.upm.miw.apaw.api.exceptions.NotFoundException;
import es.upm.miw.apaw.api.exceptions.RequestInvalidException;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpResponse;
import es.upm.miw.apaw.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dispatcher {

    private static final String ERROR_MESSAGE = "{'error':'%S'}";
    private static Logger logger = LogManager.getLogger(Dispatcher.class);

    static {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private final ProfesorApiController profesorApiController = new ProfesorApiController();
    private final AlumnoApiController alumnoApiController = new AlumnoApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                case DELETE:
                    this.doDelete(request);
                    break;
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            logger.error("Unexpected exception", exception);
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(ProfesorApiController.PROFESORES)) {
            response.setBody(profesorApiController.create((ProfesorDto) request.getBody()));
        } else if (request.isEqualsPath(AlumnoApiController.ALUMNOS)) {
            response.setBody(alumnoApiController.create((AlumnoDto) request.getBody()));
        } else if (request.isEqualsPath(AlumnoApiController.ALUMNOS + AlumnoApiController.ID_ID + AlumnoApiController.PRACTICAS)) {
            response.setBody(alumnoApiController.createPractica(request.getPath(1), (PracticaDto) request.getBody()));
        } else {
            throw requestInvalidException(request);
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(AlumnoApiController.ALUMNOS + AlumnoApiController.ID_ID + AlumnoApiController.PRACTICAS)) {
            response.setBody(alumnoApiController.readAllPracticas(request.getPath(1)));
        } else if (request.isEqualsPath(AlumnoApiController.ALUMNOS + AlumnoApiController.SEARCH)) {
            response.setBody(alumnoApiController.find(request.getParams().get("q")));
        } else {
            throw requestInvalidException(request);
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(AlumnoApiController.ALUMNOS + AlumnoApiController.ID_ID)) {
            alumnoApiController.update(request.getPath(1), (AlumnoDto) request.getBody());
        } else {
            throw requestInvalidException(request);
        }
    }

    private void doPatch(HttpRequest request) {
        if (request.isEqualsPath(AlumnoApiController.ALUMNOS + AlumnoApiController.ID_ID + AlumnoApiController.PRACTICAS + AlumnoApiController.ID_ID + AlumnoApiController.NOTA)) {
            alumnoApiController.updateNotaPractica(request.getPath(1), request.getPath(3), (Integer) request.getBody());
        } else {
            throw requestInvalidException(request);
        }
    }

    private void doDelete(HttpRequest request) {
        if (request.isEqualsPath(AlumnoApiController.ALUMNOS + AlumnoApiController.ID_ID)) {
            alumnoApiController.delete(request.getPath(1));
        } else {
            throw requestInvalidException(request);
        }
    }

    private RequestInvalidException requestInvalidException(HttpRequest request) {
        return new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
    }

}
