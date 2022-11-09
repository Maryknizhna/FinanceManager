import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(){

    }

    public void run() throws IOException {
        Information information = new Information();
        Gson gson = new Gson();
        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);) {
                    String stringFromSocket = serverIn.readLine();
                    System.out.println("сервер: " + stringFromSocket);

                    Product product = gson.fromJson(stringFromSocket, Product.class);
                    Report report = information.buyProduct(product);
                    if (report != null) {
                        serverOut.println(gson.toJson(report));
                    }
                    else {
                        serverOut.println(" ");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }
}
