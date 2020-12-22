package com.easytravel;

import java.util.Scanner;
import com.easytravel.tratamentoexcecoes.*;

public class PassageiroCrianca extends Passageiro {
    private int idade;
    private boolean descontoIdade;

    //construtores
    public PassageiroCrianca() {
    }

    //getters e setters
    public boolean isDescontoIdade() {
        return descontoIdade;
    }

    public void setDescontoIdade(boolean descontoIdade) {
        this.descontoIdade = descontoIdade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    //métodos
    @Override
    public void cadastroPassageiro() throws CPFInvalido, DadoNaoPreenchido {
        Scanner leituraPassageiroCrianca = new Scanner(System.in);
        super.cadastroPassageiro();
        System.out.print("Idade da criança: ");
        setIdade(leituraPassageiroCrianca.nextInt());
        if(this.getIdade() < 8){
            this.setDescontoIdade(true);
        }
        else{
            this.setDescontoIdade(false);
            throw new CPFInvalido("Erro: apenas crianças menores de 8 anos têm direito a desconto de 50% na passagem.");
        }
    }

    @Override
    public void imprimePassageiro() {
        super.imprimePassageiro();
        System.out.print("\nIdade: " + getIdade());
    }
}