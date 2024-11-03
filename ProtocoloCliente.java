import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ProtocoloCliente {
    public static void ejecucion(BufferedReader entrada, BufferedReader servIn, PrintWriter salida) throws IOException{
        String servidor;
        String user;
        boolean ejecutar = true;

        while(ejecutar){
            System.out.println("Mensaje a enviar: ");
            user = entrada.readLine();

            if (user != null){
                System.out.println(user);
                if (user.equalsIgnoreCase("OK")){
                    ejecutar = false;
                }
                salida.println(user);
            }
            if ((servidor = servIn.readLine()) != null){
                System.out.println(servidor);
            }
        }

        
    }
}
