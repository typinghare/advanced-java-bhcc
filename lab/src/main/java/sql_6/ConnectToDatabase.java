package sql_6;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ConnectToDatabase {
    public static void main(String[] args) throws Exception {
        try (final Socket socket = new Socket("72.14.184.50", 8000)) {
            try (
                final var outputStream = new OutputStreamWriter(socket.getOutputStream());
                final var writer = new BufferedWriter(outputStream)
            ) {
                writer.write("Zhuojian Chen");
                writer.newLine();
                writer.flush();

                outputStream.flush();
            }
        }
    }
}
