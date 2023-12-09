/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author nitro5WIN10
 */
public class UsuarioDAOSQLite implements IUsuarioDAO {
    @Override 
    public boolean criar(Usuario usuario){
        Connection conexao = ConexaoService.getConexao();    

        try {
            String sql = "INSERT INTO usuario (nome, senha, tipo, ativo, dataCadastro) VALUES (?, ?, ?, ?, ?)";
            String nome = usuario.getNome();
            String senha = usuario.getSenha();
            String tipo = usuario.getTipo();
            boolean ativo = usuario.isAtivo();
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql); 
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3,tipo);
            preparedStatement.setBoolean(4, ativo);
            preparedStatement.setString(5, LocalDate.now().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0; //true é sucesso para insercao
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override 
    public Usuario consultar(String nome){
        Connection conexao = ConexaoService.getConexao();  //conexao do banco
        try{          
            String sql = "SELECT * FROM usuario WHERE nome = ? LIMIT 1"; //sql generico para que retorne o primeiro usuario encontrado

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);  //insercao de where no sql
            
            ResultSet resultSet = preparedStatement.executeQuery(); 
            
            if(!resultSet.next()){
                throw new RuntimeException("Usuário não existe!");
            }else{
                Usuario usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(resultSet.getInt("id"));
                usuarioEncontrado.setNome(resultSet.getString("nome"));
                usuarioEncontrado.setSenha(resultSet.getString("senha"));
                usuarioEncontrado.setTipo(resultSet.getString("tipo"));
                usuarioEncontrado.setAtivo(resultSet.getBoolean("ativo"));
                usuarioEncontrado.setDataCadastro(resultSet.getString("dataCadastro"));
                return usuarioEncontrado;
            }

            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override
    public void atualizar(Usuario usuario) {
        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "UPDATE usuario SET nome = ?, senha = ?, tipo = ?, ativo = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getTipo());
            preparedStatement.setBoolean(4, usuario.isAtivo());
            preparedStatement.setInt(5, usuario.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("Nenhum usuário foi atualizado. Verifique o ID do usuário.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }

    @Override
    public boolean deletar(int id) {
        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override 
    public List<Usuario> listarTodos(){
        List<Usuario> usuarios = new ArrayList<>();

        Connection conexao = ConexaoService.getConexao();

        try {
            String sql = "SELECT * FROM usuario";
            PreparedStatement preparaLista = conexao.prepareStatement(sql);

            try (ResultSet resultSet = preparaLista.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    String senha = resultSet.getString("senha");
                    String tipo = resultSet.getString("tipo");
                    boolean ativo = resultSet.getBoolean("ativo");
                    
                    String dataCadastro = resultSet.getString("dataCadastro");
                    
                    Usuario usuario = new Usuario(nome, senha, tipo, dataCadastro);
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }

        return usuarios;
    }
    
}
