package xyz.viktuk.fall;

import xyz.viktuk.fall.http.Request;
import xyz.viktuk.fall.http.Response;

public interface Controller {
    Response handleRequest(Request request);
}
