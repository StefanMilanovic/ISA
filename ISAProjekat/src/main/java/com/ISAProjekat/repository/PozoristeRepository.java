package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Pozoriste;

public interface PozoristeRepository extends JpaRepository<Pozoriste, Long>{
	List<Pozoriste>findAll();
	Pozoriste findById(Long id);
}
