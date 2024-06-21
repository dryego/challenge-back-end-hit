package com.starWarsPlanetas.planetasStarWars.controller;

import com.starWarsPlanetas.planetasStarWars.dto.PlanetaDTO;
import com.starWarsPlanetas.planetasStarWars.model.Planeta;
import com.starWarsPlanetas.planetasStarWars.service.PlanetaService;
import com.starWarsPlanetas.planetasStarWars.util.Resposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/planeta")
@Tag(name = "Planetas",description = "planetas")
public class PlanetaController {

    @Autowired
    PlanetaService planetaService;

    @Operation(summary = "Cadastrar um novo planeta", description = "Adiciona um novo planeta com as informações fornecidas.")
    @PostMapping("/cadastro")
    public ResponseEntity<Object> postCadastroPlaneta(@RequestBody PlanetaDTO planetaDTO){

        try{
            Resposta<Planeta> novoPlaneta = planetaService.cadastraPlaneta(planetaDTO.getNome(),planetaDTO.getClima(),planetaDTO.getTerreno());

            if(novoPlaneta.getDate() == null){
                return ResponseEntity.status(novoPlaneta.getStatus()).body(novoPlaneta.getMenssagem());
            }
            return ResponseEntity.status(novoPlaneta.getStatus()).body(novoPlaneta.getMenssagem());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno. " + e);
        }
    }

    @Operation(summary = "Buscar planeta por ID", description = "Retorna o planeta com o ID especificado.")
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Object> getBuscarPlanetaId(@Parameter(description = "ID do planeta", required = true) @PathVariable Long id){
        try{
            Resposta<Planeta> planeta = planetaService.BuscarPlanetaId(id);

            if(planeta.getDate() == null){
                return ResponseEntity.status(planeta.getStatus()).body(planeta.getMenssagem());
            }
            return ResponseEntity.status(planeta.getStatus()).body(planeta.getDate());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno.");
        }
    }

    @Operation(summary = "Buscar planeta por nome", description = "Retorna o planeta com o nome especificado.")
    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<Object> getBuscarPlanetaNome(@Parameter(description = "Nome do planeta", required = true) @PathVariable String nome){
        try{
            Resposta<Planeta> planeta = planetaService.BuscarPlanetaNome(nome);

            if(planeta.getDate() == null){
                return ResponseEntity.status(planeta.getStatus()).body(planeta.getMenssagem());
            }
            return ResponseEntity.status(planeta.getStatus()).body(planeta.getDate());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno.");
        }
    }

    @Operation(summary = "Remover um planeta", description = "Remove o planeta com o ID especificado.")
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> deletePlaneta(@Parameter(description = "ID do planeta", required = true) @PathVariable Long id){
        try{
            Resposta<Planeta> planeta = planetaService.removerPlaneta(id);

            if(planeta.getStatus() == 200){
                return ResponseEntity.status(planeta.getStatus()).body(planeta.getMenssagem());
            }
            return ResponseEntity.status(planeta.getStatus()).body(planeta.getMenssagem());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno.");
        }
    }

    @Operation(summary = "Lista todos os planetas", description = "Retorna uma lista de todos os planetas cadastrados.")
    @GetMapping("/listar")
    public ResponseEntity<Object> getListarPlanetas(){
        try{
            Resposta<List<Planeta>> planetas = planetaService.listaPlanetas();

            if(planetas.getDate() == null){
                return ResponseEntity.status(planetas.getStatus()).body(planetas.getMenssagem());
            }

            return  ResponseEntity.status(planetas.getStatus()).body(planetas.getDate());
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erro interno.");
        }
    }


}
