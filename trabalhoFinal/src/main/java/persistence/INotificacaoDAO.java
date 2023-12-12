/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence;

import java.util.List;
import model.Notificacao;
import model.Usuario;

/**
 *
 * @author nitro5WIN10
 */
public interface INotificacaoDAO {
    public boolean criar(Notificacao notificacao, List<Usuario> usuariosPadrao);
    public Notificacao consultar(int id);
    public void atualizar(Notificacao notificacao);
    public boolean deletar(int id);
    public List<Notificacao> listarTodos();
    public List<Notificacao> listaPorUsuario(int idUsuario);
    public List<Notificacao> listaLidas(int idUsuario);
    public void marcaLida(Notificacao notificacao);
}
