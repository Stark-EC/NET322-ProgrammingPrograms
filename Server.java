// Server
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Choose a port
            System.out.println("Server listening...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle client communication (e.g., read/write data)
                // You can create a separate thread for each client here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
