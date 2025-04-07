package de.mochrist.servlet;

import de.mochrist.request.Request;

public class ServletRouter {
    public HttpServlet route(Request request) {
        String path = request.getRequestLine().getPath();

        if (path.equals("/")) {
            return new RootServlet();
        } else if (path.startsWith("/echo/")) {
            return new EchoServlet();
        } else if (path.equals("/user-agent")) {
            return new UserAgentServlet();
        } else {
            return new NotFoundServlet();
        }
    }
}
