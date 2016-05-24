/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.compras;

import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author Perez_25
 */
public class Compras_Cliente_Mes implements Serializable {

    private HashSet<String> produtos;
    private int numero_comprasN[];
    private int numero_comprasP[];
    private double totalgasto[];
    

    public Compras_Cliente_Mes() {
        this.produtos = new HashSet<>();
        this.numero_comprasN = new int[12];
        this.numero_comprasP = new int[12];
        this.totalgasto = new double[12];
      
    }
    
    
    public Compras_Cliente_Mes(HashSet<String> produtos,int numero_comprasP[],int numero_comprasN[],double total[]){
        
        this.produtos = new HashSet<>();
        for(String s : produtos){
         this.produtos.add(s);
        }
        this.numero_comprasP = numero_comprasP;
        this.numero_comprasN = numero_comprasN;
        this.totalgasto = total;
    }
    
    /**
     * @return the produtos
     */
    public HashSet<String> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(HashSet<String> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the numero_comprasN
     */
    public int[] getNumero_comprasN() {
        return numero_comprasN;
    }

    /**
     * @param numero_comprasN the numero_comprasN to set
     */
    public void setNumero_comprasN(int[] numero_comprasN) {
        this.numero_comprasN = numero_comprasN;
    }

    /**
     * @return the numero_comprasP
     */
    public int[] getNumero_comprasP() {
        return numero_comprasP;
    }

    /**
     * @param numero_comprasP the numero_comprasP to set
     */
    public void setNumero_comprasP(int[] numero_comprasP) {
        this.numero_comprasP = numero_comprasP;
    }

    /**
     * @return the totalgasto
     */
    public double[] getTotalgasto() {
        return totalgasto;
    }

    /**
     * @param totalgasto the totalgasto to set
     */
    public void setTotalgasto(double[] totalgasto) {
        this.totalgasto = totalgasto;
    }



}
