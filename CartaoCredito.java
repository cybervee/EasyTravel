package com.easytravel;

import java.util.Scanner;
import com.easytravel.tratamentoexcecoes.*;

public class CartaoCredito extends Cartao{
    private float limite;

    //construtores
    public CartaoCredito() {
    }

    //getters e setters
    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    //métodos
    @Override
    public void cadastroCartao() throws CartaoNumeroInvalido, DadoNaoPreenchido {
        Scanner leituraCartaoCredito = new Scanner(System.in);
        System.out.print("\nInsira o número do cartão: ");
        String numeroCartao = leituraCartaoCredito.nextLine();

        //validações do número do cartão
        if(numeroCartao.isEmpty()){
            throw new DadoNaoPreenchido("Erro! Dado não preenchido.");
        }
        else if(super.validacaoNumeroLuhn(numeroCartao)){
            super.setNumeroCartao(numeroCartao);
        }
        else{
            throw new CartaoNumeroInvalido("Número de cartão inválido!");
        }

        System.out.print("Insira o limite: ");
        setLimite(leituraCartaoCredito.nextFloat());
    }

    @Override
    public void imprimeDadosCartao() {
        System.out.print("\n*** Dados do Cartão de Crédito ***" +
                "\nÚltimos 4 números: " + super.getNumeroCartao().substring(12) +
                "\nLimite: R$ " + this.getLimite());
    }

    //validação do pagamento
    @Override
    public void pagamentoCartao(float totalCarrinho) throws CartaoRecusado{
        if(totalCarrinho < limite){
            this.setLimite(getLimite() - totalCarrinho);
            System.out.print("\n\nCompra efetuada com sucesso! O limite agora é R$ " + getLimite());
            super.setValidacaoCompra(true);
        }
        else{
            super.setValidacaoCompra(false);
            throw new CartaoRecusado("Desculpe, você não tem saldo o suficiente para essa compra :(");
        }
    }
}