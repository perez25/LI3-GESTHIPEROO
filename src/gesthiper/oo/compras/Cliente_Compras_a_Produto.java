/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.compras;

import gesthiper.oo.catalogos.Cliente;
import java.io.Serializable;

/**
 *
 * @author Perez_25
 */
public class Cliente_Compras_a_Produto implements Serializable {

    /**
     * Guarda as quantidades para cada mês
     */
    private int[] mesesN;
    private int[] mesesP;
    /**
     * Guarda o numero de vezes que o produto foi comprado para cada mês
     */
    private int[] compras_mes_N;
    private int[] compras_mes_P;
    private double total_gasto;

    public Cliente_Compras_a_Produto() {

        this.mesesN = new int[12];
        this.mesesP = new int[12];
        this.total_gasto = 0.0;
        this.compras_mes_N = new int[12];
        this.compras_mes_P = new int[12];

    }

    public Cliente_Compras_a_Produto(Cliente_Compras_a_Produto t) {

        this.mesesN = new int[11];
        this.mesesP = new int[11];
        this.total_gasto = t.getTotal_gasto();

    }

    /**
     * @return the mesesN
     */
    public int[] getMesesN() {
        return mesesN.clone();
    }

    /**
     * @param mesesN the mesesN to set
     */
    public void setMesesN(int[] mesesN) {
        this.mesesN = mesesN;
    }

    /**
     * @return the mesesP
     */
    public int[] getMesesP() {
        return mesesP.clone();
    }

    /**
     * @param mesesP the mesesP to set
     */
    public void setMesesP(int[] mesesP) {
        this.mesesP = mesesP;
    }

    /**
     * @return the numeroComprasN
     */
    public Cliente_Compras_a_Produto clone() {
        return new Cliente_Compras_a_Produto(this);
    }

    /**
     * @return the compras_mes_N
     */
    public int[] getCompras_mes_N() {
        return compras_mes_N.clone();
    }

    /**
     * @param compras_mes_N the compras_mes_N to set
     */
    public void setCompras_mes_N(int[] compras_mes_N) {
        this.compras_mes_N = compras_mes_N;
    }

    /**
     * @return the compras_mes_P
     */
    public int[] getCompras_mes_P() {
        return compras_mes_P;
    }

    /**
     * @param compras_mes_P the compras_mes_P to set
     */
    public void setCompras_mes_P(int[] compras_mes_P) {
        this.compras_mes_P = compras_mes_P;
    }

    public void add_compra_by_mes_modo_N(int mes) {
        this.compras_mes_N[mes]++;

    }

    public void add_compra_by_mes_modo_P(int mes) {
        this.compras_mes_P[mes]++;

    }

    public void add_quantidade_by_mes_modo_N(int mes, int quantidade) {
        this.mesesN[mes] += quantidade;
    }

    public void add_quantidade_by_mes_modo_P(int mes, int quantidade) {
        this.mesesP[mes] += quantidade;
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

    public int devolve_quantidade_comprada() {
        int res = 0;
        for (int i = 0; i < 12; i++) {
            res += this.mesesN[i] + this.mesesP[i];
        }
        return res;
    }

}
