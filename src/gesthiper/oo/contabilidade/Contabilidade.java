/**
 * Classe responsável por os dados de facturação de cada produto
 */
package gesthiper.oo.contabilidade;

import gesthiper.oo.catalogos.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author Perez_25
 */
public class Contabilidade implements Serializable {

    private int numero_vendas;
    private HashMap<String, Cont_produto> lista_produtos;
    /**
     * Construtor Vazio
     */
    public Contabilidade() {
        this.lista_produtos = new HashMap<>(200000);
        this.numero_vendas = 0;
    }
    /**
     * Construtor Parametrizado 
     * @param numero_vendas
     * @param lista_produtos 
     */
    public Contabilidade(int numero_vendas, Map<String, Cont_produto> lista_produtos) {
        this.numero_vendas = numero_vendas;
        this.lista_produtos = new HashMap<>();
        for (String s : lista_produtos.keySet()) {
            lista_produtos.put(s, (Cont_produto) lista_produtos.get(s).clone());
        }
    }
    
    public Contabilidade(Contabilidade c) {
        this.lista_produtos = c.getListaProdutos();
        this.numero_vendas = c.getNumeroVendas();
    }

    public HashMap<String, Cont_produto> getListaProdutos() {
        HashMap<String, Cont_produto> res = new HashMap<>();
        for (String s : this.lista_produtos.keySet()) {
            res.put(s, (Cont_produto) lista_produtos.get(s).clone());
        }
        return res;
    }

    public int getNumeroVendas() {
        return this.numero_vendas;
    }

    public void setNumero_vendas(int numero_vendas) {
        this.numero_vendas = numero_vendas;
    }

    public void setLista_produtos(Map<String, Cont_produto> lista_produtos) {
        for (Map.Entry<String, Cont_produto> entrySet : lista_produtos.entrySet()) {
            String key = entrySet.getKey();
            Cont_produto value = entrySet.getValue();

        }
    }

    @Override
    public Contabilidade clone() {
        return new Contabilidade(this);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Número total de vendas: ").append(this.numero_vendas).append("\n");
        for (String r : this.lista_produtos.keySet()) {
            s.append("Produto : ").append(r).append("\n");
            s.append(lista_produtos.get(r).toString());
        }
        return s.toString();
    }
    
    
   public boolean equals(Contabilidade c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        Contabilidade e = (Contabilidade) c;
        Iterator it=this.lista_produtos.keySet().iterator();
        boolean equals=true;
        boolean end=true;
        HashMap<String,Cont_produto> aux= c.getListaProdutos();
        while(aux.containsKey(it)  && equals && end)
        {
            if(!this.lista_produtos.get(it).equals(aux.get(it)))
                 equals=false;
            if(!it.hasNext()){
                end=false;
            }
            it.next();
        }
        return equals && this.numero_vendas == e.getNumeroVendas();
    }
   /**
    * Insere um produto no módulo
    * @param p 
    */
    public void init_cont_PROD(Produto p) {
        if (!(this.lista_produtos.containsKey(p.getCodigo_produto()))) {
            Cont_produto novo = new Cont_produto(p.getCodigo_produto());
            this.lista_produtos.put(p.getCodigo_produto(), novo);
        }

    }
    /**
     * Inserir uma unidade de contabilidade
     * @param c 
     */
    public void insert_cont(Unidade_Contabilidade c) {

        if (this.lista_produtos.containsKey(c.getP().getCodigo_produto())) {
            Cont_produto aux = this.lista_produtos.get(c.getP().getCodigo_produto());

            if (c.getTipo() == 'N') {
                aux.setTot_vendasN(c.getQuantidade() + aux.getTot_VendasN());
                aux.setValor_totalN(c.getPreco() * c.getQuantidade() + aux.getValor_totalN());
                aux.getMeses_vendas_N()[c.getMes() - 1] +=c.getQuantidade();
                aux.getMeses_fact_N()[c.getMes() - 1] += (c.getQuantidade() * c.getPreco());

            } else {
                aux.setTot_vendasP(c.getQuantidade() + aux.getTot_VendasP());
                aux.setValor_totalP((c.getPreco() * c.getQuantidade()) + aux.getValor_totalP());
                aux.getMeses_vendas_P()[c.getMes() - 1] +=c.getQuantidade();
                aux.getMeses_fact_P()[c.getMes() - 1] += (c.getQuantidade() * c.getPreco());
            }

        } else {
            Cont_produto novo = new Cont_produto(c.getP().getCodigo_produto());

            if (c.getTipo() == 'N') {
                novo.setTot_vendasN(c.getQuantidade());
                novo.setValor_totalN(c.getPreco() * c.getQuantidade());
                novo.getMeses_vendas_N()[c.getMes() - 1] = c.getQuantidade();
                novo.getMeses_fact_N()[c.getMes() - 1] = (c.getQuantidade() * c.getPreco());

            } else {
                novo.setTot_vendasP(c.getQuantidade());
                novo.setValor_totalP(c.getPreco() * c.getQuantidade());
                novo.getMeses_vendas_P()[c.getMes() - 1] = c.getQuantidade();
                novo.getMeses_fact_P()[c.getMes() - 1] = (c.getQuantidade() * c.getPreco());
            }
            this.lista_produtos.put(c.getP().getCodigo_produto(), novo);
        }

    }

