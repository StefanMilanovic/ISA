package com.ISAProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ISAProjekat.model.Dan;

public interface DanRepository extends JpaRepository<Dan, Long>{
	List<Dan>findAll();
	Dan findById(Long id);
}
