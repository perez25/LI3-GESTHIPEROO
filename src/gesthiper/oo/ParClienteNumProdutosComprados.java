/**
 * Par que guarda cliente e produtos comprados.
 */
package gesthiper.oo;

import gesthiper.oo.catalogos.Cliente;

/**
 *
 * @author Perez_25
 */
public class ParClienteNumProdutosComprados {

    private Cliente cliente;
    private int produtos_comprados;

    public ParClienteNumProdutosComprados() {
        this.cliente = new Cliente();
        this.produtos_comprados = 0;
    }

    public ParClienteNumProdutosComprados(Cliente cl, int pc) {
        this.cliente = cl.clone();
        this.produtos_comprados = pc;
    }

    public ParClienteNumProdutosComprados(ParClienteNumProdutosComprados p) {
        this.cliente = p.getCliente();
        this.produtos_comprados = p.getProdutos_comprados();
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
     * @return the produtos_comprados
     */
    public int getProdutos_comprados() {
        return produtos_comprados;
    }

    /**
     * @param produtos_comprados the produtos_comprados to set
     */
    public void setProdutos_comprados(int produtos_comprados) {
        this.produtos_comprados = produtos_comprados;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Par Cliente Produto comprado : ");
        s.append(this.cliente.toString()).append("\n");
        s.append(this.produtos_comprados).append("\n");
        return s.toString();
    }

    
    public ParClienteNumProdutosComprados clone() {
        return new ParClienteNumProdutosComprados(this);
    }
    
    public boolean equals(ParClienteNumProdutosComprados c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        ParClienteNumProdutosComprados p = (ParClienteNumProdutosComprados) c;
        return (this.cliente.equals(p.getCliente()) && this.produtos_comprados == p.getProdutos_comprados());
    }
}
