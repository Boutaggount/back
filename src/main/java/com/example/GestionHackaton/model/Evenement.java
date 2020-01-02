package com.example.GestionHackaton.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;






@Entity

public class Evenement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_Hackaton")
	private long id;
	private String intitule;
	private String description;
	private String theme;
	private int capacite;
	private Date date_debut;
	private Date date_fin;
	private String ville;
	private String etablisement;
	private String address;
	private int nbr;
	private boolean eqestcreer;



  
	public Evenement() {
		super();
		this.eqestcreer=false;
	}
	

	  public Evenement(long id, String intitule, String description, String theme, int capacite, Date date_debut,
			Date date_fin, String ville, String etablisement, String address, int nbr) {
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
		this.nbr = nbr;
		this.eqestcreer=false;
	}


	@ManyToMany(cascade = { CascadeType.ALL })
	  @JsonIgnore
	    @JoinTable(
	        name = "Pers_Hack", 
	        joinColumns = { @JoinColumn(name = "id_Hackaton") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_membre") }
	    )
	    Set<Membre> membre = new HashSet<>();

	    @OneToMany(mappedBy="event")
	    @JsonIgnore
	    private Set<equipe> eq;
		
	    @ManyToOne
	    @JsonIgnore
	    @JoinColumn(name="id_Admin")
	    private Administrateur admins;
	    
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
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
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

	public Set<equipe> getEq() {
		return eq;
	}

	public void setEq(Set<equipe> eq) {
		this.eq = eq;
	}

	public Administrateur getAdmins() {
		return admins;
	}

	public void setAdmins(Administrateur admins) {
		this.admins = admins;
	}

	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbrEquipe) {
		this.nbr = nbrEquipe;
	}


	public boolean isEqestcreer() {
		return eqestcreer;
	}


	public void setEqestcreer(boolean eqestcreer) {
		this.eqestcreer = eqestcreer;
	}

	
	
}