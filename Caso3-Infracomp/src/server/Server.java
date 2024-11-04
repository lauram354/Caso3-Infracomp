package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    private static final int PORT = 3400;
    private static final List<Integer> ALLOWED_DELEGATES = new ArrayList<>(Arrays.asList(1, 4, 8, 32));

    private boolean continueFlag = true;


    public void launchWithConsole() {
        Scanner scanner = new Scanner(System.in);
        boolean inMenu = true;

        while (inMenu) {
            System.out.println(
                    "Bienvenido al SERVIDOR, seleccione una de las opciones: \n1. Generar pareja de llaves \n2. Ejecutar \n0. Salir");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Generando pareja de llaves...");
                    // TODO: Implementar generación de llaves y almacenarlas en 2 archivos
                    break;
                case 2:
                    int nDelegates = 0;
                    boolean check = true;
                    while (check) {
                        System.out.println(
                                "Ingrese el numero de delegados concurretentes (1 para proceso iterativo, 4, 8, 32):");
                        nDelegates = scanner.nextInt();
                        if (ALLOWED_DELEGATES.contains(nDelegates)) {
                            check = false;
                        } else {
                            System.out.println("Número de delegados no permitido");
                        }
                    }
                    System.out.println("Inicializando Servidor...");
                    start(nDelegates);
                    inMenu = false;
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    inMenu = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        }
        scanner.close();
        System.out.println("Servidor finalizado");

    }

    private void generateKeys() {
        
    }

    public void start(int nDelegates) {
        if (nDelegates == 1) {
            startMonothreadServer();
        } else {
            startMultithreadServer(nDelegates);
        }
    }

    private void startMonothreadServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (continueFlag) {
                System.out.println("Servidor Monothread esperando conexión");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                continueFlag = ServerProtocol.execute(reader, writer);

                writer.close();
                reader.close();
                socket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor");
            e.printStackTrace();
            System.exit(-1);
        }

    }

    private void startMultithreadServer(int nThreads) {
        ServerSocket serverSocket = null;
        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);

        try {
            serverSocket = new ServerSocket(PORT);
            int id = 0;

            while (continueFlag) {
                System.out.println("Servidor Multithread esperando conexión");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                ServerThread serverThread = new ServerThread(socket, id);
                threadPool.execute(serverThread);
                id++;
                if (id == 4) {
                    continueFlag = false;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor");
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (threadPool != null && !threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        }
    }
    
}
