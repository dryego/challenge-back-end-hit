package com.starWarsPlanetas.planetasStarWars.model;

import jakarta.persistence.*;

@Entity
@Table(name = "planetas")
public class Planeta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String nome;
    private String clima;
    private String terreno;

    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public Planeta (){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }
}
