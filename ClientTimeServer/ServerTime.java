import java.io.*;
import java.net.*;

public class ServerTime {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Choose a port
            System.out.println("Time server listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Get the current time
                long currentTimeMillis = System.currentTimeMillis();
                String currentTime = new java.util.Date(currentTimeMillis).toString();

                // Send the time to the client
                try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    out.println(currentTime);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
