/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.contabilidade;

import gesthiper.oo.catalogos.Produto;
import java.io.Serializable;

/**
 *
 * @author Perez_25
 */
public class Unidade_Contabilidade implements Serializable {

    private Produto p;
    private double preco;
    private char tipo;
    private int mes;
    private int quantidade;

    public Unidade_Contabilidade() {
        this.p = new Produto();
        this.preco = 0.0;
        this.tipo = 'S';
        this.mes = -1;
        this.quantidade = 0;
    }

    public Unidade_Contabilidade(Produto p, double preco, char tipo, int mes, int quantidade) {
        this.p = p;
        this.preco = preco;
        this.tipo = tipo;
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public Unidade_Contabilidade(Unidade_Contabilidade uc) {
        this.p = uc.getP();
        this.preco = uc.getPreco();
        this.tipo = uc.getTipo();
        this.mes = uc.getMes();
        this.quantidade = uc.getQuantidade();
    }

    public Produto getP() {
        return p.clone();
    }

    public void setP(Produto p) {
        this.p = p.clone();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Unidade_Contabilidade clone() {
        return new Unidade_Contabilidade(this);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Produto : ");
        s.append(this.p.toString()).append("\n");
        s.append(this.preco).append("\n");
        s.append(this.tipo).append("\n");
        s.append(this.mes).append("\n");
        s.append(this.quantidade).append("\n");

        return s.toString();
    }
    
    public boolean equals(Unidade_Contabilidade uc) {
        if (this == uc) {
            return true;
        }
        else if ((uc == null) || (this.getClass() != uc.getClass())) {
            return false;
        }
        else {
        Unidade_Contabilidade e = (Unidade_Contabilidade) uc;
        return this.p.getCodigo_produto().equals(e.p.getCodigo_produto()) && this.preco == e.getPreco() && this.mes == e.getMes() && this.tipo == e.getTipo() && this.quantidade == e.getQuantidade();
        }
    }

    

}