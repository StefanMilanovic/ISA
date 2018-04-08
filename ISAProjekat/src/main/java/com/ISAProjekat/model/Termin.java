package com.ISAProjekat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Termin {
	
	@Id
	private Long id;
	
	private String from;
	private String untill;
}
