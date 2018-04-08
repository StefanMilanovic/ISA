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
	
	private ArrayList<Glumac> spisak_glumaca;
	private ArrayList<Sala> spisak_sala;
	
	
}
