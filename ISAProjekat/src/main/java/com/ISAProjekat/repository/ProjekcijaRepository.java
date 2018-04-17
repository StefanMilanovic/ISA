package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Projekcija;


public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long>{
	
	List<Projekcija> findAll();
	
}
