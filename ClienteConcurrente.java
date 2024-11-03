import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteConcurrente extends Thread{
    private int id;

    public ClienteConcurrente(int id){
        this.id = id;
    }

    @Override
    public void run(){
        Socket socket = null;
        PrintWriter escritor = null;
        BufferedReader lector = null;
        System.out.println("Comienza cliente");
    
        try {
            socket = new Socket("localhost", 3400);
            escritor = new PrintWriter(socket.getOutputStream(), true);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        try {
            ProtocoloCliente.ejecucion(entrada, lector, escritor);
            escritor.println("Hola servidor, Cliente concurrente " + String.valueOf(id));
            System.out.println(lector.readLine());
    
            socket.close();
            escritor.close();
            lector.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Termino thread " + String.valueOf(id));
    }
    
}
