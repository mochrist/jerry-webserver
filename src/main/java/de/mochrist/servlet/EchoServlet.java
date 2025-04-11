package de.mochrist.servlet;

import de.mochrist.request.ProcessedRequest;
import de.mochrist.response.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;

public class EchoServlet implements HttpServlet {
    @Override
    public void handle(ProcessedRequest request, OutputStream out) throws IOException {
        String text = request.getPathParam("text"); // Beispiel um Content von Pfadparameter zu bekommen

        String responseBody = text;
        String response = HttpStatus.OK +
                "Content-Type: text/plain\r\n" +
                "Content-Length: " + responseBody.length() + "\r\n\r\n" +
                responseBody;

        out.write(response.getBytes());
    }
}
