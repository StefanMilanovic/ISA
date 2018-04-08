package com.ISAProjekat.model;

import java.util.ArrayList;
import com.ISAProjekat.model.Projekcija;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Bioskop {
	
	public Bioskop(){
		
	}
	
	
	
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
	
}
