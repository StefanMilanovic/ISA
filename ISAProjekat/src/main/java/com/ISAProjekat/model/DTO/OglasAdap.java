package com.ISAProjekat.model.DTO;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ISAProjekat.model.Oglas;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

@Component
public class OglasAdap implements Converter<Oglas, OglasDTO>{

	@Override
	public OglasDTO convert(Oglas source) {
		
		if(source == null) {
			return null;
		}
		
		ModelMapper modelMapper = new ModelMapper();
		OglasDTO oglasDTO = modelMapper.map(source, OglasDTO.class);
		return oglasDTO;
		//return null;
	}
	
	public List<OglasDTO> convert(List<Oglas> source){
		
		List<OglasDTO> oglasDTO = new ArrayList<OglasDTO>();
		for (Oglas oglas : source) {
			oglasDTO.add(convert(oglas));
		}
		
		return oglasDTO;
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