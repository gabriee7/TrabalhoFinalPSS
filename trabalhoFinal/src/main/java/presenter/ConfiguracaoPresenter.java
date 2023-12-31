/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Usuario;
import properties.Configuracao;
import service.GerenciadorUsuarioService;
import service.Sessao;
import view.ConfiguracaoView;

/**
 *
 * @author nitro5WIN10
 */
public class ConfiguracaoPresenter {
    private Usuario usuarioLogado;
    private ConfiguracaoView view;
    private GerenciadorUsuarioService service;
    
    public ConfiguracaoPresenter() {
        this.view = new ConfiguracaoView();
        this.service = new GerenciadorUsuarioService();
        this.usuarioLogado = Sessao.getInstancia().getUsuarioLogado();
        configura();
    }
    
    private void configura(){
        view.setVisible(false);
        view.getTextFieldNome().setText(usuarioLogado.getNome());
        view.getTextFieldDataCadastro().setText(usuarioLogado.getDataCadastro());
        view.getBtnExcluir().setVisible(false);
        view.getLabelSenhaAtual().setVisible(false);
        view.getLabelNovaSenha().setVisible(false);
        view.getLabelConfirmaSenha().setVisible(false);
        view.getTextFieldSenhaAtual().setVisible(false);
        view.getTextFieldNovaSenha().setVisible(false);
        view.getTextFieldConfirmaSenha().setVisible(false);
        view.getTextFieldNome().setEnabled(false);
        view.getTextFieldDataCadastro().setEnabled(false);
        view.getLabelLog().setVisible(false);
        view.getBoxLog().setVisible(false);
        
        if("admin".equals(usuarioLogado.getTipo()))
            view.getBtnAlterarLog().setVisible(true);
        
        view.getBtnAlterarSenha().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    alterarSenha();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        for (ActionListener listener : view.getBtnSalvar().getActionListeners()) {
            view.getBtnSalvar().removeActionListener(listener);
        }
        
        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    salvar();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
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
        
        view.getBtnAlterarLog().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    configAlterarLog();
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.setVisible(true);
    }

    public ConfiguracaoView getView() {
        return view;
    }
    
    private void salvarNovoLog(String novoLog){
        Configuracao.getInstancia().setProp("LOG", novoLog);
        exibirMensagem("Novo Log salvo!! Por favor reinicie a aplicação para a atualização.", "Log", 1);
        fechar();
    }
    
    private void configAlterarLog(){
        view.getLabelNome().setVisible(false);
        view.getLabelDataCadastro().setVisible(false);
        view.getTextFieldNome().setVisible(false);
        view.getTextFieldDataCadastro().setVisible(false);
        view.getBtnAlterarSenha().setVisible(false);
        view.getBtnAlterarLog().setVisible(false);

        view.getBoxLog().removeAllItems();
        
        String tiposProperties = Configuracao.getInstancia().getProp("LOGAVALIABLE");
        ArrayList<String> tiposConverter = new ArrayList<>();
       
        for (String item : tiposProperties.split("OR")) {
            tiposConverter.add(item);
        }
        
        for(String elem: tiposConverter){
            view.getBoxLog().addItem(elem);
        }
        
        for (ActionListener listener : view.getBtnSalvar().getActionListeners()) {
            view.getBtnSalvar().removeActionListener(listener);
        }

        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    String novoLog = view.getBoxLog().getSelectedItem().toString();
                    salvarNovoLog(novoLog);
                }catch(Exception e){
                    exibirMensagem(e.getMessage(), "Erro", 0);
                }
            }
        });
        
        view.getLabelLog().setVisible(true);
        view.getBoxLog().setVisible(true);
    }
    
    private void alterarSenha(){
        view.getBtnAlterarSenha().setVisible(false);
        view.getLabelSenhaAtual().setVisible(true);
        view.getLabelNovaSenha().setVisible(true);
        view.getLabelConfirmaSenha().setVisible(true);
        view.getTextFieldSenhaAtual().setVisible(true);
        view.getTextFieldNovaSenha().setVisible(true);
        view.getTextFieldConfirmaSenha().setVisible(true);
    }
    
    private void salvar(){
        String nome = Sessao.getInstancia().getUsuarioLogado().getNome();
        String senha = Sessao.getInstancia().getUsuarioLogado().getSenha();
        String senhaAtual = view.getTextFieldSenhaAtual().getText();
        String novaSenha = view.getTextFieldNovaSenha().getText();
        String confirmaSenha = view.getTextFieldConfirmaSenha().getText();
          
        if(!senha.equals(senhaAtual)){
            throw new RuntimeException("Senha atual incorreta!");
        }
        
        if(!novaSenha.equals(confirmaSenha)){
            throw new RuntimeException("Novas senhas não conferem");
        }
        
        service.alterarSenha(nome, novaSenha);
        exibirMensagem("Sua senha foi alterada com sucesso!", "Alteracao de Senha", 1);
        configura();
    }
    
    private void fechar(){
        view.setVisible(false);
    }
    
    public void exibirMensagem(String mensagem, String titulo, int type){
        JOptionPane.showMessageDialog(this.view, mensagem, titulo,type);
    }
    
    public int exibirConfirmacao(String mensagem, String titulo){
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
    }
    
}
