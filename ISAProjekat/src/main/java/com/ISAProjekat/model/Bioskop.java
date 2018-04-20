package com.ISAProjekat.model;

import java.io.Serializable;
import java.util.HashSet;
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
public class Bioskop implements Serializable{
	
	public Bioskop(){
		
	}
	
	private static final long serialVersionUID = 1L;
	
	public Bioskop(String naziv, String adresa, String opis,
			 double prosecna_ocena, int broj_glasova, Set<Sala>sale) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.prosecna_ocena=prosecna_ocena;
		this.broj_glasova = broj_glasova;
		this.sale=sale;
	}
	
	public Bioskop(String naziv, String adresa, String opis	) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
	
	}


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bioskop_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "naziv", nullable = false, updatable = true,unique = true)
	private String naziv;
	
	@Column(name = "adresa", nullable = false, updatable = true)
	private String adresa;
	
	@Column(name = "opis", nullable = true, updatable = true)
	private String opis;
	
	@Column(name = "pros_ocena", nullable = true, updatable = true)
	private double prosecna_ocena;
	
	@Column(name ="broj_glasova")
	private int broj_glasova;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bioskop")
	@JsonIgnore
	private Set<Sala> sale;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bioskop")
	@JsonIgnore
	private Set<Ocena> ocene = new HashSet<Ocena>();
	
	
	

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getBroj_glasova() {
		return broj_glasova;
	}

	public void setBroj_glasova(int broj_glasova) {
		this.broj_glasova = broj_glasova;
	}



	public Set<Sala> getSale() {
		return sale;
	}



	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}
	
	
	
	
}
