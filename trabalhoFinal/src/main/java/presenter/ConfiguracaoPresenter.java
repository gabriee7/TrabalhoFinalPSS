/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Usuario;
import service.Sessao;
import view.ConfiguracaoView;

/**
 *
 * @author nitro5WIN10
 */
public class ConfiguracaoPresenter {
    private Usuario usuarioLogado;
    private ConfiguracaoView view;
    
    public ConfiguracaoPresenter() {
        this.view = new ConfiguracaoView();
        this.usuarioLogado = Sessao.getInstancia().getUsuarioLogado();
        configura();
    }
    
    private void configura(){
        view.setVisible(false);
        view.getTextFieldNome().setText(usuarioLogado.getNome());
        view.getTextFieldDataCadastro().setText(usuarioLogado.getDataCadastro());
        view.getBtnExcluir().setVisible(false);
        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldDataCadastro().setEnabled(false);
        
        view.getBtnAlterarSenha().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    alterarSenha();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.setVisible(true);
    }

    public ConfiguracaoView getView() {
        return view;
    }
    
    private void alterarSenha(){
        
    }
    
    private void fechar(){
        view.setVisible(false);
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
    
    public int exibirConfirmacao(String mensagem, String titulo){
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
    }
    
}
