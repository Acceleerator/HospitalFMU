/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kino
 */
public final class conexao {
    private conexao (){
        PreparedStatement pstmt = null;
    }
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado.");
            System.exit(-1);
        }
    }
    
    public static Connection getConnection(){
        Connection conn = null;
        
        // Editar user e senha de acordo com o MySQL
        String URL = "jdbc:mysql://localhost/hospitalfmu";
        String user = "root";
        String senha = "12345";
        
        
        try {
            conn = DriverManager.getConnection(URL, user, senha);
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar: " + ex.getMessage());
        }
        return conn;
    }
}
