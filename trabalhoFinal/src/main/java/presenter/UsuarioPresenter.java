/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import state.usuario.InclusaoState;
import state.usuario.UsuarioState;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class UsuarioPresenter {
    private UsuarioState estado;
    private UsuarioView view;
    
    public UsuarioPresenter() {
        this.view = new UsuarioView();
        this.estado = new InclusaoState(this);
    }
    
    public UsuarioPresenter(UsuarioState estado) {
        this.view = new UsuarioView();
        this.estado = estado;
    }
    public UsuarioState getEstado() {
        return estado;
    }

    public void setEstado(UsuarioState estado) {
        this.estado = estado;
    } 

    public UsuarioView getView() {
        return view;
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
    
    public int exibirConfirmacao(String mensagem, String titulo, int type){
        int retorno = JOptionPane.showConfirmDialog(this.view, mensagem, titulo,type);
        return retorno;
    }
}
