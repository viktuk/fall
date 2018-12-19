package xyz.viktuk.fall;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer(8080, new StaticController());
    }
}
