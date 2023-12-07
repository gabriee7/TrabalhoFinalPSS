/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import log.ILog;
import io.github.cdimascio.dotenv.Dotenv;
import java.lang.reflect.Method;
import persistence.Factory.IDAOFactory;
import properties.Configuracao;
/**
 *
 * @author nitro5WIN10
 */
public class LogService {
    private ILog metodoLog;
    private String properties;

    public LogService() {
        metodoLog = getLogFactory();
    }
    
    private ILog getLogFactory(){  
        try{
            getProperties();
            Class<?> classeProp = Class.forName(properties);
            Object instanciaLog = classeProp.getDeclaredConstructor().newInstance();
            return (ILog)instanciaLog;
        }catch(Exception e) {
            throw new RuntimeException("Erro: LOG não suportado! \n" + e.getMessage());
        }
    }
    
    public void addLog(String mensagem){
        //tratar exceçao caso metodoLog esteja nulo
        metodoLog.addLog(mensagem);
    }
    
    private void getProperties(){
        Configuracao config = Configuracao.getInstancia();
        
        properties = config.getProp("SGBD");
    }
}
