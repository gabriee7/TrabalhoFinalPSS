/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



import persistence.InicializaBanco;
import presenter.LoginPresenter;

/**
 *
 * @author nitro5WIN10
 */
public class Principal {

    public static void main(String[] args) {

        InicializaBanco.inicializar();
        
        LoginPresenter presenter = new LoginPresenter();
    }
}
