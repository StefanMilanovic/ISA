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
public class Mesec {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mesec_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name="godina")
	private int godina;
	
	@Column(name="broj_meseca")
	private int broj_meseca;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="mesec")
	@JsonIgnore
	private Set<Dan> dani = new HashSet<Dan>();
	
	@ManyToOne(optional=true)
	private Bioskop bioskop;
	
	@ManyToOne(optional=true)
	private Pozoriste pozoriste;
	
	
	
	public Mesec(){}
	

	public Mesec(String naziv, int godina, int broj_meseca, Bioskop bioskop, Pozoriste pozoriste) {
		super();
		this.naziv = naziv;
		this.godina = godina;
		this.broj_meseca = broj_meseca;
		this.bioskop = bioskop;
		this.pozoriste = pozoriste;

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

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public int getBroj_meseca() {
		return broj_meseca;
	}

	public void setBroj_meseca(int broj_meseca) {
		this.broj_meseca = broj_meseca;
	}

	public Set<Dan> getDani() {
		return dani;
	}

	public void setDani(Set<Dan> dani) {
		this.dani = dani;
	}


	public Bioskop getBioskop() {
		return bioskop;
	}


	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}


	public Pozoriste getPozoriste() {
		return pozoriste;
	}


	public void setPozoriste(Pozoriste pozoriste) {
		this.pozoriste = pozoriste;
	}
	
	
}
