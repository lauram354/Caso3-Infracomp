package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientProtocol {

    public static final String EXIT_STRING = "exit";

    public static void execute(BufferedReader stdIn, BufferedReader reader, PrintWriter writer) throws IOException {
        System.out.println("Escriba un mensaje para enviar al servidor");
        String userInput = stdIn.readLine();
        writer.println(userInput);

        String response = reader.readLine();
        System.out.println("Respuesta del servidor: " + response);
        if (response.equals(EXIT_STRING)) {
            System.out.println("Cerrando conexión con el servidor");
        }
    }

}
