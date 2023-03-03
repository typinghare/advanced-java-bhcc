package socket_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

public class RandomsServer {
    public static void main(String[] args) throws IOException {
        final int LISTENING_PORT = 8080;

        System.out.println("Starting server at: " + new Date());

        // start a server socket and wait for client
        try (final ServerSocket serverSocket = new ServerSocket(LISTENING_PORT)) {
            System.out.println("The sever is listening on port: " + LISTENING_PORT);

            final Random random = new Random();

            while (true) {
                final Socket socket = serverSocket.accept();
                final InetAddress inetAddress = socket.getInetAddress();
                System.out.println("Server gets request from: " + inetAddress.getHostAddress());

                final DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                final int upperBound = inputStream.readInt();
                final int randomNumber = random.nextInt(upperBound);

                outputStream.writeInt(randomNumber);
                outputStream.flush();

                System.out.println("Upper bound received: " + upperBound);
                System.out.println("Generated random number: " + randomNumber);

                inputStream.close();
                outputStream.close();
            }
        }
    }
}
