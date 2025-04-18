package de.mochrist;

import de.mochrist.config.RoutingConfig;
import de.mochrist.request.ProcessedRequest;
import de.mochrist.request.Request;
import de.mochrist.servlet.RoutedServlet;
import de.mochrist.servlet.ServletRouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {

    private final ExecutorService executor = Executors.newFixedThreadPool(10); // Max. 10 gleichzeitige Clients

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setReuseAddress(true);
        System.out.println("Server läuft auf Port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            executor.submit(() -> {
                try {
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.err.println("Fehler beim Verarbeiten des Clients: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        System.err.println("Fehler beim Schließen des Sockets: " + e.getMessage());
                    }
                }
            });
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ServletRouter servletRouter = new ServletRouter();
        RoutingConfig.configureRoutes(servletRouter);

        // 1. Request lesen
        StringBuilder requestBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            requestBuilder.append(line).append("\r\n");
        }

        // 2. Request parsen
        RequestParser parser = new RequestParser();
        Request request = parser.parse(requestBuilder.toString());

        // 3. Pfad analysieren und passendes Servlet zurückgeben
//        HttpServlet servlet = servletRouter.route(request);
//        servlet.handle(request, outputStream);
        RoutedServlet routed = servletRouter.route(request);
        ProcessedRequest processedRequest = new ProcessedRequest(request, routed.getPathParams());
        routed.getServlet().handle(processedRequest, outputStream);
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}