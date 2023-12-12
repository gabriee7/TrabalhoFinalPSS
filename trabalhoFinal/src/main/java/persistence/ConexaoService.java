/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nitro5WIN10
 */
public class ConexaoService {
    private static final String url = "jdbc:sqlite:trabalhoFinal.db";
    private static Connection conexao;

    public ConexaoService() {
    
    }
    public static Connection getConexao(){
        if(conexao == null){
            try{
                conexao = DriverManager.getConnection(url);
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
        
        return conexao;
    }
    
    public static void closeConexao(Connection connection){
        try{
            if(!connection.isClosed() && connection != null)
                connection.isClosed();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}