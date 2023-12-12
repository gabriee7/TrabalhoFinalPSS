/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author nitro5WIN10
 */
public class InicializaBanco {

    public static void inicializar() {
        Connection conexao = null;
        try {
            conexao = ConexaoService.getConexao();
            Statement preparaSql = conexao.createStatement();

            preparaSql.execute(
                "CREATE TABLE IF NOT EXISTS notificacao (" +
                "id_notificacao INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo VARCHAR(255)," +
                "mensagem TEXT)"
            );

            preparaSql.executeUpdate(
                "CREATE TABLE IF NOT EXISTS usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT UNIQUE," +
                "senha TEXT," +
                "tipo TEXT," +
                "ativo BOOLEAN," +
                "dataCadastro TEXT)"
            );

            preparaSql.execute(
                "CREATE TABLE IF NOT EXISTS notificacaoUsuario (" +
                "id_notificacao INT," +
                "id_usuario INT," +
                "lida BOOLEAN," +
                "PRIMARY KEY (id_notificacao, id_usuario)," +
                "FOREIGN KEY (id_notificacao) REFERENCES notificacao(id_notificacao)," +
                "FOREIGN KEY (id_usuario) REFERENCES usuario(id))"
            );
            preparaSql.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally{
            ConexaoService.closeConexao(conexao);
        }
    }
}