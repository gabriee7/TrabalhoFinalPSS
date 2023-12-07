/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;
import model.Usuario;
import persistence.Factory.DAOFactoryService;
import persistence.IUsuarioDAO;

/**
 *
 * @author nitro5WIN10
 */
public class GerenciadorUsuarioService {
    private IUsuarioDAO usuarioDAO;
    private DAOFactoryService factoryService;
    private Usuario usuarioAutenticado;
    public static GerenciadorUsuarioService instancia = null;
    
    public GerenciadorUsuarioService() {
        this.factoryService = new DAOFactoryService();
        this.usuarioDAO = factoryService.getUsuarioDAO();
        this.usuarioAutenticado = Sessao.getInstancia().getUsuarioLogado();
    }
   
    public void inserir(String nome, String senha){
        ValidadorSenha validador = new ValidadorSenha();
        List<String> resultValida = validador.validar(senha);
        
        if(!resultValida.isEmpty()){
            throw new RuntimeException("Erro: \n" + resultValida.toString().replace(";", "\n").replace(",", "").replace("[", "").replace("]", ""));
        }
        
        Usuario usuario = new Usuario(nome, senha);
        if(listarTodos().isEmpty()){
           usuario.setTipo("admin");
           usuario.setAtivo(true);
        }else if(usuarioAutenticado == null || !"admin".equalsIgnoreCase(usuarioAutenticado.getTipo())){
            usuario.setAtivo(false);
            usuario.setTipo("padrao");
        }else{
            usuario.setAtivo(true);
            usuario.setTipo("padrao");
        }
        usuarioDAO.criar(usuario);
    }
   
    public List<Usuario> listarTodos(){
        return usuarioDAO.listarTodos();
    }
   
    public boolean excluir(int id){
       return usuarioDAO.deletar(id);
    }
   
    public Usuario consultar(String nome){
       return usuarioDAO.consultar(nome);
    }
   
    public void alterarSenha(Usuario usuario){
       usuarioDAO.atualizar(usuario);
    }
}
