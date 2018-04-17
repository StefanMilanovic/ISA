package com.ISAProjekat.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sala {
	
	@Id
	@Column(name = "sala_id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="pripada")
	private Long id_parent;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	
	//private List<Projekcija> projekcije;

	public Sala(String naziv, Long id_parent) {
		super();
		this.naziv = naziv;
		this.id_parent = id_parent;
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

	public Long getId_parent() {
		return id_parent;
	}

	public void setId_parent(Long id_parent) {
		this.id_parent = id_parent;
	}
}
