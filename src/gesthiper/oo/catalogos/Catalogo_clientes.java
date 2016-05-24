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
public class Catalogo_clientes  implements Serializable{

    private HashMap<String, Cliente> lista_clientes;

    public Catalogo_clientes() {
        this.lista_clientes = new HashMap<>(200000);
    }

    public Catalogo_clientes(Map<String, Cliente> lista_clientes) {
        this.lista_clientes = new HashMap<>(200000);
        for (Cliente cl : lista_clientes.values()) {
            lista_clientes.put(cl.getCodigo_cliente(), (Cliente) cl.clone());
        }
    }

    public Catalogo_clientes(Catalogo_clientes cc) {
        this.lista_clientes = cc.getListaClientes();
    }

    public HashMap<String, Cliente> getListaClientes() {
        HashMap<String, Cliente> res = new HashMap<>();
        for (Cliente cl : this.lista_clientes.values()) {
            res.put(cl.getCodigo_cliente(), cl.clone());
        }
        return res;
    }

    public int getNumerodeClientes() {
        return this.lista_clientes.size();
    }

    public void setLista_clientes(Map<String, Cliente> lista_clientes) {
        for (Map.Entry<String, Cliente> entrySet : lista_clientes.entrySet()) {
            String key = entrySet.getKey();
            Cliente value = entrySet.getValue();
            
        }
    }
    
    

    public boolean existeCliente(Cliente c) {
        
        return (this.lista_clientes.containsKey(c.getCodigo_cliente().trim()));
    }

    @Override
    public Catalogo_clientes clone() {
        return new Catalogo_clientes(this);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Cliente cl : this.lista_clientes.values()) {
            s.append("Cliente : ");
            s.append(cl.getCodigo_cliente()).append("\n");
        }
        return s.toString();
    }

    public void insert_cliente(Cliente t) {
        this.lista_clientes.put(t.getCodigo_cliente(), t.clone());
    }
    
    
    public boolean equals(Catalogo_clientes c) {
        if (this == c) {
            return true;
        }
        if ((c == null) || (this.getClass() != c.getClass())) {
            return false;
        }
        Catalogo_clientes e = (Catalogo_clientes) c;
        Iterator it=this.lista_clientes.keySet().iterator();
        boolean equals=true;
        boolean end=true;
        HashMap<String,Cliente> aux= c.getListaClientes();
        while(aux.containsKey(it)  && equals && end)
        {
            if(!this.lista_clientes.get(it).equals(aux.get(it)))
                 equals=false;
            if(!it.hasNext()){
                end=false;
            }
            it.next();
        }
        return equals;
    }
}
