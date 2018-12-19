package xyz.viktuk.fall.http;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {
    private final StringBuilder stringBuilder = new StringBuilder();

    public RequestBuilder() {
    }

    public RequestBuilder append(String line){
        stringBuilder.append(line).append('\n');
        return this;
    }

    public Request build(){
        String lines[] = stringBuilder.toString().split("\n");
        String generals[] = lines[0].split(" ");
        Request request = new Request(Request.Method.valueOf(generals[0]), generals[1], generals[2]);

        Map<String, String> headers = new HashMap<>();
        for(int i = 1; i< lines.length; i++){
            String header[] = lines[i].split(": ");
            headers.put(header[0], header[1]);
        }
        request.setHeaders(headers);
        return request;
    }
}
