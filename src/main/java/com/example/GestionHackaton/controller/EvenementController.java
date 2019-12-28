package com.example.GestionHackaton.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionHackaton.model.Evenement;
import com.example.GestionHackaton.model.Membre;
import com.example.GestionHackaton.repository.EvenementRepository;
import com.example.GestionHackaton.repository.MembreRepository;



@RestController
@RequestMapping("evenements")
@CrossOrigin(origins="http://localhost:4200")
public class EvenementController {
	@Autowired
	EvenementRepository evenementrepository;
	@Autowired
	MembreRepository memrepository;

	@PostMapping("/save")
	public Evenement save(@RequestBody Evenement event){
		return evenementrepository.saveAndFlush(event);
	}
	
	@GetMapping("/all")
	public List<Evenement> load(){
		return evenementrepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Evenement> getEvent(@PathVariable Long id){	
		return evenementrepository.findById(id);
	}
	
	@PostMapping("/participer")
	public void participer(@RequestBody LinkedMultiValueMap<String,String>  params) {
		String idM=params.getFirst("id_membre");
		String idH=params.getFirst("id_Hackaton");
		long id_membre = Long.parseLong(idM);
		long id_Hackaton = Long.parseLong(idH);
		 Evenement evt= evenementrepository.findById(id_Hackaton).get();
		 //System.out.print("event**"+evt.getIntitule());
		 Membre m= memrepository.findById(id_membre).get();
		 //System.out.print("mem***"+m.getAdresse());
		 
		 evt.getMembre().add(m);
		 //System.out.print("Bien add");
			evenementrepository.saveAndFlush(evt);
	
	}
	
	@PostMapping("/ispart")
	public boolean ispart(@RequestBody LinkedMultiValueMap<String,String>  params) {
		String idM=params.getFirst("id_membre");
		String idH=params.getFirst("id_Hackaton");
		boolean exists =false;
		long id_membre = Long.parseLong(idM);
		long id_Hackaton = Long.parseLong(idH);
		System.out.print("*******************");
		Evenement evt= evenementrepository.findById(id_Hackaton).get();
		Set<Membre> m = new HashSet<>();
		m=evt.getMembre();
		for(Membre mem : m) {
		if(mem.getId_admin()==id_membre) {exists=true;} 
		 }
		 return exists;
	}
	
	@PostMapping(value="/delete")
	public boolean deleteEvent(@RequestBody Long id) {
		evenementrepository.deleteById(id);
		System.out.println("*******************");
		return true;
		
	}
	
}	
	
	


