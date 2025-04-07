package de.mochrist.request.parts;

public class RequestLine {
    private String method;
    private String path;
    private String httpVersion;

    private RequestLine() {}

    public static RequestLine parse(String rawRequestLine) {
        RequestLine requestLine = new RequestLine();
        String[] requestLineParts = splitLines(rawRequestLine);
        requestLine.setMethod(requestLineParts[0]);
        requestLine.setPath(requestLineParts[1]);
        requestLine.setHttpVersion(requestLineParts[2]);
        return requestLine;
    }

    private static String[] splitLines(String rawRequestLine) {
        String[] lines = rawRequestLine.split(" ");
        if(lines.length != 3) {
            throw new IllegalArgumentException("Ungültige Request-Line: " + rawRequestLine);
        }
        return lines;
    }

    public String getMethod() {
        return method;
    }

    private void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    private void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    //    public boolean isGetRequest {...}

//    public boolean isValid {...}

}
