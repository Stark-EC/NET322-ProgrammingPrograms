import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new thread to handle client communication
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Add the client's writer to the set
            synchronized (clientWriters) {
                clientWriters.add(out);
            }

            // Read messages from the client and broadcast them to other clients
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);
                broadcastMessage(clientMessage);
            }

            // Remove the client's writer when they disconnect
            synchronized (clientWriters) {
                clientWriters.remove(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcastMessage(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}
