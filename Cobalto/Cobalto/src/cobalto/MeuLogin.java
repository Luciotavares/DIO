/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cobalto;

import lista.Repositorio;
import usuarios.Pessoa;

/**
 *
 * @author felipe
 */
public class MeuLogin implements Login {

    @Override
    public boolean verifica(String cpf, Repositorio r) {
        if (r.recupera(cpf) != null)
            return true;
        return false;
    }
    
}
