package Algorithms;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Simetrico {
    private final static String PADDING = "AES/ECB/PKCS5Padding";

    public static byte[] cifrar(SecretKey llave, String mensaje){
        byte[] cifrado;

        try {
            Cipher cifrador = Cipher.getInstance(PADDING);
            byte[] mensajeBytes = mensaje.getBytes();
            cifrador.init(Cipher.ENCRYPT_MODE, llave);
            cifrado = cifrador.doFinal(mensajeBytes);
            return cifrado;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    
    public static byte[] descifrar(SecretKey llave, byte[] mensaje){
        byte[] descifrado;

        try {
            Cipher cifrador = Cipher.getInstance(PADDING);
            cifrador.init(Cipher.DECRYPT_MODE, llave);
            descifrado = cifrador.doFinal(mensaje);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return descifrado;
    }

    public static void generarLlave() throws NoSuchAlgorithmException{
        KeyGenerator keygen = KeyGenerator.getInstance(PADDING);
        SecretKey secretKey = keygen.generateKey();
        
    }

    public static void imprimir (byte[] contenido) throws UnsupportedEncodingException{
        int i = 0;
        for (; i < contenido.length -1; i++){
            System.out.print(contenido[i] + " ");
        }
        System.out.print(contenido[i] + " ");
    }
}
