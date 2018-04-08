package com.ISAProjekat.model;

import java.util.ArrayList;
import com.ISAProjekat.model.Projekcija;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bioskop")
public class Bioskop {
	
	public Bioskop(){
		
	}
	
	
	
	public Bioskop(Long id, String naziv, String adresa, String opis, ArrayList<Projekcija> projekcije,
			ArrayList<Karta> karte, double prosecna_ocena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.projekcije = projekcije;
		this.karte = karte;
		this.prosecna_ocena=prosecna_ocena;
	}


	@Id
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	private ArrayList<Projekcija> projekcije;
	private ArrayList<Karta> karte;
	private double prosecna_ocena;
	
}
