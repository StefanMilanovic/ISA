package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Termin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "termin_id", nullable = false, updatable=false)
	private Long id;
	
	@Column(name = "termin_od", nullable = false)
	private String from;
	
	@Column(name = "termin_do", nullable = false)
	private String untill;
}
