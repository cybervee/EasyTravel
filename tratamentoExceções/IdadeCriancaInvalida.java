package com.easytravel.tratamentoexcecoes;

import java.lang.Exception;

public class IdadeCriancaInvalida extends Exception {
    public IdadeCriancaInvalida(String mensagem){
        super(mensagem);
    }
}