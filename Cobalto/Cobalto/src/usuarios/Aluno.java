/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import cadastrodedisciplinas.Disciplina;
import cadastrodedisciplinas.Disciplinas;
import cadastrodedisciplinas.DisciplinasComputacao;

/**
 *
 * @author felipe
 */
public class Aluno extends Usuario {
    private String matricula;
    private Data primeiraMatricula;
    private Disciplinas historico;
    
    public Aluno(String nome, String cpf, String email, Data data,
                 String matricula, Data primeiraMatricula) {
        super(nome, cpf, email, data);
        this.matricula = matricula;
        this.primeiraMatricula = primeiraMatricula;
        this.historico = new DisciplinasComputacao();
    }
    
    public void efetuarMatricula(Disciplina d) {
        historico.guarda(d);
    }
    
    @Override
    public String toString() {
        String str = super.toString();
        str += "\nNúmero de Matrícula: " + matricula + "\n";
        str += "Data da Primeira Matrícula: " + primeiraMatricula + "\n";
        str += historico;
        return str;
    }
}
