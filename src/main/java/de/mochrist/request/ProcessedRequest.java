package de.mochrist.request;

import java.util.Map;

public class ProcessedRequest {
    private final Request request;
    final Map<String, String> pathParams;

    public ProcessedRequest(Request request, Map<String, String> pathParams) {
        this.request = request;
        this.pathParams = pathParams;
    }

    public Request getRequest() {
        return request;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public String getPathParam(String name) {
        return pathParams.get(name);
    }
}
