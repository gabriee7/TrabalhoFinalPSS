/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

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
    
}
