/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesthiper.oo.comparator;

import gesthiper.oo.TriploClienteVezesQuefoiCompradoTotalGasto;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Perez_25
 */
public class ComparatorTriploClienteVezesQuefoiCOmpradoTotalGasto implements Comparator<TriploClienteVezesQuefoiCompradoTotalGasto>,Serializable{

    @Override
    public int compare(TriploClienteVezesQuefoiCompradoTotalGasto o1, TriploClienteVezesQuefoiCompradoTotalGasto o2) {
    
        if (o1.getVezes_comprado() > o2.getVezes_comprado()) {
            return -1;
        }
        if (o1.getVezes_comprado() < o2.getVezes_comprado()) {
            return 1;
        }
        return o1.getCliente().getCodigo_cliente().compareTo(o2.getCliente().getCodigo_cliente());
         
    
    }
    
}
