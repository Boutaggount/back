package com.example.GestionHackaton.model;



import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	 
	 @OneToMany(mappedBy="Eqs")
	 @JsonIgnore
	 private Set<Membre> members;
	 
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

	public Set<Membre> getMembers() {
		return members;
	}

	public void setMembers(Set<Membre> members) {
		this.members = members;
	}
	
}