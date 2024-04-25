import java.io.*;
import java.net.*;

public class ClientTime {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345); // Connect to server
            System.out.println("Connected to time server.");

            // Read the time from the server
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String serverTime = in.readLine();
                System.out.println("Server time: " + serverTime);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Close the client socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
