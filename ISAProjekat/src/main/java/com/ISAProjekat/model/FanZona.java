package com.ISAProjekat.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FanZona {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "naziv", nullable = false, updatable = true)
	private String naziv;
	
	//Value of mappedBy is name of the field that is owning side of bidirectional relationship
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fanZona")
	@JsonIgnore
	private Set<Oglas> oglasi;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fanZona")
	@JsonIgnore
	private Set<Blagajna> blagajna;
	
	public FanZona(){
		
	}

	

	public FanZona(Long id, String naziv, Set<Oglas> oglasi) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.oglasi = oglasi;
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



	public Set<Oglas> getOglasi() {
		return oglasi;
	}



	public void setOglasi(Set<Oglas> oglasi) {
		this.oglasi = oglasi;
	}
	
	
}
