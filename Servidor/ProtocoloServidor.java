package Servidor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;


public class ProtocoloServidor{

    public static void ejecucion(BufferedReader entrada, PrintWriter salida) throws IOException{
        String input;
        String output;
        input = entrada.readLine();
        System.out.println(input);
        System.out.println("Ejecución servidor ");
    }

    
}
