package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Sediste;

public interface SedisteRepository extends JpaRepository<Sediste, Long>{
	List<Sediste>findAll();
}
