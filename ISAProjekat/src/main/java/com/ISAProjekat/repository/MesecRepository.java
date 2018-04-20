package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Mesec;

public interface MesecRepository extends JpaRepository<Mesec, Long>{
	List<Mesec>findAll();
	Mesec findById(Long id);
}
