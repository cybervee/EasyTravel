package com.easytravel;

import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.ArrayList;

public class Voo {
    private String localOrigem;
    private String localChegada;
    private int qtdeAssentosPrimeiraClasse;
    private int qtdeAssentosSegundaClasse;
    private int qtdeAssentosTotal;
    private float taxaServico;
    private float taxaPrimeiraClasse;
    private float taxaSegundaClasse;
    private LocalDateTime dataPartida;
    private LocalDateTime dataChegada;
    private ArrayList<Passagem>listaPassageiros = new ArrayList<>();

    //construtor
    public Voo(String localOrigem, String localChegada, int qtdeAssentosPrimeiraClasse, int qtdeQtdeAssentosSegundaClasse, int qtdeAssentosTotal, float taxaServico, float taxaPrimeiraClasse, float taxaSegundaClasse) {
        this.localOrigem = localOrigem;
        this.localChegada = localChegada;
        this.qtdeAssentosPrimeiraClasse = qtdeAssentosPrimeiraClasse;
        this.qtdeAssentosSegundaClasse = qtdeQtdeAssentosSegundaClasse;
        this.qtdeAssentosTotal = qtdeAssentosTotal;
        this.taxaServico = taxaServico;
        this.taxaPrimeiraClasse = taxaPrimeiraClasse;
        this.taxaSegundaClasse = taxaSegundaClasse;
        this.listaPassageiros = listaPassageiros;
    }

    //getters e setters
    public ArrayList<Passagem> getListaPassageiros() {
        return listaPassageiros;
    }

    public void setListaPassageiros(ArrayList<Passagem> listaPassageiros) {
        this.listaPassageiros = listaPassageiros;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public String getLocalChegada() {
        return localChegada;
    }

    public int getQtdeAssentosPrimeiraClasse() {
        return qtdeAssentosPrimeiraClasse;
    }

    public int getQtdeAssentosSegundaClasse() {
        return qtdeAssentosSegundaClasse;
    }

    public int getQtdeAssentosTotal() {
        return qtdeAssentosTotal;
    }

    public float getTaxaServico() {
        return taxaServico;
    }

    public float getTaxaPrimeiraClasse() {
        return taxaPrimeiraClasse;
    }

    public float getTaxaSegundaClasse() {
        return taxaSegundaClasse;
    }

    public String getDataPartida() {
        return dataPartida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public void setDataPartida(LocalDateTime dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getDataChegada() {
        return dataChegada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public void setDataChegada(LocalDateTime dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setQtdeAssentosPrimeiraClasse(int qtdeAssentosPrimeiraClasse) {
        this.qtdeAssentosPrimeiraClasse = qtdeAssentosPrimeiraClasse;
    }

    public void setQtdeAssentosSegundaClasse(int qtdeAssentosSegundaClasse) {
        this.qtdeAssentosSegundaClasse = qtdeAssentosSegundaClasse;
    }

    //métodos
    public int qtdeAssentosDisponíveis(){
        return getQtdeAssentosPrimeiraClasse() + getQtdeAssentosSegundaClasse();
    }

    public String imprimeDadosVoo(){
        return "\nSaída: " + getLocalOrigem() +
                "\nChegada: " + getLocalChegada() +
                "\nHorário Partida: " + getDataPartida() +
                "\nHorário Chegada: " + getDataChegada();
    }
}