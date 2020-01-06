package com.example.GestionHackaton.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.GestionHackaton.model.Demande;
import com.example.GestionHackaton.model.Evenement;
import com.example.GestionHackaton.model.Membre;
import com.example.GestionHackaton.repository.DemandeRepository;
import com.example.GestionHackaton.repository.EvenementRepository;
import com.example.GestionHackaton.repository.MembreRepository;


@RestController
@RequestMapping("demandes")
@CrossOrigin(origins="http://localhost:4200")
public class DemandeController {
	
	@Autowired
	DemandeRepository demandeRep;
	@Autowired
	EvenementRepository evenementrepository;
	@Autowired
	MembreRepository memrepository;
	
	@PostMapping("/save")
	public String save(@RequestBody Demande dem){
		Demande d= demandeRep.save(dem);
		return ""+d.getId_demande();
	}
	@PostMapping("/save3")
	public void save2(@RequestBody LinkedMultiValueMap <String,String>  params){
		String idD=params.getFirst("id_demande");
		String idH=params.getFirst("id_membre");
		long id_membre = Long.parseLong(idH);
		long id_demande = Long.parseLong(idD);
		Demande d=demandeRep.findById(id_demande).get();
		Membre m=memrepository.findById(id_membre).get();
		d.setMembre(m);
		demandeRep.saveAndFlush(d);
	}
	
	@PostMapping("/save2")
	public String save1(@RequestBody LinkedMultiValueMap <String,String>  params){
		String idD=params.getFirst("id_demande");
		String idH=params.getFirst("id_Hackaton");
		long id_Hackaton = Long.parseLong(idH);
		long id_demande = Long.parseLong(idD);
		Demande d=demandeRep.findById(id_demande).get();
		Evenement ev=evenementrepository.findById(id_Hackaton).get();
		d.setEven(ev);
		demandeRep.saveAndFlush(d);
		return ""+d.getId_demande();
	}
	@PostMapping("/maDemande")
	public Demande maDemande(@RequestBody LinkedMultiValueMap <String,String>  params){
		String idH=params.getFirst("id_Hackaton");
		String idM=params.getFirst("id_membre");
		long id_Hackaton = Long.parseLong(idH);
		long id_membre = Long.parseLong(idM);
		Evenement ev=evenementrepository.findById(id_Hackaton).get();
		Demande maDemande=new Demande();
		Set<Demande> demandes=ev.getDemandes();
		for(Demande el:demandes) {
			if(el.getMembre().getId_mem()==id_membre) {
				maDemande=el;
			}
		}
		return maDemande;
	}
	@PostMapping("/Annuler")
	public void annuler(@RequestBody Long id) {
		Demande d=demandeRep.findById(id).get();
		d.setEtat("A");
		demandeRep.saveAndFlush(d);
	}
	
}
