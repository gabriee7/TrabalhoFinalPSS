/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import service.Sessao;
import view.ConfiguracaoView;

/**
 *
 * @author nitro5WIN10
 */
public class ConfiguracaoPresenter {
    private String nome;
    private String dataCadastro;
    private ConfiguracaoView view;
    
    public ConfiguracaoPresenter() {
        this.view = new ConfiguracaoView();
        this.nome = Sessao.getInstancia().getUsuarioLogado().getNome();
        this.dataCadastro = Sessao.getInstancia().getUsuarioLogado().getDataCadastro();
        configura();
    }
    
    private void configura(){
        view.setVisible(false);
        view.getTextFieldNome().setText(nome);
        view.getTextFieldDataCadastro().setText(dataCadastro);
        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldDataCadastro().setEnabled(false);
        view.setVisible(true);
    }

    public ConfiguracaoView getView() {
        return view;
    }
    
    
}
