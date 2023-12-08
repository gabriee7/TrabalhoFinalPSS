/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.usuario;

import presenter.UsuarioPresenter;

/**
 *
 * @author nitro5WIN10
 */
public abstract class UsuarioState {
    protected UsuarioPresenter presenter;

    public UsuarioState(UsuarioPresenter presenter) {
        this.presenter = presenter;
    }
   
    public void salvar(){
        throw new RuntimeException("Não é possível realizar esta operacao.");
    }
    public void editar(){
        throw new RuntimeException("Não é possível realizar esta operacao.");
    }
    public void excluir(){
        throw new RuntimeException("Não é possível realizar esta operacao.");
    }
    public void fechar(){
        throw new RuntimeException("Não é possível realizar esta operacao.");
    }
}
