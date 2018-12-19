package xyz.viktuk.fall;

import xyz.viktuk.fall.http.Request;
import xyz.viktuk.fall.http.Response;
import xyz.viktuk.fall.http.ResponseBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticController implements Controller {
    @Override
    public Response handleRequest(Request request) {
        if(request.getMethod()!= Request.Method.GET) return Response.METHOD_NOT_ALLOWED;
        String uri = request.getURI().split("\\?")[0];
        if(uri.equals("/")){
            uri = "/index.html";
        }
        Path path = Paths.get("static"+uri);
        try {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            responseBuilder
                    .setMime(Files.probeContentType(path))
                    .setData(Files.readAllBytes(path));
            return responseBuilder.build();
        } catch (IOException e) {
            return Response.NOT_FOUND;
        }
    }
}
