package com.example.GestionHackaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionHackaton.model.Administrateur;
import com.example.GestionHackaton.repository.AdministrateurRepository;


@RestController
@RequestMapping("admins")
@CrossOrigin(origins="http://localhost:4200")
public class AdministrateurController {
	
	@Autowired
	AdministrateurRepository adminrepository;

	@PostMapping("/save")
	public Administrateur save(@RequestBody Administrateur mem){
		return adminrepository.save(mem);
	}
	
	@GetMapping("/all")
	public List<Administrateur> load(){
		return adminrepository.findAll();
	}

}
