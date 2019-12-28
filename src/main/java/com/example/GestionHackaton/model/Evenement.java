package com.example.GestionHackaton.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;





@Entity

public class Evenement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_Hackaton")
	private long id;
	private String intitule;
	private String description;
	private String theme;
	private String capacite;
	private Date date_debut;
	private Date date_fin;
	private String ville;
	private String etablisement;
	private String address;
	//private   Set<equipe> Equipe;

  
	public Evenement() {
		super();
	}
	
	public Evenement(long id, String intitule, String description, String theme, String capacite, Date date_debut,
			Date date_fin, String ville, String etablisement, String address) {
		super();
		this.id = id;
		this.intitule = intitule;
		this.description = description;
		this.theme = theme;
		this.capacite = capacite;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.ville = ville;
		this.etablisement = etablisement;
		this.address = address;
	}
	  @ManyToMany(cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "Pers_Hack", 
	        joinColumns = { @JoinColumn(name = "id_Hackaton") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_membre") }
	    )
	    Set<Membre> membre = new HashSet<>();
	  
		
	public Set<Membre> getMembre() {
		return membre;
	}
	public void setMembre(Set<Membre> membre) {
		this.membre = membre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCapacite() {
		return capacite;
	}
	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getEtablisement() {
		return etablisement;
	}
	public void setEtablisement(String etablisement) {
		this.etablisement = etablisement;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
/*
	public Set<equipe> getEquipe() {
		return Equipe;
	}

	public void setEquipe(Set<equipe> equipe) {
		Equipe = equipe;
	}
	*/
	
	
}