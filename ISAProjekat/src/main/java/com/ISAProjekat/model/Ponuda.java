package com.ISAProjekat.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ponuda {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String cena;
	
	@Column(nullable = false)
	private boolean odRegistrovanog;		
	
	@Column(nullable = false)
	private String status;			
	
	@ManyToOne(optional = false)
	private Oglas oglas = new Oglas();
	
	@ManyToOne(optional = false)
	private Korisnik korisnik = new Korisnik();
	
	
	
	public Ponuda(){
		
	}
	

	public Ponuda(Long id, String cena, String status, Oglas oglas, Korisnik korisnik,boolean odRegistrovanog) {
		super();
		this.id = id;
		this.cena = cena;
		this.status = status;
		this.oglas = oglas;
		this.korisnik = korisnik;
		this.odRegistrovanog = odRegistrovanog;
	}

	public Ponuda(String cena) {
		super();

		this.cena = cena;

	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}


	public boolean isOdRegistrovanog() {
		return odRegistrovanog;
	}


	public void setOdRegistrovanog(boolean odRegistrovanog) {
		this.odRegistrovanog = odRegistrovanog;
	}
	
	
}
