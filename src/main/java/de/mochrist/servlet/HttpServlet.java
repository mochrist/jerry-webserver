package de.mochrist.servlet;

import de.mochrist.request.ProcessedRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface HttpServlet {
    void handle(ProcessedRequest processedRequest, OutputStream outputStream) throws IOException;
}
