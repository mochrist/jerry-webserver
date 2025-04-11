package de.mochrist.servlet;

import de.mochrist.request.ProcessedRequest;
import de.mochrist.response.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;

public class RootServlet implements HttpServlet {

    @Override
    public void handle(ProcessedRequest request, OutputStream outputStream) throws IOException {
        String response = HttpStatus.OK + "\r\n";
        outputStream.write(response.getBytes());
    }
}
