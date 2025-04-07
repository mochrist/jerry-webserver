package de.mochrist.request;



import de.mochrist.request.parts.Header;
import de.mochrist.request.parts.RequestBody;
import de.mochrist.request.parts.RequestLine;

import java.util.List;

public class Request {
    private RequestLine requestLine;
    private List<Header> headers;
    private RequestBody requestBody;

    public Request(RequestLine requestLine, List<Header> headers) {
        this.requestLine = requestLine;
        this.headers = headers;
    }

    public Request(RequestLine requestLine, List<Header> headers, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.headers = headers;
        this.requestBody = requestBody;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }
}
