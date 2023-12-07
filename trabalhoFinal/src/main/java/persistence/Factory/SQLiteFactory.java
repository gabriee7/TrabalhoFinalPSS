/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.Factory;

import persistence.INotificacaoDAO;
import persistence.IUsuarioDAO;
import persistence.NotificacaoDAOSQLite;
import persistence.UsuarioDAOSQLite;

/**
 *
 * @author nitro5WIN10
 */
public class SQLiteFactory implements IDAOFactory {
    @Override
    public IUsuarioDAO getUsuarioDAO(){
        return new UsuarioDAOSQLite();
    }
    
    public INotificacaoDAO getNotificacaoDAO(){
        return new NotificacaoDAOSQLite();
    }
}
