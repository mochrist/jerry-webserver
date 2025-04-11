package de.mochrist.config;

import de.mochrist.request.parts.HttpMethod;
import de.mochrist.servlet.*;

public class RoutingConfig {

    public static void configureRoutes(ServletRouter router) {
        router.register("/", HttpMethod.GET, new RootServlet());
        router.register("/user-agent", HttpMethod.GET, new UserAgentServlet());
        router.register("notfound", HttpMethod.GET, new NotFoundServlet());
        router.register("/echo/:text", HttpMethod.GET, new EchoServlet());
    }
}
