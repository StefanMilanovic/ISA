package com.ISAProjekat.model;

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
public class Projekcija {
	
	public Projekcija(){}
	
	
	
	
	public Projekcija(String naziv, String zarn, String ime_reditelja, String trajanje,String termin_od, String termin_do, double prosecna_ocena,
			int broj_glasova, String opis, double cena, String spisak_glumaca, Sala sala, PozorisnaSala poz_sala) {
		super();
		this.naziv = naziv;
		this.zarn = zarn;
		this.ime_reditelja = ime_reditelja;
		this.trajanje = trajanje;
		this.prosecna_ocena = prosecna_ocena;
		this.broj_glasova = broj_glasova;
		this.opis = opis;
		this.cena = cena;
		this.spisak_glumaca = spisak_glumaca;
		this.sala = sala;
		this.termin_do = termin_do;
		this.termin_od = termin_do;
		this.poz_sala = poz_sala;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "projekcija_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "zanr", nullable = false)
	private String zarn;
	
	@Column(name = "ime_reditelja", nullable = false)
	private String ime_reditelja;
	
	@Column(name = "trajanje", nullable = false)
	private String trajanje;

	
	//private Image poster;
	
	@Column(name = "pros_ocena", nullable = true)
	private double prosecna_ocena;
	
	@Column(name = "opis", nullable = true)
	private String opis;
	
	@Column(name = "cena", nullable = false)
	private double cena;
	
	@Column(name="glumci",nullable=false)
	private String spisak_glumaca;
	
	@ManyToOne(optional=true)
	private Sala sala;
	
	@ManyToOne(optional=true)
	private PozorisnaSala poz_sala;
	
	@Column(name="broj_glasova")
	private int broj_glasova;
	
	@Column(name="termin_od")
	private String termin_od;
	
	@Column(name="termin_do")
	private String termin_do;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="projekcija")
	@JsonIgnore
	private Set<Ocena>ocene = new HashSet<Ocena>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="projekcija")
	@JsonIgnore
	private Set<Karta> karte = new HashSet<Karta>();
	
	
	
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
	public String getZarn() {
		return zarn;
	}
	public void setZarn(String zarn) {
		this.zarn = zarn;
	}
	public String getIme_reditelja() {
		return ime_reditelja;
	}
	public void setIme_reditelja(String ime_reditelja) {
		this.ime_reditelja = ime_reditelja;
	}
	public String getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}
	public double getProsecna_ocena() {
		return prosecna_ocena;
	}
	public void setProsecna_ocena(double prosecna_ocena) {
		this.prosecna_ocena = prosecna_ocena;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public String getSpisak_glumaca() {
		return spisak_glumaca;
	}
	public void setSpisak_glumaca(String spisak_glumaca) {
		this.spisak_glumaca = spisak_glumaca;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public int getBroj_glasova() {
		return broj_glasova;
	}




	public void setBroj_glasova(int broj_glasova) {
		this.broj_glasova = broj_glasova;
	}




	public String getTermin_od() {
		return termin_od;
	}




	public void setTermin_od(String termin_od) {
		this.termin_od = termin_od;
	}




	public String getTermin_do() {
		return termin_do;
	}




	public void setTermin_do(String termin_do) {
		this.termin_do = termin_do;
	}




	public Set<Ocena> getOcene() {
		return ocene;
	}




	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}




	public PozorisnaSala getPoz_sala() {
		return poz_sala;
	}




	public void setPoz_sala(PozorisnaSala poz_sala) {
		this.poz_sala = poz_sala;
	}




	public Set<Karta> getKarte() {
		return karte;
	}




	public void setKarte(Set<Karta> karte) {
		this.karte = karte;
	}
	
	
}
