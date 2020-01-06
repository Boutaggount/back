package com.example.GestionHackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GestionHackaton.model.Demande;

public interface DemandeRepository extends JpaRepository<Demande,Long> {

}
