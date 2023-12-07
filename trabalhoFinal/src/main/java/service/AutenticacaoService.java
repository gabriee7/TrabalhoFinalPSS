/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Usuario;

/**
 *
 * @author nitro5WIN10
 */
public class AutenticacaoService {
    GerenciadorUsuarioService gerenciadorUsuario;
    public static AutenticacaoService instancia;

    public AutenticacaoService() {
        this.gerenciadorUsuario = new GerenciadorUsuarioService();
    }
    
    public Usuario autentica(String nome, String senha){
        try{ 
            if(gerenciadorUsuario.listarTodos().isEmpty()){
                throw new RuntimeException("Não há usuarios, cadastre agora.");}
            Usuario usuario = gerenciadorUsuario.consultar(nome);

            if(usuario == null){
                throw new RuntimeException("Login Incorreto!");
            }else if (!usuario.isAtivo()){
                throw new RuntimeException("Não é possível realizar login, seu usuário permance inativo até que um administrador aprove seu cadastro.");
            }else if(senha.equals(usuario.getSenha())) {
                Sessao.getInstancia().setUsuarioLogado(usuario); 
                return usuario;
            }else{
                throw new RuntimeException("Senha Incorreta!");
            }
            

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }      
    } 
}
