/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence.Factory;

import persistence.INotificacaoDAO;
import persistence.IUsuarioDAO;

/**
 *
 * @author nitro5WIN10
 */
public interface IDAOFactory {
    public IUsuarioDAO getUsuarioDAO();
    public INotificacaoDAO getNotificacaoDAO();
}
