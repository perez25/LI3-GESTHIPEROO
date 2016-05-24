/**
 * Classe Responsável por guardar todas as estruturas. Nesta classe estão
 * contidas, as classes Compras,Contabilidade e os catálogos
 */
package gesthiper.oo;

import gesthiper.oo.catalogos.Produto;
import gesthiper.oo.catalogos.Cliente;
import gesthiper.oo.catalogos.Catalogo_produtos;
import gesthiper.oo.catalogos.Catalogo_clientes;
import gesthiper.oo.menu.Crono;
import gesthiper.oo.contabilidade.Unidade_Contabilidade;
import gesthiper.oo.compras.Compra;
import gesthiper.oo.contabilidade.Contabilidade;
import gesthiper.oo.compras.Compras;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 *
 * @author Perez_25
 */
public class Hipermercado implements Serializable {

    private Contabilidade cont;
    private Compras compras;
    private Catalogo_clientes ct_clientes;
    private Catalogo_produtos ct_produtos;
    private Dados_leitura dados_leitura;
    
    
    /**
     * Construtor Vazio
     */
    public Hipermercado() {
        this.ct_clientes = new Catalogo_clientes();
        this.ct_produtos = new Catalogo_produtos();
        this.compras = new Compras();
        this.cont = new Contabilidade();
        this.dados_leitura = new Dados_leitura();
    }

    private Hipermercado(Hipermercado hp) {
        this.compras = hp.getCompras();
        this.cont = hp.getCont();
        this.ct_clientes = hp.getCt_clientes();
        this.ct_produtos = hp.getCt_produtos();
        this.dados_leitura = hp.getDados_leitura();

    }

    public Compras getCompras() {
        return this.compras.clone();
    }

    public Contabilidade getCont() {
        return this.cont.clone();
    }

    /**
     *
     * @return Catalogo_Clientes
     */
    public Catalogo_clientes getCt_clientes() {
        return ct_clientes.clone();
    }

    /**
     *
     * @return Catalogo_produtos
     */
    public Catalogo_produtos getCt_produtos() {
        return ct_produtos.clone();
    }

    public void setCont(Contabilidade cont) {
        this.cont = cont.clone();
    }

    public void setCompras(Compras compras) {
        this.compras = compras.clone();
    }

    public void setCt_clientes(Catalogo_clientes ct_clientes) {
        this.ct_clientes = ct_clientes.clone();
    }

    public void setCt_produtos(Catalogo_produtos ct_produtos) {
        this.ct_produtos = ct_produtos.clone();
    }

    public void setDados_leitura(Dados_leitura dados_leitura) {
        this.dados_leitura = dados_leitura.clone();
    }

    /**
     * Responsável pela leitura do ficheiro de produtos
     *
     * @param ficheiro
     * @return
     */
    private Hipermercado le_produtos(String ficheiro) {
       
        String path = "/Users/Perez_25/Desktop/GESTHIPER-OO/src/gesthiper/oo/";

        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(path + ficheiro));

            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                Produto n = new Produto(sCurrentLine);
                this.ct_produtos.insert_produto(n);
                this.cont.init_cont_PROD(n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return this;
    }

    /**
     * Responsável pela leitura do ficheiro de clientes
     *
     * @param ficheiro
     * @return
     */
    private Hipermercado le_clientes(String ficheiro) {
        String path = "/Users/Perez_25/Desktop/GESTHIPER-OO/src/gesthiper/oo/";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(path + ficheiro));

            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                Cliente n = new Cliente(sCurrentLine);
                this.ct_clientes.insert_cliente(n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return this;
    }

    /**
     * Responsável pela leitura do ficheiro de compras
     *
     * @param ficheiro
     * @return
     */
    private Hipermercado le_compras(String ficheiro) {
        String path = "/Users/Perez_25/Desktop/GESTHIPER-OO/src/gesthiper/oo/";

        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(path + ficheiro));

