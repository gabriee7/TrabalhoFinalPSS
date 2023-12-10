/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.usuario;

import command.usuario.ExcluirCommand;
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
public class VisualizacaoState extends UsuarioState {
    private IUsuarioCommand comando = null;
    private String nome;
    private String senha;
    
    public VisualizacaoState(UsuarioPresenter presenter, String nome, String senha) {
        super(presenter);
        this.nome = nome;
        this.senha = senha;
        configuraTela();
    }
    
    @Override
    public void excluir(){
        comando = new ExcluirCommand(nome);
        comando.executa();
    }
    
    @Override
    public void editar(){
        presenter.setEstado(new EdicaoState(presenter, nome, senha));
    }
  
    @Override
    public void fechar(){
        comando = new FecharCommand(presenter);
        comando.executa();
    }
    
    @Override
    public void configuraTela(){
        UsuarioView view = presenter.getView();

        view.setTitle("Visualização de Usuário");
        
        view.getTextFieldNome().setText(nome);
        view.getTextFieldSenha().setText(senha);
        
        view.getTextFieldConfirmaSenha().setVisible(false);
        view.getLabelConfirmaSenha().setVisible(false);

        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldSenha().setEnabled(false);
  

        view.getBtnExcluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    excluir();
                }catch(Exception e){
                    presenter.exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getBtnEditar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    editar();
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
        
        
        view.getTextFieldNotificacoesEnviadas().setVisible(true);
        view.getTextFieldNotificacoesLidas().setVisible(true);
        view.getLabelNotificacoesEnviadas().setVisible(true);
        view.getTextFieldNotificacoesEnviadas().setEnabled(false);
        view.getTextFieldNotificacoesLidas().setEnabled(false);
        view.getLabelNotificacoesLidas().setVisible(true);
        view.getBtnExcluir().setVisible(true);
        view.getBtnEditar().setVisible(true);
        view.getBtnSalvar().setVisible(false);
        
        
        view.setVisible(true);
    }
}
