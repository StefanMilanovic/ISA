package com.ISAProjekat.service;

import com.ISAProjekat.model.BodovnaSkala;

public interface BodovnaSkalaService {
	BodovnaSkala findById(Long id);
	BodovnaSkala save(BodovnaSkala bodovnaSkala);
}
