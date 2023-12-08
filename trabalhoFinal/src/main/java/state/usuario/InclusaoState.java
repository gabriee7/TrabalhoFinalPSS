/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state.usuario;

import command.usuario.IUsuarioCommand;
import command.usuario.SalvarCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presenter.UsuarioPresenter;
import view.UsuarioView;

/**
 *
 * @author nitro5WIN10
 */
public class InclusaoState extends UsuarioState{
    private IUsuarioCommand comando = null;
    
    
    public InclusaoState(UsuarioPresenter presenter) {
        super(presenter);
        configuraTela();
    }
    
    @Override
    public void salvar(){
        comando = new SalvarCommand();
        comando.executa();
        presenter.setEstado(new EdicaoState(presenter));
    }
    
    @Override
    public void fechar(){
        presenter.getView().setVisible(false);
    }
    
    @Override
    public void configuraTela(){
        UsuarioView view = presenter.getView();
        
        view.getBtnExcluir().setVisible(false);
        view.getBtnEditar().setVisible(false);
        view.setTitle("Cadastro de Usuário");
        
        
        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    salvar();
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                try{
                    fechar();
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
        

        view.setVisible(true);
    }
}
