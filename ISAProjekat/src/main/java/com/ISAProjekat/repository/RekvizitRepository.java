package com.ISAProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISAProjekat.model.Rekvizit;

@Repository
public interface RekvizitRepository extends JpaRepository<Rekvizit,Long> {

}
