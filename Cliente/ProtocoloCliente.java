package Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ProtocoloCliente {
    public static void ejecucion(BufferedReader entrada, BufferedReader servIn, PrintWriter salida) throws IOException{
        String servidor;
        String user;

        user = "Hola :)";
        System.out.println("Mensaje enviado " + user);     
        salida.println(user);

        salida.println("SECINIT");
        BigInteger reto = generarReto(1024); //TODO: revisar longitud del reto 
        //TODO: Revisar como obtener la llave
        //Key llave = 
        //byte[] R = Asimetrico.cifrar(null, "RSA", reto);

            
    }

    public static BigInteger generarReto(int longitud){
        SecureRandom aleatorio = new SecureRandom();
        BigInteger reto = new BigInteger(longitud, aleatorio);
        return reto;
    }
}

