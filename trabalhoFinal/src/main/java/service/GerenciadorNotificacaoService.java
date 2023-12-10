/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Notificacao;
import model.Usuario;
import persistence.Factory.DAOFactoryService;
import persistence.INotificacaoDAO;

/**
 *
 * @author nitro5WIN10
 */
public class GerenciadorNotificacaoService {
    private INotificacaoDAO notificacaoDAO;
    private DAOFactoryService factoryService;

    public GerenciadorNotificacaoService() {
        this.factoryService = new DAOFactoryService();
        this.notificacaoDAO = factoryService.getNotificacaoDAO();
    }
    
    public boolean inserir(Notificacao notificacao, List<Usuario> usuariosPadrao){    
        return notificacaoDAO.criar(notificacao, usuariosPadrao);
    }
    
    public List<Notificacao> listarTodas(){
        return null;
    }
    
    public void excluir(){
       
    }
    
    public void consultar(){
    
    }

     
}
