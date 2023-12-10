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
    public static MenuPresenter instancia = null;
    
    private MenuPresenter(Usuario usuario) {
        this.view = new MenuView();
        this.usuarioAutenticado = usuario;
        this.gerenciadorUsuario = new GerenciadorUsuarioService();
        configura();
    }
    
    public static MenuPresenter getInstancia(Usuario usuario){
        if(instancia == null){
            instancia = new MenuPresenter(usuario);
        }
        return instancia;
    }
    
    public static MenuPresenter getInstancia(){
        return instancia;
    }
    public MenuView getView() {
        return view;
    }
    
    
    
    private void configura(){
        this.view.setVisible(false);
        
        if(!"admin".equalsIgnoreCase(usuarioAutenticado.getTipo())){
            view.getTextUsuarioMenuBar().setVisible(false);
            view.getTextNotificacoesMenuBar().setVisible(false);
            view.getMenuVisualizarTodasNotificacoes().setVisible(false);
            view.getBtnNotificacoes().setVisible(true); // adicionar evento
        }else{
            view.getMenuVisualizarTodasNotificacoes().setVisible(true);
            view.getBtnNotificacoes().setVisible(false);
        }

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
        
        view.getMenuAutorizacaoPendente().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ListaInativosPresenter presenter = new ListaInativosPresenter();
                view.getjDesktopPane1().add(presenter.getView()).setVisible(true);
            }
        });
        
        this.view.setVisible(true);
    }
    
}
