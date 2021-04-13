package org.emnmem.oblig2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Oblig2Application {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();

        SpringApplication.run(Oblig2Application.class, args);
    }

}

class Server extends Thread{

    @Override
    public void run() {
        Map<Socket, Integer> socketIntegerMap = new HashMap<>();

        try (ServerSocket server = new ServerSocket(8081)) {
            System.out.println("Server running on port 8081");
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            var lines = bufferedReader.lines();
            lines.forEach(System.out::println);

        } catch (Exception e) {

        }
    }
}
