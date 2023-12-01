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

    public static Connection getConexao(){
        try{
            return DriverManager.getConnection(url);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
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