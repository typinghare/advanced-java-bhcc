package edu.bhcc;

import edu.bhcc.networking.model.Weather;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

/**
 * Application tests. But I don't know how to write unit tests for my socket application!!!
 * @author James (Zhuojian Chen)
 */
public class WeatherApplicationTest {
    @Test
    public void requestTest() {
        final Random random = new Random(System.currentTimeMillis());

        final BiFunction<Integer, Integer, Integer> randBetween = (Integer lower, Integer upper) ->
            (random.nextInt(Integer.MAX_VALUE) % (upper - lower + 1)) + lower;

        final Runnable runnable = () -> {
            try {
                final Socket socket = new Socket("localhost", 8080);
                final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                final Integer year = randBetween.apply(2012, 2015);
                final Integer month = randBetween.apply(1, 12);
                final Integer day = randBetween.apply(1, 28);

                // padding and build date string
                final String monthString = month < 10 ? "0" + month : month.toString();
                final String dayString = day < 10 ? "0" + day : day.toString();
                final String dateString = year + "-" + monthString + "-" + dayString;

                // send request
                outputStream.writeObject(dateString);
                outputStream.flush();

                // get response
                final Weather weather = (Weather) inputStream.readObject();
                final String str = "From thread: " + Thread.currentThread().getName()
                    + "\n[date] " + dateString
                    + "\nprecipitation: " + weather.getPrecipitation()
                    + "\ntempMin: " + weather.getTempMin()
                    + "\ntempMax: " + weather.getTempMax()
                    + "\nwind: " + weather.getWind()
                    + "\nweather: " + weather.getWeather() + '\n';
                System.out.println(str);

                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        final int numSocketThread = 5;
        try (final ExecutorService executorService = Executors.newFixedThreadPool(numSocketThread)) {
            for (int i = 0; i < numSocketThread; i++) {
                executorService.execute(runnable);
            }
        }
    }
}

///**
// * Application test.
// * @author James (Zhuojian Chen)
// */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Timeout(value = 5)
//public class WeatherApplicationTest {
//    private static Server server;
//
//    @BeforeAll
//    public static void runServer() throws Exception {
//        final int LISTENING_PORT = 8080;
//        final int MAX_THREAD = 5;
//
//        server = new WeatherServer(LISTENING_PORT, MAX_THREAD);
//        try {
//            server.start();
//            // Wait for the server to start listening
//            while (!server.isListening()) {
//                Thread.yield();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @AfterAll
//    public static void closeAll() throws Exception {
//        server.close();
//    }
//
//    @Test
//    public void requestTest() throws Exception {
//        final Random random = new Random(System.currentTimeMillis());
//
//        final BiFunction<Integer, Integer, Integer> randBetween = (Integer lower, Integer upper) ->
//            (random.nextInt(Integer.MAX_VALUE) % (upper - lower + 1)) + lower;
//
//        final Socket socket = new Socket("localhost", 8080);
//        final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//        final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
//
//        final Integer year = randBetween.apply(2012, 2015);
//        final Integer month = randBetween.apply(1, 12);
//        final Integer day = randBetween.apply(1, 28);
//
//        // padding and build date string
//        final String monthString = month < 10 ? "0" + month : month.toString();
//        final String dayString = day < 10 ? "0" + day : day.toString();
//        final String dateString = year + "-" + monthString + "-" + dayString;
//
//        // send request
//        outputStream.writeObject(dateString);
//        outputStream.flush();
//
//        // get response
//        System.out.println("before read");
//        final Weather weather = (Weather) inputStream.readObject();
//
//        outputStream.close();
//        inputStream.close();
//        socket.close();
//    }
//}
