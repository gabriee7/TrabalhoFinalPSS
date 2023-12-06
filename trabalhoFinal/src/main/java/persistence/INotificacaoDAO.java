/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence;

import java.util.List;
import model.Notificacao;

/**
 *
 * @author nitro5WIN10
 */
public interface INotificacaoDAO {
    public boolean criar(Notificacao notificacao);
    public Notificacao consultar(int id);
    public void atualizar(Notificacao notificacao);
    public boolean deletar(int id);
    public List<Notificacao> listarTodos();
}