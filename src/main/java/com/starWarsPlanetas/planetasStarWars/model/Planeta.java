package com.starWarsPlanetas.planetasStarWars.model;

import jakarta.persistence.*;

@Entity
@Table(name = "planetas")
public class Planeta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String clima;
    private String terreno;
    @Column(name = "quantidade_aparicoes")
    private Integer quantidadeAparicoes = 0;

    public Planeta(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantidadeAparicoes() {
        return quantidadeAparicoes;
    }

    public void setQuantidadeAparicoes(int quantidadeAparicoes) {
        this.quantidadeAparicoes = quantidadeAparicoes;
    }
}
