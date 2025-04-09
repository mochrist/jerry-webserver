package de.mochrist.servlet;

import de.mochrist.PrefixRoute;
import de.mochrist.request.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServletRouter {
    private final Map<String, HttpServlet> exactRoutesMap = new HashMap<>();
    private final List<PrefixRoute> prefixRoutesList = new ArrayList<>();


    public HttpServlet route(Request request) {
        String path = request.getRequestLine().getPath();

        if (exactRoutesMap.containsKey(path)) {
            return exactRoutesMap.get(path);
        }

        for (PrefixRoute route : prefixRoutesList) {
            if (path.startsWith(route.getPrefix())) {
                return route.getServlet();
            }
        }
        return new NotFoundServlet();
    }

    public void registerExact(String path, HttpServlet servlet) {
        if(!exactRoutesMap.containsKey(path)) {
            exactRoutesMap.put(path, servlet);
        }
    }

    public void registerPrefix(String path, HttpServlet servlet) {
        prefixRoutesList.add(new PrefixRoute(servlet, path));
    }
}
