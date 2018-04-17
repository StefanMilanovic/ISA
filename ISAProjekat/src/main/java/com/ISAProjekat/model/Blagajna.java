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
public class Blagajna {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false,updatable = true)
	private String naziv;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blagajna")
	@JsonIgnore
	private Set<Rekvizit> rekviziti;

	public Blagajna(Long id, String naziv, Set<Rekvizit> rekviziti) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.rekviziti = rekviziti;
	}
	
	
	
	public Blagajna(){
		
		
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



	public Set<Rekvizit> getRekviziti() {
		return rekviziti;
	}



	public void setRekviziti(Set<Rekvizit> rekviziti) {
		this.rekviziti = rekviziti;
	}
	
	
	
	
	
}
