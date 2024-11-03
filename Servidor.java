import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Servidor {

    private static final int PUERTO = 3400;

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in); 
        boolean inMenu = true;

        while (inMenu){
            System.out.println("Bienvenido Servidor, seleccione una de las opciones: \n1. Generar pareja de llaves \n2. Ejecutar \n9. Salir");
            String option = reader.nextLine(); 
            if( option.equals("1")){
                System.out.println("Generando llaves...");
                //TODO:Generar la pareja de llaves asimétricas del servidor y almacenarlas en dos archivos


            }else if(option.equals("2")){
                boolean check = true;
                while (check){
                    System.out.println("Ingrese la cantidad de servidores a ejecutar (1 para proceso iterativo, 4, 8, 32): \n");
                    int servidores = Integer.valueOf(reader.nextLine()); 
                    if (servidores == 1 || servidores == 4 || servidores == 8 || servidores == 32 ){
                        check = false;
                        iniciarServidor(servidores);
                    }else{
                        System.out.println("Número no valido. Intente de nuevo");
                    }
                }
                inMenu = false;
                // TODO: Crear servidores y realizar proceso 

            }else if(option.equals("9")){
                System.out.println("Gracias por usar el sistema");
                inMenu= false;
            }
        }
        System.out.println(".....................................................................");
        reader.close();
    }

    public static void iniciarServidor(int threads) throws IOException{
        if (threads == 1){
            ServerSocket ss = null;

            try {
                ss = new ServerSocket(PUERTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            boolean ejecutar = true;
            System.out.println("Inicia Servidor monothread");
            while (ejecutar){
                Socket socket = ss.accept();
                
                try {
                    PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
                    System.out.println(lector.readLine());
                    escritor.println("Servidor mono");
                    ProtocoloServidor.ejecucion(lector, escritor);
                    escritor.close();
                    lector.close();
                    socket.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ss.close();

        }else{
            System.out.println("Inicia Servidor multithread");
            final ExecutorService delegado = Executors.newFixedThreadPool(threads);
            
            ServerSocket ss = null;
            try {
                ss = new ServerSocket(PUERTO);
                
                while (true){
                    Socket clienteSocket = ss.accept();
                    delegado.execute(new ServidorConcurrente(clienteSocket));
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                try {
                    ss.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            

            
        }
        

    }
    
}
