package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.PozorisnaSala;

public interface PozoristeSaleRepository extends JpaRepository<PozorisnaSala,Long>{
	List<PozorisnaSala>findAll();
}	
