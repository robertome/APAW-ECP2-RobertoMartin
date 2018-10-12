package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiControllers.ProfesorApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.ProfesorDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;
import es.upm.miw.apaw.api.exceptions.NotFoundException;
import es.upm.miw.apaw.api.exceptions.RequestInvalidException;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpResponse;
import es.upm.miw.apaw.http.HttpStatus;

public class Dispatcher {

    static {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private final ProfesorApiController profesorApiController = new ProfesorApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
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
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(ProfesorApiController.PROFESORES)) {
            response.setBody(profesorApiController.create((ProfesorDto) request.getBody()));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
    }

    private void doPut(HttpRequest request) {
        throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
    }

    private void doPatch(HttpRequest request) {
        throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
    }

    private void doDelete(HttpRequest request) {
        throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
    }

}
