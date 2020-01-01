package com.example.GestionHackaton.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;

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
import com.example.GestionHackaton.repository.AdministrateurRepository;
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
	@Autowired
	AdministrateurRepository adminrepository;

	@PostMapping("/save")
	public String save(@RequestBody Evenement event){
		Evenement ev= evenementrepository.saveAndFlush(event);	
		return ""+ev.getId();
	}
	
	@PostMapping("/save2")
	public void save(@RequestBody LinkedMultiValueMap <String,String>  params){
		String idA=params.getFirst("id_Admin");
		String idE=params.getFirst("evnt");
		long id_admin = Long.parseLong(idA);
		long id_Hackaton = Long.parseLong(idE);
		Administrateur admin =adminrepository.findById(id_admin).get();
		Evenement event =evenementrepository.findById(id_Hackaton).get();
		event.setAdmins(admin);
		evenementrepository.saveAndFlush(event);
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
		Membre m= memrepository.findById(id_membre).get();
	    evt.getMembre().add(m);
		evenementrepository.saveAndFlush(evt);
	
	}
	
	@PostMapping("/ispart")
	public boolean ispart(@RequestBody LinkedMultiValueMap<String,String>  params) {
		String idM=params.getFirst("id_membre");
		String idH=params.getFirst("id_Hackaton");
		boolean exists =false;
		long id_membre = Long.parseLong(idM);
		long id_Hackaton = Long.parseLong(idH);
		Evenement evt= evenementrepository.findById(id_Hackaton).get();
		Set<Membre> m = new HashSet<>();
		m=evt.getMembre();
		for(Membre mem : m) {
		if(mem.getId_mem()==id_membre) {exists=true;} 
		 }
		 return exists;
	}
	
	@PostMapping(value="/delete")
	public boolean deleteEvent(@RequestBody Long id) {
		evenementrepository.deleteById(id);
		return true;
	}
	
	@GetMapping("/maListEvnts/{id}")
	public List<Evenement> MaListEvent(@PathVariable Long id){
		List<Evenement> ListAll = evenementrepository.findAll();
		List<Evenement> maList = new ArrayList<Evenement>();
		for(Evenement ev :ListAll) {	
			if(ev.getAdmins().getId_admin()==id) {
				maList.add(ev);
			}	
		}
		
		return maList;
	}
	
	@GetMapping("/nbrPaticipant/{id}")
	public int nbrPaticipant(@PathVariable Long id){
		int a = 0;
		List<Evenement> ListAll = evenementrepository.findAll();
		for(Evenement ev :ListAll) {	
			if(ev.getId()==id) {	
				a=ev.getMembre().size();
			}		
		}
		return a;
	}
	@GetMapping("/listmembers/{id}")
	public Set<Membre> listmembers(@PathVariable Long id){
		Set<Membre> m = new HashSet<>();
		List<Evenement> ListAll = evenementrepository.findAll();
		for(Evenement ev :ListAll) {	
			if(ev.getId()==id) {	
				m=ev.getMembre();
			}		
		}
		return m;
	}
	
		
}	
	
