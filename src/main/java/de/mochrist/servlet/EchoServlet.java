package de.mochrist.servlet;



import de.mochrist.request.Request;

import java.io.IOException;
import java.io.OutputStream;

public class EchoServlet implements HttpServlet {

    @Override
    public void handle(Request request, OutputStream outputStream) throws IOException {
        String path = request.getRequestLine().getPath();
        String message = path.substring("/echo/".length());
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: " +
                message.length() + "\r\n\r\n" + message;
        outputStream.write(response.getBytes());
    }
}
