package com.ISAProjekat.model;

import java.awt.Image;
import java.util.ArrayList;

import javax.persistence.Id;

public class Projekcija {
	
	public Projekcija(){}
	
	@Id
	private Long id;
	private String naziv;
	private String zarn;
	private String ime_reditelja;
	private String trajanje;
	private Image poster;
	private double prosecna_ocena;
	private String opis;
	private double cena;
	private ArrayList<Glumac> spisak_glumaca;
	private ArrayList<Sala> spisak_sala;
	
	
}
