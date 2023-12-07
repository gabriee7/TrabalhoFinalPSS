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
public class AutenticacaoService {
    GerenciadorUsuarioService gerenciadorUsuario;
    private Usuario usuarioAutenticado;

    public AutenticacaoService() {
        this.gerenciadorUsuario = new GerenciadorUsuarioService();
        this.usuarioAutenticado = null;
    }
    
    public Usuario autentica(String nome, String senha){
        try{ 
            if(gerenciadorUsuario.listarTodos().isEmpty()){
                throw new RuntimeException("Não há usuarios, cadastre agora.");}
            Usuario usuario = gerenciadorUsuario.consultar(nome);

            if(usuario == null){
                throw new RuntimeException("Login Incorreto!");
            } else if(senha.equals(usuario.getSenha())) {
                setUsuarioAutenticado(usuario); 
                return usuario;
            }else{
                throw new RuntimeException("Senha Incorreta!");
            }
            

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }      
    }

    private void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
    
    
}
