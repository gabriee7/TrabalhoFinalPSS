/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.Factory;

import persistence.INotificacaoDAO;
import persistence.IUsuarioDAO;
import properties.Configuracao;

/**
 *
 * @author nitro5WIN10
 */
public class DAOFactoryService {
    private IDAOFactory sgbd;
    private String properties;
    
    public DAOFactoryService(){
        this.sgbd = getDAOFactory();
    }
    
    private IDAOFactory getDAOFactory(){
        try{
            getProperties();
            Class<?> classeProp = Class.forName(properties);
            Object instanciaSGBD = classeProp.getDeclaredConstructor().newInstance();
            return (IDAOFactory)instanciaSGBD;
        }catch(Exception e) {
            throw new RuntimeException("Erro: SGBD não suportado! \n" + e.getMessage());
        }
    }
    
    public IUsuarioDAO getUsuarioDAO(){
        return this.sgbd.getUsuarioDAO();
    }
    
    public INotificacaoDAO getNotificacaoDAO(){
        return this.sgbd.getNotificacaoDAO();
    }
    
    private void getProperties(){
        Configuracao config = Configuracao.getInstancia();
        
        properties = config.getProp("SGBD");
    }
}