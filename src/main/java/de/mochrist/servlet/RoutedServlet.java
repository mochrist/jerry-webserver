package de.mochrist.servlet;

import java.util.Map;

public class RoutedServlet {
    private final HttpServlet servlet;
    private final Map<String, String> pathParams;

    public RoutedServlet(HttpServlet servlet, Map<String, String> pathParams) {
        this.servlet = servlet;
        this.pathParams = pathParams;
    }

    public HttpServlet getServlet() {
        return servlet;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }
}