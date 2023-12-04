/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import model.Usuario;
import persistence.UsuarioDAOSQLite;
import service.AutenticacaoService;
import view.LoginView;

/**
 *
 * @author nitro5WIN10
 */
public class LoginPresenter {
    private LoginView view;
    private AutenticacaoService service;
    
    public LoginPresenter() {
        this.view = new LoginView();
        this.service = new AutenticacaoService(new UsuarioDAOSQLite()); //alterar a passagem de parametro por construtor, implementar FactoryMethod ou outro metodo com DotEnv
        configura();
    }
    
    private void autentica(){
        String nome = view.getTextFieldUsuario().getText();
        String senha = view.getTextFieldSenha().getText();
        Usuario usuario = service.autentica(nome, senha);
        
        if("".equals(nome) || "".equals(senha))
            throw new RuntimeException("Campo vazio!");
            
        if(usuario != null){
            MenuPresenter menu = new  MenuPresenter(usuario);
            this.view.setVisible(false);
        }
    }
    
    private void configura(){
        this.view.setVisible(false);
        view.getBtnEntrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    autentica();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getLabelTextCadastro().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                try{
                    CadastroPresenter cadastro = new CadastroPresenter();
                    view.getLabelTextCadastro().setForeground(Color.blue);
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
