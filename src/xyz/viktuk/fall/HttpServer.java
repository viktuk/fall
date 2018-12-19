package xyz.viktuk.fall;

import xyz.viktuk.fall.http.Request;
import xyz.viktuk.fall.http.RequestBuilder;
import xyz.viktuk.fall.http.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HttpServer {
    private Controller controller;

    public HttpServer(int port) throws IOException {
        start(port);
    }


    public HttpServer(int port, Controller controller) throws IOException {
        this.controller = controller;
        start(port);
    }

    private void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started successfully");
        new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    Socket client = serverSocket.accept();
                    InputStream inputStream = client.getInputStream();
                    OutputStream outputStream = client.getOutputStream();
                    try {
                        handleRequest(inputStream, outputStream);
                    }catch (Exception e){
                        System.out.println("Error handling message: " + e.getMessage());
                        Response.BAD_REQUEST.send(outputStream);
                    }
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error client connecting: " + e.getMessage());
                }
            }
        }).start();
    }

    private void handleRequest(InputStream inputStream, OutputStream outputStream) {
        RequestBuilder requestBuilder = new RequestBuilder();
        Scanner scanner = new Scanner(inputStream);
        String line;
        while (scanner.hasNextLine() && !(line = scanner.nextLine()).isEmpty()) {
            requestBuilder.append(line);
        }
        Request request = requestBuilder.build();
        if (controller != null) {
            controller.handleRequest(request).send(outputStream);
        } else {
            Response.METHOD_NOT_ALLOWED.send(outputStream);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}