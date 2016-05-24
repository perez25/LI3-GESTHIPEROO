/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.contabilidade;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Perez_25
 */
public class Cont_produto implements Serializable {

    private int tot_vendasN;
    private double valor_totalN;
    private int tot_vendasP;
    private double valor_totalP;
    private int meses_vendas_N[];
    private int meses_vendas_P[];
    private double meses_fact_P[];
    private double meses_fact_N[];

    public Cont_produto(String nome_produto) {
        this.tot_vendasN = 0;
        this.valor_totalN = 0.0;
        this.tot_vendasP = 0;
        this.valor_totalP = 0.0;
        this.meses_vendas_N = new int[12];
        this.meses_vendas_P = new int[12];
        this.meses_fact_N = new double[12];
        this.meses_fact_P = new double[12];

    }

    public Cont_produto(int tot_vendasN, double valor_totalN, int tot_vendasP, double valor_totalP, List<Integer> meses_vendas_N, List<Integer> meses_vendas_P) {

        this.tot_vendasN = tot_vendasN;
        this.valor_totalN = valor_totalN;
        this.tot_vendasP = tot_vendasP;
        this.valor_totalP = valor_totalP;

        this.meses_vendas_N = new int[12];
        for (int i : meses_vendas_N) {
            this.meses_vendas_N[i] = 0;
        }

        this.meses_vendas_P = new int[12];
        for (int i : meses_vendas_P) {
            this.meses_vendas_P[i] = 0;
        }
    }

    public Cont_produto(Cont_produto cp) {
        this.tot_vendasN = cp.getTot_VendasN();
        this.valor_totalN = cp.getValor_totalN();
        this.tot_vendasP = cp.getTot_VendasP();
        this.valor_totalP = cp.getValor_totalP();
        this.meses_vendas_N = cp.getMeses_vendas_N();
        this.meses_vendas_P = cp.getMeses_vendas_P();

        this.meses_fact_N = cp.getMeses_fact_N();
        this.meses_fact_P = cp.getMeses_fact_P();

    }

    public int getTot_VendasN() {
        return this.tot_vendasN;
    }

    public double getValor_totalN() {
        return this.valor_totalN;
    }

    public int getTot_VendasP() {
        return this.tot_vendasP;
    }

    public double getValor_totalP() {
        return this.valor_totalP;
    }

    public void setTot_vendasN(int tot_vendasN) {
        this.tot_vendasN = tot_vendasN;
    }

    public void setValor_totalN(double valor_totalN) {
        this.valor_totalN = valor_totalN;
    }

    public void setTot_vendasP(int tot_vendasP) {
        this.tot_vendasP = tot_vendasP;
    }

    public void setValor_totalP(double valor_totalP) {
        this.valor_totalP = valor_totalP;
    }

    @Override
    public Cont_produto clone() {
        return new Cont_produto(this);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Total de vendas do tipo N " + this.tot_vendasN + "\n");
        s.append("Total de vendas do tipo P " + this.tot_vendasP + "\n");
        s.append("Valor total de vendas do tipo N" + this.valor_totalN + "\n");
        s.append("Valor total de vendas do tipo P" + this.valor_totalP + "\n");

        for (int i = 0; i < 12; i++) {
            s.append("Houve ").append(this.meses_vendas_N[i]).append(" vendas do tipo N no mês ").append(i).append("\n");
            s.append("Houve ").append(this.meses_vendas_P[i]).append(" vendas do tipo P no mês ").append(i).append("\n");
        }
        return s.toString();

    }
    
    public boolean equals(Cont_produto c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        Cont_produto e = (Cont_produto) c;
        return (Arrays.equals(this.meses_fact_N, e.getMeses_fact_N()) && Arrays.equals(this.meses_fact_P,e.getMeses_fact_P()) && Arrays.equals(this.meses_vendas_N, e.getMeses_vendas_N()) && Arrays.equals(this.meses_vendas_P, e.getMeses_vendas_P()) && this.tot_vendasN == e.getTot_VendasN() && this.tot_vendasP == e.getTot_VendasP() && this.valor_totalN == e.getValor_totalN() && this.valor_totalP == e.getValor_totalP());
    }

    /**
     * @return the meses_vendas_N
     */
    public int[] getMeses_vendas_N() {
        return meses_vendas_N;
    }

    /**
     * @param meses_vendas_N the meses_vendas_N to set
     */
    public void setMeses_vendas_N(int[] meses_vendas_N) {
        this.meses_vendas_N = meses_vendas_N;
    }

    /**
     * @return the meses_vendas_P
     */
    public int[] getMeses_vendas_P() {
        return meses_vendas_P;
    }

    /**
     * @param meses_vendas_P the meses_vendas_P to set
     */
    public void setMeses_vendas_P(int[] meses_vendas_P) {
        this.meses_vendas_P = meses_vendas_P;
    }

    /**
     * @return the meses_fact_P
     */
    public double[] getMeses_fact_P() {
        return meses_fact_P;
    }

    /**
     * @param meses_fact_P the meses_fact_P to set
     */
    public void setMeses_fact_P(double[] meses_fact_P) {
        this.meses_fact_P = meses_fact_P;
    }

    /**
     * @return the meses_fact_N
     */
    public double[] getMeses_fact_N() {
        return meses_fact_N;
    }

    /**
     * @param meses_fact_N the meses_fact_N to set
     */
    public void setMeses_fact_N(double[] meses_fact_N) {
        this.meses_fact_N = meses_fact_N;
    }
}
