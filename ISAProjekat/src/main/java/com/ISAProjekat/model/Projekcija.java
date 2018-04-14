package com.ISAProjekat.model;

import java.awt.Image;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Projekcija {
	
	public Projekcija(){}
	
	
	
	
	public Projekcija(String naziv, String zarn, String ime_reditelja, String trajanje, double prosecna_ocena,
			String opis, double cena, String spisak_glumaca, String sala) {
		super();
		this.naziv = naziv;
		this.zarn = zarn;
		this.ime_reditelja = ime_reditelja;
		this.trajanje = trajanje;
		this.prosecna_ocena = prosecna_ocena;
		this.opis = opis;
		this.cena = cena;
		this.spisak_glumaca = spisak_glumaca;
		this.sala = sala;
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
	
	@Column(name="sala", nullable=true)
	private String sala;
	
	
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
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	
}
