import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Consola {

    public static void main(String[] args) throws FileNotFoundException, IOException{
        Scanner reader = new Scanner(System.in); 
        boolean inMenu = true;

        while (inMenu){
            System.out.println("Bienvenido, seleccione una de las opciones: \n1. Generar pareja de llaves \n2. Ejecutar \n9. Salir");
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
                    }else{
                        System.out.println("Número no valido. Intente de nuevo");
                    }
                }
                // TODO: Crear servidores y realizar proceso 

            }else if(option.equals("9")){
                System.out.println("Gracias por usar el sistema");
                inMenu= false;
            }
        }
        System.out.println(".....................................................................");
    }
}
