package de.mochrist.servlet;

import de.mochrist.request.Request;

import java.io.IOException;
import java.io.OutputStream;

public interface HttpServlet {
    void handle(Request request, OutputStream outputStream) throws IOException;
}
