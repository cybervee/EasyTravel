package com.easytravel;

import java.util.Scanner;
import com.easytravel.tratamentoexcecoes.*;

public class CartaoDebito extends Cartao {
    private float saldo;

    //construtores
    public CartaoDebito() {
    }

    //getters e setters
    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    //métodos
    @Override
    public void cadastroCartao() throws CartaoNumeroInvalido, DadoNaoPreenchido {
        Scanner leituraCartaoDebito = new Scanner(System.in);
        System.out.print("\nInsira o número do cartão: ");
        String numeroCartao = leituraCartaoDebito.nextLine();

        //validações do número de cartão
        if(numeroCartao.isEmpty()){
            throw new DadoNaoPreenchido("Erro! Dado não preenchido.");
        }
        else if(super.validacaoNumeroLuhn(numeroCartao)){
            super.setNumeroCartao(numeroCartao);
        }
        else{
            throw new CartaoNumeroInvalido("Número de cartão inválido!");
        }
        System.out.print("Insira o saldo: ");
        setSaldo(leituraCartaoDebito.nextFloat());
    }

    @Override
    public void imprimeDadosCartao() {
        System.out.print("\n*** Dados do Cartão de Crédito ***" +
                "\nÚltimos 4 números: " + super.getNumeroCartao().substring(12) +
                "\nSaldo: R$ " + this.getSaldo());
    }

    //validação do pagamento
    @Override
    public void pagamentoCartao(float totalCarrinho) throws CartaoRecusado {
        if(totalCarrinho < saldo){
            this.setSaldo(getSaldo() - totalCarrinho);
            System.out.print("\n\nCompra efetuada com sucesso! O saldo agora é R$ " + getSaldo());
            super.setValidacaoCompra(true);
        }
        else{
            super.setValidacaoCompra(false);
            throw new CartaoRecusado("Desculpe, você não tem saldo o suficiente para essa compra :(");
        }
    }
}