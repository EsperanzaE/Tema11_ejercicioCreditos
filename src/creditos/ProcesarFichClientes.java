package creditos;

import java.io.*;

import static creditos.Utilidades.*;

/**
 * En esta clase vamos a desarrollar todos los procedimientos relacionados con la escritura de los 5 posibles ficheros
 * de salida así como la generación de los listados que se nos pide en las tres primeras opciones
 */
public class ProcesarFichClientes {

    static final String FICH_SALDO_CERO = "Clientes_Saldo_Cero.dat";
    static final String FICH_SALDO_CREDITO = "Clientes_Saldo_Credito.dat";
    static final String FICH_SALDO_DEBITO = "Clientes_Saldo_Debito.dat";
    static final String FICH_VIPS = "Clientes_Vips.dat";
    static final String FICH_ROBINSON = "Clientes_Robinson.dat";
    static ObjectInputStream input = null;
    static ObjectOutputStream output = null;

    static String fichError = "";

    /**
     * proceso que abre el fichero de entrada recién creado "clientes.dat" y crea el correspondiente fichero de salida
     * en función de la opción elegida: FICH_SALDO_CERO = "Clientes_Saldo_Cero.dat",
     * FICH_SALDO_CREDITO = "Clientes_Saldo_Credito.dat";
     * FICH_SALDO_DEBITO = "Clientes_Saldo_Debito.dat";
     * FICH_VIPS = "Clientes_Vips.dat";
     * FICH_ROBINSON ="Clientes_Robinson.dat";
     * finalmente cerrará los ficheros que estén abiertos
     *
     * @param opcion con la opción elegida por el usuario
     *               precondiciones: opcion ha de valer entre 1 y 5
     */
    public static void proceso(int opcion) {
        //abrimos para leer el fichero recién creado
        if (abrirFicheroClientes()) {
            //si no ha habido problemas en la apertura del fichero de lectura
            // creamos el fichero de salida en función de la opción que nos introduzcan
            output = CrearFicheroSalida(opcion);
            if (output != null) {//si se ha podido crear el fichero de salida, se prosigue con el proceso
                ClienteMasDireccion clienteLeido = null;

                try {
                    fichError = FILEOUTPUT;
                    clienteLeido = (ClienteMasDireccion) input.readObject();
                    while (clienteLeido != null) {
                        switch (opcion) {//valida la opcion para generar el fichero de salida que corresponda siempre
                            // y cuando cada registro cumpla con la condición para grabarse en el fichero
                            case SALDOCERO: {
                                //para escribir un registro en el fichero de Saldo Cero, el saldo del cliente ha de
                                //estar a cero
                                if (clienteLeido.getSaldo() == 0) {
                                    fichError = FICH_SALDO_CERO;
                                    output.writeObject((Cliente) clienteLeido);
                                }
                                break;
                            }
                            //para escribir un registro en el fichero de Créditos, el saldo del cliente ha de
                            //ser negativo
                            case CREDITOS: {
                                if (clienteLeido.getSaldo() < 0) {
                                    fichError = FICH_SALDO_CREDITO;
                                    output.writeObject((Cliente) clienteLeido);
                                }
                                break;
                            }
                            //para escribir un registro en el fichero de Débitos, el saldo del cliente ha de
                            //ser positivo
                            case DEBITOS: {
                                if (clienteLeido.getSaldo() > 0) {
                                    fichError = FICH_SALDO_DEBITO;
                                    output.writeObject((Cliente) clienteLeido);
                                }
                                break;
                            }
                            //para escribir un registro en el fichero Vips, el método clienteVips ha de devolver true
                            case VIPS: {
                                if (clienteLeido.clienteVips()) {
                                    fichError = FICH_VIPS;
                                    output.writeObject(clienteLeido);
                                    System.out.println(clienteLeido);
                                }
                                break;
                            }
                            //para escribir un registro en el fichero Vips, el método clienteRobinson ha de devolver true
                            case ROBINSON: {
                                if (clienteLeido.clienteRobinson()) {
                                    fichError = FICH_ROBINSON;
                                    output.writeObject(clienteLeido);
                                    System.out.println(clienteLeido);
                                }
                                break;
                            }
                        }
                        clienteLeido = (ClienteMasDireccion) input.readObject();

                    }
                } catch (EOFException eofException) {
                    System.out.println("Fin de fichero, fichero " + fichError);
                    System.out.println();
                } catch (InvalidClassException invalidClassException) {
                    System.out.println("clase no coincide con la esperada, fichero " + fichError);
                } catch (ClassNotFoundException classNotFoundException) {
                    System.out.println("clase no encontrada, fichero " + fichError);
                } catch (IOException ioException) {
                    System.out.println("error general en la escritura del fichero " + fichError);
                } finally {// se hace un método para cerrar el fichero que realmente corresponda
                    metodofinally(fichError);
                }
            }
        }
    }

    private static void metodofinally(String fichError) {
        if (output != null) {
            try {
                output.close();
            } catch (Exception exception) {
                System.out.println("error al cerrar el archivo " + fichError);
            }
        }
        if (input != null) {
            try {
                input.close();
            } catch (Exception exception) {
                System.out.println("error al cerrar el archivo " + FILEOUTPUT);
            }
        }
    }


