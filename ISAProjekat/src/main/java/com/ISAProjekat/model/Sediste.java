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
public class Sediste {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sediste_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name="vip_sediste", nullable = true)
	private boolean isVip;
	
	@Column(name="balkon_sediste",nullable=true)
	private boolean isBalkonSediste;
	
	@ManyToOne(optional = true)
	private Sala sala;
	
	@ManyToOne(optional = true)
	private PozorisnaSala pozorisnaSala;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="sediste")
	@JsonIgnore
	private Set<Karta> karte = new HashSet<Karta>();
	
	public Sediste(){}

	public Sediste(boolean isVip, boolean isBalkonSediste, Sala sala, PozorisnaSala p) {
		super();
		this.isVip = isVip;
		this.isBalkonSediste = isBalkonSediste;
		this.sala = sala;
		this.pozorisnaSala = p;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public boolean isBalkonSediste() {
		return isBalkonSediste;
	}

	public void setBalkonSediste(boolean isBalkonSediste) {
		this.isBalkonSediste = isBalkonSediste;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public PozorisnaSala getPozorisnaSala() {
		return pozorisnaSala;
	}

	public void setPozorisnaSala(PozorisnaSala pozorisnaSala) {
		this.pozorisnaSala = pozorisnaSala;
	}

	public Set<Karta> getKarte() {
		return karte;
	}

	public void setKarte(Set<Karta> karte) {
		this.karte = karte;
	}
	
	
	
}
