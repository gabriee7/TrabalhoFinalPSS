/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Usuario;
import persistence.IUsuarioDAO;

/**
 *
 * @author nitro5WIN10
 */
public class AutenticacaoService {
    IUsuarioDAO usuarioDAO;

    public AutenticacaoService(IUsuarioDAO dao) {
        this.usuarioDAO = dao;
    }
    
    public void autentica(Usuario usuario){
        try{
            if(usuarioDAO.consultar(usuario)){  //se existe o usuario se torna autenticado
                usuario.setAutenticado(true);
            }else{
                DotEnvService dotEnvService = new DotEnvService(); 
                String env = dotEnvService.getDotEnv("userCount");
                
                if(env.equalsIgnoreCase("0") && usuarioDAO.criar(usuario)){ //verifica se é o primeiro usuario do sistema e já cria o usuario se for e se torna autenticado posteriormente alterar para encaminhar para tela de cadastro
                    //verificar maneira de fazer um set no DotEnv
                    usuario.setAutenticado(true);
                }
            }
            
            usuarioDAO.atualizar(usuario); //atualiza o usuario no banco de dados

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }      
    }
}
