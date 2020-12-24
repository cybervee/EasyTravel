package com.easytravel.tratamentoexcecoes;

import java.lang.Exception;

public class CartaoNumeroInvalido extends Exception {
    public CartaoNumeroInvalido(String mensagem){
        super(mensagem);
    }
}