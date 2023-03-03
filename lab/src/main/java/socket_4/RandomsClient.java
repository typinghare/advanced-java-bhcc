package socket_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RandomsClient {
    public static void main(String[] args) throws IOException {
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 8080;

        try (final Socket clientSocket = new Socket(SERVER_IP, SERVER_PORT)) {
            final DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            final DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            final int upperBound = 100;
            dataOutputStream.writeInt(upperBound);
            dataOutputStream.flush();

            final int randomNumber = dataInputStream.readInt();

            System.out.println("Upper bound sent to the server: " + upperBound);
            System.out.println("Random number got from the server: " + randomNumber);

            dataInputStream.close();
            dataOutputStream.close();
        }
    }
}
