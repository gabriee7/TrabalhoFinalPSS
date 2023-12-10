/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.usuario;

import command.usuario.FecharCommand;
import command.usuario.IUsuarioCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presenter.UsuarioPresenter;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class EdicaoState extends UsuarioState {
    private IUsuarioCommand comando = null;
    private String nome;
    private String senha;
    
    public EdicaoState(UsuarioPresenter presenter, String nome, String senha) {
        super(presenter);
        this.nome = nome;
        this.senha = senha;
        configuraTela();
    }
    
    @Override
    public void salvar(){
    
    }
    
    @Override
    public void fechar(){
        comando = new FecharCommand(presenter);
        comando.executa();
    }
    
    @Override
    public void configuraTela(){
        UsuarioView view = presenter.getView();
        
        view.setTitle("Edição de Usuário");
        
        view.getTextFieldNome().setText(nome);
        view.getTextFieldSenha().setText(senha);
        
        view.getTextFieldConfirmaSenha().setVisible(true);
        view.getTextFieldConfirmaSenha().setVisible(true);
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.getBtnSalvar().setVisible(true);
        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldSenha().setEnabled(true);
        
        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    salvar();
                }catch(Exception e){
                    presenter.exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        view.getTextFieldNotificacoesEnviadas().setVisible(false);
        view.getTextFieldNotificacoesLidas().setVisible(false);
        view.getLabelConfirmaSenha().setVisible(true);
        view.getLabelNotificacoesEnviadas().setVisible(false);
        view.getLabelNotificacoesLidas().setVisible(false);
        
        view.setVisible(true);
    }
}
