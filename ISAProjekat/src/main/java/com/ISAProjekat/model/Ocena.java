package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ocena")
public class Ocena {
	
	public Ocena(){}
	
	
	
	
	
	public Ocena(Korisnik korisnik, Bioskop bioskop, Pozoriste pozoriste, Projekcija projekcija, int vrednost) {
		super();
		this.korisnik = korisnik;
		this.bioskop = bioskop;
		this.vrednost = vrednost;
		this.pozoriste = pozoriste;
		this.projekcija = projekcija;
	}





	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ocena_id", nullable = false, updatable = false)
	private Long id;
	
	@ManyToOne(optional = false)
	private Korisnik korisnik;
	
	@ManyToOne(optional= true)
	private Bioskop bioskop;

	@ManyToOne(optional= true)
	private Pozoriste pozoriste;
	
	@ManyToOne(optional= true)
	private Projekcija projekcija;
	
	
	
	
	public Long getId() {
		return id;
	}
	
	@Column(name="vrednost")
	private int vrednost;
	
	




	public void setId(Long id) {
		this.id = id;
	}





	public Korisnik getKorisnik() {
		return korisnik;
	}





	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}





	public Bioskop getBioskop() {
		return bioskop;
	}





	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}





	public int getVrednost() {
		return vrednost;
	}





	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}





	public Pozoriste getPozoriste() {
		return pozoriste;
	}





	public void setPozoriste(Pozoriste pozoriste) {
		this.pozoriste = pozoriste;
	}





	public Projekcija getProjekcija() {
		return projekcija;
	}





	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}
	
	
}
