/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package log;

import org.json.JSONArray;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author nitro5WIN10
 */


public class JsonLog implements ILog {
    private static JsonLog instancia;
    private String arquivoJson;

    private JsonLog() {
        arquivoJson = "logs.json";
        criaArquivo();
    }

    public static JsonLog getInstancia() {
        if (instancia == null)
            instancia = new JsonLog();

        return instancia;
    }

    public void addLog(String mensagem) {
        JSONArray jsonArray = lerArquivo();
        jsonArray.put(mensagem);
        salvarArquivo(jsonArray);
    }

    private void criaArquivo() {
        File arquivo = new File(arquivoJson);

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

private JSONArray lerArquivo() {
    JSONArray jsonArray = new JSONArray();
    File arquivo = new File(arquivoJson);

    if (arquivo.exists()) {
        try (FileReader fileReader = new FileReader(arquivo)) {
            int fileSize = (int) arquivo.length();

            if (fileSize > 0) {
                char[] content = new char[fileSize];
                fileReader.read(content);

                jsonArray = new JSONArray(new String(content));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    return jsonArray;
}

private void salvarArquivo(JSONArray jsonArray) {
    try (FileWriter fileWriter = new FileWriter(arquivoJson)) {
        if (jsonArray.length() == 0) {
            fileWriter.write("[]");
        } else {
            fileWriter.write(jsonArray.toString());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

