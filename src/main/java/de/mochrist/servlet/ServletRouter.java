package de.mochrist.servlet;

import de.mochrist.RouteDefinition;
import de.mochrist.request.Request;
import de.mochrist.request.parts.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServletRouter {
    private final List<RouteDefinition> routes = new ArrayList<>();

    public void register(String path, HttpMethod method, HttpServlet servlet) {
        routes.add(new RouteDefinition(path, method, servlet));
    }

    public RoutedServlet route(Request request) {
        String path = request.getRequestLine().getPath();
        HttpMethod method = HttpMethod.valueOf(request.getRequestLine().getMethod());

        for (RouteDefinition route : routes) {
            if (route.matches(path, method)) {
                Map<String, String> params = route.extractParams(path);
                return new RoutedServlet(route.getServlet(), params);
            }
        }

        return new RoutedServlet(new NotFoundServlet(), Map.of());
    }
}