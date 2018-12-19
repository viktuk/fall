package xyz.viktuk.fall.http;
import java.util.Map;

public class Request {
    public enum Method {
        GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
    }

    private final Method method;
    private final String URI;
    private final String version;
    private Map<String, String> headers;

    public Request(Method method, String URI, String version) {
        this.method = method;
        this.URI = URI;
        this.version = version;
    }

    public Method getMethod() {
        return method;
    }

    public String getURI() {
        return URI;
    }

    public String getVersion() {
        return version;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Method: ").append(method).append("\nURI: ").append(URI).append("\nHttp version: ").append(version).append('\n');
        if(headers!=null) {
            stringBuilder.append("Headers:\n");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
            }
        }
        return stringBuilder.toString();
    }
}