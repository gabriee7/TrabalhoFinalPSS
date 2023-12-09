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
        Connection conexao = null;
        try {
            conexao = ConexaoService.getConexao();
            PreparedStatement preparaUsuario = conexao.prepareStatement(
                "CREATE TABLE IF NOT EXISTS usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT UNIQUE," +
                    "senha TEXT," +
                    "tipo TEXT," +
                    "ativo BOOLEAN," +
                    "dataCadastro TEXT);"+

                "CREATE TABLE Notificacao ("+
                    "id_notificacao INT PRIMARY KEY,"+
                    "titulo VARCHAR(255),"+
                    "mensagem TEXT,"+

                "CREATE TABLE NotificacaoUsuario ("+
                    "id_notificacao INT,"+
                    "id_usuario INT,"+
                    "lida BOOLEAN,"+
                    "PRIMARY KEY (id_notificacao, id_usuario),"+
                    "FOREIGN KEY (id_notificacao) REFERENCES Notificacao(id_notificacao),"+
                    "FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario));"
            );
            preparaUsuario.executeUpdate();
            preparaUsuario.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally{
            ConexaoService.closeConexao(conexao);
        }
    }
}