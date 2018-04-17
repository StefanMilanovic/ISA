package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	
	List<Sala>findAll();

}
