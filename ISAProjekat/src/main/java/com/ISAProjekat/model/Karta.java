package com.ISAProjekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Karta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "karta_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "rezervisana_od", nullable = true)
	private User rezervisana_od;
	
}
