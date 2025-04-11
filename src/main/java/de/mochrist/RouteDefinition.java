package de.mochrist;

import de.mochrist.request.parts.HttpMethod;
import de.mochrist.servlet.HttpServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteDefinition {
    private final String rawPath;
    private final Pattern pattern;
    private final List<String> paramNames;
    private final HttpServlet servlet;
    private final HttpMethod method;

    public RouteDefinition(String rawPath, HttpMethod method, HttpServlet servlet) {
        this.rawPath = rawPath;
        this.method = method;
        this.servlet = servlet;
        this.paramNames = new ArrayList<>();

        StringBuilder regexBuilder = new StringBuilder();
        regexBuilder.append("^");

        String[] parts = rawPath.split("/");

        for (String part : parts) {
            if (part.isEmpty()) continue; // wegen führendem Slash

            regexBuilder.append("/");

            if (part.startsWith(":")) {
                paramNames.add(part.substring(1)); // ":id" -> "id"
                regexBuilder.append("([^/]+)");
            } else {
                regexBuilder.append(Pattern.quote(part)); // normale Pfadbestandteile escapen
            }
        }

        regexBuilder.append("$");

        this.pattern = Pattern.compile(regexBuilder.toString());
    }

    public Map<String, String> extractParams(String path) {
        Matcher matcher = pattern.matcher(path);
        Map<String, String> params = new HashMap<>();

        if (!matcher.matches()) {
            return params; // leer, kein Match
        }

        for (int i = 0; i < paramNames.size(); i++) {
            String name = paramNames.get(i);
            String value = matcher.group(i + 1); // group(1), group(2), ...
            params.put(name, value);
        }

        return params;
    }

    public boolean matches(String path, HttpMethod method) {
        return this.method == method && pattern.matcher(path).matches();
    }

    public HttpServlet getServlet() {
        return servlet;
    }
}
