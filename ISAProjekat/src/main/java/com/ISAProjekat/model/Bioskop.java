package com.ISAProjekat.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bioskop implements Serializable{
	
	public Bioskop(){
		
	}
	
	private static final long serialVersionUID = 1L;
	
	public Bioskop(String naziv, String adresa, String opis, ArrayList<Projekcija> projekcije,
			ArrayList<Karta> karte, double prosecna_ocena) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.projekcije = projekcije;
		this.karte = karte;
		this.prosecna_ocena=prosecna_ocena;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bioskop_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "naziv", nullable = false, updatable = true)
	private String naziv;
	
	@Column(name = "adresa", nullable = false, updatable = true)
	private String adresa;
	
	@Column(name = "opis", nullable = true, updatable = true)
	private String opis;
	private ArrayList<Projekcija> projekcije;
	private ArrayList<Karta> karte;
	
	@Column(name = "pros_ocena", nullable = true, updatable = true)
	private double prosecna_ocena;

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

	public ArrayList<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(ArrayList<Projekcija> projekcije) {
		this.projekcije = projekcije;
	}

	public ArrayList<Karta> getKarte() {
		return karte;
	}

	public void setKarte(ArrayList<Karta> karte) {
		this.karte = karte;
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
	
	
	
	
}
