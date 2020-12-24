package com.easytravel.tratamentoexcecoes;

import java.lang.Exception;

public class CPFInvalido extends Exception {
    public CPFInvalido(String mensagem){
        super(mensagem);
    }
}