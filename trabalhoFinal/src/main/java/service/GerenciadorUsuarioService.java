/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Usuario;
import persistence.IUsuarioDAO;

/**
 *
 * @author nitro5WIN10
 */
public class GerenciadorUsuarioService {
   private IUsuarioDAO usuarioDAO;

    public GerenciadorUsuarioService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
   
   public void inserir(String nome, String senha){
       Usuario usuario = new Usuario(nome, senha);
       usuarioDAO.criar(usuario);
   }
   
   public List<Usuario> listarTodos(){
       return usuarioDAO.listarTodos();
   }
   
   public boolean excluir(int id){
       return usuarioDAO.deletar(id);
   }
   
   public boolean consultar(String nome, String senha){
       Usuario usuario = new Usuario(nome, senha);
       return usuarioDAO.consultar(usuario);
   }
   
   public void atualizar(Usuario usuario){
       usuarioDAO.atualizar(usuario);
   }
}
