package org.krystilize.resourceful;

import com.sun.net.httpserver.*;
import org.jetbrains.annotations.NotNull;
import org.krystilize.resourceful.resourcepack.ResourcePack;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourcePackServer {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newWorkStealingPool();

    private final HttpServer server;

    public ResourcePackServer(@NotNull ResourcePack resourcePack) {
        this(resourcePack.compile());
    }

    public ResourcePackServer(byte @NotNull [] resourcePack) {
        this.server = createServer();
        server.setExecutor(EXECUTOR_SERVICE);
        server.createContext("/", new RequestHandler(resourcePack));
    }

    public void start() {
        server.start();
    }

    private HttpServer createServer() {
        try {
            return HttpServer.create(new InetSocketAddress("0.0.0.0", 8001), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Server was not able to open");
    }

    private static class RequestHandler implements HttpHandler {

        private final byte[] response;
        private final int responseLength;

        private RequestHandler(byte[] response) {
            this.response = response;
            this.responseLength = response.length;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(200, responseLength);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }
}
