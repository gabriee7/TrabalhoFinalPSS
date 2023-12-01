/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            // Consulta SQL para inserir um novo usuário
            String sql = "INSERT INTO usuario (nome, senha, autenticado) VALUES (?, ?, ?)";
            String nome = usuario.getNome();
            String senha = usuario.getSenha();
            boolean autenticado = usuario.isAutenticado();
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql); 
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setBoolean(3, autenticado);

            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0; //true é sucesso para insercao
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override 
    public boolean consultar(Usuario usuario){
        Connection conexao = ConexaoService.getConexao();  //conexao do banco
        try{
            String nome = usuario.getNome();
            String senha = usuario.getSenha();
            
            String sql = "SELECT 1 FROM usuario WHERE nome = ? AND senha = ? LIMIT 1"; //sql generico para que retorne o primeiro usuario encontrado

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);  //insercao de where no sql
            preparedStatement.setString(2, senha);

            ResultSet resultSet = preparedStatement.executeQuery(); 
            
            if(resultSet.next()) {
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConexaoService.closeConexao(conexao);
        }
    }
    
    @Override 
    public void atualizar(Usuario usuario){
        //Implementar
    }
    
    @Override
    public void deletar(Usuario usuario){
        //Implementar
    }
    
//    @Override 
//    public List<Usuario> listarTodos(){
            //Implementar

//        return List<Usuario>;
//    }
}
