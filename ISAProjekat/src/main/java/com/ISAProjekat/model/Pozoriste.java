package com.ISAProjekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pozoriste implements Serializable{
	
	public Pozoriste(){}
	
	private static final long serialVersionUID = 1L;
	
	
	public Pozoriste(String naziv, String adresa, String opis, double prosecna_ocena) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.prosecna_ocena=prosecna_ocena;
	}
	public Pozoriste(String naziv, String adresa, String opis) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;

	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pozoriste_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "naziv", nullable = false, updatable = true,unique = true)
	private String naziv;
	
	@Column(name = "adresa", nullable = false, updatable = true)
	private String adresa;
	
	@Column(name = "opis", nullable = true, updatable = true)
	private String opis;
	
	@Column(name = "pros_ocena", nullable = true, updatable = true)
	private double prosecna_ocena;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pozoriste")
	@JsonIgnore
	private Set<PozorisnaSala> pozorisneSale;

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getProsecna_ocena() {
		return prosecna_ocena;
	}

	public void setProsecna_ocena(double prosecna_ocena) {
		this.prosecna_ocena = prosecna_ocena;
	}
	public Set<PozorisnaSala> getPozorisneSale() {
		return pozorisneSale;
	}
	public void setPozorisneSale(Set<PozorisnaSala> pozorisneSale) {
		this.pozorisneSale = pozorisneSale;
	}
	
	
}
