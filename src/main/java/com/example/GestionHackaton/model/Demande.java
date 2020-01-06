package com.example.GestionHackaton.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Demande implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_demande")
	private long id_demande;
	private String motivation;
	private String etat;
	private String dep;
	private String niveau;
	
	
	public Demande() {	
		super();
		this.etat="C";
	}
	public Demande(long id_demande, String motivation, String dep, String niveau) {
		super();
		this.id_demande = id_demande;
		this.motivation = motivation;
		this.etat="C";
		this.dep = dep;
		this.niveau = niveau;
	}
	
	 
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name="id_Hackaton")
	  private Evenement even;

	 @ManyToOne
	 @JoinColumn(name="id_membre")
	 @JsonIgnore
	 private Membre membre;
	
	public long getId_demande() {
		return id_demande;
	}
	public void setId_demande(long id_demande) {
		this.id_demande = id_demande;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public Evenement getEven() {
		return even;
	}
	public void setEven(Evenement even) {
		this.even = even;
	}
	public Membre getMembre() {
		return membre;
	}
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
	
	
	
	
	
}
