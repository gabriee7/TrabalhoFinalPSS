/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author nitro5WIN10
 */
public class CSVLog implements ILog {
    private static CSVLog instancia;
    private String arquivoCSV;

    private CSVLog() {
        arquivoCSV = "logs.csv";
        criaArquivo();
    }

    public static CSVLog getInstancia() {
        if (instancia == null)
            instancia = new CSVLog();

        return instancia;
    }

    public void addLog(String mensagem) {
        salvarLog(mensagem);
    }

    private void criaArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            if (reader.readLine() == null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV, true))) {
                    writer.write("Log;\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarLog(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV, true))) {
            writer.write(mensagem);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}