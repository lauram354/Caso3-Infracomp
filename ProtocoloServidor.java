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
    public static void creacionLlaves(String algoritmo) throws NoSuchAlgorithmException, IOException{
        KeyPairGenerator generator = KeyPairGenerator.getInstance(algoritmo);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey publica = keyPair.getPublic();
        PrivateKey privada = keyPair.getPrivate();
        
    
        FileOutputStream publicaFile = new FileOutputStream("llavePublica.txt");
        ObjectOutputStream oosPublica = new ObjectOutputStream(publicaFile);
        oosPublica.writeObject(publica);
        oosPublica.close();
        

        FileOutputStream privadaFile = new FileOutputStream("llavePrivada.txt");
        ObjectOutputStream oosPrivada = new ObjectOutputStream(privadaFile);
        oosPrivada.writeObject(privada);
        oosPrivada.close();
    
    }

    public static void ejecucion(BufferedReader entrada, PrintWriter salida) throws IOException{
        String input;
        String output;
        input = entrada.readLine();
        System.out.println(input);
        System.out.println("Ejecución servidor ");
    }

    
}
