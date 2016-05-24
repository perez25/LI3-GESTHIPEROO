/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Perez_25
 */
public class Dados_leitura implements Serializable{

    private String ficheiro_compras;
    private int vezes_lido;
    private String ficheiro_produtos;
    private String ficheiro_clientes;
    private int total_produtos;
    private int total_produtos_diferentes_comprados;
    private int total_produtos_nao_comprados;
    private int total_clientes_que_nao_compraram;
    private int total_clientes;
    private int total_compras;
    private int total_clientes_que_compraram;
    private int total_compras_valor_zero;
    private double facturacao_total;
    private int total_compras_invalidas;
    private HashSet<String> linhas_invalidas;
    
    public Dados_leitura(){
     this.ficheiro_compras = "NR";
     this.facturacao_total = 0.0;
     this.ficheiro_clientes = "NR";
     this.total_clientes = 0;
     this.total_produtos = 0;
     this.total_compras = 0;
     this.total_clientes_que_compraram = 0;
     this.total_clientes_que_nao_compraram = 0;
     this.total_produtos_nao_comprados = 0;
     this.total_produtos_diferentes_comprados = 0;
     this.total_compras_valor_zero = 0;
     this.ficheiro_produtos = "NR";
     this.total_compras_invalidas = 0;
     this.vezes_lido = 0;
     this.linhas_invalidas = new HashSet<>(100000);
    }
    
    
    public Dados_leitura(Dados_leitura p){
     this.ficheiro_compras = p.getFicheiro_compras();
     this.facturacao_total = p.getFacturacao_total();
     this.ficheiro_clientes = p.getFicheiro_clientes();
     this.total_clientes = p.getTotal_clientes();
     this.total_produtos = p.getTotal_produtos();
     this.total_clientes_que_compraram = p.getTotal_clientes_que_compraram();
     this.total_clientes_que_nao_compraram = p.getTotal_clientes_que_nao_compraram();
     this.total_produtos_nao_comprados = p.getTotal_produtos_nao_comprados();
     this.total_produtos_diferentes_comprados = p.getTotal_produtos_diferentes_comprados();
     this.total_compras_valor_zero = p.getTotal_compras_valor_zero();
     this.ficheiro_produtos = p.getFicheiro_produtos();
     this.total_compras = p.getTotal_compras();
     this.total_compras_invalidas = p.getTotal_compras_invalidas();
     this.vezes_lido = p.getVezes_lido();
     this.linhas_invalidas = p.getLinhas_invalidas();
    
    }

    /**
     * @return the ficheiro_compras
     */
    public String getFicheiro_compras() {
        return ficheiro_compras;
    }

    /**
     * @param ficheiro_compras the ficheiro_compras to set
     */
    public void setFicheiro_compras(String ficheiro_compras) {
        this.ficheiro_compras = ficheiro_compras;
    }

    /**
     * @return the ficheiro_produtos
     */
    public String getFicheiro_produtos() {
        return ficheiro_produtos;
    }

    /**
     * @param ficheiro_produtos the ficheiro_produtos to set
     */
    public void setFicheiro_produtos(String ficheiro_produtos) {
        this.ficheiro_produtos = ficheiro_produtos;
    }

    /**
     * @return the ficheiro_clientes
     */
    public String getFicheiro_clientes() {
        return ficheiro_clientes;
    }

    /**
     * @param ficheiro_clientes the ficheiro_clientes to set
     */
    public void setFicheiro_clientes(String ficheiro_clientes) {
        this.ficheiro_clientes = ficheiro_clientes;
    }

    /**
     * @return the total_produtos
     */
    public int getTotal_produtos() {
        return total_produtos;
    }

    /**
     * @param total_produtos the total_produtos to set
     */
    public void setTotal_produtos(int total_produtos) {
        this.total_produtos = total_produtos;
    }

    /**
     * @return the total_produtos_diferentes_comprados
     */
    public int getTotal_produtos_diferentes_comprados() {
        return total_produtos_diferentes_comprados;
    }

    /**
     * @param total_produtos_diferentes_comprados the total_produtos_diferentes_comprados to set
     */
    public void setTotal_produtos_diferentes_comprados(int total_produtos_diferentes_comprados) {
        this.total_produtos_diferentes_comprados = total_produtos_diferentes_comprados;
    }

    /**
     * @return the total_produtos_nao_comprados
     */
    public int getTotal_produtos_nao_comprados() {
        return total_produtos_nao_comprados;
    }

    /**
     * @param total_produtos_nao_comprados the total_produtos_nao_comprados to set
     */
    public void setTotal_produtos_nao_comprados(int total_produtos_nao_comprados) {
        this.total_produtos_nao_comprados = total_produtos_nao_comprados;
    }

    /**
     * @return the total_clientes_que_nao_compraram
     */
    public int getTotal_clientes_que_nao_compraram() {
        return total_clientes_que_nao_compraram;
    }

    /**
     * @param total_clientes_que_nao_compraram the total_clientes_que_nao_compraram to set
     */
    public void setTotal_clientes_que_nao_compraram(int total_clientes_que_nao_compraram) {
        this.total_clientes_que_nao_compraram = total_clientes_que_nao_compraram;
    }

