package de.mochrist;

import de.mochrist.servlet.HttpServlet;

public class PrefixRoute {
    private final String prefix;
    private final HttpServlet servlet;

    public PrefixRoute(HttpServlet servlet, String prefix) {
        this.servlet = servlet;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public HttpServlet getServlet() {
        return servlet;
    }

}
