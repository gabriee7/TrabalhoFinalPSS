/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package properties;

/**
 *
 * @author nitro5WIN10
 */
import java.io.*;
import java.util.Properties;

public class Configuracao {
    public static Configuracao instancia = null;
    private Properties prop = null;

    private Configuracao() {
        prop = new Properties();
    }

    public static Configuracao getInstancia(){
        if(instancia == null)
            instancia = new Configuracao();
        
        return instancia;
    }
    
    public String getProp(String chave){
        try (
            InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            return prop.getProperty(chave.toUpperCase());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public void setProp(String chave, String valor){
    try (        
        OutputStream output = new FileOutputStream("config.properties")) {
            prop.setProperty(chave.toUpperCase(), valor);
            prop.store(output, null);
            System.out.println("Propriedade gravada com sucesso.");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());

        }
    }
}