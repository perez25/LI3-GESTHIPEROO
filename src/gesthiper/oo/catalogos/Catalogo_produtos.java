 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.catalogos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Perez_25
 */
public class Catalogo_produtos implements Serializable {

    private Map<String, Produto> lista_produtos;

    public Catalogo_produtos() {
        this.lista_produtos = new HashMap<>(200000);
    }

    public Catalogo_produtos(Map<String, Produto> lista_produtos) {
        this.lista_produtos = new HashMap<>(200000);
        for (Produto p : lista_produtos.values()) {
            lista_produtos.put(p.getCodigo_produto(), (Produto) p.clone());
        }
    }
    
    public Catalogo_produtos(Catalogo_produtos cp) {
        this.lista_produtos = cp.getListaProdutos();
    }

    public Map<String, Produto> getListaProdutos() {
        Map<String, Produto> res = new HashMap<>();
        for (Produto p : this.lista_produtos.values()) {
            res.put(p.getCodigo_produto(), p.clone());
        }
        return res;
    }

    public boolean existeProduto(Produto p) {
      
        return (this.lista_produtos.containsKey(p.getCodigo_produto().trim()));
    }

    public int getNumerodeProdutos() {
        return this.lista_produtos.size();
    }

    public void setLista_produtos(Map<String, Produto> lista_produtos) {
        for (Map.Entry<String, Produto> entrySet : lista_produtos.entrySet()) {
            String key = entrySet.getKey();
            Produto value = entrySet.getValue();
            
        }
    }
    
    

    public Catalogo_produtos clone() {
        return new Catalogo_produtos(this);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Produto p : this.lista_produtos.values()) {
            s.append("Produto : ");
            s.append(p.getCodigo_produto()).append("\n");
        }
        return s.toString();
    }

    public void insert_produto(Produto t) {
        this.lista_produtos.put(t.getCodigo_produto(), t.clone());
    }
    
    public boolean equals(Catalogo_produtos c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        Catalogo_produtos e = (Catalogo_produtos) c;
        Iterator it=this.lista_produtos.keySet().iterator();
        boolean equals=true;
        boolean end=true;
        Map<String,Produto> aux= c.getListaProdutos();
        while(aux.containsKey(it)  && equals && end)
        {
            if(!this.lista_produtos.get(it).equals(aux.get(it)))
                 equals=false;
            if(!it.hasNext()){
                end=false;
            }
            it.next();
        }
        return equals;
    }
}
