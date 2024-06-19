package com.starWarsPlanetas.planetasStarWars.service;

import com.starWarsPlanetas.planetasStarWars.dto.SwapiPlanetResponse;
import com.starWarsPlanetas.planetasStarWars.model.Planeta;
import com.starWarsPlanetas.planetasStarWars.repositories.PlanetaRepositoy;
import com.starWarsPlanetas.planetasStarWars.util.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetaService {

    @Autowired
    PlanetaRepositoy planetaRepositoy;

    @Autowired
    RestTemplate restTemplate;

    public Resposta<Planeta> cadastraPlaneta(String nome, String clima, String terreno){
       Planeta planetaExistente = this.buscarPlanetaPeloNome(nome);

       if(planetaExistente != null){
           return new Resposta<>(404,"Planeta já cadastrado.", null);
       }else {
           Planeta novoPlaneta = new Planeta(nome,clima,terreno);
           int quantidade = getFilmCount(novoPlaneta.getNome());
           novoPlaneta.setQuantidadeAparicoes(quantidade);
           planetaRepositoy.save(novoPlaneta);

           return new Resposta<>(200,"Planeta cadastrado com sucesso.", novoPlaneta);
       }
    }

    private Planeta buscarPlanetaPeloNome(String nome){
        return planetaRepositoy.findByNome(nome).orElse(null);
    }

    public Resposta<Planeta> BuscarPlanetaId(Long id){
        Optional<Planeta> planetaExistente = planetaRepositoy.findById(id);

        if(planetaExistente.isPresent()){
            Planeta planeta = planetaExistente.get();
            int quantidade = getFilmCount(planeta.getNome());
            planeta.setQuantidadeAparicoes(quantidade);
            return new Resposta<>(200,"Planeta localizado.", planeta);
        }else {
            return new Resposta<>(404,"Planeta não encontrado.", null);
        }
    }

    public Resposta<Planeta> BuscarPlanetaNome(String nome){

        Planeta planetaExistente = this.buscarPlanetaPeloNome(nome);

        if(planetaExistente != null){
            return new Resposta<>(200,"Planeta localizado.", planetaExistente);
        }else {
            return new Resposta<>(200,"Planeta não encontrado.", null);
        }
    }

    private int getFilmCount(String planetName) {
        String url = "https://swapi.dev/api/planets/?search=" + planetName;
        SwapiPlanetResponse response = restTemplate.getForObject(url, SwapiPlanetResponse.class);
        if (response != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0).getFilms().size();
        }
        return 0;
    }

    public Resposta<Planeta> removerPlaneta(Long id){
       Optional<Planeta> planetaExistente = planetaRepositoy.findById(id);

       if(planetaExistente.isPresent()){
           planetaRepositoy.delete(planetaExistente.get());

           return new Resposta<>(200, "Planeta excluido com sucesso.", null);
       }

       return new Resposta<>(404,"Planeta não encontrado.",null);
    }

    public Resposta<List<Planeta>> listaPlanetas(){
        List<Planeta> planetas = planetaRepositoy.findAll();

        if (!planetas.isEmpty()){
            for(Planeta planeta : planetas){
                int quantidade = getFilmCount(planeta.getNome());
                planeta.setQuantidadeAparicoes(quantidade);
            }
            return new Resposta<>(200,"Planetas encontrados.", planetas.stream().toList());
        }

        return new Resposta<>(404,"Não à planetas cadastrados.", null);
    }

}
