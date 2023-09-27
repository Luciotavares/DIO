/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meubanco;

import meubanco.excecoes.SaldoInsuficiente;
import java.util.InputMismatchException;
import java.util.Scanner;
import meubanco.excecoes.ContaNaoLocalizada;
import meubanco.excecoes.IndiceForaDosLimites;
import meubanco.excecoes.NaoHaEspacoSuficiente;

/**
 *
 * @author Felipe Marques
 */
public class Banco {
    private final Correntista[] contas;
    private int numeroDeContas;
    
    public Banco(int numeroMaximoDeContas) {
        contas = new Correntista[numeroMaximoDeContas];
        numeroDeContas = 0;
    }
    
    public void incluirConta(Correntista correntista) {
        if (numeroDeContas < contas.length) {
            contas[numeroDeContas++] = correntista;
        } else {
            throw new NaoHaEspacoSuficiente("\nErro: Não há mais espaço para inserir novas contas.\n");
        }
    }
    
    public Correntista excluirConta(int indice) {
        if (indice >= 0 && indice < numeroDeContas) {
            Correntista conta = contas[indice]; // Para preservar a referência à conta excluída
            for (int i = indice; i < numeroDeContas-1; i++)
                contas[i] = contas[i+1];
            numeroDeContas--;
            return conta;
        }
        throw new IndiceForaDosLimites("\nErro: Não foi possível excluir a conta. Índice inválido.\n");
    }
    
    public void listarContas() {
        System.out.println("### Listagem dos Correntistas do Meu Banco ###");
        for (int i = 0; i < numeroDeContas; i++) {
            System.out.println("-----------------------------------------------------------");
            System.out.println(contas[i]);
        }
    }
    
    public int localizarIndiceDaConta(int numeroDaConta) {
        int i = 0;
        while (i < contas.length) {
            if (contas[i].verificaNumeroDaConta(numeroDaConta))
                return i;
            i++;
        }
        throw new ContaNaoLocalizada("\nErro: A conta de número " + numeroDaConta + " não foi localizada.\n");
    }
    
    public Correntista localizarConta(int indice) {
        if (indice >= 0 && indice < numeroDeContas) {
            return contas[indice];
        }
        throw new IndiceForaDosLimites("\nErro: Não foi possível localizar a conta. Índice inválido.\n");
    }
       
    @Override
    public String toString() {
        return "Meu Banco: " + numeroDeContas + " correntistas cadastrados.";
    }
    