            while ((sCurrentLine = br.readLine()) != null) {

                String[] parts = sCurrentLine.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                Produto p = new Produto(parts[0]);
                Cliente c = new Cliente(parts[4]);

                if (this.ct_clientes.existeCliente(c) && this.ct_produtos.existeProduto(p)) {
                    this.dados_leitura.setTotal_compras(this.dados_leitura.getTotal_compras() + 1);

                    Unidade_Contabilidade uc = new Unidade_Contabilidade(p, Double.valueOf(parts[1]), parts[3].charAt(0),
                            Integer.valueOf(parts[5]), Integer.valueOf(parts[2]));
                    Compra n = new Compra(p, c, Integer.valueOf(parts[5]), Double.valueOf(parts[1]),
                            parts[3].charAt(0), Integer.valueOf(parts[2]));

                    this.cont.insert_cont(uc);
                    this.compras.insert_compra(n);
                } else {
                    this.dados_leitura.getLinhas_invalidas().add(sCurrentLine);
                    this.dados_leitura.setTotal_compras_invalidas(this.dados_leitura.getTotal_compras_invalidas() + 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return this;
    }

    /**
     * Devolve o numero total de produtos inseridos
     *
     * @return
     */
    private int devolve_numero_total_de_produtos() {
        return this.ct_produtos.getNumerodeProdutos();
    }

    /**
     * Devolve o numero total de clientes inseridos
     *
     * @return
     */
    private int devolve_numero_total_de_clientes() {
        return this.ct_clientes.getNumerodeClientes();
    }

    private int devolve_numero_total_de_clientes_que_nao_compraram() {
        return abs(this.devolve_numero_total_de_clientes() - this.devolve_numero_total_de_clientes_que_compraram());
    }

    private int devolve_numero_total_de_compras_com_valor_zero() {
        return this.compras.getTotal_de_compras_com_valor_zero();
    }

    private int devolve_numero_total_de_produtos_comprados() {
        return this.compras.getTotal_de_produtos_comprados();
    }

    private int devolve_numero_total_de_produtos_nao_comprados() {
        return this.devolve_numero_total_de_produtos() - this.devolve_numero_total_de_produtos_comprados();
    }

    private int devolve_numero_total_de_clientes_que_compraram() {
        return this.compras.getNumeroDeClientesQueCompraram();
    }

    private double devolve_facturacao_total() {
        return this.cont.facturacao_total();
    }

    /**
     * Responsável pelo tratamento dos ficheiros
     *
     * @param produtos
     * @param clientes
     * @param compras
     * @return
     */
    public boolean leitura_de_ficheiros(String produtos, String clientes, String compras) {
        if (this.dados_leitura.getVezes_lido() == 1) {
            this.compras.getCliente_compras().clear();
            this.compras.getProduto_compras().clear();
            this.cont.getListaProdutos().clear();
            this.dados_leitura = new Dados_leitura();
            this.ct_clientes.getListaClientes().clear();
            this.ct_clientes.getListaClientes().clear();
        }
        Crono c = new Crono();
        this.dados_leitura.setFicheiro_clientes(clientes);
        this.dados_leitura.setFicheiro_produtos(produtos);
        this.dados_leitura.setFicheiro_compras(compras);
        c.start();
        this.le_produtos(produtos);
        c.stop();
        System.out.println("Demorou " + c.print() + " a ler o ficheiro" + produtos);
        c.start();
        this.le_clientes(clientes);
        c.stop();
        System.out.println("Demorou " + c.print() + " a ler o ficheiro" + clientes);
        c.start();
        this.le_compras(compras);
        c.stop();
        System.out.println("Demorou " + c.print() + " a ler o ficheiro" + compras);
        this.dados_leitura.setVezes_lido(1);
        this.dados_leitura.setTotal_clientes_que_compraram(this.devolve_numero_total_de_clientes_que_compraram());
        this.dados_leitura.setTotal_produtos(this.devolve_numero_total_de_produtos());
        this.dados_leitura.setTotal_clientes(this.devolve_numero_total_de_clientes());
        this.dados_leitura.setTotal_clientes_que_compraram(this.devolve_numero_total_de_clientes_que_compraram());
        this.dados_leitura.setTotal_clientes_que_nao_compraram(this.devolve_numero_total_de_clientes_que_nao_compraram());
        this.dados_leitura.setTotal_compras_valor_zero(this.devolve_numero_total_de_compras_com_valor_zero());
        this.dados_leitura.setTotal_produtos_diferentes_comprados(this.devolve_numero_total_de_produtos_comprados());
        this.dados_leitura.setTotal_produtos_nao_comprados(this.devolve_numero_total_de_produtos_nao_comprados());
        this.dados_leitura.setFacturacao_total(this.devolve_facturacao_total());
        return true;
    }

    /**
     * Devolve os dados de leitura
     *
     * @return Dados_leitura
     */
    public Dados_leitura getDados_leitura() {
        return this.dados_leitura.clone();
    }

    /**
     * insere uma compra
     *
     * @param t
     */
    public void insert_compra(Compra t) {
        this.compras.insert_compra(t);
    }

    /**
     * insere uma Unidade de Contabilidade
     *
     * @param t
     */
    public void insert_unidade_contabilidade(Unidade_Contabilidade t) {
        this.cont.insert_cont(t);
    }

    /**
     * Insere um produto no catálogo de produtos
     *
     * @param p
     */
    public void insert_produto_catalogo(Produto p) {
        this.ct_produtos.insert_produto(p);
    }

    /**
     * insere um cliente no catálogo de clientes
     *
     * @param c
     */
    public void insert_cliente_catalogo(Cliente c) {
        this.ct_clientes.insert_cliente(c);
    }

    /**
     * Verifica se um produto existe no catálogo de produtos
     *
     * @param t
     * @return
     */
    public boolean existe_produto_catalogo_produtos(Produto t) {
        return this.ct_produtos.existeProduto(t);
    }

    /**
     * Método que devolve as compras efectuadas mês a mês
     *
     * @return ArrayList de Strings
     */
    public ArrayList<String> devolve_compras_mes_a_mes() {
        return this.compras.devolve_numero_compras_mes_a_mes();
    }

    /**
     * Método que devolve a facturação total por mês
     *
     * @return ArrayList de Strings
     */
    public ArrayList<String> devolve_facturacao_total_por_mes() {
        return this.cont.devolve_facturacao_total_por_mes();

    }

    /**
     * Devolve o numero total de clientes que compraram mês a mês
     *
     * @return ArrayList de Strings
     */
    public ArrayList<String> devolve_clientes_que_compraram_mes() {
        return this.compras.devolve_numero_clientes_mes_a_mes_que_compraram();
    }

    /**
     * Devolve a lista de Produtos não comprados
     *
     * @return TreeSet de Strings
     */
    public TreeSet<String> devolve_lista_de_produtos_nao_comprados() {
        return this.cont.devolve_produtos_nao_comprados();

    }

    /**
     * Carrega uma ObjectStream
     *
     * @param nomeFicheiro
     * @return
     */
    public Hipermercado load_hipermercado(String nomeFicheiro) {
        Hipermercado res = new Hipermercado();
        String path = "/Users/Perez_25/Desktop/GESTHIPER-OO/src/gesthiper/oo/";
        try {
            try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(path + nomeFicheiro + ".obj")))) {
                res = (Hipermercado) input.readObject();
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());

        } catch (ClassNotFoundException | IOException e) {
            System.err.println(e.getMessage());

        }

