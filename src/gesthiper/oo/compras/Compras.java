/**
 * Classe Compras Contêm relações do tipo Produto , Cliente e vice-versa
 *
 */
package gesthiper.oo.compras;

import gesthiper.oo.catalogos.Cliente;
import gesthiper.oo.ParClienteNumProdutosComprados;
import gesthiper.oo.ParProdutoNumVezesClienteComprou;
import gesthiper.oo.TriploClienteVezesQuefoiCompradoTotalGasto;
import gesthiper.oo.catalogos.Produto;
import gesthiper.oo.TriploProdutoUnidadesVendidas_e_clientes_que_compraram;
import gesthiper.oo.comparator.ComparatorParClienteNumProdutosComprados;
import gesthiper.oo.comparator.ComparatorTriploProdutoUnidadesCliente;
import gesthiper.oo.comparator.ComparatorParProdutoNumVezesClienteComprou;
import gesthiper.oo.comparator.ComparatorTriploClienteVezesQuefoiCOmpradoTotalGasto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author Perez_25
 */
public class Compras implements Serializable {

    private HashMap<String, Compras_Cliente_Mes> cliente_compras;
    private HashMap<String, HashMap<String, Cliente_Compras_a_Produto>> produto_compras;
    private int total_de_compras_com_valor_zero;

    public Compras() {
        this.cliente_compras = new HashMap<>(1000000);
        this.produto_compras = new HashMap<>(1000000);
    }

    public Compras(HashMap<String, HashMap<String, Cliente_Compras_a_Produto>> t) {
        this.produto_compras = new HashMap<>(1000000);
        for (Map.Entry<String, HashMap<String, Cliente_Compras_a_Produto>> entrySet : t.entrySet()) {
            String key = entrySet.getKey();
            HashMap<String, Cliente_Compras_a_Produto> value = entrySet.getValue();
            this.produto_compras.put(key, value);
        }
    }

    public Compras(Compras c) {
        this.cliente_compras = c.getCliente_compras();
        this.produto_compras = c.getProduto_compras();
        this.total_de_compras_com_valor_zero = c.getTotal_de_compras_com_valor_zero();

    }

