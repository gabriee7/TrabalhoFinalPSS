/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import service.GerenciadorUsuarioService;
import view.CadastroView;

/**
 *
 * @author nitro5WIN10
 */
public class CadastroPresenter {
    private CadastroView view;
    private GerenciadorUsuarioService gerenciadorUsuario;

    public CadastroPresenter() {
        this.view = new CadastroView();
        this.gerenciadorUsuario = new GerenciadorUsuarioService();
        configurar();
    }
    
    private void cadastrar(){
        String nome = view.getTextFieldNome().getText();
        String senha = view.getTextFieldSenha().getText();
        String confirmaSenha = view.getTextFieldConfirmaSenha().getText();
        
        if("".equals(nome) || "".equals(senha) || "".equals(confirmaSenha)){
            throw new RuntimeException("Campo vazio!!");
        }else if(!senha.equals(confirmaSenha)){
            throw new RuntimeException("Senhas não conferem!!" + senha + confirmaSenha);
        }else{            
            gerenciadorUsuario.inserir(nome, senha);
        }
        this.view.setVisible(false);
    }
    
    private void configurar(){
        view.getBtnCadastrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    cadastrar();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.setVisible(true);
    }
    
    private void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
