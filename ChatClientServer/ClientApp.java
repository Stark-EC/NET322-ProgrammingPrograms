import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        int PORT = 12345;

        try {
            Socket clientSocket = new Socket("localhost", PORT); // Connect to server
            System.out.println("Connected to chat server.");

            // Read input from the user
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read messages from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread serverThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Server says: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();

            // Send messages to the server
            while (true) {
                String userMessage = scanner.nextLine();
                out.println(userMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
