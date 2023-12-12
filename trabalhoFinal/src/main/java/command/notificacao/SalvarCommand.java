/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.notificacao;

import java.util.List;
import model.Notificacao;
import model.Usuario;
import service.GerenciadorNotificacaoService;
import service.GerenciadorUsuarioService;

/**
 *
 * @author nitro5WIN10
 */
public class SalvarCommand implements INotificacaoCommand {
    private GerenciadorNotificacaoService service;
    private Notificacao notificacao;
    private List<Usuario> usuarios;
    
    public SalvarCommand(Notificacao notificacao, List<Usuario>usuarios) {
        this.service = new GerenciadorNotificacaoService();
        this.notificacao = notificacao;
        this.usuarios = usuarios;
    }
    
    public void executa(){
        service.inserir(notificacao, usuarios);
    }
}
//    public void atualizaTabela(){
//        List<Usuario> usuarios = service.listarTodos();
//
//        for(Usuario usuario:usuarios){
//            Object[] dados = {usuario.getNome(), usuario.getDataCadastro()};
//            modeloTabela.addRow(dados);
//        }
//    }
//}
        // Supondo que você tenha uma lista de usuários do tipo "padrao"
//        List<Usuario> usuarios = obterUsuariosPadrao();
//
//        // Criar uma nova notificação
//        Notificacao notificacao = new Notificacao();
//        notificacao.setTitulo("Título da Notificação");
//        notificacao.setMensagem("Conteúdo da Notificação");
//
//        // Testar o método criar
//        boolean sucesso = service.inserir(notificacao, usuarios);
//
//        if (sucesso) {
//            System.out.println("Notificação criada com sucesso!");
//        } else {
//            System.out.println("Falha ao criar a notificação.");
//        }

//    private static List<Usuario> obterUsuariosPadrao() {
//        // Implemente a lógica para obter a lista de usuários do tipo "padrao"
//        // Aqui você pode usar o seu serviço de gerenciamento de usuários ou banco de dados
//        // Simplesmente retorne uma lista de usuários para fins de teste.
//        List<Usuario> usuariosPadrao = new ArrayList<>();
//        // Adicione usuários à lista, conforme necessário.
//        GerenciadorUsuarioService uService = new GerenciadorUsuarioService();
//        usuariosPadrao = uService.listarTodos();
//        return usuariosPadrao;
//    }
