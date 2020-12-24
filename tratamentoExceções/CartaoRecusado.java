package com.easytravel.tratamentoexcecoes;

import java.lang.Exception;

public class CartaoRecusado extends Exception {
    public CartaoRecusado(String mensagem){
        super(mensagem);
    }
}