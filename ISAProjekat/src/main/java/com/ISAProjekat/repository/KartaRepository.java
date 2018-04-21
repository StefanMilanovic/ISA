package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Karta;

public interface KartaRepository extends JpaRepository<Karta, Long>{
	List<Karta>findAll();
}
