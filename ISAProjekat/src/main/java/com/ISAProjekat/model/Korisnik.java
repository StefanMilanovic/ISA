package com.ISAProjekat.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity 
public class Korisnik implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Korisnik() {
		
	}
	

	



	public Korisnik(Long id, String email, String sifra,  String ime, String prezime, String grad,
			String telefon, String tipKorisnika) {
		super();
		this.id = id;
		this.email = email;
		this.sifra = sifra;

		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.telefon = telefon;
		this.tipKorisnika = tipKorisnika;
	}






	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "sifra", nullable = false)
    private String sifra;

    @Column(name = "ime", nullable = false)
    private String ime;
    
    @Column(name = "prezime", nullable = false)
    private String prezime;
    
    @Column(name = "grad", nullable = false)
    private String grad;

    @Column(name = "telefon", nullable = false)
    private String telefon;
    
    @Column(name =  "tipKorisnika", nullable= false)
    private String tipKorisnika; 
    
    
    
    //get i set metode

	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public String getSifra() {
		return sifra;
	}






	public void setSifra(String sifra) {
		this.sifra = sifra;
	}













	public String getIme() {
		return ime;
	}






	public void setIme(String ime) {
		this.ime = ime;
	}






	public String getPrezime() {
		return prezime;
	}






	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}






	public String getGrad() {
		return grad;
	}






	public void setGrad(String grad) {
		this.grad = grad;
	}






	public String getTelefon() {
		return telefon;
	}






	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}






	public String getTipKorisnika() {
		return tipKorisnika;
	}






	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    


   
    
}

