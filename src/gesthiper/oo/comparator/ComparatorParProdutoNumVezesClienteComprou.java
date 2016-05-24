/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.comparator;

import gesthiper.oo.ParProdutoNumVezesClienteComprou;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Perez_25
 */
public class ComparatorParProdutoNumVezesClienteComprou implements Comparator<ParProdutoNumVezesClienteComprou>, Serializable {

    @Override
    public int compare(ParProdutoNumVezesClienteComprou o1, ParProdutoNumVezesClienteComprou o2) {
        if (o1.getVezes_que_cliente_comprou() > o2.getVezes_que_cliente_comprou()) {
            return -1;
        }
        if (o1.getVezes_que_cliente_comprou() < o2.getVezes_que_cliente_comprou()) {
            return 1;
        }
        return o1.getProduto().getCodigo_produto().compareTo(o2.getProduto().getCodigo_produto());
     }

}
