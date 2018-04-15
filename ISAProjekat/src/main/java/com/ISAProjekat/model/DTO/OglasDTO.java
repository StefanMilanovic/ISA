package com.ISAProjekat.model.DTO;
import java.sql.Date;

public class OglasDTO {

	private Long id;
	private String naziv;
	private String opis;
	private Date datum; 
	private String slika;
	private String cena;
	private boolean odobren;
	private Long fanZonaId;
	private String fanZonaNaziv;
	
	public OglasDTO() {
		
	}

	public OglasDTO(Long id, String naziv, String opis, Date datum, String slika, String cena, boolean odobren,
			Long fanZonaId, String fanZonaNaziv) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.datum = datum;
		this.slika = slika;
		this.cena = cena;
		this.odobren = odobren;
		this.fanZonaId = fanZonaId;
		this.fanZonaNaziv = fanZonaNaziv;
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public Long getFanZonaId() {
		return fanZonaId;
	}

	public void setFanZonaId(Long fanZonaId) {
		this.fanZonaId = fanZonaId;
	}

	public String getFanZonaNaziv() {
		return fanZonaNaziv;
	}

	public void setFanZonaNaziv(String fanZonaNaziv) {
		this.fanZonaNaziv = fanZonaNaziv;
	}
	
}