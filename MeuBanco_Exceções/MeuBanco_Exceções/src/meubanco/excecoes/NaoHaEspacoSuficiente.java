/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meubanco.excecoes;

/**
 *
 * @author Felipe Marques
 */
public class NaoHaEspacoSuficiente extends ExcecoesDoBanco {
    public NaoHaEspacoSuficiente() {
        super();
    }
    
    public NaoHaEspacoSuficiente(String mensagem) {
        super(mensagem);
    }    
}
