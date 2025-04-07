package de.mochrist;

import de.mochrist.request.Request;
import de.mochrist.request.parts.Header;
import de.mochrist.request.parts.RequestLine;

import java.util.ArrayList;
import java.util.List;

public class RequestParser {

    public Request parse(String rawRequest) {
        // 1. In Zeilen aufsplitten
        String[] lines = splitLines(rawRequest);
        // 2. Erste Zeile: RequestLine
        RequestLine requestLine = RequestLine.parse(lines[0]);
        // 3. Weitere Zeilen: Header[]
        HeaderResult headerResult = parseHeaders(lines);
        List<Header> headerList = headerResult.headers();
        // 4. Body (nach leerer Zeile)
        int bodyStartIndex = headerResult.nextLineIndex(); //TODO: Body
        return new Request(requestLine, headerList);
    }

    private String[] splitLines(String rawRequest) {
        return rawRequest.split("\r\n");
    }

    private record HeaderResult(List<Header> headers, int nextLineIndex) {}

    private HeaderResult parseHeaders(String[] lines) {
        List<Header> headers = new ArrayList<>();
        int i = 1;
        for (; i < lines.length; i++) {
            if (lines[i].isEmpty()) break;
            headers.add(Header.parse(lines[i]));
        }
        return new HeaderResult(headers, i + 1); // Body beginnt danach
    }
}
