package de.mochrist;

public class Route {
    String path;
    String method;
    String servlet;

    public Route() {
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setServlet(String servlet) {
        this.servlet = servlet;
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
