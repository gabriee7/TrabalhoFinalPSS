/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author nitro5WIN10
 */
public class Usuario {
    private String nome;
    private String senha;
    private String tipo;
    private boolean ativo;
    private LocalDate dataCadastro;
    private int id;

    public Usuario(String nome, String senha, String tipo, LocalDate data) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.ativo = false;
        this.dataCadastro = data;
    }
    
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.ativo = false;
    }
    public Usuario() {
        this.nome = null;
        this.tipo = null;
        this.senha = null;
        this.ativo = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean autenticado) {
        this.ativo = autenticado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    
    
}
