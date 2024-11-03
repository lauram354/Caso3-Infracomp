import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente {
    public static final int PUERTO = 3400;
	public static final String SERVIDOR = "localhost";
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in); 
        boolean inMenu = true;

        while (inMenu){
            System.out.println("Bienvenido Cliente, seleccione una de las opciones: \n1. Ejecutar \n9. Salir");
            String option = reader.nextLine(); 
            if( option.equals("1")){
                boolean check = true;
                while (check){
                    System.out.println("Ingrese la cantidad de clientes a ejecutar (1 para proceso iterativo, 4, 8, 32): \n");
                    int clientes = Integer.valueOf(reader.nextLine()); 
                    if (clientes == 1 || clientes == 4 || clientes == 8 || clientes == 32 ){
                        check = false;
                        iniciarCliente(clientes);
                    }else{
                        System.out.println("Número no valido. Intente de nuevo");
                    }
                }
                inMenu = false;
            }else if(option.equals("9")){
                System.out.println("Gracias por usar el sistema");
                inMenu= false;
            }
        }
        System.out.println(".....................................................................");
        reader.close();
		
	}

    public static void iniciarCliente(int threads) throws IOException{

        if (threads == 1){
            Socket socket = null;
            PrintWriter escritor = null;
            BufferedReader lector = null;
            System.out.println("Comienza cliente");
		
            try {
                socket = new Socket(SERVIDOR, PUERTO);
                escritor = new PrintWriter(socket.getOutputStream(), true);
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
	
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            ProtocoloCliente.ejecucion(entrada, lector, escritor);
            System.out.println(lector.readLine());
            
            socket.close();
            escritor.close();
            lector.close();

        }else{
            System.out.println("Inicia Cliente multithread");
            for (int i=0; i<threads; i++){
                ClienteConcurrente newCliente = new ClienteConcurrente(i);
                newCliente.start();
            }
        }

    }
}
