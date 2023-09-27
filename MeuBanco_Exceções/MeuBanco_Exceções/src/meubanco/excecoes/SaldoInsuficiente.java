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
public class SaldoInsuficiente extends ExcecoesDoBanco {
    public SaldoInsuficiente() {
        super();
    }
    
    public SaldoInsuficiente(String mensagem) {
        super(mensagem);
    }
}
