package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bioskop")
public class Bioskop {
	
	public Bioskop(){
		
	}
	
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	private ArrayList<Projekcija> projekcije;
	private ArrayList<Karta> karte;
	
}
