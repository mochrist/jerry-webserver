package de.mochrist.servlet;

import de.mochrist.request.ProcessedRequest;
import de.mochrist.response.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;

public class NotFoundServlet implements HttpServlet {
    @Override
    public void handle(ProcessedRequest request, OutputStream outputStream) throws IOException {
        String response = HttpStatus.NOT_FOUND + "\r\n";
        outputStream.write(response.getBytes());
    }
}
