package creditos;

import java.io.Serializable;

public class Cliente implements VipsRobinson, Serializable {
    private int idcliente, saldo, ingresos, gastos;
    private String nombre, ape1, ape2;

    public Cliente(int idcliente, String nombre, String ape1, String ape2, int saldo, int ingresos, int gastos) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.saldo = saldo;
        this.ingresos = ingresos;
        this.gastos = gastos;

    }

    public int getIdcliente() {
        return idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getIngresos() {
        return ingresos;
    }

    public int getGastos() {
        return gastos;
    }

    @Override
    //Un cliente Vips es un cliente con saldo con crédito cuya media de ingresos es superior a los 3000€.
    public boolean clienteVips() {
        if (this.saldo < 0 && this.ingresos > 3000)
            return true;
        else return false;
    }
//    Un cliente Robinson es aquel cliente con saldo con débito y cuya media de gastos supera los 3000€.
    @Override
    public boolean clienteRobinson() {
        if (this.saldo > 0 && this.gastos > 3000)
            return true;
        else return false;
    }
}
