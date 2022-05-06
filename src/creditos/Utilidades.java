package creditos;

import java.io.*;
import java.util.Scanner;

public class Utilidades {
    static File path = null;
    static File fichero = null;
    static BufferedReader input = null;

    public static void proceso() {
        montarArchivos();//montamos el fichero de entrada que vamnos a procesar


    }

    private static void montarArchivos() {
        path = new File("C:\\Users\\Esperanza\\Documents\\IES Nervión\\1 DAM\\Tercera Evaluacion\\Tema 11\\Práctica");
        fichero = new File(path, "Ejercicio_Creditos_Clientes.txt");

        try {
            input = new BufferedReader(new FileReader(fichero));
            procesarArchivo();//procesamos el fichero de entrada convirtíéndolo en binario con registros tipo object

        } catch (IOException ioException) {
            System.out.println("error de entrada salida");
        } catch (Exception exception) {
            System.out.println("error general");
        }


    }

    private static void procesarArchivo() {
        Cliente cliente = null;
        Scanner scanner = null;
        int idcliente, saldo, ingresos, gastos;
        String nombre, ape1, ape2, direccion, codPostal;

        boolean finFichero = false;
        while (!finFichero) {
            try {
                scanner = new Scanner(input.readLine());
                idcliente = scanner.nextInt();
                scanner = new Scanner(input.readLine());
                nombre=scanner.next();
                scanner = new Scanner(input.readLine());
                ape1=scanner.next();
                scanner = new Scanner(input.readLine());
                ape2=scanner.next();

                finFichero = true;
            } catch (EOFException eofException) {
            } catch (IOException exception) {
            } catch (Exception exception) {
            }
        }

    }
}