package com.easytravel;

import java.time.*;
import java.util.*;
import com.easytravel.tratamentoexcecoes.*;

public class Main {

    public static void main(String[] args) throws QuantidadePassageirosInvalida, CartaoNumeroInvalido, CartaoRecusado, CPFInvalido, DadoNaoPreenchido {
	    //inicialização dos vôos
        //vôo 1
        ArrayList<Voo> listaVoos = new ArrayList<>();

        Voo voo1 = new Voo("Belo Horizonte", "Salvador", 10,20,30,90,70,50);
        LocalDateTime dataPartidaVoo1 = LocalDateTime.of(2021,3,10,10,15);
        voo1.setDataPartida(dataPartidaVoo1);
        LocalDateTime dataChegadaVoo1 = LocalDateTime.of(2021,3,10,14,30);
        voo1.setDataChegada(dataChegadaVoo1);
        listaVoos.add(voo1);

        //vôo 2
        Voo voo2 = new Voo("Curitiba", "Porto Alegre", 10,20,30,200,70,50);
        LocalDateTime dataPartidaVoo2 = LocalDateTime.of(2021,1,4,20,50);
        voo2.setDataPartida(dataPartidaVoo2);
        LocalDateTime dataChegadaVoo2 = LocalDateTime.of(2021,1,5,2,30);
        voo2.setDataChegada(dataChegadaVoo2);
        listaVoos.add(voo2);

        //vôo 3
        Voo voo3 = new Voo("Porto Alegre", "Curitiba", 10,20,30,170,70,50);
        LocalDateTime dataPartidaVoo3 = LocalDateTime.of(2021,5,13,15,20);
        voo3.setDataPartida(dataPartidaVoo3);
        LocalDateTime dataChegadaVoo3 = LocalDateTime.of(2021,5,13,19,30);
        voo3.setDataChegada(dataChegadaVoo3);
        listaVoos.add(voo3);

        //menu de início
        Scanner leitura = new Scanner(System.in);
        System.out.print("*** Bem vindo à Easy Travel! ***" +
                "\nSelecione uma opção: " +
                "\n1. Busca de Passagens" +
                "\n2. Lista de Destinos" +
                "\n3. Sair do programa" +
                "\nDigite a opção desejada: ");
        int escolha = leitura.nextInt();

        //validação de escolha do menu inicial
        while(escolha < 1 || escolha > 3){
            System.out.print("Opção inválida, digite novamente: ");
            escolha = leitura.nextInt();
        }

        switch (escolha){
            //busca de vôos
            case 1:
                System.out.print("\n*** Busca ***\n" +
                    "Insira o destino desejado: ");
                leitura.nextLine();
                String buscaDestino = leitura.nextLine();

                System.out.print("Insira a origem: ");
                String buscaPartida = leitura.nextLine();

                int resultadoPesquisa = 0;

                //validação de preenchimento de busca
                if(buscaDestino.isEmpty() || buscaPartida.isEmpty()){
                    throw new DadoNaoPreenchido("Erro! Dado não preenchido.");
                }

                for(int i = 0; i < listaVoos.size(); i++){
                    if(listaVoos.get(i).getLocalChegada().equalsIgnoreCase(buscaDestino) && listaVoos.get(i).getLocalOrigem().equalsIgnoreCase(buscaPartida)){
                        System.out.print("\n*** Dados do Vôo " +
                                i + " ***" +
                                listaVoos.get(i).imprimeDadosVoo() +
                                "\nPreços a partir de R$ " + listaVoos.get(i).getTaxaServico());
                        resultadoPesquisa++;
                    }
                }

                if(resultadoPesquisa == 0){
                    System.out.println("\nDesculpe, não encontramos vôos com os parâmetros desejados!");
                }

                break;

            //lista de destinos
            case 2:
                System.out.print("\n*** Lista de Destinos ***\n");
                for(int i = 0; i < listaVoos.size(); i++){
                    System.out.print("\n******************************" +
                            "\nVôo " + i + ": " +
                            listaVoos.get(i).imprimeDadosVoo() +
                            "\nPreços a partir de R$ " + listaVoos.get(i).getTaxaServico() +
                            "\n******************************");
                }
                System.out.print("\n\nDigite o número do vôo escolhido: ");
                int escolhaVoo = leitura.nextInt();

                //validação de escolha do número do vôo
                if(escolhaVoo < 0 || escolhaVoo > listaVoos.size()){
                    System.out.print("\nOpção inválida, digite novamente: ");
                    escolhaVoo = leitura.nextInt();
                }

                //se houver assentos disponíveis
                if(listaVoos.get(escolhaVoo).qtdeAssentosDisponíveis() > 0){
                    System.out.print("\nHá " + listaVoos.get(escolhaVoo).qtdeAssentosDisponíveis() + " assentos disponíveis para compra." );
                    float totalCarrinho = 0;

                    //quantidade de passageiros
                    System.out.print("\n\nInsira o número de passageiros adultos: "); //ERRO
                    int qtdeAdultos = leitura.nextInt();

                    //validação de quantidade de adultos; crianças não podem viajar desacompanhadas
                    if(qtdeAdultos == 0){
                        throw new QuantidadePassageirosInvalida("Erro: por favor insira um número mínimo de adultos.");
                    }

                    System.out.print("Insira o número de passageiros crianças: ");
                    int qtdeCriancas = leitura.nextInt();

                    //escolha de classe
                    System.out.print("\nDigite 1 para Primeira classe ou 2 para Segunda classe: ");
                    int escolhaClasse = leitura.nextInt();

                    for (int i = 0; i < qtdeAdultos; i++) {
                        Passageiro passageiro = new Passageiro();
                        passageiro.cadastroPassageiro();
                        Passagem passagem = new Passagem();
                        passagem.setPassageiro(passageiro);
                        listaVoos.get(escolhaVoo).getListaPassageiros().add(passagem);
                    }

                    //loop das crianças
                    if(qtdeCriancas > 0){
                        for(int i = 0; i < qtdeCriancas; i++){
                            PassageiroCrianca passageiroCrianca = new PassageiroCrianca();
                            passageiroCrianca.cadastroPassageiro();
                            Passagem passagem = new Passagem();
                            passagem.setPassageiro(passageiroCrianca);
                            listaVoos.get(escolhaVoo).getListaPassageiros().add(passagem);
                            if (passageiroCrianca.isDescontoIdade()){
                                switch (escolhaClasse){
                                    case 1:
                                        totalCarrinho += (listaVoos.get(escolhaVoo).getTaxaServico() + listaVoos.get(escolhaVoo).getTaxaPrimeiraClasse()) * 0.5;
                                        listaVoos.get(escolhaVoo).setQtdeAssentosPrimeiraClasse(listaVoos.get(escolhaVoo).getQtdeAssentosPrimeiraClasse() - 1);
                                        break;
                                    case 2:
                                        totalCarrinho += (listaVoos.get(escolhaVoo).getTaxaServico() + listaVoos.get(escolhaVoo).getTaxaSegundaClasse()) * 0.5;
                                        listaVoos.get(escolhaVoo).setQtdeAssentosSegundaClasse(listaVoos.get(escolhaVoo).getQtdeAssentosSegundaClasse() - 1);
                                        break;
                                }
                            }
                            else {
                                switch (escolhaClasse){
                                    case 1:
                                        totalCarrinho += (listaVoos.get(escolhaVoo).getTaxaServico() + listaVoos.get(escolhaVoo).getTaxaPrimeiraClasse());
                                        listaVoos.get(escolhaVoo).setQtdeAssentosPrimeiraClasse(listaVoos.get(escolhaVoo).getQtdeAssentosPrimeiraClasse() - 1);
                                        break;
                                    case 2:
                                        totalCarrinho += (listaVoos.get(escolhaVoo).getTaxaServico() + listaVoos.get(escolhaVoo).getTaxaSegundaClasse());
                                        listaVoos.get(escolhaVoo).setQtdeAssentosSegundaClasse(listaVoos.get(escolhaVoo).getQtdeAssentosSegundaClasse() - 1);
                                        break;
                                }
                            }
                        }
                    }

                    //escolha de classe dos adultos (sem desconto)
                    if(escolhaClasse == 1){
                        totalCarrinho += (listaVoos.get(escolhaVoo).getTaxaServico() + listaVoos.get(escolhaVoo).getTaxaPrimeiraClasse()) * (qtdeAdultos);
                        listaVoos.get(escolhaVoo).setQtdeAssentosPrimeiraClasse(listaVoos.get(escolhaVoo).getQtdeAssentosPrimeiraClasse() - (qtdeAdultos));
                    }
                    else{
                        totalCarrinho += (listaVoos.get(escolhaVoo).getTaxaServico() + listaVoos.get(escolhaVoo).getTaxaSegundaClasse()) * (qtdeAdultos);
                        listaVoos.get(escolhaVoo).setQtdeAssentosSegundaClasse(listaVoos.get(escolhaVoo).getQtdeAssentosSegundaClasse() - (qtdeAdultos));
                    }

                    //menu de pagamento
                    System.out.print("\nO total da compra é R$ " + totalCarrinho +
                            "\nDigite 1 para Cartão de Crédito ou 2 para Cartão de Débito: ");
                    int escolhaPagamento = leitura.nextInt();
                    while(escolhaPagamento < 1 || escolhaPagamento > 2){ //ERRO
                        System.out.println("Opção inválida, digite novamente: ");
                        escolhaPagamento = leitura.nextInt();
                    }

                    //cartão de crédito
                    if(escolhaPagamento == 1){
                        Cartao cartaoCredito = new CartaoCredito();
                        cartaoCredito.cadastroCartao();
                        cartaoCredito.imprimeDadosCartao();
                        cartaoCredito.pagamentoCartao(totalCarrinho);

                        //se pagamento for aprovado
                        if(cartaoCredito.isValidacaoCompra()){
                            for(int i = listaVoos.get(escolhaVoo).getListaPassageiros().size() - (qtdeAdultos + qtdeCriancas); i < qtdeAdultos + qtdeCriancas; i++){
                                System.out.print("\n******************************");
                                listaVoos.get(escolhaVoo).getListaPassageiros().get(i).imprimePassagem();
                                System.out.print(listaVoos.get(escolhaVoo).imprimeDadosVoo() +
                                        "\n******************************");
                            }
                        }

                        //se pagamento for recusado
                        else{
                            for(int i = listaVoos.get(escolhaVoo).getListaPassageiros().size() - (qtdeAdultos + qtdeCriancas); i < qtdeAdultos + qtdeCriancas; i++){
                                listaVoos.get(escolhaVoo).getListaPassageiros().remove(listaVoos.get(escolhaVoo).getListaPassageiros().size() - (qtdeAdultos + qtdeCriancas));
                                switch (escolhaClasse){
                                    case 1:
                                        listaVoos.get(escolhaVoo).setQtdeAssentosPrimeiraClasse(listaVoos.get(escolhaVoo).getQtdeAssentosPrimeiraClasse() + (qtdeAdultos + qtdeCriancas));
                                        break;
                                    case 2:
                                        listaVoos.get(escolhaVoo).setQtdeAssentosSegundaClasse(listaVoos.get(escolhaVoo).getQtdeAssentosSegundaClasse() + (qtdeAdultos + qtdeCriancas));
                                        break;
                                }
                            }
                        }
                    }

                    //cartão de débito
                    else {
                        Cartao cartaoDebito = new CartaoDebito();
                        cartaoDebito.cadastroCartao();
                        cartaoDebito.imprimeDadosCartao();
                        cartaoDebito.pagamentoCartao(totalCarrinho);

                        //se pagamento for aprovado
                        if(cartaoDebito.isValidacaoCompra()){
                            for(int i = listaVoos.get(escolhaVoo).getListaPassageiros().size() - (qtdeAdultos + qtdeCriancas); i < qtdeAdultos + qtdeCriancas; i++){
                                System.out.print("\n******************************");
                                listaVoos.get(escolhaVoo).getListaPassageiros().get(i).imprimePassagem();
                                listaVoos.get(escolhaVoo).imprimeDadosVoo();
                                System.out.print(listaVoos.get(escolhaVoo).imprimeDadosVoo() +
                                        "\n******************************");
                            }
                        }

                        //se pagamento for recusado
                        else{
                            for(int i = listaVoos.get(escolhaVoo).getListaPassageiros().size() - (qtdeAdultos + qtdeCriancas); i < qtdeAdultos + qtdeCriancas; i++){
                                listaVoos.get(escolhaVoo).getListaPassageiros().remove(listaVoos.get(escolhaVoo).getListaPassageiros().size() - (qtdeAdultos + qtdeCriancas));
                                switch (escolhaClasse){
                                    case 1:
                                        listaVoos.get(escolhaVoo).setQtdeAssentosPrimeiraClasse(listaVoos.get(escolhaVoo).getQtdeAssentosPrimeiraClasse() + (qtdeAdultos + qtdeCriancas));
                                        break;
                                    case 2:
                                        listaVoos.get(escolhaVoo).setQtdeAssentosSegundaClasse(listaVoos.get(escolhaVoo).getQtdeAssentosSegundaClasse() + (qtdeAdultos + qtdeCriancas));
                                        break;
                                }
                            }
                        }
                    }
                }

                //se não tem assentos
                else {
                    System.out.print("Desculpe, vôo cheio :(");
                }
                break;

            //saída do programa
            case 3:
                System.out.print("\nA Easy Travel agradece sua preferência. Até logo!");
                break;
        }
    }
}