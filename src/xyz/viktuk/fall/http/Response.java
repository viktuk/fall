package xyz.viktuk.fall.http;

import xyz.viktuk.fall.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;

public class Response {
    public static final Response BAD_REQUEST = new Response(400 , "Bad Request");
    public static final Response NOT_FOUND = new Response(404, "Not Found");
    public static final Response METHOD_NOT_ALLOWED = new Response(405, "Method Not Allowed");
    public static final Response INTERNAL_SERVER_ERROR = new Response(500, "Internal Server Error");

    private int status = 200;
    private String phrase = "OK";
    private Map<String, String> headers;
    private String mime;
    private byte[] data;

    public Response() {
    }

    public Response(int status, String phrase) {
        this.status = status;
        this.phrase = phrase;
    }

    public int getStatus() {
        return status;
    }
    public String getPhrase() {
        return phrase;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Config.getHttpVersion()).append(' ').append(status).append(' ').append(phrase).append('\n');
        stringBuilder.append("Server: ").append(Config.getServerName()).append('\n');
        if(headers!=null){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
            }
        }
        if(mime!=null){
            stringBuilder.append("Content-Type: ").append(mime).append('\n');
        }
        if(data!=null) {
            stringBuilder.append("Content-Length: ").append(data.length).append('\n');
        }
        stringBuilder.append("Connection: close\n\n");
        return stringBuilder.toString();
    }

    public void send(OutputStream outputStream){
        try {
            outputStream.write(toString().getBytes());
            if(data!=null) {
                outputStream.write(data);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}