/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import javax.swing.JOptionPane;
import state.notificacao.InclusaoState;
import state.notificacao.NotificacaoState;

import view.NotificacaoView;

/**
 *
 * @author nitro5WIN10
 */
public class NotificacaoPresenter {
    private NotificacaoState estado;
    private NotificacaoView view;

    public NotificacaoPresenter() {
        this.view = new NotificacaoView();
        this.estado = new InclusaoState(this);
        configuraTela();
    }

    public NotificacaoView getView() {
        return view;
    }
    
    
    
    private void configuraTela(){
    
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
    
}
