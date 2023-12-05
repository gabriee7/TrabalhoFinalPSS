/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import log.ILog;
import io.github.cdimascio.dotenv.Dotenv;
import java.lang.reflect.Method;
/**
 *
 * @author nitro5WIN10
 */
public class LogService {
    private ILog metodoLog;
    private String env;

    public LogService() {
        setMetodo();
    }
    
    private void setMetodo(){  
        getDotEnv();
        try{
            Class<?> classeEnv = Class.forName(env);
            Method metodoGetInstancia = classeEnv.getMethod("getInstancia");
            Object instancia = metodoGetInstancia.invoke(null);
            metodoLog = (ILog)instancia;
        }catch(Exception e) {
            throw new RuntimeException("Erro: LOG não suportado! \n" + e.getMessage());
        }
    }
    
    private void getDotEnv(){
        Dotenv dotenv = Dotenv.configure().load();

        env = dotenv.get("LOG");
    }
    
    public void addLog(String mensagem){
        metodoLog.addLog(mensagem);
    }
}
