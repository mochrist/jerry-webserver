package de.mochrist.servlet;

import de.mochrist.request.Request;
import de.mochrist.request.parts.Header;

import java.io.IOException;
import java.io.OutputStream;

public class UserAgentServlet implements HttpServlet {

    @Override
    public void handle(Request request, OutputStream outputStream) throws IOException {
        String userAgent = getUserAgentHeaderValue(request);
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: " +
                userAgent.length() + "\r\n\r\n" + userAgent;
        outputStream.write(response.getBytes());
    }

    private String getUserAgentHeaderValue(Request request) {
        for (Header header : request.getHeaders()) {
            if (header.getName().equalsIgnoreCase("User-Agent")) {
                return header.getValue();
            }
        }
        return "";
    }
}
