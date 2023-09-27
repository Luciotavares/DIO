/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodedisciplinas;

/**
 *
 * @author felipe
 */
public class Disciplina {
    private final String codigo;
    private final String nome;
    
    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    public String pegaCodigo() {
        return codigo;
    }
    
    @Override
    public String toString() {
        return "Código: " + codigo + "\t| Disciplina: " + nome;
    }
}