    /**
     * Método responsável por inserir uma compra
     *
     * @param t
     */
    public void insert_compra(Compra t) {
        if (t.getPreco() == 0.0) {
            this.total_de_compras_com_valor_zero++;
        }
        if (this.getCliente_compras().containsKey(t.getCl().getCodigo_cliente())) {
            this.getCliente_compras().get(t.getCl().getCodigo_cliente()).getProdutos().add(t.getProd().getCodigo_produto());
            if (t.getModo() == 'N') {
                this.getCliente_compras().get(t.getCl().getCodigo_cliente()).getNumero_comprasN()[t.getMes() - 1]++;
            } else {
                this.getCliente_compras().get(t.getCl().getCodigo_cliente()).getNumero_comprasP()[t.getMes() - 1]++;
            }
            this.getCliente_compras().get(t.getCl().getCodigo_cliente()).getTotalgasto()[t.getMes() - 1] += t.getPreco() * t.getQuantidade();

        } else {

            Compras_Cliente_Mes c = new Compras_Cliente_Mes();
            c.getProdutos().add(t.getProd().getCodigo_produto());

            if (t.getModo() == 'N') {
                c.getNumero_comprasN()[t.getMes() - 1] = 1;
            } else {
                c.getNumero_comprasP()[t.getMes() - 1] = 1;

            }
            c.getTotalgasto()[t.getMes() - 1] += t.getQuantidade() * t.getPreco();

            this.getCliente_compras().put(t.getCl().getCodigo_cliente(), c);

        }
        if (this.getProduto_compras().containsKey(t.getProd().getCodigo_produto())) {
            if (this.getProduto_compras().get(t.getProd().getCodigo_produto()).containsKey((t.getCl().getCodigo_cliente()))) {
                if (t.getModo() == 'P') {
                    this.produto_compras.get(t.getProd().getCodigo_produto()).get(t.getCl().getCodigo_cliente()).add_quantidade_by_mes_modo_P(t.getMes() - 1, t.getQuantidade());
                    this.produto_compras.get(t.getProd().getCodigo_produto()).get(t.getCl().getCodigo_cliente()).add_compra_by_mes_modo_P(t.getMes() - 1);

                } else {
                    this.produto_compras.get(t.getProd().getCodigo_produto()).get(t.getCl().getCodigo_cliente()).add_quantidade_by_mes_modo_N(t.getMes() - 1, t.getQuantidade());
                    this.produto_compras.get(t.getProd().getCodigo_produto()).get(t.getCl().getCodigo_cliente()).add_compra_by_mes_modo_N(t.getMes() - 1);
                }
                this.produto_compras.get(t.getProd().getCodigo_produto()).get(t.getCl().getCodigo_cliente()).setTotal_gasto(this.produto_compras.get(t.getProd().getCodigo_produto()).get(t.getCl().getCodigo_cliente()).getTotal_gasto() + t.getPreco() * t.getQuantidade());
            } else {
                Cliente_Compras_a_Produto cc = new Cliente_Compras_a_Produto();
                if (t.getModo() == 'P') {
                    cc.add_quantidade_by_mes_modo_P(t.getMes() - 1, t.getQuantidade());

                    cc.add_compra_by_mes_modo_P(t.getMes() - 1);
                } else {
                    cc.add_quantidade_by_mes_modo_N(t.getMes() - 1, t.getQuantidade());

                    cc.add_compra_by_mes_modo_N(t.getMes() - 1);
                }
                cc.setTotal_gasto(cc.getTotal_gasto() + t.getQuantidade() * t.getPreco());
                this.produto_compras.get(t.getProd().getCodigo_produto()).put(t.getCl().getCodigo_cliente(), cc);
            }
        } else {
            HashMap<String, Cliente_Compras_a_Produto> novo = new HashMap<>();
            Cliente_Compras_a_Produto cc = new Cliente_Compras_a_Produto();
            if (t.getModo() == 'P') {
                cc.add_quantidade_by_mes_modo_P(t.getMes() - 1, t.getQuantidade());

                cc.add_compra_by_mes_modo_P(t.getMes() - 1);
            } else {
                cc.add_quantidade_by_mes_modo_P(t.getMes() - 1, t.getQuantidade());

                cc.add_compra_by_mes_modo_N(t.getMes() - 1);
            }
            cc.setTotal_gasto(cc.getTotal_gasto() + t.getQuantidade() * t.getPreco());
            novo.put(t.getCl().getCodigo_cliente(), cc);
            this.getProduto_compras().put(t.getProd().getCodigo_produto(), novo);

        }

    }

    @Override
    public Compras clone() {
        return new Compras(this);

    }

