/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.compras;

import gesthiper.oo.catalogos.Cliente;
import gesthiper.oo.catalogos.Produto;
import java.io.Serializable;

/**
 *
 * @author Perez_25
 */
public class Compra implements Serializable{

    private Produto prod;
    private Cliente cl;
    private int mes;
    private double preco;
    private char modo;
    private int quantidade;

    public Compra() {
        this.cl = null;
        this.prod = null;
        this.mes = 0;
        this.preco = 0.0;
        this.modo = 'S';
        this.quantidade = 0;
    }

    public Compra(Produto prod, Cliente cl, int mes, double preco, char modo, int quantidade) {
        //fazer o clone !!!!
        this.cl = cl;
        this.prod = prod;
        this.mes = mes;
        this.preco = preco;
        this.modo = modo;
        this.quantidade = quantidade;
    }

    public Compra(Compra c) {
        this.cl = c.getCl();
        this.prod = c.getProd();
        this.mes = c.getMes();
        this.preco = c.getPreco();
        this.modo = c.getModo();
        this.quantidade = c.getQuantidade();
    }

    /**
     * @return the prod
     */
    public Produto getProd() {
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(Produto prod) {
        this.prod = prod;
    }

    /**
     * @return the cl
     */
    public Cliente getCl() {
        return cl;
    }

    /**
     * @param cl the cl to set
     */
    public void setCl(Cliente cl) {
        this.cl = cl;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the modo
     */
    public char getModo() {
        return modo;
    }

    /**
     * @param modo the modo to set
     */
    public void setModo(char modo) {
        this.modo = modo;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public Compra clone() {
        return new Compra(this);
    }

    @Override
    public String toString() {
        return null;
    }

  
    public boolean equals(Compra obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        Compra e = (Compra) obj;
        return (this.getCl().equals(e.getCl()) && this.getMes() == e.getMes() && this.modo == e.getModo()
                && e.quantidade == this.quantidade && this.preco == e.getPreco() && this.getProd().equals(e.getProd()));
    }
}
