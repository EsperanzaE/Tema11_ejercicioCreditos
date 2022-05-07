package creditos;

import java.io.Serializable;

public class ClienteMasDireccion extends Cliente implements Serializable {

    private String direccion;
    private int codPostal;

    public ClienteMasDireccion(int idcliente, String nombre, String ape1, String ape2, int saldo, int ingresos,

                               int gastos,String direccion, int codPostal) {
        super(idcliente, nombre, ape1, ape2, saldo, ingresos, gastos);
        this.direccion=direccion;
        this.codPostal=codPostal;
    }

    public String toString(){

        return "id " + getIdcliente() + " nombre" + getNombre()+ "  ape1 "+ getApe1() + " ape2 " + getApe2() + " saldo "
                +getSaldo() + " ingresos "+getIngresos()+ " gastos" + getGastos()+ " direccion " + direccion
                + " codpostal "+ codPostal;
    }


}
