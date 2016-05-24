/**
 * Classe main do programa
 */
package gesthiper.oo;

import gesthiper.oo.menu.Menu;
import java.io.IOException;

/**
 *
 * @author Perez_25
 */
public class GESTHIPEROO {
 /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Hipermercado hiper = new Hipermercado();
        
        Menu menu = new Menu(hiper);
        menu.menu_principal();
        
        
       
    }
}

