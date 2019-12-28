package com.example.GestionHackaton.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrateur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_Admin")
	private long id_admin;
	private String prenom;
	private String nom;
	private String adresse;
	private String email;
	private String num;
	private String login;
	private String pwd;
	
	public Administrateur() {
		super();
	}

	public Administrateur(long id_admin, String prenom, String nom, String adresse, String email, String num,
			String login, String pwd) {
		super();
		this.id_admin = id_admin;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.num = num;
		this.login = login;
		this.pwd = pwd;
	}

	public long getId_admin() {
		return id_admin;
	}

	public void setId_admin(long id_admin) {
		this.id_admin = id_admin;
	}

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
	
	
	
}

