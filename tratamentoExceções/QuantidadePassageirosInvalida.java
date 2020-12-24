package com.easytravel.tratamentoexcecoes;

import java.lang.Exception;

public class QuantidadePassageirosInvalida extends Exception{
    public QuantidadePassageirosInvalida(String mensagem){
        super(mensagem);
    }
}