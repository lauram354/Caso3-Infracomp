import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorConcurrente implements Runnable{

    private Socket socketCliente = null;
    public ServidorConcurrente(Socket cliente){
        this.socketCliente = cliente;

    }

    public void run(){
        System.out.println("Ejecutando servidor concurrente " );
        try {
            PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
            BufferedReader lector = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            
            System.out.println(lector.readLine());
            escritor.println("Respuesta de serv concurrente");
            ProtocoloServidor.ejecucion(lector, escritor);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
