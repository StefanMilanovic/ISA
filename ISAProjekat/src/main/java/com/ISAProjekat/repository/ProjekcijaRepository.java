package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISAProjekat.model.Projekcija;

@Repository
public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long>{
	
	List<Projekcija> findAll();
	
}
