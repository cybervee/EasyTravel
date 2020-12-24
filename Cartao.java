package com.easytravel;

import com.easytravel.tratamentoexcecoes.*;

public abstract class Cartao {
    private String numeroCartao;
    private boolean validacaoCompra;

    //construtores
    public Cartao(){

    }

    public Cartao(String numeroCartao, boolean validacaoCompra) {
        this.numeroCartao = numeroCartao;
        this.validacaoCompra = validacaoCompra;
    }

    //getters e setters
    public boolean isValidacaoCompra() {
        return validacaoCompra;
    }

    public void setValidacaoCompra(boolean validacaoCompra) {
        this.validacaoCompra = validacaoCompra;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    //métodos abstratos implementados nas classes de crédito e débito
    public abstract void cadastroCartao() throws CartaoNumeroInvalido, DadoNaoPreenchido;
    public abstract void imprimeDadosCartao();
    public abstract void pagamentoCartao(float totalCarrinho) throws CartaoRecusado;

    //verifica se o número de cartão é válido segundo o algoritmo de luhn
    public boolean validacaoNumeroLuhn(String numeroCartao){
        int numeroDigitos = numeroCartao.length();
        int somaDigitos = 0;
        boolean segundoDigito = false;
        for (int i = numeroDigitos - 1; i >= 0; i--){
            int d = numeroCartao.charAt(i) - '0';
            if (segundoDigito == true)
                d = d * 2;
            somaDigitos += d / 10;
            somaDigitos += d % 10;
            segundoDigito = !segundoDigito;
        }
        return (somaDigitos % 10 == 0);
    }
}