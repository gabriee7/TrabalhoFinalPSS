/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author nitro5WIN10
 */
public class InicializaBanco {

    public static void inicializar() {
        try {
            Connection conexao = ConexaoService.getConexao();
            PreparedStatement preparaUsuario = conexao.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS usuario (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT," +
                        "senha TEXT," +
                        "tipo TEXT," +
                        "autenticado BOOLEAN)"); 
            preparaUsuario.executeUpdate();
            preparaUsuario.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}