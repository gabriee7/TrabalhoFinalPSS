/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.usuario;

import presenter.UsuarioPresenter;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class EdicaoState extends UsuarioState {

    public EdicaoState(UsuarioPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void salvar(){
    
    }
    
    @Override
    public void fechar(){
    
    }
    
    @Override
    public void configuraTela(){
        UsuarioView view = presenter.getView();
        
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.setVisible(true);
    }
}
