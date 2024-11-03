import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ProtocoloCliente {
    public static void ejecucion(BufferedReader entrada, BufferedReader servIn, PrintWriter salida) throws IOException{
        String servidor;
        String user;

        user = "Hola :)";
        System.out.println("Mensaje enviado " + user);
                
        salida.println(user);
            
        if ((servidor = servIn.readLine()) != null){
                System.out.println(servidor);
        }

        
    }
}
