/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import presenter.UsuarioPresenter;

/**
 *
 * @author nitro5WIN10
 */
public class FecharCommand implements IUsuarioCommand {
    private UsuarioPresenter presenter;
    
    public FecharCommand(UsuarioPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void executa(){
        presenter.getView().setVisible(false);
    }
}
