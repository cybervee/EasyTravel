package com.easytravel;

import java.util.Scanner;

import com.easytravel.tratamentoexcecoes.*;

public class Passageiro {
    private String nome;
    private String cpf;

    //construtores
    public Passageiro(){
    }

    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //métodos
    public void cadastroPassageiro() throws CPFInvalido, DadoNaoPreenchido {
        Scanner leituraPassageiro = new Scanner(System.in);
        System.out.print("\nNome: ");
        String nome = leituraPassageiro.nextLine();
        setNome(nome);
        System.out.print("CPF: ");
        String cpf = leituraPassageiro.nextLine();

        //validação de preenchimento de dados
        if(nome.isEmpty() || cpf.isEmpty()){
            throw new DadoNaoPreenchido("Erro! Dado não preenchido.");
        }
        if(validacaoCPF(cpf)){
            setCpf(cpf);
        }
        else{
            throw new CPFInvalido("CPF inválido!");
        }
    }

    public void imprimePassageiro(){
        System.out.print("\nNome: " + getNome() +
                "\nCPF: " + getCpf());
    }

    //valida o cpf do passageiro utilizando o mesmo cálculo da receita federal
    public boolean validacaoCPF(String cpf){
        if(cpf.equals("00000000000") ||
                cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") ||
                cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)){
            return false;
        }
        char digito10, digito11;
        int soma, i, resto, num, peso;

        //validação do primeiro dígito verificador
        soma = 0;
        peso = 10;

        for (i = 0; i < 9; i++) {
            num = cpf.charAt(i) - 48;
            soma = soma + (num * peso);
            peso = peso - 1;
        }

        resto = 11 - (soma % 11);

        if ((resto == 10) || (resto == 11))
            digito10 = '0';
        else digito10 = (char)(resto + 48);

        //validação do segundo dígito verificador
        soma = 0;
        peso = 11;
        for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
        }

        resto = 11 - (soma % 11);
        if ((resto == 10) || (resto == 11))
            digito11 = '0';
        else digito11 = (char)(resto + 48);

        //verificação se os dígitos conferem
        if ((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10)))
            return(true);
        else{
            return false;
        }
    }
}