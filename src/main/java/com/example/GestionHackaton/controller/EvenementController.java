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
import com.example.GestionHackaton.model.Demande;
import com.example.GestionHackaton.model.Evenement;
import com.example.GestionHackaton.model.Membre;
import com.example.GestionHackaton.model.equipe;
import com.example.GestionHackaton.repository.AdministrateurRepository;
import com.example.GestionHackaton.repository.DemandeRepository;
import com.example.GestionHackaton.repository.EvenementRepository;
import com.example.GestionHackaton.repository.MembreRepository;
import com.example.GestionHackaton.repository.equipeRepository;



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
	@Autowired
	equipeRepository equipeRep;
	@Autowired
	DemandeRepository demandeRep;
	
	
	@PostMapping("/update")
	public void update(@RequestBody Evenement event){
		Evenement newev= evenementrepository.findById(event.getId()).get();
		event.setAdmins(newev.getAdmins());
		event.setMembre(newev.getMembre());
		System.out.println("************"+newev.getMembre().size());
			evenementrepository.saveAndFlush(event);
		}
		
			
		
	
	
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
	    /* new */
	    Demande maDemande=new Demande();
		Set<Demande> demandes=evt.getDemandes();
		for(Demande el:demandes) {
			if(el.getMembre().getId_mem()==id_membre) {
				maDemande=el;
				maDemande.setEtat("A");
			}
		}
		demandeRep.saveAndFlush(maDemande);
	    /*fin new */
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

	@GetMapping("/nbMemebersH")
	public ArrayList<Integer> getNbMembersH(){
		ArrayList<Integer> nbMembersH = new ArrayList<Integer>();
		ArrayList<Membre> members = (ArrayList<Membre>)memrepository.findAll();
		ArrayList<Evenement> evenements = (ArrayList<Evenement>)evenementrepository.findAll();
		for(int event=0;event<evenements.size();event++) {
			nbMembersH.add(0);
			for(Membre m:members) {
				if(m.getHackatons().contains(evenements.get(event))) {
					System.out.println("---------------------------------------");
					if(m.getSexe().equals("homme")) {
						System.out.println("****************************");
						nbMembersH.set(event, nbMembersH.get(event)+1);
					}
				}
			}
		}

		
		return nbMembersH;
	}
	
	@GetMapping("/nbHackaton")
	public int getNbHackatons(){
		List<Evenement> ev = evenementrepository.findAll();

		return ev.size();
	}
	
	@GetMapping("/nbEquipe")
	public int NbEquipe(){
		List<equipe> eq =equipeRep.findAll();

		return eq.size();
	}
	
	@GetMapping("/nbDemande")
	public int NbDemande(){
		List<Demande> d =demandeRep.findAll();

		return d.size();
	}
	
	
	@GetMapping("/nbParticipants")
	public int getNbPArticipants(){
		List<Membre> m = memrepository.findAll();
		Set<Membre> s = new HashSet<Membre>();
		for(Membre el:m) {
			if(el.getHackatons().size() != 0) {
				s.add(el);
			}
		}
		return s.size();
	}
		
		
	@GetMapping("/nbMemebersF")
	public ArrayList<Integer> getNbMembersF(){
		ArrayList<Integer> nbMembersF = new ArrayList<Integer>();
		ArrayList<Membre> members = (ArrayList<Membre>)memrepository.findAll();
		ArrayList<Evenement> evenements = (ArrayList<Evenement>)evenementrepository.findAll();
		for(int event=0;event<evenements.size();event++) {
			nbMembersF.add(0);
			for(Membre m:members) {
				if(m.getHackatons().contains(evenements.get(event))) {
					System.out.println("---------------------------------------");
					if(m.getSexe().equals("femme")) {
						System.out.println("*******************************");
						nbMembersF.set(event, nbMembersF.get(event)+1);
					}
				}
			}
		}
		
		return nbMembersF;
	}
	
	@GetMapping("/mesDemandes/{id}")
	public Set<Membre> mesDemandes(@PathVariable Long id){
		Set<Demande> d = new HashSet<>();
		Set<Membre> m = new HashSet<>();
		Evenement event = evenementrepository.findById(id).get();
		d=event.getDemandes();
		for(Demande el:d){
			
			if(el.getEtat().equals("C")) {
				
			m.add(el.getMembre());
			}
		}
		return m;
	}
		
	
}
		
	

	
