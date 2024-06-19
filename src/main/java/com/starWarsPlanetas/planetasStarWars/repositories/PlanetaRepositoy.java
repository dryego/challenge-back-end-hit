package com.starWarsPlanetas.planetasStarWars.repositories;

import com.starWarsPlanetas.planetasStarWars.model.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetaRepositoy extends JpaRepository<Planeta, Long> {
    //@Override
    Optional<Planeta> findByNome(String nome);

    Optional<Planeta> findById(Long id);

}
