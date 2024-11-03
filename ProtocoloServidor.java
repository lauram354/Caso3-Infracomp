import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ProtocoloServidor{


    public static void ejecucion(BufferedReader entrada, PrintWriter salida) throws IOException{
        String input;
        String output;
        input = entrada.readLine();
        System.out.println(input);
        System.out.println("Ejecución servidor ");
    }

    
}
