/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import com.log.LogService;
import persistence.InicializaBanco;
import presenter.LoginPresenter;
import com.log.*; // repositorio github projeto maven externo com jitpack

/**
 *
 * @author nitro5WIN10
 */
public class Principal {

    public static void main(String[] args) {    
//        LogService log;       //COMO USA O LOG
//
//        log = new LogService("com.log.CSVLog");
//        log.addLog("HEEI");


        InicializaBanco.inicializar();
        
        LoginPresenter presenter = new LoginPresenter();
    }
}