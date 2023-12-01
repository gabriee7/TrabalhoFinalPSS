/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence;

import model.Usuario;

/**
 *
 * @author nitro5WIN10
 */
public interface IUsuarioDAO {
    public boolean criar(Usuario usuario);
    public boolean consultar(Usuario usuario);
    public void atualizar(Usuario usuario);
    public void deletar(Usuario usuario);
//    public List<Usuario> listarTodos();
}