    /**
     * @return the total_clientes
     */
    public int getTotal_clientes() {
        return total_clientes;
    }

    /**
     * @param total_clientes the total_clientes to set
     */
    public void setTotal_clientes(int total_clientes) {
        this.total_clientes = total_clientes;
    }

    /**
     * @return the total_clientes_que_compraram
     */
    public int getTotal_clientes_que_compraram() {
        return total_clientes_que_compraram;
    }

    /**
     * @param total_clientes_que_compraram the total_clientes_que_compraram to set
     */
    public void setTotal_clientes_que_compraram(int total_clientes_que_compraram) {
        this.total_clientes_que_compraram = total_clientes_que_compraram;
    }

    /**
     * @return the total_compras_valor_zero
     */
    public int getTotal_compras_valor_zero() {
        return total_compras_valor_zero;
    }

    /**
     * @param total_compras_valor_zero the total_compras_valor_zero to set
     */
    public void setTotal_compras_valor_zero(int total_compras_valor_zero) {
        this.total_compras_valor_zero = total_compras_valor_zero;
    }

    /**
     * @return the facturacao_total
     */
    public double getFacturacao_total() {
        return facturacao_total;
    }

    /**
     * @param facturacao_total the facturacao_total to set
     */
    public void setFacturacao_total(double facturacao_total) {
        this.facturacao_total = facturacao_total;
    }
    
    
     @Override
    public Dados_leitura clone() {
        return new Dados_leitura(this);
    }

    /**
     * @return the total_compras
     */
    public int getTotal_compras() {
        return total_compras;
    }

    /**
     * @param total_compras the total_compras to set
     */
    public void setTotal_compras(int total_compras) {
        this.total_compras = total_compras;
    }

    /**
     * @return the total_compras_invalidas
     */
    public int getTotal_compras_invalidas() {
        return total_compras_invalidas;
    }

    /**
     * @param total_compras_invalidas the total_compras_invalidas to set
     */
    public void setTotal_compras_invalidas(int total_compras_invalidas) {
        this.total_compras_invalidas = total_compras_invalidas;
    }
    
    public boolean equals(Dados_leitura d) {
        if (this == d) {
            return true;
        }
        if ((d == null) || (this.getClass() != d.getClass())) {
            return false;
        }
        Dados_leitura dl = (Dados_leitura) d;
        return (this.getFicheiro_compras().equals(dl.getFicheiro_compras()) 
                && this.getFacturacao_total()==dl.getFacturacao_total() 
                && this.getFicheiro_clientes().equals(dl.getFicheiro_clientes()) 
                && this.getFicheiro_produtos().equals(dl.getFicheiro_produtos()) 
                && this.getTotal_clientes() == dl.getTotal_clientes() 
                && this.getTotal_clientes_que_compraram() == dl.getTotal_clientes_que_compraram() 
                && this.getTotal_clientes_que_nao_compraram() == dl.getTotal_clientes_que_nao_compraram() 
                && this.getTotal_compras() == dl.getTotal_compras() 
                && this.getTotal_compras_invalidas() == dl.getTotal_compras_invalidas() 
                && this.getTotal_compras_valor_zero() == dl.getTotal_compras_valor_zero() 
                && this.getTotal_produtos() == dl.getTotal_produtos() 
                && this.getTotal_produtos_diferentes_comprados() == dl.getTotal_produtos_diferentes_comprados() 
                && this.getTotal_produtos_nao_comprados() == dl.getTotal_produtos_nao_comprados());
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Dados de Leitura : ");
        s.append(this.facturacao_total).append("\n");
        s.append(this.ficheiro_clientes).append("\n");
        s.append(this.ficheiro_compras).append("\n");
        s.append(this.ficheiro_produtos).append("\n");
        s.append(this.total_clientes).append("\n");
        s.append(this.total_clientes_que_compraram).append("\n");
        s.append(this.total_clientes_que_nao_compraram).append("\n");
        s.append(this.total_compras).append("\n");
        s.append(this.total_compras_invalidas).append("\n");
        s.append(this.total_compras_valor_zero).append("\n");
        s.append(this.total_produtos).append("\n");
        s.append(this.total_produtos_diferentes_comprados).append("\n");
        s.append(this.total_produtos_nao_comprados).append("\n");
        return s.toString();
    }

    /**
     * @return the vezes_lido
     */
    public int getVezes_lido() {
        return vezes_lido;
    }

    /**
     * @param vezes_lido the vezes_lido to set
     */
    public void setVezes_lido(int vezes_lido) {
        this.vezes_lido = vezes_lido;
    }

    /**
     * @return the linhas_invalidas
     */
    public HashSet<String> getLinhas_invalidas() {
        return linhas_invalidas;
    }

    /**
     * @param linhas_invalidas the linhas_invalidas to set
     */
    public void setLinhas_invalidas(HashSet<String> linhas_invalidas) {
        this.linhas_invalidas = linhas_invalidas;
    }
    
    
}
