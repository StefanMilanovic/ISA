package com.ISAProjekat.service;

import java.util.List;

import com.ISAProjekat.model.Dan;

public interface DanService {
	List<Dan>findAll();
	Dan findDanById(Long id);
	Dan save (Dan d);
}
