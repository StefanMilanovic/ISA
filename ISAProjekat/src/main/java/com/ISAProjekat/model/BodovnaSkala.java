package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class BodovnaSkala {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

	@Column(name = "bronzaniBod", nullable = false)
	private int bronzaniBod;
	@Column(name = "srebrniBod", nullable = false)
	private int srebrniBod;
	@Column(name = "zlatniBod", nullable = false)
	private int zlatniBod;
	
	
	public BodovnaSkala(){
		
	}

	
	public BodovnaSkala(int bronzaniBod, int srebrniBod, int zlatniBod) {
		super();
		this.bronzaniBod = bronzaniBod;
		this.srebrniBod = srebrniBod;
		this.zlatniBod = zlatniBod;
	}


	public int getBronzaniBod() {
		return bronzaniBod;
	}


	public void setBronzaniBod(int bronzaniBod) {
		this.bronzaniBod = bronzaniBod;
	}


	public int getSrebrniBod() {
		return srebrniBod;
	}


	public void setSrebrniBod(int srebrniBod) {
		this.srebrniBod = srebrniBod;
	}


	public int getZlatniBod() {
		return zlatniBod;
	}


	public void setZlatniBod(int zlatniBod) {
		this.zlatniBod = zlatniBod;
	}
	
	
}
