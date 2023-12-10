/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Usuario;
import service.GerenciadorUsuarioService;
import view.ConfiguracaoView;
import view.MenuView;
import view.NotificacaoView;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class MenuPresenter {
    MenuView view;
    Usuario usuarioAutenticado;
    GerenciadorUsuarioService gerenciadorUsuario;
    
    public MenuPresenter(Usuario usuario) {
        this.view = new MenuView();
        this.usuarioAutenticado = usuario;
        this.gerenciadorUsuario = new GerenciadorUsuarioService();
        configura();
    }
    
    private void configura(){
        this.view.setVisible(false);

        view.getLabelUsuarioPrivilegio().setText("usuario: " + usuarioAutenticado.getNome() + " | privilégio: " + usuarioAutenticado.getTipo());
//        if("padrao".equalsIgnoreCase(usuarioAutenticado.getTipo())){
//           
//        }

        view.getMenuNovoUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                UsuarioPresenter presenter = new UsuarioPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        


        view.getMenuNovaNotificacao().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                NotificacaoPresenter presenter = new NotificacaoPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        view.getMenuConta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ConfiguracaoPresenter presenter = new ConfiguracaoPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        
        view.getMenuVisualizarTodosUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ListaUsuarioPresenter presenter = new ListaUsuarioPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        this.view.setVisible(true);
    }
    
}
