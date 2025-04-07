package de.mochrist.servlet;

import de.mochrist.request.Request;
import java.io.IOException;
import java.io.OutputStream;

public class NotFoundServlet implements HttpServlet {
    @Override
    public void handle(Request request, OutputStream outputStream) throws IOException {
        outputStream.write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
    }
}
