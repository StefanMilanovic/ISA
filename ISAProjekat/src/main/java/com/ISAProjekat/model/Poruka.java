package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity 
public class Poruka {

	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "sadrzaj", nullable = false)
    private String sadrzaj;
    
	@ManyToOne(optional = true)
	private Korisnik korisnik = new Korisnik();
	

    public Poruka(){
    	
    }
    
    

	public Poruka( String sadrzaj) {
		super();
		this.sadrzaj = sadrzaj;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}



	public Korisnik getKorisnik() {
		return korisnik;
	}



	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
    
    
}
