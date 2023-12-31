/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean criar(Notificacao notificacao, List<Usuario> usuariosPadrao) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para inserir uma nova notificação
            String sql = "INSERT INTO Notificacao (titulo, mensagem) VALUES (?, ?)";
            String titulo = notificacao.getTitulo();
            String mensagem = notificacao.getMensagem();

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, titulo);
                preparedStatement.setString(2, mensagem);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {

                    try (Statement statement = conexao.createStatement()) {
                    ResultSet rs = statement.executeQuery("SELECT last_insert_rowid()");
                    if (rs.next()) {
                        int idNotificacao = rs.getInt(1);
                        associarNotificacaoUsuarios(idNotificacao, usuariosPadrao);
                    } else {
                        throw new RuntimeException("Falha ao obter a chave gerada automaticamente.");
                    }

                }catch(Exception e){
                    System.out.println("rro: " + e.getMessage());
                }
                }
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }

    private void associarNotificacaoUsuarios(int idNotificacao, List<Usuario> usuarios) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para associar notificação aos usuários do tipo "padrao"
            String sql = "INSERT INTO notificacaoUsuario (id_notificacao, id_usuario, lida) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {


                for (Usuario usuario : usuarios) {
                    preparedStatement.setInt(1, idNotificacao);
                    preparedStatement.setInt(2, usuario.getId());
                    preparedStatement.setBoolean(3, false);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
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
    public void marcaLida(Notificacao notificacao) {
        Connection conexao = ConexaoService.getConexao();

        try {
            // Consulta SQL para atualizar uma notificação
            String sql = "UPDATE notificacaoUsuario SET lida = ? WHERE id_notificacao = ?";
            boolean lida = true;
            int id = notificacao.getId();

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setBoolean(1, lida);
                preparedStatement.setInt(2, id);

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
    
        public List<Notificacao> listaLidas(int idUsuario){
            List<Notificacao> notificacoes = new ArrayList<>();
            Connection conexao = ConexaoService.getConexao();
            String sql = "SELECT n.* " +
                     "FROM notificacao n " +
                     "JOIN notificacaoUsuario nu ON n.id_notificacao = nu.id_notificacao " +
                     "WHERE nu.id_usuario = ? AND nu.lida = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setBoolean(2, true);
            

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idNotificacao = resultSet.getInt("id_notificacao");
                    String titulo = resultSet.getString("titulo");
                    String mensagem = resultSet.getString("mensagem");

                    Notificacao notificacao = new Notificacao(idNotificacao, titulo, mensagem);
                    notificacoes.add(notificacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter notificações por usuário: " + e.getMessage());
        }finally {
            ConexaoService.closeConexao(conexao);
        }

        return notificacoes;
    }
    
    public List<Notificacao> listaPorUsuario(int idUsuario){
            List<Notificacao> notificacoes = new ArrayList<>();
            Connection conexao = ConexaoService.getConexao();
            String sql = "SELECT n.*, nu.lida " +
                 "FROM notificacao n " +
                 "JOIN notificacaoUsuario nu ON n.id_notificacao = nu.id_notificacao " +
                 "WHERE nu.id_usuario = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, idUsuario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idNotificacao = resultSet.getInt("id_notificacao");
                    String titulo = resultSet.getString("titulo");
                    String mensagem = resultSet.getString("mensagem");
                    boolean lida = resultSet.getBoolean("lida");

                    Notificacao notificacao = new Notificacao(idNotificacao, titulo, mensagem);
                    notificacao.setLida(lida);
                    notificacoes.add(notificacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter notificações por usuário: " + e.getMessage());
        }finally {
            ConexaoService.closeConexao(conexao);
        }

        return notificacoes;
    }
}
