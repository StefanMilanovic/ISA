package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mesec_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name="broj_dana")
	private int broj_dana;
	
	@Column(name ="poseta_pozorista")
	private int broj_poseta_poz;
	
	@Column(name ="poseta_bio")
	private int broj_poseta_bio;
	
	@ManyToOne(optional=false)
	private Mesec mesec;
	
	public Dan(){}
	
	public Dan(int broj_dana, Mesec mesec, int broj_poseta_poz, int broj_poseta_bio) {
		super();
		this.broj_dana = broj_dana;
		this.mesec = mesec;
		this.broj_poseta_poz=broj_poseta_poz;
		this.broj_poseta_bio=broj_poseta_bio;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBroj_dana() {
		return broj_dana;
	}

	public void setBroj_dana(int broj_dana) {
		this.broj_dana = broj_dana;
	}

	public Mesec getMesec() {
		return mesec;
	}

	public void setMesec(Mesec mesec) {
		this.mesec = mesec;
	}

	public int getBroj_poseta_poz() {
		return broj_poseta_poz;
	}

	public void setBroj_poseta_poz(int broj_poseta_poz) {
		this.broj_poseta_poz = broj_poseta_poz;
	}

	public int getBroj_poseta_bio() {
		return broj_poseta_bio;
	}

	public void setBroj_poseta_bio(int broj_poseta_bio) {
		this.broj_poseta_bio = broj_poseta_bio;
	}
	
	
	
}	
