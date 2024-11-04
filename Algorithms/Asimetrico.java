package Algorithms;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Asimetrico {
    

    public static byte[] cifrar(Key llave, String algoritmo, String mensaje){
        byte[] cifrado;

        try {
            Cipher cifrador = Cipher.getInstance(algoritmo);
            byte[] mensajeBytes = mensaje.getBytes();
            cifrador.init(Cipher.ENCRYPT_MODE, llave);
            cifrado = cifrador.doFinal(mensajeBytes);
            return cifrado;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    

    public static byte[] descifrar(Key llave, String algoritmo, byte[] mensaje){
        byte[] descifrado;

        try {
            Cipher cifrador = Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.DECRYPT_MODE, llave);
            descifrado = cifrador.doFinal(mensaje);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return descifrado;
    }

     public static void creacionLlaves(String algoritmo) throws NoSuchAlgorithmException, IOException{
        KeyPairGenerator generator = KeyPairGenerator.getInstance(algoritmo);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey publica = keyPair.getPublic();
        PrivateKey privada = keyPair.getPrivate();
        
        //TODO: revisar que las llaves se generen y se puedan leeer en ese formato 
        FileOutputStream publicaFile = new FileOutputStream("llavePublica.txt");
        ObjectOutputStream oosPublica = new ObjectOutputStream(publicaFile);
        oosPublica.writeObject(publica);
        oosPublica.close();
        

        FileOutputStream privadaFile = new FileOutputStream("llavePrivada.txt");
        ObjectOutputStream oosPrivada = new ObjectOutputStream(privadaFile); 
        oosPrivada.writeObject(privada);
        oosPrivada.close();

        /* 
        byte[] cifrado = cifrar(publica, algoritmo, "hola como estas");
        imprimir(cifrado);
        System.out.println("Descifrado");
        byte[] descifrado = descifrar(privada, algoritmo, cifrado);
        imprimir(descifrado);
        */
    }

    public static void imprimir (byte[] contenido) throws UnsupportedEncodingException{
        int i = 0;
        for (; i < contenido.length -1; i++){
            System.out.print(contenido[i] + " ");
        }
        System.out.print(contenido[i] + " ");
        /*
        System.out.println("Representación en String");
        String prueba = new String(contenido, "UTF-8");
        System.out.println(prueba);
        imprimir2(prueba.getBytes());
        */
    }
    public static void imprimir2 (byte[] contenido) throws UnsupportedEncodingException{
        int i = 0;
        for (; i < contenido.length -1; i++){
            System.out.print(contenido[i] + " ");
        }
        System.out.print(contenido[i] + " ");
        System.out.println("Representación en String");
        
    }
}
