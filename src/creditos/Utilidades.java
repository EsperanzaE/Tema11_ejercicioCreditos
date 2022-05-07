package creditos;

import java.io.*;
import java.util.Scanner;

public class Utilidades {

    enum DATO {ID, NOMBRE, APE1, APE2, SALDO, INGRESOS, GASTOS, DIRECCION, CPOSTAL};

    public static final String FILEOUTPUT = "clientes.dat";
    public static final int SALDOCERO = 1;
    public static final int CREDITOS = 2;
    public static final int DEBITOS = 3;
    public static final int VIPS = 4;
    public static final int ROBINSON = 5;
    static final String FILEINPUT = "Ejercicio_Creditos_Clientes.txt";
    static final int SALIR = 0;
    static File path = null;
    static File fichero = null;
    static String fichError = null;
    static BufferedReader input = null;
    static ObjectOutputStream output = null;
    static Scanner scanner = null;

    /**El método Proceso va a mostrar el menú, solicitará una opción, y si es válida, realizará cada una de las
     // 5 operaciones que requiere el proyecto mediante el método  ProcesarFichClientes.proceso(opcion) en el que
     // se le pasará la opción introducida. Previamente abrirá/creará los ficheros de entrada y salida y
     //procesará los datos del fichero de entrada convirtiendo los registros en objetos cliente y los grabará en el
     // fichero de salida
     *
     */
    public static void proceso() {
        int opcion = 0;
        opcion = mostrarMenu();
        if (opcion != SALIR) {//creamos los ficheros si al menos se ejecuta el programa un vez con una opción válida

            montarArchivos();//montamos el fichero de entrada que vamos a procesar
            procesarArchivo();//procesamos el fichero de entrada convirtíéndolo en binario con registros tipo object
            //ahora tenemos el fichero "clientes.dat" binario con registros objetos ClienteMasDireccion. Este fichero
            //será con el que trabajemos en el resto del programa
        }
        while (( opcion != SALIR )) {

            ProcesarFichClientes.proceso(opcion);
            if (opcion==SALDOCERO || opcion==CREDITOS ||opcion==DEBITOS){
                ProcesarFichClientes.listados(opcion);
            }

            opcion = mostrarMenu();
        }
    }

    /**
     * Método para mostrar el menú y capturar la opción del usuario, solo devuelve un valor entre 0 y 1. si el
     * usuario introduce valores no válidos, dará los correspondientes mensajes de aviso
     * @return opcion que valdrá entre 0 y 5
     * precondiciones: ninguna
     * postcondiciones: ha de devolver un valor válido de opción, entre 0 y 5
     */
    private static int mostrarMenu() {
        int opcion = 0;
        boolean valido = false;
        scanner = new Scanner(System.in);
        mostrarOpciones();
        while (!valido) {
            try {
                opcion = scanner.nextInt();
                if (opcion >= 0 && opcion <= 5) {
                    valido = true;
                } else {
                    System.out.println("La opción ha de ser un número entre 0 y 5");
                    scanner.nextLine();//vaciamos el buffer
                }
            } catch (Exception exception) {
                System.out.println("introduzca un número entero entre 0 y 5");
                scanner.nextLine();//vaciamos el buffer
            }

        }
        return opcion;
    }

    /**
     * método para pintar el menú
     * precondiciones: ninguna
     * postcondiciones: ninguna
     */
    private static void mostrarOpciones() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PROGRAMA PARA PROCESAR EL FICHERO DE CLIENTES\n");
        stringBuilder.append("---------------------------------------------\n");
        stringBuilder.append("--Seleccione una de las siguientes opciones- \n");
        stringBuilder.append("1.- Obtener clientes con saldo 0             \n");
        stringBuilder.append("2.- Obtener clientes con saldos con crédito  \n");
        stringBuilder.append("3.- Obtener clientes con saldos con débito   \n");
        stringBuilder.append("4.- Obtener clientes VIPS                    \n");
        stringBuilder.append("5.- Obtener clientes Robinso                 \n");
        stringBuilder.append("0.- Terminar el programa                     \n");

