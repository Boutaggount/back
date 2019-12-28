package com.example.GestionHackaton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GestionHackaton.model.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
