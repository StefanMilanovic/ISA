package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Bioskop;

public interface BioskopRepository extends JpaRepository<Bioskop, Long>{
	List<Bioskop>findAll();
}
