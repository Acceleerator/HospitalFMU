/*
 * To change this license header, choose License Headers in Project Properties.x'
 * and open the template in the editor.
 */
package hospitalfmu;

import java.sql.SQLException;
import javax.swing.UIManager;
import visualMenus.login;

/**
 *
 * @author Kino
 */
public class hospitalFMU {
    /**
     * @param args the command line arguments
     */
    public static String id_pesquisa;
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        login telaLogin = new login();
        telaLogin.setVisible(true);
    }
    
}
