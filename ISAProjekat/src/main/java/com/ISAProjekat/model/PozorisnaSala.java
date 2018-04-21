package com.ISAProjekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PozorisnaSala {

	@Id
	@Column(name="p_sala_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="poz_sala")
	@JsonIgnore
	private Set<Projekcija> projekcije;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="pozorisnaSala")
	@JsonIgnore
	private Set<Sediste> sedista = new HashSet<Sediste>();
	
	@ManyToOne(optional = false)
	private Pozoriste pozoriste;
	
	public PozorisnaSala(){
		
		
	}
	
	
	
	public PozorisnaSala(Long id, String naziv, Set<Projekcija> projekcije,Pozoriste pozoriste) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.projekcije = projekcije;
		this.pozoriste = pozoriste;
	}
	public PozorisnaSala(String naziv) {
		super();
		this.naziv = naziv;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(Set<Projekcija> projekcije) {
		this.projekcije = projekcije;
	}

	public Pozoriste getPozoriste() {
		return pozoriste;
	}

	public void setPozoriste(Pozoriste pozoriste) {
		this.pozoriste = pozoriste;
	}



	public Set<Sediste> getSedista() {
		return sedista;
	}



	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}
	
	
}