        System.out.println(stringBuilder);
    }

    /**
     *creamos/componemos los ficheros de entrada y de salida
     */
    private static void montarArchivos() {
        fichero = new File(FILEINPUT);
//abrimos el fichero de entrada
        try {
            fichError = FILEINPUT;
            input = new BufferedReader(new FileReader(fichero));

        } catch (IOException ioException) {
            System.out.println("error de entrada salida, fichero " + fichError);
            cerrarFichero();
        } catch (Exception exception) {
            System.out.println("error general, fichero " + fichError);
            cerrarFichero();
        }
//Creamos el fichero de salida
        try {
            fichError = FILEOUTPUT;
            output = new ObjectOutputStream(new FileOutputStream(FILEOUTPUT));

        } catch (IOException ioException) {
            System.out.println("error de entrada salida, fichero " + fichError);
            cerrarFichero();
        } catch (Exception exception) {
            System.out.println("error general, fichero " + fichError);
            cerrarFichero();
        }


    }

    /**
     * Con este método leemos cada registro del fichero de entrada, montamos un objeto y lo escribimos en un
     * fichero de salida que he llamando cliente.dat
     */
    private static void procesarArchivo() {
        ClienteMasDireccion cliente = null;
        String registro = null;
        int idcliente = 0, saldo = 0, ingresos = 0, gastos = 0, codPostal = 0;
        String nombre = "", ape1 = "", ape2 = "", direccion = "";
        DATO tipoDato[] = DATO.values();
        int indice = 0;

        try {
            registro = input.readLine();

            while (registro != null) {
                scanner = new Scanner(registro);
                while (indice < 9) {
                    switch (tipoDato[indice]) {
                        case ID: {
                            idcliente = scanner.nextInt();
                            indice++;
                            break;
                        }
                        case NOMBRE: {
                            nombre = scanner.next();
                            indice++;
                            break;
                        }
                        case APE1: {
                            ape1 = scanner.next();
                            indice++;
                            break;
                        }
                        case APE2: {
                            ape2 = scanner.next();
                            indice++;
                            break;
                        }
                        case SALDO: {
                            saldo = scanner.nextInt();
                            indice++;
                            break;
                        }
                        case INGRESOS: {
                            ingresos = scanner.nextInt();
                            indice++;
                            break;
                        }
                        case GASTOS: {
                            gastos = scanner.nextInt();
                            indice++;
                            break;
                        }
                        case DIRECCION: {
                            direccion = scanner.next();
                            indice++;
                            break;
                        }
                        case CPOSTAL: {
                            codPostal = scanner.nextInt();
                            indice++;
                            break;
                        }
                    }
                }
                if (indice == 9) {
                    fichError = FILEOUTPUT;
                    cliente = new ClienteMasDireccion(idcliente, nombre, ape1, ape2, saldo, ingresos, gastos,
                            direccion, codPostal);

                    output.writeObject(cliente);
                    indice = 0;
                }
                fichError = FILEINPUT;
                registro = input.readLine();
            }

        } catch (EOFException eofException) {
            System.out.println("se ha llegado al final del fichero " + fichError);
        } catch (IOException exception) {
            System.out.println("error de IO en el fichero " + fichError);
        } catch (Exception exception) {
            System.out.println("error general en el fichero " + fichError);
        } finally {
            cerrarFichero();
        }

    }

    /**
     * cerramos los ficheros de entrada y el de salida "clientes.dat" que acabamos de crear
     */
    private static void cerrarFichero() {
        if (input != null) {
            try {
                input.close();
            } catch (Exception exception) {
                System.out.println("error al cerrar el archivo de entrada " + FILEINPUT);
            }
        }
        if (output != null) {
            try {
                output.close();
            } catch (Exception exception) {
                System.out.println("error al cerrar el archivo de salida " + FILEOUTPUT);
            }
        }
    }


}
