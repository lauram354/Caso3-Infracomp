package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientProtocol {

    public static final String EXIT_STRING = "exit";

    public static void execute(BufferedReader reader, PrintWriter writer) throws IOException {
        // Generate a random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        int length = 3; // Length of the random string
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }
        writer.println(randomString.toString());

        String response = reader.readLine();
        System.out.println("Respuesta del servidor: " + response);
        if (response.equals(EXIT_STRING)) {
            System.out.println("Cerrando conexión con el servidor");
        }
    }

}
