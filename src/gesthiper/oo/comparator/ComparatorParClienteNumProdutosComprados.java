/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.comparator;

import gesthiper.oo.ParClienteNumProdutosComprados;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Perez_25
 */
public class ComparatorParClienteNumProdutosComprados implements Comparator<ParClienteNumProdutosComprados>, Serializable {

    @Override
    public int compare(ParClienteNumProdutosComprados o1, ParClienteNumProdutosComprados o2) {
        if (o1.getProdutos_comprados() > o2.getProdutos_comprados()) {
            return -1;
        }
        if (o1.getProdutos_comprados() < o2.getProdutos_comprados()) {
            return 1;
        }
        return o1.getCliente().getCodigo_cliente().compareTo(o2.getCliente().getCodigo_cliente());
    }
}
