package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISAProjekat.model.PozorisnaSala;
@Repository
public interface PozoristeSaleRepository extends JpaRepository<PozorisnaSala,Long>{
	List<PozorisnaSala>findAll();
}	
