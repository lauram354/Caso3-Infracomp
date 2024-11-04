package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerProtocol {

    public static final String EXIT_STRING = "exit";

    public static boolean execute(BufferedReader reader, PrintWriter writer) throws IOException {
        String inputLine;
        String outputLine;

        inputLine = reader.readLine();
        System.out.println("Entrada: " + inputLine);
        if (inputLine.equals(EXIT_STRING)) {
            writer.println(EXIT_STRING);
            System.out.println("Cerrando conexión con el cliente");
            return false;
        }
        
        outputLine = inputLine + " procesada";
        writer.println(outputLine);
        System.out.println("Salida: " + outputLine);
        return true;
    }
    
}
