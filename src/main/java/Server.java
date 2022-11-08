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
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream());) {
                    String stringFromSocket = in.readLine();

                    Product product = gson.fromJson(stringFromSocket, Product.class);
                    if (information.buyProduct(product)) {
                        out.println(gson.toJson(information.GetReport()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }
}
