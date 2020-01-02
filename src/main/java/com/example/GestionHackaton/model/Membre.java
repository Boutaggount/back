package com.example.GestionHackaton.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Membre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_membre")
	private long id_mem;
	private String prenom;
	private String nom;
	private String adresse;
	private String email;
	private String num;
	private String login;
	private String pwd;
	private String qust;
	private String rep;
	private String sexe;

	
	public Membre() {
		super();
	}
	public Membre(long id_admin, String prenom, String nom, String adresse, String email, String num, String login,
			String pwd, String qust, String rep,String sexe) {
		super();
		this.id_mem = id_admin;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.num = num;
		this.login = login;
		this.pwd = pwd;
		this.qust = qust;
		this.rep = rep;
		this.sexe=sexe;
	}
	

	@ManyToMany (mappedBy = "membre")
	@JsonIgnore
	private Set<Evenement> Hackatons = new HashSet<>();
	
	@ManyToMany (mappedBy = "membres")
	@JsonIgnore
	private Set<equipe> eq = new HashSet<>();


	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getQust() {
		return qust;
	}
	public void setQust(String qust) {
		this.qust = qust;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public long getId_mem() {
		return id_mem;
	}
	public void setId_mem(long id_mem) {
		this.id_mem = id_mem;
	}
	public Set<Evenement> getHackatons() {
		return Hackatons;
	}
	public void setHackatons(Set<Evenement> hackatons) {
		Hackatons = hackatons;
	}
	public Set<equipe> getEq() {
		return eq;
	}
	public void setEq(Set<equipe> eq) {
		this.eq = eq;
	}

	
	

}