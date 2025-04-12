package de.mochrist;

import de.mochrist.request.parts.HttpMethod;
import de.mochrist.servlet.HttpServlet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Route {
    String path;
    HttpMethod method;
    HttpServlet servlet;

    public Route() {
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = resolveMethod(method);
    }

    public void setServlet(String servlet) {
        this.servlet = resolveServlet(servlet);
    }

    private HttpMethod resolveMethod(String method) {
        return switch (method.toLowerCase()) {
            case "get" -> HttpMethod.GET;
            case "post" -> HttpMethod.POST;
            case "put" -> HttpMethod.PUT;
            case "delete" -> HttpMethod.DELETE;
            case "patch" -> HttpMethod.PATCH;
            default -> null; //TODO
        };
    }

    private HttpServlet resolveServlet(String servlet) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> httpServlet = Class.forName(servlet);
        Constructor<?> servletConstructor = httpServlet.getDeclaredConstructor([]);
        servletConstructor.setAccessible(true);
        Object newHttpServlet = servletConstructor.newInstance();
    }

    @Override
    public String toString() {
        return "Route{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", servlet='" + servlet + '\'' +
                '}';
    }
}
