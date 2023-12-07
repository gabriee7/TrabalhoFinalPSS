/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author nitro5WIN10
 */
public class DotEnvService {
    
    // EXCLUIR EM BREVE E APAGAR DEPENDENCIA NO POM.XML
    public String getDotEnv(String param){
        Dotenv dotenv = Dotenv.configure().load();
        
        return dotenv.get(param);
    }
    
//    public void setDotEnv(String param){
//        Dotenv dotenv = Dotenv.configure().load();
//        
//    }
}
