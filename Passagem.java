package com.easytravel;

public class Passagem{
    private Passageiro passageiro;
    private String voucher;

    //construtor
    public Passagem() {
    }

    //getters e setters
    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public String getVoucher() {
        return voucher;
    }

    //métodos
    public void setVoucher() {
        String alfabetoNumerico = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder numeroVoucher = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(alfabetoNumerico.length() * Math.random());
            numeroVoucher.append(alfabetoNumerico.charAt(index));
        }
        this.voucher = numeroVoucher.toString();
    }

    public void imprimePassagem(){
        passageiro.imprimePassageiro();
        setVoucher();
        System.out.print("\nVoucher: " + getVoucher());
    }
}