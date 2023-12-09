/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence;

import java.util.List;
import model.Usuario;

/**
 *
 * @author nitro5WIN10
 */
public interface IUsuarioDAO {
    public boolean criar(Usuario usuario);
    public Usuario consultar(String nome);
    public void atualizar(Usuario usuario);
    public boolean deletar(String nome);
    public List<Usuario> listarTodos();
    public List<Usuario> listaInativo();
}
