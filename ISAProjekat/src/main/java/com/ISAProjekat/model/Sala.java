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
public class Sala {
	
	@Id
	@Column(name = "sala_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private Bioskop bioskop;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name ="vip_enabled")
	private boolean vip_enabled;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sala")
	@JsonIgnore
	private Set<Projekcija> projekcije;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="sala")
	@JsonIgnore
	private Set<Sediste> sedista = new HashSet<Sediste>();

	public Sala(String naziv, Bioskop bioskop, Set<Projekcija> projekcije) {
		super();
		this.naziv = naziv;
		this.bioskop = bioskop;
		this.projekcije=projekcije;
	}
	public Sala(String naziv) {
		super();
		this.naziv = naziv;
	}
	public Sala(){}
	
		

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

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	public Set<Sediste> getSedista() {
		return sedista;
	}
	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}
	public boolean isVip_enabled() {
		return vip_enabled;
	}
	public void setVip_enabled(boolean vip_enabled) {
		this.vip_enabled = vip_enabled;
	}
	
	
}
