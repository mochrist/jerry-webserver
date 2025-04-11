package de.mochrist.servlet;

import de.mochrist.request.ProcessedRequest;
import de.mochrist.request.Request;
import de.mochrist.request.parts.Header;
import de.mochrist.response.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;

public class UserAgentServlet implements HttpServlet {

    @Override
    public void handle(ProcessedRequest request, OutputStream outputStream) throws IOException {
        String userAgent = getUserAgentHeaderValue(request.getRequest());

        String response = HttpStatus.OK +
                "Content-Type: text/plain\r\n" +
                "Content-Length: " + userAgent.length() + "\r\n\r\n" +
                userAgent;

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
