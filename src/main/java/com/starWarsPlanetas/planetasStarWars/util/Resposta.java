package com.starWarsPlanetas.planetasStarWars.util;

public class Resposta<T> {

    private int status;
    private String menssagem;
    private T date;

    public Resposta(int status, String menssagem, T date) {
        this.status = status;
        this.menssagem = menssagem;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
