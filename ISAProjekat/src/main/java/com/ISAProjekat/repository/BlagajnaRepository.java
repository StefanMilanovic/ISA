package com.ISAProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ISAProjekat.model.Blagajna;

@Repository
public interface BlagajnaRepository  extends JpaRepository<Blagajna,Long> {

}
