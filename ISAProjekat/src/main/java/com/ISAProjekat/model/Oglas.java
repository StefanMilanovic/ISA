package com.ISAProjekat.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Oglas {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "naziv", nullable = false, updatable = true)
	private String naziv;
	
	@Column(name = "opis", nullable = false, updatable = true)
	private String opis;
	
	@Column(name = "cena", nullable = false, updatable = true)
	private String cena;
	
	@Column(name = "slika", nullable = false, updatable = true)
	private String slika;
	
	@Column(name = "datum", nullable = false, updatable = true)
	private Date datum;
	
	@ManyToOne(optional = false)
	private FanZona fanZona;
	
	@Column(name = "odobren",nullable = true, updatable = true)
	private boolean odobren;//da li ga je admin fan zone odobrio
	
	@Column(name = "proveren",nullable = true, updatable = true)
	private boolean proveren; //da li ga je admin fan zone proverio
	
	@Column(name = "prodat",nullable = true, updatable = true)
	private boolean prodat; //da li ga je admin fan zone proverio
	
	@ManyToOne(optional = false)
	private Korisnik korisnik = new Korisnik();
	

	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "oglas")
	 @JsonIgnore
	 private Set<Ponuda> ponude = new HashSet<Ponuda>();//oglasi koje je korisnik objavio 
	  
	public Oglas(){
		
	}

	
	




	public Oglas(Long id, String naziv, String opis, String cena, String slika, Date datum, FanZona fanZona,
			boolean odobren,boolean proveren,boolean prodat) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.slika = slika;
		this.datum = datum;
		this.fanZona = fanZona;
		this.odobren = odobren;
		this.proveren = proveren;
		this.prodat = prodat;
	}







	public boolean isProdat() {
		return prodat;
	}







	public void setProdat(boolean prodat) {
		this.prodat = prodat;
	}







	public boolean isProveren() {
		return proveren;
	}







	public void setProveren(boolean proveren) {
		this.proveren = proveren;
	}







	public boolean isOdobren() {
		return odobren;
	}







	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public FanZona getFanZona() {
		return fanZona;
	}

	public void setFanZona(FanZona fanZona) {
		this.fanZona = fanZona;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Set<Ponuda> getPonude() {
		return ponude;
	}

	public void setPonude(Set<Ponuda> ponude) {
		this.ponude = ponude;
	}

	
}
