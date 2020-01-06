package com.example.GestionHackaton.model;



import java.io.Serializable;
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


import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class equipe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_equipe")
	private long id_equipe;
	private String nom;


	public equipe() {
		super();
	}
	
	 public equipe(long id_equipe, String nom) {
		super();
		this.id_equipe = id_equipe;
		this.nom = nom;
	}

	@ManyToOne
	 @JsonIgnore
	 @JoinColumn(name="id_Hackaton")
	    private Evenement event;
	 
	@ManyToMany(cascade = { CascadeType.ALL })
	  @JsonIgnore
	    @JoinTable(
	        name = "Mem_eq", 
	        joinColumns = { @JoinColumn(name = "id_equipe") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_membre") }
	    )
	    Set<Membre> membres = new HashSet<>();
	 
	public long getId_equipe() {
		return id_equipe;
	}

	

	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public void setId_equipe(long id_equipe) {
		this.id_equipe = id_equipe;
	}



	public Evenement getEvent() {
		return event;
	}

	public void setEvent(Evenement event) {
		this.event = event;
	}

	public Set<Membre> getMembres() {
		return membres;
	}

	public void setMembres(Set<Membre> membres) {
		this.membres = membres;
	}


	
}