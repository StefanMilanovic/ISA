package com.ISAProjekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ISAProjekat.model.Bioskop;

public interface BioskopService {
	List<Bioskop> findAll();
}
