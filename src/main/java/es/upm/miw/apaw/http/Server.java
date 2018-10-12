package es.upm.miw.apaw.http;

import es.upm.miw.apaw.api.Dispatcher;

public class Server {
    private Dispatcher dispatcher = new Dispatcher();

    public HttpResponse submit(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        dispatcher.submit(request, response);
        return response;
    }

}
