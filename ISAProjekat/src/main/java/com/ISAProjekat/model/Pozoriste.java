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
public class Pozoriste implements Serializable{
	
	public Pozoriste(){}
	
	private static final long serialVersionUID = 1L;
	
	
	public Pozoriste(String naziv, String adresa, String opis, double prosecna_ocena, int broj_glasova, int ukupan_prihod) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.prosecna_ocena=prosecna_ocena;
		this.broj_glasova = broj_glasova;
		this.ukupan_prihod = ukupan_prihod;
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
	
	@Column(name="broj_glasova")
	private int broj_glasova;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pozoriste")
	@JsonIgnore
	private Set<PozorisnaSala> pozorisneSale;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="pozoriste")
	@JsonIgnore
	private Set<Ocena>ocene = new HashSet<Ocena>();
	
	@Column(name="ukupan_prihod")
	private int ukupan_prihod;
	
	
	
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
	public Set<Ocena> getOcene() {
		return ocene;
	}
	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
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

	public int getUkupan_prihod() {
		return ukupan_prihod;
	}

	public void setUkupan_prihod(int ukupan_prihod) {
		this.ukupan_prihod = ukupan_prihod;
	}
	
	
}
