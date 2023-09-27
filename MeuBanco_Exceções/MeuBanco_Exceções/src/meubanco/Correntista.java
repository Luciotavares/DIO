/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meubanco;

import meubanco.excecoes.SaldoInsuficiente;

/**
 *
 * @author Felipe Marques
 */
public abstract class Correntista {
    private static int contadorNumeroDaConta = 10000;
    private final int numeroDaConta;
    private double saldo;
    
    public Correntista() {
        numeroDaConta = ++contadorNumeroDaConta;
        saldo = 0;
    }
    
    public void depositar(double valor) {
        saldo += valor;
    }
    
    public void sacar(double valor) {
        if (valor <= saldo)
            saldo -= valor;
        else
            throw new SaldoInsuficiente("\nErro: Saldo Insuficiente - Tentativa de saque: " + valor + " Saldo atual: " + saldo + "\n");
    }
    
    public void transferir(double valor, Correntista contaDestino) throws SaldoInsuficiente {
        sacar(valor);
        contaDestino.depositar(valor);
    }
    
    public boolean verificaNumeroDaConta(int numero) {
        return numero == numeroDaConta;
    }
    
    @Override
    public String toString() {
        return "Conta: " + numeroDaConta + " - Saldo: " + saldo;
    }
}
