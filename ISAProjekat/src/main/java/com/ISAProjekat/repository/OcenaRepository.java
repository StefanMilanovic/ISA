package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Ocena;

public interface OcenaRepository extends JpaRepository<Ocena,Long>{
	List<Ocena>findAll();
	Ocena findById(Long id);
}
