/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import service.GerenciadorUsuarioService;
import view.ListaUsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class ListaUsuarioPresenter {
    private ListaUsuarioView view;
    private GerenciadorUsuarioService service;

    public ListaUsuarioPresenter() {
        this.service = new GerenciadorUsuarioService();
        this.view = new ListaUsuarioView();
        configura();
    }
    
    
    private void configura(){
        DefaultTableModel modeloTabela = new DefaultTableModel();
        view.setTitle("Listar Usuarios");
        view.getTableTodosUsuarios().setModel(modeloTabela);
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Data de Cadastro");
        modeloTabela.setRowCount(0);
        
        List<Usuario> usuarios = service.listarTodos();
         
        for(Usuario usuario:usuarios){
            Object[] dados = {usuario.getNome(), usuario.getDataCadastro()};
            modeloTabela.addRow(dados);
        }
        view.setVisible(true);
    }

    public ListaUsuarioView getView() {
        return view;
    }
    
    
}
