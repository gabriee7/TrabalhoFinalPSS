/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Notificacao;
import service.GerenciadorNotificacaoService;
import view.VisualizaNotificacaoView;

/**
 *
 * @author nitro5WIN10
 */
public class VisualizaNotificacaoPresenter {
    private VisualizaNotificacaoView view;
    private GerenciadorNotificacaoService service;

    public VisualizaNotificacaoPresenter(Notificacao notificacao) {
        this.view = new VisualizaNotificacaoView();
        this.service = new GerenciadorNotificacaoService();
        configura(notificacao);
    }
    
    private void fechar(){
        view.setVisible(false); //alterar implementacao
    }
    
    private void marcaLida(Notificacao notificacao){
        service.marcaLida(notificacao);
        exibirMensagem("Lida!", "Notificacao", 1);
    }

    public VisualizaNotificacaoView getView() {
        return view;
    }
    
    
    
    private void configura(Notificacao notificacao){
        
        view.getTextFieldTitulo().setText("");
        view.getTextAreaMensagem().setText("");
        view.getTextFieldTitulo().setText(notificacao.getTitulo());
        view.getTextAreaMensagem().setText(notificacao.getMensagem());
        view.getTextFieldTitulo().setEditable(false);
        view.getTextAreaMensagem().setEditable(false);
        
        view.getBtnMarcaLida().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    marcaLida(notificacao);
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
     
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
