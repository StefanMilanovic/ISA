package com.ISAProjekat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ISAProjekat.model.Oglas;
import com.ISAProjekat.repository.OglasRepository;
import com.ISAProjekat.service.OglasService;


@Transactional
@Service
public class OglasServiceImpl  implements OglasService{

	

		@Autowired
		private OglasRepository oglasRepository;
		
		@Override
		public Oglas findOglasByNaziv(String naziv) {
			// TODO Auto-generated method stub
			return oglasRepository.findByNazivIgnoreCaseContaining(naziv);
		}
		
		@Override
		public Oglas findOne(Long id) {
			return oglasRepository.findOne(id);
		}

		@Override
		public List<Oglas> findAll() {
			return oglasRepository.findAll();
		}

		@Override
		public Oglas save(Oglas oglas) {
			return oglasRepository.save(oglas);
		}

		@Override
		public List<Oglas> save(List<Oglas> oglasi) {
			return oglasRepository.save(oglasi);
		}

		@Override
		public Oglas delete(Long id) {
			Oglas oglas = oglasRepository.findOne(id);
			if(oglas == null){
				throw new IllegalArgumentException("Tried to delete"
						+ "non-existant oglas");
			}
			oglasRepository.delete(oglas);
			return oglas;
		}


	
}
