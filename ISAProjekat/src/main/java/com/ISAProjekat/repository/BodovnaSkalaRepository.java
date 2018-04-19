package com.ISAProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISAProjekat.model.BodovnaSkala;
@Repository
public interface BodovnaSkalaRepository extends JpaRepository<BodovnaSkala, Long>{

}
