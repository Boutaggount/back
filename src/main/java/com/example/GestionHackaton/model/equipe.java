package com.example.GestionHackaton.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class equipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_equipe")
	private long id_equipe;
    //private Evenement event;

	public equipe() {
		super();
	}

	public long getId_equipe() {
		return id_equipe;
	}

	public void setId_equipe(long id_equipe) {
		this.id_equipe = id_equipe;
	}
/*
	public Evenement getEvent() {
		return event;
	}

	public void setEvent(Evenement event) {
		this.event = event;
	}
	*/
}
