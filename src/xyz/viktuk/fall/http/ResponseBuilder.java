package xyz.viktuk.fall.http;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private int status = 200;
    private String phrase;
    private Map<String, String> headers;
    private String mime;
    private byte[] data;

    public Response build(){
        Response response;
        if(phrase==null){
            response= new Response();
        }else {
            response = new Response(status, phrase);
        }
        if(headers!=null){
            response.setHeaders(headers);
        }
        if(mime!=null){
            response.setMime(mime);
        }
        if(data!=null){
            response.setData(data);
        }
        return response;
    }

    public ResponseBuilder setData(byte[] data) {
        this.data = data;
        return this;
    }

    public ResponseBuilder setStatus(int status, String phrase) {
        this.status = status;
        this.phrase = phrase;
        return this;
    }

    public ResponseBuilder addHeader(String header, String value){
        if(headers==null){
            headers = new HashMap<>();
        }
        headers.put(header, value);
        return this;
    }

    public ResponseBuilder setMime(String mime) {
        this.mime = mime;
        return this;
    }
}
