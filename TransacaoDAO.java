/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import models.*;
/**
 *
 * @author André
 */
public class TransacaoDAO {
    private final String stmTransacoes = "INSERT INTO transacoes values (?,?,?,?,?,?,?)";
    
    public void salvarTransacao(Transacoes transac){
        Connection conexao =  null;PreparedStatement pstmt = null;
        try{
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmTransacoes, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setInt(1, transac.getTipoTransacao());
            pstmt.setDouble(2, transac.getValor());
            pstmt.setDate(3, transac.getDataTransacao());            
            pstmt.setInt(4, transac.getIdConta1());            
            pstmt.setInt(5, transac.getIdCliente1()); 
            pstmt.setInt(6, transac.getIdConta2());            
            pstmt.setInt(7, transac.getIdCliente2());
            
            pstmt.executeUpdate();            
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally{
            try{pstmt.close();} catch(Exception ex){System.out.println("Erro:" + ex.getMessage());}
            try{conexao.close();} catch(Exception ex){System.out.println("Erro:" + ex.getMessage());}
        }
    }
}
