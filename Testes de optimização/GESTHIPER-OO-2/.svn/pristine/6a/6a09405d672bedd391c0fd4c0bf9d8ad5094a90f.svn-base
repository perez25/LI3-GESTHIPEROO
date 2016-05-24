/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.comparator;

import gesthiper.oo.TriploProdutoUnidadesVendidas_e_clientes_que_compraram;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Perez_25
 */
public class ComparatorTriploProdutoUnidadesCliente implements Comparator<TriploProdutoUnidadesVendidas_e_clientes_que_compraram>,Serializable {

    @Override
    public int compare(TriploProdutoUnidadesVendidas_e_clientes_que_compraram o, TriploProdutoUnidadesVendidas_e_clientes_que_compraram o2) {
        if(o.getUnidades_vendidas() > o2.getUnidades_vendidas()) return -1;
        else return 1;
    }

}
