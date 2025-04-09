package de.mochrist.config;

import de.mochrist.servlet.*;

public class RoutingConfig {

    public static void configureRoutes(ServletRouter router) {
        router.registerExact("/", new RootServlet());
        router.registerExact("/user-agent", new UserAgentServlet());
        router.registerExact("notfound", new NotFoundServlet());
        router.registerPrefix("/echo/", new EchoServlet());
    }
}
