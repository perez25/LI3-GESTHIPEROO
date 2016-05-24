/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo;

import gesthiper.oo.catalogos.Produto;

/**
 *
 * @author Perez_25
 */
public class TriploProdutoUnidadesVendidas_e_clientes_que_compraram {

    private Produto produto;
    private int unidades_vendidas;
    private int clientes_que_compraram;

    public TriploProdutoUnidadesVendidas_e_clientes_que_compraram() {
        this.produto = new Produto();
        this.unidades_vendidas = 0;
        this.clientes_que_compraram = 0;

    }

    public TriploProdutoUnidadesVendidas_e_clientes_que_compraram(TriploProdutoUnidadesVendidas_e_clientes_que_compraram e) {
        this.produto = e.getProduto().clone();
        this.unidades_vendidas = e.getUnidades_vendidas();
        this.clientes_que_compraram = e.getClientes_que_compraram();

    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the unidades_vendidas
     */
    public int getUnidades_vendidas() {
        return unidades_vendidas;
    }

    /**
     * @param unidades_vendidas the unidades_vendidas to set
     */
    public void setUnidades_vendidas(int unidades_vendidas) {
        this.unidades_vendidas = unidades_vendidas;
    }

    /**
     * @return the clientes_que_compraram
     */
    public int getClientes_que_compraram() {
        return clientes_que_compraram;
    }

    /**
     * @param clientes_que_compraram the clientes_que_compraram to set
     */
    public void setClientes_que_compraram(int clientes_que_compraram) {
        this.clientes_que_compraram = clientes_que_compraram;
    }

    @Override
    public TriploProdutoUnidadesVendidas_e_clientes_que_compraram clone() {
        return new TriploProdutoUnidadesVendidas_e_clientes_que_compraram(this);
    }

    public boolean equals(TriploProdutoUnidadesVendidas_e_clientes_que_compraram c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        TriploProdutoUnidadesVendidas_e_clientes_que_compraram e = (TriploProdutoUnidadesVendidas_e_clientes_que_compraram) c;
        return (this.getProduto().getCodigo_produto().equals(e.getProduto().getCodigo_produto())
                && this.getClientes_que_compraram() == e.getClientes_que_compraram()
                && this.getUnidades_vendidas() == e.getUnidades_vendidas());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" o " + this.getProduto().getCodigo_produto() + " UN " + this.getUnidades_vendidas() + " CL " + this.clientes_que_compraram);
        return s.toString();

    }
}
