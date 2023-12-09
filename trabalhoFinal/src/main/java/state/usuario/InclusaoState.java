/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.usuario;

import command.usuario.FecharCommand;
import command.usuario.IUsuarioCommand;
import command.usuario.SalvarCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presenter.UsuarioPresenter;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class InclusaoState extends UsuarioState{
    private IUsuarioCommand comando = null;
    UsuarioView view ;

    
    public InclusaoState(UsuarioPresenter presenter) {
        super(presenter);
        this.view = presenter.getView();
        configuraTela();
    }
    
    @Override
    public void salvar(){
        String nome = view.getTextFieldNome().getText();
        String senha = view.getTextFieldSenha().getText();
        String confirmaSenha = view.getTextFieldConfirmaSenha().getText();
        
        if(!senha.equals(confirmaSenha))
            throw new RuntimeException("Senhas não conferem!");
        
        comando = new SalvarCommand(nome,senha);
        comando.executa();
        
        view.getTextFieldNome().setText("");
        view.getTextFieldSenha().setText("");
        view.getTextFieldConfirmaSenha().setText("");
        
        presenter.setEstado(new VisualizacaoState(presenter, nome, senha));
    }
    
    @Override
    public void fechar(){
        comando = new FecharCommand(presenter);
        comando.executa();
    }
    
    @Override
    public void configuraTela(){
        
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.setTitle("Cadastro de Usuário");
        
        
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
        view.getLabelNotificacoesEnviadas().setVisible(false);
        view.getLabelNotificacoesLidas().setVisible(false);
        view.setVisible(true);
    }
}
