package com.ISAProjekat.model;

import java.util.ArrayList;

import javax.persistence.Id;

public class Sala {
	
	@Id
	private Long id;
	private ArrayList<Termin> termini;
}
