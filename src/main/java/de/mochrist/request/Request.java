package de.mochrist.request;



import de.mochrist.request.parts.Header;
import de.mochrist.request.parts.RequestBody;
import de.mochrist.request.parts.RequestLine;

import java.util.List;

public class Request {
    private final RequestLine requestLine;
    private final List<Header> headers;
    private RequestBody requestBody;

    public Request(RequestLine requestLine, List<Header> headers) {
        this.requestLine = requestLine;
        this.headers = headers;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

}
