/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Notificacao;
import model.Usuario;
/**
 *
 * @author nitro5WIN10
 */
public class NotificacaoDAOSQLite implements INotificacaoDAO {

    @Override
    public boolean criar(Notificacao notificacao) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para inserir uma nova notificação
            String sql = "INSERT INTO Notificacao (titulo, mensagem) VALUES (?, ?)";
            String titulo = notificacao.getTitulo();
            String mensagem = notificacao.getMensagem();

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, titulo);
                preparedStatement.setString(2, mensagem);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // true se a inserção for bem-sucedida
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }

    @Override
    public Notificacao consultar(int id) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para obter uma notificação pelo ID
            String sql = "SELECT * FROM Notificacao WHERE id_notificacao = ?";
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String titulo = resultSet.getString("titulo");
                        String mensagem = resultSet.getString("mensagem");

                        return new Notificacao(id, titulo, mensagem);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }

        return null; // Retorna null se a notificação não for encontrada
    }

    @Override
    public void atualizar(Notificacao notificacao) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para atualizar uma notificação
            String sql = "UPDATE Notificacao SET titulo = ?, mensagem = ? WHERE id_notificacao = ?";
            String titulo = notificacao.getTitulo();
            String mensagem = notificacao.getMensagem();

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, titulo);
                preparedStatement.setString(2, mensagem);
                preparedStatement.setInt(3, notificacao.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }

    @Override
    public boolean deletar(int id) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para deletar uma notificação pelo ID
            String sql = "DELETE FROM Notificacao WHERE id_notificacao = ?";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // true se a deleção for bem-sucedida
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }

    @Override
    public List<Notificacao> listarTodos() {
        List<Notificacao> notificacoes = new ArrayList<>();

        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para obter todas as notificações
            String sql = "SELECT * FROM Notificacao";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_notificacao");
                    String titulo = resultSet.getString("titulo");
                    String mensagem = resultSet.getString("mensagem");

                    Notificacao notificacao = new Notificacao(id, titulo, mensagem);
                    notificacoes.add(notificacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }

        return notificacoes;
    }
}
