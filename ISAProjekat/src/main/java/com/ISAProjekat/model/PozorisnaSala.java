package com.ISAProjekat.model;

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
public class PozorisnaSala {

	@Id
	@Column(name="b_sala_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="sala")
	@JsonIgnore
	private Set<Projekcija> projekcije;

	public PozorisnaSala(Long id, String naziv, Set<Projekcija> projekcije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.projekcije = projekcije;
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
	
}
