package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Karta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "karta_id", nullable = false, updatable = false)
	private Long id;
	
	@ManyToOne(optional = true)
	private Korisnik korisnik;
	
	@ManyToOne(optional=false)
	private Projekcija projekcija;
	
	@ManyToOne(optional = false)
	private Sediste sediste;
	
	@Column(name="popust")
	private int popust;
	
	public Karta(){}

	public Karta(Korisnik korisnik, Projekcija projekcija, Sediste sediste, int popust) {
		super();
		this.korisnik = korisnik;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.popust = popust;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}
	
	
	
}