    public double facturacao_total() {
        double res = 0.0;
        for (Cont_produto cp : this.lista_produtos.values()) {
            res += cp.getValor_totalN() + cp.getValor_totalP();
        }
        return res;
    }
    /**
     * Devolve facturação total por mês
     * @return 
     */
    public ArrayList<String> devolve_facturacao_total_por_mes() {
        ArrayList<String> res = new ArrayList<>();
        double aux = 0.0;
        
        int i = 0;
        while (i < 12) {
            for (Map.Entry<String, Cont_produto> entrySet : lista_produtos.entrySet()) {

                Cont_produto value = entrySet.getValue();
                aux += value.getMeses_fact_N()[i] + value.getMeses_fact_P()[i];
            }

            res.add(i, i + 1 + " - " + String.valueOf(aux));
            aux = 0.0;
            i++;
        }

        return res;
    }
    /**
     * Devolve produtos não comprados
     * @return 
     */
    public TreeSet<String> devolve_produtos_nao_comprados() {
        TreeSet<String> res = new TreeSet<>();
        for (String produto : this.lista_produtos.keySet()) {
            if (this.lista_produtos.get(produto).getTot_VendasN() + this.lista_produtos.get(produto).getTot_VendasP() == 0) {
                res.add(produto);
            }
        }

        return res;
    }

    /**
     * Devolve total facturado de produto em certo mês
     * @param produto
     * @param mes
     * @return 
     */
    public double devolve_total_facturado_de_produto_de_mes(String produto, int mes) {
        double res = 0.0;
        if (this.lista_produtos.containsKey(produto)) {
            res += this.lista_produtos.get(produto).getMeses_fact_N()[mes - 1] + this.lista_produtos.get(produto).getMeses_fact_N()[mes - 1];
        }
        return res;
    }
    /**
     * Devolve total de vezes que produto foi comprado em Modo N
     * @param produto
     * @param mes
     * @return 
     */
    public int devolve_total_de_vezes_que_produto_foi_comprado_em_mes_MODO_N(String produto, int mes) {

        int res = 0;
        if (this.lista_produtos.containsKey(produto)) {
            res += this.lista_produtos.get(produto).getMeses_vendas_N()[mes - 1];
        }
        return res;
    }
    /**
     * Devolve total de vezes que produto foi comprado em Modo P
     * @param produto
     * @param mes
     * @return 
     */
    public int devolve_total_de_vezes_que_produto_foi_comprado_em_mes_MODO_P(String produto, int mes) {

        int res = 0;
        if (this.lista_produtos.containsKey(produto)) {
            res += this.lista_produtos.get(produto).getMeses_vendas_P()[mes - 1];
        }
        return res;
    }
    /**
     * Devolve o total facturado de um produto em modo N num certo mês
     * @param produto
     * @param mes
     * @return 
     */
    public double devolve_total_facturado_de_produto_de_mes_modo_N(String produto, int mes) {
        double res = 0.0;
        if (this.lista_produtos.containsKey(produto)) {
            res += this.lista_produtos.get(produto).getMeses_fact_N()[mes - 1];
        }
        return res;
    }
    /**
     * Devolve o total facturado de um produto em modo P num certo mês
     * @param produto
     * @param mes
     * @return double
     */
    public double devolve_total_facturado_de_produto_de_mes_modo_P(String produto, int mes) {
        double res = 0.0;
        if (this.lista_produtos.containsKey(produto)) {
            res += this.lista_produtos.get(produto).getMeses_fact_P()[mes - 1];
        }
        return res;
    }

}
