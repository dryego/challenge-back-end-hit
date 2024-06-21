package com.starWarsPlanetas.planetasStarWars.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para cadastrar um planeta")
public class PlanetaDTO {
    @Schema(description = "Nome do planeta", required = true)
    private String nome;
    @Schema(description = "Clima do planeta", required = true)
    private String clima;
    @Schema(description = "Terreno do planeta", required = true)
    private String terreno;

    public PlanetaDTO(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

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