        return res;
    }

    /**
     *
     * @param hiper
     * @param nomeFicheiro
     * @return
     */
    public boolean gravar_hipermercado(Hipermercado hiper, String nomeFicheiro) {
        String path = "/Users/Perez_25/Desktop/GESTHIPER-OO/src/gesthiper/oo/";
        boolean sucesso = false;

        while (!sucesso) {

            try {

                ObjectOutputStream oos = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(path + nomeFicheiro + ".obj")));
                oos.writeObject(hiper);
                oos.flush();
                oos.close();
                sucesso = true;
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();

            } catch (IOException e) {

                System.err.println(e.getMessage());
                e.printStackTrace();

            }
        }

        return true;
    }

    /**
     * Devolve a lista de clientes que não compraram
     *
     * @return
     */
    public TreeSet<String> devolve_lista_de_clientes_que_nao_compraram() {
        TreeSet<String> res = new TreeSet<>();
        HashMap<String, Cliente> lista_clientes = this.ct_clientes.getListaClientes();
        for (String cli : lista_clientes.keySet()) {
            if (!(this.compras.getCliente_compras().containsKey(cli))) {
                res.add(cli);
            }

        }

        return res;
    }

    /**
     * Devolve o numero de compras num certo mês
     *
     * @param mes
     * @return
     */
    public int devolve_numero_de_compras_em_certo_mes(int mes) {
        return this.compras.getNumero_Compras_By_Mes(mes - 1);
    }

    public TreeSet<String> devolve_lista_de_clientes_que_compraram_em_certo_mes(int mes) {
        return this.compras.getClientes_que_compraram_em_certo_mes(mes - 1);
    }

    public int devolve_numero_compras_mes_by_cliente(String cliente, int mes) {
        return this.compras.getNumero_Compras_cliente_mes(cliente, mes);
    }

    /**
     * Devolve o numero de produtos comprados por cliente em certo mês
     *
     * @param cliente
     * @param mes
     * @return
     */
    public int devolve_numero_produtos_comprados_by_cliente_mes(String cliente, int mes) {
        return this.compras.getNumero_Produtos_cliente_mes(cliente, mes);
    }

    /**
     * @param cliente
     * @param mes
     * @return
     */
    public double devolve_quantia_gasta_por_mes_by_cliente(String cliente, int mes) {
        return this.compras.devolve_quantia_gasta_por_cliente_mes(cliente, mes);
    }

    /**
     *
     * @param cliente
     * @return double
     */
    public double devolve_quantia_total_gasta_por_cliente(String cliente) {
        double res = 0.0;
        int i = 1;
        while (i <= 12) {
            res += devolve_quantia_gasta_por_mes_by_cliente(cliente, i);
            i++;
        }

        return res;
    }

    /**
     *
     * @param produto
     * @param mes
     * @return
     */
    public int devolve_numero_de_vezes_que_produto_foi_comprado_by_mes(String produto, int mes) {
        return this.compras.devolve_numero_de_vezes_que_produto_foi_comprado_mes(produto, mes);

    }

    /**
     *
     * @param produto
     * @param mes
     * @return Devolve o numero de clientes que compram um certo produto num dado mês
     */
    public int devolve_numero_de_clientes_que_compraram_produto_em_mes(String produto, int mes) {
        return this.compras.devolve_numero_de_clientes_que_compraram_produto_em_certo_mes(produto, mes);
    }

    /**
     *
     * @param mes
     * @param produto
     * @return double
     */
    public double devolve_total_facturado_por_produto_em_mes(int mes, String produto) {
        return this.cont.devolve_total_facturado_de_produto_de_mes(produto, mes);
    }

    /**
     *
     * @param produto
     * @param mes
     * @return double
     */
    public double devolve_total_facturado_por_produto_em_mes_modo_N(String produto, int mes) {
        return this.cont.devolve_total_facturado_de_produto_de_mes_modo_N(produto, mes);
    }

    /**
     *
     * @param produto
     * @param mes
     * @return
     */
    public double devolve_total_facturado_por_produto_em_mes_modo_P(String produto, int mes) {
        return this.cont.devolve_total_facturado_de_produto_de_mes_modo_P(produto, mes);
    }

    /**
     *  Devolve o numero de vezes que um produto foi comprado num mes em MODO N
     * @param produto
     * @param mes
     * @return
     */
    public int devolve_total_vezes_que_produto_foi_comprado_modo_N_em_mes(String produto, int mes) {
      return this.compras.get_numero_de_vezes_que_compraramProduto_modo_N_by_mes(produto, mes);
    }

    /**
     * Devolve o numero de vezes que um produto foi comprado num mes em MODO P
     * @param produto
     * @param mes
     * @return
     */
    public int devolve_total_vezes_que_produto_foi_comprado_modo_P_em_mes(String produto, int mes) {
        return this.compras.get_numero_de_vezes_que_compraramProduto_modo_P_by_mes(produto, mes);
    }

    /**
     * Devolve a lista dos produtos mais comprados por um dado cliente
     * @param cliente
     * @return TreeSet de ParProdutoNumVezesClienteComprou
     */
    public TreeSet<ParProdutoNumVezesClienteComprou> devolve_lista_de_produtos_mais_comprados_por_cliente(String cliente) {
        return this.compras.devolve_produtos_mais_comprados_por_cliente(cliente);
    }

    /**
     * Devolve a lista dos produtos mais vendidos
     * @param N
     * @return TreeSet
     */
    public TreeSet<TriploProdutoUnidadesVendidas_e_clientes_que_compraram> devolve_lista_de_X_produtos_mais_vendidos(int N) {
        return this.compras.devolve_lista_produtos_mais_vendidos(N);
    }

    /**
     * Devolve pares Cliente,NumProdutosComprados
     *
     * @return TreeSet de ParClienteNumProdutosComprados
     */
    public TreeSet<ParClienteNumProdutosComprados> devolve_lista_de_X_clientes_que_compraram_mais() {
        return this.compras.devolve_lista_de_clientes_que_compraram_mais_produtos();
    }

    /**
     * Devolve triplos Cliente,Vezes_que_comprou_produto,total_gasto
     *
     * @param produto
     * @return TreeSet ParClienteNumProdutosComprados
     */
    public TreeSet<TriploClienteVezesQuefoiCompradoTotalGasto> devolve_lista_de_clientes_que_mais_compraram_produto(String produto) {
        return this.compras.devolve_lista_de_clientes_que_mais_compraram_produto(produto);
    }

    public boolean equals(Hipermercado hp) {
        if (this == hp) {
            return true;
        }
        if ((hp == null) || (this.getClass() != hp.getClass())) {
            return false;
        }
        Hipermercado h = (Hipermercado) hp;
        return (this.compras.equals(h.getCompras()) && this.cont.equals(h.getCont()) && this.ct_clientes.equals(h.getCt_clientes()) && this.ct_produtos.equals(h.getCt_produtos()) && this.dados_leitura.equals(h.getDados_leitura()));
    }
    /**
     * Guarda as linhas inválidas
     * @param ficheiro
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void guardar_linhas_invalidas(String ficheiro) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter writer = new PrintWriter(ficheiro, "UTF-8")) {
            for(String s :this.dados_leitura.getLinhas_invalidas()){
                writer.println(s);
            }
        }

    }

    @Override
    public Hipermercado clone() {
        return new Hipermercado(this);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Hipermercado : ");
        s.append(this.compras.toString()).append("\n");
        s.append(this.cont.toString()).append("\n");
        s.append(this.ct_clientes.toString()).append("\n");
        s.append(this.ct_produtos.toString()).append("\n");
        s.append(this.dados_leitura.toString()).append("\n");
        return s.toString();
    }
}
