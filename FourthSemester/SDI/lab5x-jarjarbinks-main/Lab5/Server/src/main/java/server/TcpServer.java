package server;

import rpc.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {
    private final ExecutorService execSrv;
    private final int port;

    private final Map<String, UnaryOperator<Message>> handlers;

    public TcpServer(ExecutorService execSrv, int port) {
        this.execSrv = execSrv;
        this.port = port;
        this.handlers = new HashMap<>();
    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        handlers.put(methodName, handler);
    }

    public void start() {
        System.out.println("Server starting...");
        try (var ss = new ServerSocket(this.port)) {
            while (true) {
                var clientSocket = ss.accept();
                this.execSrv.submit(new Handler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Handler implements Runnable {
        private final Socket socket;

        public Handler(Socket clientSocket) {
            this.socket = clientSocket;
        }

        @Override
        public void run() {
            try {
                var req = new Message();
                req.readFrom(socket.getInputStream());
                var res = handlers.get(req.getHeader()).apply(req);
                res.writeTo(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
