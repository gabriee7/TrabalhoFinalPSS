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

    public AutenticacaoService(IUsuarioDAO dao) {
        this.gerenciadorUsuario = new GerenciadorUsuarioService(dao);
    }
    
    public Usuario autentica(String nome, String senha){
        try{ 
            if(gerenciadorUsuario.listarTodos().isEmpty()){
                throw new RuntimeException("Não há usuarios, cadastre agora.");}
            Usuario usuarioAutentica = gerenciadorUsuario.consultar(nome);

            if(usuarioAutentica == null){
                throw new RuntimeException("Login Incorreto!");
            } else if(senha.equals(usuarioAutentica.getSenha())) {
                return usuarioAutentica;
            }else{
                throw new RuntimeException("Senha Incorreta!");
            }
            
//            if(usuarioAutentica != null){  //se existe o usuario se torna autenticado
//                usuarioAutentica.setAutenticado(true);
//                return usuarioAutentica;
//            }else if(gerenciadorUsuario.listarTodos().isEmpty()){                    //verifica se é o primeiro usuario do sistema e já cria o usuario se for e se torna autenticado posteriormente alterar para encaminhar para tela de cadastro
//                throw new RuntimeException("Não há usuarios, cadastre agora.");
//            }
//            
//            throw new RuntimeException("Login Incorreto!");
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }      
    }
}
