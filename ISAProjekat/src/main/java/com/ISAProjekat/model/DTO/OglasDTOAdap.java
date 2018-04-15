package com.ISAProjekat.model.DTO;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ISAProjekat.model.FanZona;
import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.service.FanZonaService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

@Component
public class OglasDTOAdap implements Converter<OglasDTO, Oglas>{

	@Autowired
	private FanZonaService fanZonaService;
	
	@Override
	public Oglas convert(OglasDTO source) {
		
		if(source == null) {
			return null;
		}
		
		Oglas oglas = new Oglas();
		
		oglas.setId(source.getId());
		oglas.setDatum(source.getDatum());
		oglas.setNaziv(source.getNaziv());
		oglas.setOdobren(source.isOdobren());
		oglas.setOpis(source.getOpis());
		oglas.setSlika(source.getSlika());
		oglas.setCena(source.getCena());
		
		if(source.getFanZonaId() != null) {
			FanZona fanZona = fanZonaService.findOne(source.getFanZonaId());
			oglas.setFanZona(fanZona);
		}else
			oglas.setFanZona(fanZonaService.findOne((long) 1));
		
		return oglas;
	}
	
	
	public List<Oglas> convert(List<OglasDTO> source){
		
		List<Oglas> oglasi = new ArrayList<Oglas>();
		for (OglasDTO oglasDTO : source) {
			oglasi.add(convert(oglasDTO));
		}
		
		return oglasi;
	}


	@Override
	public JavaType getInputType(TypeFactory arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public JavaType getOutputType(TypeFactory arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}