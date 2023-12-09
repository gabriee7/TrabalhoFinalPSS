/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.usuario;

import service.GerenciadorUsuarioService;

/**
 *
 * @author nitro5WIN10
 */
public class ExcluirCommand implements IUsuarioCommand {
    private GerenciadorUsuarioService service;
    private String nome;
    
    public ExcluirCommand(String nome) {
        service = new GerenciadorUsuarioService();
        this.nome = nome;
    }
    
    @Override
    public void executa(){
        service.excluir(nome);
    }
}
