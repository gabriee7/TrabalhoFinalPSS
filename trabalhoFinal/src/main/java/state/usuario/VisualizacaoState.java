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
import javax.swing.JOptionPane;
import presenter.UsuarioPresenter;
import service.GerenciadorNotificacaoService;
import service.GerenciadorUsuarioService;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class VisualizacaoState extends UsuarioState {
    private IUsuarioCommand comando = null;
    private String nome;
    private String senha;
    private GerenciadorUsuarioService serviceUsuario;
    private GerenciadorNotificacaoService serviceNotificacao;
    
    public VisualizacaoState(UsuarioPresenter presenter, String nome, String senha) {
        super(presenter);
        this.nome = nome;
        this.senha = senha;
        this.serviceUsuario = new GerenciadorUsuarioService();
        this.serviceNotificacao = new GerenciadorNotificacaoService();
        configuraTela();
    }
    
    @Override
    public void excluir(){
        String nome = presenter.getView().getTextFieldNome().getText();
        int confirm = presenter.exibirConfirmacao("Deseja realmente excluir " + nome.toUpperCase() + "?", "Excluir", 0);
        
        if(confirm == JOptionPane.YES_OPTION){
            comando = new ExcluirCommand(nome);
            comando.executa();
            presenter.exibirMensagem("Excluido com sucesso", "Exclusão", 1);
        }
        
        presenter.setEstado(new InclusaoState(presenter));
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
        int id = serviceUsuario.consultar(nome).getId();
        int enviada = serviceNotificacao.consultarTodasPorUsuario(id).size();
        int lidas = serviceNotificacao.consultarTodasLidas(id).size();
        UsuarioView view = presenter.getView();

        view.setTitle("Visualização de Usuário");
        
        view.getTextFieldNotificacoesEnviadas().setText(Integer.toString(enviada));
        view.getTextFieldNotificacoesLidas().setText(Integer.toString(lidas));
        view.getTextFieldNome().setText(nome);
        view.getTextFieldSenha().setText(senha);
        
        view.getTextFieldConfirmaSenha().setVisible(false);
        view.getLabelConfirmaSenha().setVisible(false);

        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldSenha().setEnabled(false);
  

        for (ActionListener listener : view.getBtnExcluir().getActionListeners()) {
            view.getBtnExcluir().removeActionListener(listener);
        }
        
        view.getBtnExcluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    excluir();
                }catch(Exception e){
                    presenter.exibirMensagem(e.getMessage(), "Erro", 1);
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
