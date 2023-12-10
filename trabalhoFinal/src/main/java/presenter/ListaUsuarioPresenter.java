/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import service.GerenciadorUsuarioService;
import state.usuario.UsuarioState;
import state.usuario.VisualizacaoState;
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
        
        atualizaTabela(modeloTabela);
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        
        view.getBtnVisualizaDetalhes().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    visualizarDetalhes(modeloTabela);
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.setVisible(true);
    }

    public void atualizaTabela(DefaultTableModel modeloTabela){
        List<Usuario> usuarios = service.listarTodos();

        for(Usuario usuario:usuarios){
            Object[] dados = {usuario.getNome(), usuario.getDataCadastro()};
            modeloTabela.addRow(dados);
        }
    }
    
    private void fechar(){
        view.setVisible(false); //alterar implementacao
    }
    
    private void visualizarDetalhes(DefaultTableModel modeloTabela){
        int linhaSelecionada = view.getTableTodosUsuarios().getSelectedRow();
        
        if(linhaSelecionada == -1){
            throw new RuntimeException("Nenhuma linha selecionada.");
        }
        
        String nome = modeloTabela.getValueAt(linhaSelecionada, 0).toString();
        UsuarioPresenter visualizaPresenter = new UsuarioPresenter();
        visualizaPresenter.getView().setVisible(false);
        visualizaPresenter.setEstado(new VisualizacaoState(visualizaPresenter, nome, null));
        visualizaPresenter.getView().setVisible(true);
    }
    
    public ListaUsuarioView getView() {
        return view;
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
}
