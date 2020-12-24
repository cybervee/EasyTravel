package com.easytravel.tratamentoexcecoes;

import java.lang.Exception;

public class DadoNaoPreenchido extends Exception {
    public DadoNaoPreenchido(String mensagem){
        super(mensagem);
    }
}