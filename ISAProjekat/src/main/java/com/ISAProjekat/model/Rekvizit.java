package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Rekvizit {

	

		
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
		
	
		@ManyToOne(optional = false)
		private Blagajna blagajna;
		
		@ManyToOne(optional = true)
		private Korisnik korisnik = new Korisnik();
		
		@Column(name = "rezervisan", nullable = false, updatable = true)
		private boolean rezervisan;
		
		
		public Rekvizit(){
			
		}

		
		
		public Rekvizit(Long id, String naziv, String opis, String cena, String slika, Blagajna blagajna, boolean rezervisan) {
			super();
			this.id = id;
			this.naziv = naziv;
			this.opis = opis;
			this.cena = cena;
			this.slika = slika;
			this.blagajna = blagajna;
			this.rezervisan = rezervisan;
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

		public Blagajna getBlagajna() {
			return blagajna;
		}

		public void setBlagajna(Blagajna blagajna) {
			this.blagajna = blagajna;
		}



		public Korisnik getKorisnik() {
			return korisnik;
		}



		public void setKorisnik(Korisnik korisnik) {
			this.korisnik = korisnik;
		}



		public boolean isRezervisan() {
			return rezervisan;
		}



		public void setRezervisan(boolean rezervisan) {
			this.rezervisan = rezervisan;
		}
		
		
		
}
