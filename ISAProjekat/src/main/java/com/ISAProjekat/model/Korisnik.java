package com.ISAProjekat.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity 
public class Korisnik implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Korisnik() {
		
	}
	

	



	public Korisnik( String email, String sifra,  String ime, String prezime, String grad,
			String telefon, String tipKorisnika, boolean bioUlogovan, boolean predefinisan) {
		super();
		
		this.email = email;
		this.sifra = sifra;

		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.telefon = telefon;
		this.tipKorisnika = tipKorisnika;
		this.bioUlogovan = bioUlogovan;
		this.predefinisan = predefinisan;
		
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
    
    @Column(name =  "bioUlogovan", nullable= false)
    private boolean bioUlogovan; 
    
    @Column(name =  "predefinisan", nullable= false)
    private boolean predefinisan;
    
    
    @Column(name =  "brojPoseta", nullable= false)
    private int brojPoseta;
    
    @Column(name =  "status", nullable= false) //zlatni srebrni i bronzani
    private String status;
    //get i set metode
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="korisnik")
    @JsonIgnore
    private Set<Ocena> ocene = new HashSet<Ocena>();
    
    
    

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik")
	@JsonIgnore
	private Set<Rekvizit> rekviziti = new HashSet<Rekvizit>(); //rezervisani rekviziti
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik")
   	@JsonIgnore
   	private Set<Oglas> oglasi = new HashSet<Oglas>();//oglasi koje je korisnik objavio 
     
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnik")
   	@JsonIgnore
   	private Set<Ponuda> ponude = new HashSet<Ponuda>();
  
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






	




	public boolean isBioUlogovan() {
		return bioUlogovan;
	}






	public void setBioUlogovan(boolean bioUlogovan) {
		this.bioUlogovan = bioUlogovan;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	public boolean isPredefinisan() {
		return predefinisan;
	}






	public void setPredefinisan(boolean predefinisan) {
		this.predefinisan = predefinisan;
	}






	public int getBrojPoseta() {
		return brojPoseta;
	}






	public void setBrojPoseta(int brojPoseta) {
		this.brojPoseta = brojPoseta;
	}






	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
	}






	public Set<Ocena> getOcene() {
		return ocene;
	}
		
		public Set<Rekvizit> getRekviziti() {
		return rekviziti;
	}






	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
		
	}
	public void setRekviziti(Set<Rekvizit> rekviziti) {
		this.rekviziti = rekviziti;
	}






	public Set<Oglas> getOglasi() {
		return oglasi;
	}






	public void setOglasi(Set<Oglas> oglasi) {
		this.oglasi = oglasi;
	}






	public Set<Ponuda> getPonude() {
		return ponude;
	}






	public void setPonude(Set<Ponuda> ponude) {
		this.ponude = ponude;
	}

    


   
    
}

