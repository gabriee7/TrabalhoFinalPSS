/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Usuario;
import persistence.UsuarioDAOSQLite;
import service.AutenticacaoService;
import view.LoginView;

/**
 *
 * @author nitro5WIN10
 */
public class LoginPresenter {
    LoginView view;
    AutenticacaoService service;
    
    public LoginPresenter() {
        this.view = new LoginView();
        this.service = new AutenticacaoService(new UsuarioDAOSQLite()); //alterar a passagem de parametro por construtor, implementar FactoryMethod ou outro metodo com DotEnv
        configura();
    }
    
    private void autentica(){
        String nome = view.getTextFieldUsuario().getText();
        String senha = view.getTextFieldSenha().getText();
        Usuario usuario = new Usuario(nome,senha);
        
        service.autentica(usuario);
    }
    
    private void configura(){
        this.view.setVisible(false);
        view.getBtnEntrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    autentica();
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        view.getLabelTextCadastro().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                try{
                    
                    //chamar a presenter do cadastro
                    System.out.println("Clicou no cadastro!!");
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        this.view.setVisible(true);
    }
}
