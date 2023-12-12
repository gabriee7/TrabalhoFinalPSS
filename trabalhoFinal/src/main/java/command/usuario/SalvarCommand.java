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
public class SalvarCommand implements IUsuarioCommand {
    private GerenciadorUsuarioService service;
    private String nome;
    private String senha;
    
    public SalvarCommand(String nome, String senha) {
        service = new GerenciadorUsuarioService();
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public void executa(){
        service.inserir(nome, senha);
    }
}
