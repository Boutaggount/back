package com.example.GestionHackaton.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionHackaton.model.Administrateur;
import com.example.GestionHackaton.model.Evenement;
import com.example.GestionHackaton.model.Membre;
import com.example.GestionHackaton.model.equipe;
import com.example.GestionHackaton.repository.EvenementRepository;
import com.example.GestionHackaton.repository.MembreRepository;
import com.example.GestionHackaton.repository.equipeRepository;

import javassist.compiler.ast.Member;

@RestController
@RequestMapping("equipes")
@CrossOrigin(origins="http://localhost:4200")
public class equipeController {
	
	@Autowired
	equipeRepository equipeRep;
	
	@Autowired
	EvenementRepository evenementrepository;
	@Autowired
	MembreRepository memrepository;
	
	@PostMapping("/save")
	public String save(@RequestBody equipe eq){
	   equipe eqq=equipeRep.saveAndFlush(eq);
	   return ""+eqq.getId_equipe();
	}
	@PostMapping("/save2")
	public void save(@RequestBody LinkedMultiValueMap <String,String>  params){
		String idH=params.getFirst("id_hack");
		String idE=params.getFirst("id_eq");
		long id_hack = Long.parseLong(idH);
		long id_eq = Long.parseLong(idE);
		Evenement event =evenementrepository.findById(id_hack).get();
		equipe eqq =equipeRep.findById(id_eq).get();
		eqq.setEvent(event);
	    equipeRep.saveAndFlush(eqq);
	
	}
	@GetMapping("/all")
	public List<equipe> load(){
		return equipeRep.findAll();
	}
	@PostMapping("/addmembre")
	public void addmembre(@RequestBody LinkedMultiValueMap <String,String>  params) {
		String idE=params.getFirst("id_eq");
		String idM=params.getFirst("id_membre");
		System.out.println(idE+""+idM);
		long id_eq = Long.parseLong(idE);
		long id_membre = Long.parseLong(idM);
		Membre m=memrepository.findById(id_membre).get();
		equipe eqq =equipeRep.findById(id_eq).get();
		m.setEqs(eqq);
		memrepository.saveAndFlush(m);
		}
	
	@GetMapping("/{id}")
	public Optional<equipe> getEquipe(@PathVariable Long id){	
		return equipeRep.findById(id);
	}
	
	@PostMapping(value="/delete")
	public boolean deleteEquipe(@RequestBody Long id) {
		equipeRep.deleteById(id);
		return true;
		
	}
}