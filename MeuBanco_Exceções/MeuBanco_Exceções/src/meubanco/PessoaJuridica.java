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
public class PessoaJuridica extends Correntista {
    public final String cnpj;
    public final String razaoSocial;
    public final String inscricaoEstadual;
    
    public PessoaJuridica(String cnpj, String razaoSocial, String inscricaoEstadual) {
        super();
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.razaoSocial = razaoSocial;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "CNPJ: " + cnpj
                + "\nRazao Social: " + razaoSocial
                + "\nInscrição Estadual: " + inscricaoEstadual;
                
    }
}