    // abrimos para leer el fichero que hemos creado en el proceso anterior
    private static boolean abrirFicheroClientes() {
        boolean operacion = false;
        try {
            input = new ObjectInputStream(new FileInputStream(FILEOUTPUT));
            operacion = true;
        } catch (
                FileNotFoundException fileNotFoundException) {
        } catch (
                IOException ioException) {
            System.out.println("error de apertura del fichero Clientes.Dat como entrada");
        } catch (
                Exception exception) {
            System.out.println("error general en el  fichero Clientes.Dat como entrada");
        } finally {
            return operacion;
        }
    }

    /**
     * Con este método creamos el fichero de salida que corresponda según la opción introducida por
     * el usuario
     *
     * @param opcion, se corresponde con la opción del menú elegido por el usuari
     * @return el fichero de salida que corresponda
     */
    private static ObjectOutputStream CrearFicheroSalida(int opcion) {
        String tipoFichero = "";
        ObjectOutputStream output = null;
        switch (opcion) {
            case SALDOCERO: {
                tipoFichero = FICH_SALDO_CERO;
                break;
            }
            case CREDITOS: {
                tipoFichero = FICH_SALDO_CREDITO;
                break;
            }
            case DEBITOS: {
                tipoFichero = FICH_SALDO_DEBITO;
                break;
            }
            case VIPS: {
                tipoFichero = FICH_VIPS;
                break;
            }
            case ROBINSON: {
                tipoFichero = FICH_ROBINSON;
                break;
            }
        }
        try {
            output = new ObjectOutputStream(new FileOutputStream(tipoFichero));
        } catch (
                FileNotFoundException fileNotFoundException) {
            System.out.println("error de creación del fichero " + tipoFichero);
        } catch (
                IOException ioException) {
            System.out.println("error de apertura del fichero " + tipoFichero);
        } catch (
                Exception exception) {
            System.out.println("error general en el  fichero " + tipoFichero);
        }
        return output;
    }

    public static void listados(int opcion) {

        switch (opcion) {
            case SALDOCERO: {
                listarSaldosCero();
                break;
            }
            case CREDITOS: {
                listarCreditos();
                break;
            }
            case DEBITOS: {
                listardebitos();
                break;
            }
        }

    }

    private static void listardebitos() {
        Cliente cliente = null;
        try {
            input = new ObjectInputStream(new FileInputStream(FICH_SALDO_DEBITO));
            cliente = (Cliente) input.readObject();
            System.out.println("El listado de los clientes con saldo con Débito es");
            while (true) {
                System.out.println(cliente.toString());
                cliente = (Cliente) input.readObject();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("fichero de entrada no encontrado " + FICH_SALDO_DEBITO);
        } catch (EOFException eofException) {
            System.out.println("fin de fichero alcanzado " + FICH_SALDO_DEBITO);
        } catch (IOException ioException) {
            System.out.println("Error general " + FICH_SALDO_DEBITO);
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Clase no esperada " + FICH_SALDO_DEBITO);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ioException) {
                    System.out.println("Error al cerrar el fichero " + FICH_SALDO_DEBITO);
                }
            }
        }
    }

    private static void listarCreditos() {
        Cliente cliente = null;
        try {
            input = new ObjectInputStream(new FileInputStream(FICH_SALDO_CREDITO));
            cliente = (Cliente) input.readObject();
            System.out.println("El listado de los clientes con saldo con Crédito es");
            while (true) {
                System.out.println(cliente.toString());
                cliente = (Cliente) input.readObject();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("fichero de entrada no encontrado " + FICH_SALDO_CREDITO);
        } catch (EOFException eofException) {
            System.out.println("fin de fichero alcanzado " + FICH_SALDO_CREDITO);
        } catch (IOException ioException) {
            System.out.println("Error general " + FICH_SALDO_CREDITO);
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Clase no esperada " + FICH_SALDO_CREDITO);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ioException) {
                    System.out.println("Error al cerrar el fichero " + FICH_SALDO_CREDITO);
                }
            }
        }
    }


    private static void listarSaldosCero() {
        Cliente cliente = null;
        try {
            input = new ObjectInputStream(new FileInputStream(FICH_SALDO_CERO));
            cliente = (Cliente) input.readObject();
            System.out.println("El listado de los clientes con saldo Cero es");
            while (true) {
                System.out.println(cliente.toString());
                cliente = (Cliente) input.readObject();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("fichero de entrada no encontrado " + FICH_SALDO_CERO);
        } catch (EOFException eofException) {
            System.out.println("fin de fichero alcanzado " + FICH_SALDO_CERO);
        } catch (IOException ioException) {
            System.out.println("Error general " + FICH_SALDO_CERO);
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Clase no esperada " + FICH_SALDO_CERO);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ioException) {
                    System.out.println("Error al cerrar el fichero " + FICH_SALDO_CERO);
                }
            }
        }
    }
}





