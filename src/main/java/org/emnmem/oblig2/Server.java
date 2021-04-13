package org.emnmem.oblig2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread{

    public static Map<Socket, Integer> socketIntegerMap = new HashMap<>();

    @Override
    public void run() {

        try (ServerSocket server = new ServerSocket(8081)) {
            System.out.println("Server running on port 8081");
            while (true) {
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                var lines = bufferedReader.lines();
                lines.forEach(System.out::println);
                /*
                String line = "";
                for (int i = 0; i < 13; i++) {
                    line = bufferedReader.readLine();
                }
                System.out.println(line);
                String roomId = line
                        .split("[:]")[1]
                        .replaceAll("[\"]", "")
                        .replaceAll("[}]", "");
                System.out.println(roomId);
                socketIntegerMap.put(socket, Integer.parseInt(roomId));
                 */
            }

        } catch (Exception e) {

        }
    }
}
