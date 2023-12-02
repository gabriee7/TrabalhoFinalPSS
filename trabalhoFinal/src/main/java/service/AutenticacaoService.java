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
    
    public void autentica(String nome, String senha){
        try{
            Usuario usuario = new Usuario(nome, senha);
            if(gerenciadorUsuario.consultar(nome, senha)){  //se existe o usuario se torna autenticado
                usuario.setAutenticado(true);
            }else if(gerenciadorUsuario.listarTodos().isEmpty()){                    //verifica se é o primeiro usuario do sistema e já cria o usuario se for e se torna autenticado posteriormente alterar para encaminhar para tela de cadastro
                usuario.setAutenticado(true);
            }
            
            gerenciadorUsuario.atualizar(usuario); //atualiza o usuario no banco de dados


//            List<Usuario> todos = usuarioDAO.listarTodos();
//            for(Usuario elem: todos){   
//                System.out.println("Nome: " + elem.getNome() + "\tSenha: " + elem.getSenha() + "\n");
//            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }      
    }
}
