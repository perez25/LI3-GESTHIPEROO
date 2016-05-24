/**
 * Guarda Triplos que contêm um Cliente, vezes comprado e o total gasto.
 */
package gesthiper.oo;

import gesthiper.oo.catalogos.Cliente;

/**
 *
 * @author Perez_25
 */
public class TriploClienteVezesQuefoiCompradoTotalGasto {

    private Cliente cliente;
    private int vezes_comprado;
    private double total_gasto;

    public TriploClienteVezesQuefoiCompradoTotalGasto() {
        this.cliente = new Cliente();
        this.total_gasto = 0.0;
        this.vezes_comprado = 0;
    }
    
    public TriploClienteVezesQuefoiCompradoTotalGasto(String c,int to,double v) {
        this.cliente = new Cliente(c);
        this.total_gasto = v;
        this.vezes_comprado = to;
    }

    public TriploClienteVezesQuefoiCompradoTotalGasto(TriploClienteVezesQuefoiCompradoTotalGasto t){
        this.cliente = t.getCliente();
        this.total_gasto = t.getTotal_gasto();
        this.vezes_comprado = t.getVezes_comprado();
    }
    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the vezes_comprado
     */
    public int getVezes_comprado() {
        return vezes_comprado;
    }

    /**
     * @param vezes_comprado the vezes_comprado to set
     */
    public void setVezes_comprado(int vezes_comprado) {
        this.vezes_comprado = vezes_comprado;
    }

    /**
     * @return the total_gasto
     */
    public double getTotal_gasto() {
        return total_gasto;
    }

    /**
     * @param total_gasto the total_gasto to set
     */
    public void setTotal_gasto(double total_gasto) {
        this.total_gasto = total_gasto;
    }
    
    @Override
    public String toString(){
    StringBuilder s = new StringBuilder();
    s.append(" o ").append(this.getCliente().getCodigo_cliente()).append(" - UN ").append(this.getVezes_comprado()).append(" - TG "+this.getTotal_gasto());
    return s.toString();
    }
    
    public TriploClienteVezesQuefoiCompradoTotalGasto clone() {
        return new TriploClienteVezesQuefoiCompradoTotalGasto(this);
    }
    
    public boolean equals(TriploClienteVezesQuefoiCompradoTotalGasto c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        TriploClienteVezesQuefoiCompradoTotalGasto p = (TriploClienteVezesQuefoiCompradoTotalGasto) c;
        return (this.cliente.equals(p.getCliente()) && this.total_gasto == p.getTotal_gasto() && this.vezes_comprado == p.getTotal_gasto());
    }
    
}
