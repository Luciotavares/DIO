/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobalto;

import cadastrodedisciplinas.*;
import io.DataBase;
import java.io.IOException;
import java.util.Scanner;
import lista.*;
import usuarios.*;

/**
 *
 * @author felipe
 */
public class Cobalto {
    private Repositorio alunos;
    private Disciplinas disciplinas;
    private String cpf;
    
    public Cobalto() {
        alunos = new MeuRepositorio();
        disciplinas = new DisciplinasComputacao();
        cpf = null;
        
        try {
            DataBase.lerAlunosDoArquivo("DadosDosAlunos.txt", alunos);
        } catch (IOException e) {
            System.err.println("Erro na abertura do arquivo de alunos.\n" + e.getMessage());
            System.exit(1);
        }

        try {
            DataBase.lerDisciplinasDoArquivo("Disciplinas.txt", disciplinas);
        } catch (IOException e) {
            System.err.println("Erro na abertura do arquivo de disciplinas.\n" + e.getMessage());
            System.exit(1);
        }
    }

    public void login() {
        boolean login = false;
        while (login == false) {
            System.out.println("+=======================================================+");
            System.out.println("|                        COBALTO                        |");
            System.out.println("+=======================================================+");
            System.out.print("Digite seu CPF: ");
            Scanner sc = new Scanner(System.in);
            cpf = sc.nextLine();
            Login l = new MeuLogin();
            login = l.verifica(cpf, alunos);
        }
    }
    
    public void menu() {
        char opcao;
        do {
            System.out.println("+=======================================================+");
            System.out.println("| 1 - Mostrar dados do aluno                            |");
            System.out.println("| 2 - Matricular em uma disciplina                      |");
            System.out.println("| 3 - Sair                                              |");
            System.out.println("+=======================================================+");
            System.out.print("Digite o número correspondente a uma das opções acima: ");
            Scanner sc = new Scanner(System.in);
            opcao = sc.next().charAt(0);
            switch (opcao) {
                case '1' :
                    System.out.println("\n-- Lista Completa de Alunos -----------------------------------");
                    System.out.println(alunos.recupera(cpf));
                    break;
                case '2' :
                    matriculaDisciplina();
                    break;
            }
        } while (opcao != '3');
    }
    
    public void matriculaDisciplina() {
        System.out.println("\n-- Lista Completa de Disciplinas do Curso ---------------------");
        System.out.println(disciplinas);
        System.out.print("Digite o código da disciplina: ");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();
        Disciplina d = disciplinas.recupera(codigo);
        if (d != null)
            ((Aluno) alunos.recupera(cpf)).efetuarMatricula(d);
        else
            System.out.println("\nCódigo da disciplina inválido!");            
    }
    
    public void imprimirListagemDeAlunos() {
        // Implemente aqui o método da questão 4
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cobalto cobalto = new Cobalto();
        
        cobalto.login();
        cobalto.menu();
        
        cobalto.imprimirListagemDeAlunos();
    }
    
    
    
}
