/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.notificacao;

import command.notificacao.INotificacaoCommand;
import command.notificacao.SalvarCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Notificacao;
import model.Usuario;
import presenter.NotificacaoPresenter;
import service.GerenciadorUsuarioService;
import view.NotificacaoView;

/**
 *
 * @author nitro5WIN10
 */
public class InclusaoState extends NotificacaoState {
    private NotificacaoView view;
    private INotificacaoCommand comando = null;
    private GerenciadorUsuarioService serviceUsuario;
    private List<Usuario> nomeUsuarios;
    private String usuarioAdicionados = "";

    public InclusaoState(NotificacaoPresenter presenter) {
        super(presenter);
        this.serviceUsuario = new GerenciadorUsuarioService();
        this.view = presenter.getView();
        this.nomeUsuarios = new ArrayList<>();
        configuraTela();
    }
    
    @Override
    public void salvar(){
        String titulo = view.getTextFieldTitulo().getText();
        String mensagem = view.getTextAreaMensagem().getText();
        Usuario temp;

        for(Usuario elem: nomeUsuarios){
            elem.setId((serviceUsuario.consultar(elem.getNome()).getId()));
        }

        Notificacao notificacao = new Notificacao(titulo, mensagem);
        
        comando = new SalvarCommand(notificacao, nomeUsuarios);
        comando.executa();
    }

    @Override
    public void fechar(){
        view.setVisible(false);
    }
    

    public void atualizaTabela(DefaultTableModel modeloTabela){
        List<Usuario> usuarios = serviceUsuario.listarTodos();
        Object[] dado = {"TODOS"};
        modeloTabela.addRow(dado);
        int i = 0;
        
        for(Usuario usuario:usuarios){
            Object[] dados = {usuario.getNome()};
            modeloTabela.addRow(dados);
            i++;
        }
    }
 
    private void adicionarUsuario(){
        int i = view.getTableUsuarios().getSelectedRow();
        String nome =view.getTableUsuarios().getValueAt(i,0).toString();
//        nomeUsuarios.add(view.getTableUsuarios().getValueAt(i,0).toString());
        nomeUsuarios.add(new Usuario(nome, null));
        usuarioAdicionados += nome + "\n";
        view.getTextAreaUsuarios().setText(usuarioAdicionados);
    }
    
    @Override
    public void configuraTela(){
        view.setVisible(false);
        DefaultTableModel modeloTabela = new DefaultTableModel();
        view.getTableUsuarios().setModel(modeloTabela);
        modeloTabela.addColumn("Nome");
        modeloTabela.setRowCount(0);
        atualizaTabela(modeloTabela);
        
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        
        
        view.getTableUsuarios().setRowSelectionAllowed(false);

        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    salvar();
                }catch(Exception e){
                    
                }
            }
        });
        
        
        view.getBtnAdicionar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    adicionarUsuario();
                }catch(Exception e){
                    
                }
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    
                }
            }
        });
        
        view.setVisible(true);
    }
        
        
}

