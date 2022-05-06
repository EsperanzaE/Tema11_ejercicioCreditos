package creditos;

public class Cliente {
    private int idcliente, saldo, ingresos, gastos;
    private String nombre, ape1, ape2,direccion,codPostal;

    public Cliente(int idcliente, String nombre, String ape1,String ape2,int saldo,int ingresos,int gastos,
                   String direccion, String codPostal){
        this.idcliente=idcliente;
        this.nombre=nombre;
        this.ape1=ape1;
        this.ape2=ape2;
        this.saldo=saldo;
        this.ingresos=ingresos;
        this. gastos=gastos;
        this.direccion=direccion;
        this.codPostal=codPostal;


    }
}
