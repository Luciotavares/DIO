/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meubanco;

/**
 *
 * @author Felipe Marques
 */
public class PessoaFisica extends Correntista {
    private final String cpf;
    private final String nome;
    
    public PessoaFisica(String cpf, String nome) {
        super();
        this.cpf = cpf;
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "CPF: " + cpf
                + "\nNome: " + nome;                
    }    
}
