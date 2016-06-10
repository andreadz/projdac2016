package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author André
 */
public class DbConexao {
    public static Connection getConection(){        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao =  DriverManager.getConnection("jdbc:mysql://localhost/bancovsf", "usuariodac", "sysdac");
            return conexao;
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } 
    }
}
