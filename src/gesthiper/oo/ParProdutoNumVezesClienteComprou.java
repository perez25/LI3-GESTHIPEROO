/**
 * Par que guarda o produto e as vezes que o cliente comprou este
 */
package gesthiper.oo;

import gesthiper.oo.catalogos.Produto;

/**
 *
 * @author Perez_25
 */
public class ParProdutoNumVezesClienteComprou {

    private Produto produto;
    private int vezes_que_cliente_comprou;

    public ParProdutoNumVezesClienteComprou() {
        this.produto = new Produto();
        this.vezes_que_cliente_comprou = 0;
    }

    private ParProdutoNumVezesClienteComprou(ParProdutoNumVezesClienteComprou p) {
    this.produto = p.getProduto();
    this.vezes_que_cliente_comprou = p.getVezes_que_cliente_comprou();
    
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
     * @return the vezes_que_cliente_comprou
     */
    public int getVezes_que_cliente_comprou() {
        return vezes_que_cliente_comprou;
    }

    /**
     * @param vezes_que_cliente_comprou the vezes_que_cliente_comprou to set
     */
    public void setVezes_que_cliente_comprou(int vezes_que_cliente_comprou) {
        this.vezes_que_cliente_comprou = vezes_que_cliente_comprou;
    }

    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("O cliente comprou o produto ");
        s.append(this.produto);
        s.append(" ");
        s.append(this.vezes_que_cliente_comprou);
        s.append(" vezes \n");
        
        return s.toString();
    }
    
    public ParProdutoNumVezesClienteComprou clone() {
        return new ParProdutoNumVezesClienteComprou(this);
    }
    
    public boolean equals(ParProdutoNumVezesClienteComprou c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        ParProdutoNumVezesClienteComprou p = (ParProdutoNumVezesClienteComprou) c;
        return (this.produto.equals(p.getProduto()) && this.vezes_que_cliente_comprou == p.getVezes_que_cliente_comprou());
    }
}