    // -------------------------------------------------------------------------------------
    // -- Métodos adicionados para criar uma interface para interação com o usuário --------
    // -------------------------------------------------------------------------------------
    private int menu() {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("###  BEM-VINDO AO MEU BANCO ###");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Abrir nova conta");
            System.out.println("2 - Encerrar uma conta");
            System.out.println("3 - Localizar uma conta");
            System.out.println("4 - Listar todas as contas");
            System.out.println("5 - Sair");
            try {
                opcao = sc.nextInt();
            } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
                System.out.println("\nErro: O valor digitado não corresponde a um número.\n");
            } finally {
                sc.nextLine();
            }
        } while(opcao < 1 && opcao > 5);
        return opcao;    
    }

    private void menuPessoaFisica() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        try {
            incluirConta(new PessoaFisica(cpf, nome));
        } catch (NaoHaEspacoSuficiente e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void menuPessoaJuridica() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o CNPJ: ");
        String cnpj = sc.nextLine();
        System.out.print("Digite a razão social: ");
        String rs = sc.nextLine();
        System.out.print("Digite a inscrição estadual: ");
        String ie = sc.nextLine();
        try {
            incluirConta(new PessoaJuridica(cnpj, rs, ie));
        } catch (NaoHaEspacoSuficiente e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void menuIncluir() {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Qual o tipo da conta?");
            System.out.println("1 - Pessoa Física");
            System.out.println("2 - Pessoa Jurídica");
            System.out.println("3 - Cancelar");
            try {
                opcao = sc.nextInt();
            } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
                System.out.println("\nErro: O valor digitado não corresponde a um número.\n");
            } finally {
                sc.nextLine();
            }
        } while(opcao < 1 && opcao > 3);
        if (opcao == 1)
            menuPessoaFisica();
        else if (opcao == 2)
            menuPessoaJuridica();
    }
    
    private void menuExcluir() {
        int nconta;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Digite o número da conta: ");
            nconta = sc.nextInt();
            excluirConta(localizarIndiceDaConta(nconta));
        } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
            System.out.println("\nErro: O valor digitado não corresponde a um número de conta válido.\n");
        } catch(ContaNaoLocalizada | IndiceForaDosLimites e) { // Quando o valor do número da conta não é encontrado no array
            System.out.println(e.getMessage());
        }
        // Pode ser lançada pelo método de exclusão
         finally {
            sc.nextLine();
        }
    }
    
    private void menuLocalizarConta() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Digite o número da conta: ");
            int nconta = sc.nextInt();
            Correntista conta = localizarConta(localizarIndiceDaConta(nconta));
            menuDoCorrentista(conta);
        } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
            System.out.println("\nErro: O valor digitado não corresponde a um número de conta válido.\n");
        } catch(ContaNaoLocalizada | IndiceForaDosLimites e) { // Quando o valor do número da conta não é encontrado no array
            System.out.println(e.getMessage());
        }
        // Pode ser lançada pelo método que localiza conta pelo índice
         finally {
            sc.nextLine();
        }
    }
    
    private void menuDoCorrentista(Correntista conta) {
        int opcao = 0;        
        Scanner sc = new Scanner(System.in);
        while (opcao != 4) {
            do {
                System.out.println("#========================================================================#");
                System.out.println(conta);
                System.out.println("#========================================================================#");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Transferir");
                System.out.println("4 - Voltar");
                try {
                    opcao = sc.nextInt();
                } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
                    System.out.println("\nErro: O valor digitado não corresponde a um número.\n");
                } finally {
                    sc.nextLine();
                }
            } while(opcao < 1 && opcao > 4);
        
            switch(opcao) {
                case 1: // Depositar
                    System.out.println("- DEPÓSITO -");
                    System.out.print("Digite o valor: ");
                    try {
                        double valor = sc.nextDouble();
                        conta.depositar(valor);
                    } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
                        System.out.println("\nErro: O valor digitado é inválido.\n");
                    } finally {
                        sc.nextLine();
                    }
                    break;
                case 2: // Sacar
                    System.out.println("- SAQUE -");
                    System.out.print("Digite o valor: ");
                    try {
                        double valor = sc.nextDouble();
                        conta.sacar(valor);
                    } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
                        System.out.println("\nErro: O valor digitado é inválido.\n");
                    } catch (SaldoInsuficiente e) {
                        System.out.println(e.getMessage());
                    } finally {
                        sc.nextLine();
                    }
                    break;
                case 3: // Transferir
                    System.out.println("- TRANSFERENCIA -");
                    try {
                        System.out.print("Digite o número da conta do favorecido: ");
                        int nconta = sc.nextInt();
                        Correntista contaDestino = localizarConta(localizarIndiceDaConta(nconta));
                        System.out.print("Digite o valor: ");
                        double valor = sc.nextDouble();
                        conta.transferir(valor, contaDestino);
                    } catch(InputMismatchException ime) { // if the next token does not match the Integer regular expression
                        System.out.println("\nErro: O valor digitado não corresponde a um número de conta válido.\n");
                    } catch(ContaNaoLocalizada | IndiceForaDosLimites | SaldoInsuficiente e) { // Quando o valor do número da conta não é encontrado no array
                        System.out.println(e.getMessage());
                    }
                // Pode ser lançada pelo método de exclusão
                 finally {
                        sc.nextLine();
                    }
                    break;

            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco meuBanco = new Banco(5);
        
        // -- Inclusão de contas para teste ------------------------------------
        meuBanco.incluirConta(new PessoaFisica("99999999999", "Darth Vader"));
        meuBanco.incluirConta(new PessoaFisica("12345678910", "Luke Skywalker"));
        meuBanco.incluirConta(new PessoaJuridica("666666666666666", "Imperio", "6660000666"));
        meuBanco.incluirConta(new PessoaJuridica("123400001234099", "Rebeldes", "0123456789"));
        meuBanco.incluirConta(new PessoaFisica("89101234567", "Yoda"));

        //-- Impementação do controle do menu ----------------------------------
        int opcao = 0;
        while (opcao != 5) {
            opcao = meuBanco.menu();
            switch (opcao) {
                case 1 : meuBanco.menuIncluir(); break;
                case 2 : meuBanco.menuExcluir(); break;
                case 3 : meuBanco.menuLocalizarConta(); break;
                case 4 :
                    System.out.println("======================================================================");
                    System.out.println("= " + meuBanco + " =");
                    meuBanco.listarContas();
                    System.out.println("======================================================================");
                    break;                
            }
        }
    }
}
