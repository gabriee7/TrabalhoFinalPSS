/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package log;
import com.log. *; // repositorio github projeto maven externo com jitpack
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import properties.Configuracao;
import service.Sessao;
//"com.log.CSVLog"

/**
 *
 * @author nitro5WIN10
 */
public class LogAdapter {
    private LogService log;
    public static LogAdapter instancia = null;
    
    private LogAdapter() {
        this.log = new LogService(getProperties());
    }

    public static LogAdapter getInstancia(){
        if(instancia == null)
            instancia = new LogAdapter();
               
        return instancia;
    }
    
    private String getProperties(){
        Configuracao config = Configuracao.getInstancia();
        
        return config.getProp("LOG");
    }
    
    public void addLog(String operacao, String mensagem){
        String usuario = Sessao.getInstancia().getUsuarioLogado().getNome();
        if(usuario ==null)
            usuario = "";
            
        //NÃO FUNCIONOU
//        LocalDate data = LocalDate.now();
//        LocalTime hora = LocalTime.now();
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        String dataFormatada = formatador.format(data);
//        String horaFormatada = formatador.format(hora);

        
        log.addLog("["+ operacao + ": "+ mensagem +","+ "("+ LocalDate.now().toString() +", "+LocalTime.now().toString() + ", " + usuario + ")]");
    }
}