    public boolean equals(Compras t) {
        if (this == t) {
            return true;
        }
        if ((t == null) || (this.getClass() != t.getClass())) {
            return false;
        }
        Compras e = (Compras) t;
        for (String cc : e.cliente_compras.keySet()) {
            if (!this.cliente_compras.containsKey(cc)) {
                return false;
            } else {
                if (!this.cliente_compras.get(cc).equals(e.cliente_compras.get(cc))) {
                    return false;
                }
            }
        }

        for (String cc : e.produto_compras.keySet()) {
            if (!this.cliente_compras.containsKey(cc)) {
                return false;
            } else {
                for (String cp : this.produto_compras.get(cc).keySet()) {
                    if (!this.produto_compras.get(cc).containsKey(cp)) {
                        return false;
                    } else {
                        if (!this.produto_compras.get(cc).get(cp).equals(e.produto_compras.get(cc).get(cp))) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Módulo de Compras\n");
        s.append("Numero de Produtos existentes : ").append(this.getProduto_compras().size()).append("\n");

        return s.toString();
    }

    /**
     * @return the produto_compras
     */
    public HashMap<String, HashMap<String, Cliente_Compras_a_Produto>> getProduto_compras() {
        return produto_compras;
    }

    /**
     * @return the total_de_compras_com_valor_zero
     */
    public int getTotal_de_compras_com_valor_zero() {
        return total_de_compras_com_valor_zero;
    }

    public int getTotal_de_produtos_comprados() {
        return this.produto_compras.size();
    }

    public ArrayList<String> devolve_numero_compras_mes_a_mes() {
        ArrayList<String> res = new ArrayList<>();

        int aux = 0;
        int i = 0;
        while (i < 12) {
            for (HashMap<String, Cliente_Compras_a_Produto> cp : this.produto_compras.values()) {
                for (Cliente_Compras_a_Produto cl : cp.values()) {
                    aux += cl.getCompras_mes_P()[i] + cl.getCompras_mes_N()[i];
                }
            }
            res.add(i, i + 1 + " - " + String.valueOf(aux));
            aux = 0;
            i++;
        }
        return res;
    }

    public ArrayList<String> devolve_numero_clientes_mes_a_mes_que_compraram() {
        ArrayList<String> res = new ArrayList<>();
        int aux = 0;
        int mes = 0;
        while (mes < 12) {
            for (Compras_Cliente_Mes c : this.getCliente_compras().values()) {
                if (c.getNumero_comprasN()[mes] > 0 || c.getNumero_comprasP()[mes] > 0) {
                    aux++;
                }
            }
            res.add(mes, mes + " - " + aux);
            aux = 0;
            mes++;
        }
        return res;
    }

    public int getNumeroDeClientesQueCompraram() {
        return this.getCliente_compras().size();
    }

    /**
     * @return the cliente_compras
     */
    public HashMap<String, Compras_Cliente_Mes> getCliente_compras() {
        return cliente_compras;
    }

    /**
     * @param cliente_compras the cliente_compras to set
     */
    public void setCliente_compras(HashMap<String, Compras_Cliente_Mes> cliente_compras) {
        this.cliente_compras = cliente_compras;
    }

    public int getNumero_Compras_By_Mes(int mes) {
        int res = 0;
        for (Compras_Cliente_Mes c : this.cliente_compras.values()) {
            res += c.getNumero_comprasN()[mes] + c.getNumero_comprasP()[mes];
        }
        return res;
    }

    public TreeSet<String> getClientes_que_compraram_em_certo_mes(int mes) {
        TreeSet<String> res = new TreeSet<>();
        for (String s : this.cliente_compras.keySet()) {
            Compras_Cliente_Mes c = this.cliente_compras.get(s);
            if (c.getNumero_comprasN()[mes] > 0 || c.getNumero_comprasP()[mes] > 0) {
                res.add(s);
            }
        }
        return res;
    }

    public int get_numero_de_vezes_que_compraramProduto_modo_N_by_mes(String produto, int mes) {
        int res = 0;
        for (String k : this.produto_compras.get(produto).keySet()) {
            res += this.produto_compras.get(produto).get(k).getCompras_mes_N()[mes - 1];
        }
        return res;
    }

    public int get_numero_de_vezes_que_compraramProduto_modo_P_by_mes(String produto, int mes) {
        int res = 0;
        for (String k : this.produto_compras.get(produto).keySet()) {
            res += this.produto_compras.get(produto).get(k).getCompras_mes_P()[mes - 1];
        }
        return res;
    }

    public int getNumero_Compras_cliente_mes(String c, int mes) {
        if (this.cliente_compras.containsKey(c)) {

            Compras_Cliente_Mes cm = this.cliente_compras.get(c);
            return cm.getNumero_comprasN()[mes - 1] + cm.getNumero_comprasP()[mes - 1];
        }
        return 0;
    }

    public int getNumero_Produtos_cliente_mes(String c, int mes) {
        int res = 0;
        if (this.cliente_compras.containsKey(c)) {
            for (HashMap<String, Cliente_Compras_a_Produto> cl : this.produto_compras.values()) {
                if (cl.containsKey(c)) {
                    if (cl.get(c).getCompras_mes_N()[mes - 1] > 0 || cl.get(c).getCompras_mes_P()[mes - 1] > 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public double devolve_quantia_gasta_por_cliente_mes(String c, int mes) {
        double res = 0.0;
        if (this.cliente_compras.containsKey(c)) {
            res += this.cliente_compras.get(c).getTotalgasto()[mes - 1];
        }

        return res;
    }

    public int devolve_numero_de_vezes_que_produto_foi_comprado_mes(String produto, int mes) {
        int res = 0;
        if (this.produto_compras.containsKey(produto)) {
            HashMap<String, Cliente_Compras_a_Produto> cp = this.produto_compras.get(produto);
            for (Cliente_Compras_a_Produto c : cp.values()) {
                res += c.getCompras_mes_N()[mes - 1] + c.getCompras_mes_P()[mes - 1];
            }
        }

        return res;
    }

    public int devolve_numero_de_clientes_que_compraram_produto_em_certo_mes(String produto, int mes) {
        int res = 0;
        if (this.produto_compras.containsKey(produto)) {
            HashMap<String, Cliente_Compras_a_Produto> cp = this.produto_compras.get(produto);
            for (Cliente_Compras_a_Produto c : cp.values()) {
                if (c.getCompras_mes_N()[mes - 1] > 0 || c.getCompras_mes_P()[mes - 1] > 0) {
                    res++;
                }
            }
        }

        return res;
    }

    public TreeSet<ParProdutoNumVezesClienteComprou> devolve_produtos_mais_comprados_por_cliente(String c) {
        TreeSet<ParProdutoNumVezesClienteComprou> res = new TreeSet<>(new ComparatorParProdutoNumVezesClienteComprou());
        if (this.cliente_compras.containsKey(c)) {

            for (String produto : this.produto_compras.keySet()) {
                if (this.produto_compras.get(produto).containsKey(c)) {

                    ParProdutoNumVezesClienteComprou n = new ParProdutoNumVezesClienteComprou();
                    Produto novo = new Produto(produto);
                    n.setProduto(novo);
                    n.setVezes_que_cliente_comprou(this.produto_compras.get(produto).get(c).devolve_quantidade_comprada());
                    res.add(n);
                }
            }
        }
        return res;
    }

    public TreeSet<TriploProdutoUnidadesVendidas_e_clientes_que_compraram> devolve_lista_produtos_mais_vendidos(int N) {
        TreeSet<TriploProdutoUnidadesVendidas_e_clientes_que_compraram> res = new TreeSet<>(new ComparatorTriploProdutoUnidadesCliente());
        int unidades = 0;
        int clientes = 0;
        for (String produto : this.produto_compras.keySet()) {
            Produto novo = new Produto(produto);
            unidades = 0;
            clientes = 0;
            for (Cliente_Compras_a_Produto cp : this.produto_compras.get(produto).values()) {
                unidades += cp.devolve_quantidade_comprada();
                clientes++;
            }
            TriploProdutoUnidadesVendidas_e_clientes_que_compraram novo1 = new TriploProdutoUnidadesVendidas_e_clientes_que_compraram();
            novo1.setProduto(novo);
            novo1.setClientes_que_compraram(clientes);
            novo1.setUnidades_vendidas(unidades);
            res.add(novo1);
        }

        return res;
    }

    public TreeSet<ParClienteNumProdutosComprados> devolve_lista_de_clientes_que_compraram_mais_produtos() {
        TreeSet<ParClienteNumProdutosComprados> res;
        res = new TreeSet<>(new ComparatorParClienteNumProdutosComprados());
        for (String k : this.cliente_compras.keySet()) {
            int cont = this.cliente_compras.get(k).getProdutos().size();
            Cliente c = new Cliente(k);
            ParClienteNumProdutosComprados n = new ParClienteNumProdutosComprados(c, cont);
            res.add(n);
        }
        return res;

    }

    public TreeSet<TriploClienteVezesQuefoiCompradoTotalGasto> devolve_lista_de_clientes_que_mais_compraram_produto(String produto) {
        TreeSet<TriploClienteVezesQuefoiCompradoTotalGasto> res = new TreeSet<>(new ComparatorTriploClienteVezesQuefoiCOmpradoTotalGasto());
        if (this.produto_compras.containsKey(produto)) {
            HashMap<String, Cliente_Compras_a_Produto> cp = this.produto_compras.get(produto);
            for (String cl : cp.keySet()) {
                int vezes_comprado = 0;
                double total_gasto = cp.get(cl).getTotal_gasto();
                for (int i = 0; i < 12; i++) {
                    vezes_comprado += cp.get(cl).getCompras_mes_N()[i] + cp.get(cl).getCompras_mes_P()[i];
                }
                TriploClienteVezesQuefoiCompradoTotalGasto novo = new TriploClienteVezesQuefoiCompradoTotalGasto(cl, vezes_comprado, total_gasto);
                res.add(novo);
            }
        }
        return res;
    }
}
