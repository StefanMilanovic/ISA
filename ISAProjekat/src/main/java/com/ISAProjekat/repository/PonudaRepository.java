package com.ISAProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISAProjekat.model.Ponuda;

@Repository
public  interface PonudaRepository  extends JpaRepository<Ponuda, Long>{

}
