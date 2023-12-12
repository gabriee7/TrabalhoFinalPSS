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
import com.log. *; // repositorio github projeto maven externo com jitpack
import log.LogAdapter;

/**
 *
 * @author nitro5WIN10
 */
public class GerenciadorNotificacaoService {
    private INotificacaoDAO notificacaoDAO;
    private DAOFactoryService factoryService;
    private LogService log;
    
    public GerenciadorNotificacaoService() {
        this.log = new LogService("com.log.CSVLog");
        this.factoryService = new DAOFactoryService();
        this.notificacaoDAO = factoryService.getNotificacaoDAO();
    }
    
    public boolean inserir(Notificacao notificacao, List<Usuario> usuariosPadrao){
        boolean retorno = notificacaoDAO.criar(notificacao, usuariosPadrao);
        LogAdapter.getInstancia().addLog("Envio de Notificacao: ", notificacao.getTitulo());
        return retorno;
    }
    
    public List<Notificacao> listarTodas(){
        return null;
    }
    
    public void excluir(){
       
    }
    
    public void marcaLida(Notificacao notificacao){
        notificacaoDAO.marcaLida(notificacao);
        LogAdapter.getInstancia().addLog("Leitura Notificacao: ", notificacao.getTitulo());
    }
    
    public List<Notificacao> consultarTodasLidas(int id){
        return notificacaoDAO.listaLidas(id);
    }
    
    public List<Notificacao> consultarTodasPorUsuario(int id){
        return notificacaoDAO.listaPorUsuario(id);
    }

     
}
