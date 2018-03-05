package com.leysoft.app.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leysoft.app.entity.Autor;
import com.leysoft.app.repository.AutorRepository;
import com.leysoft.app.service.inter.AutorService;

@Service
public class AutorServiceImp implements AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}

	@Override
	public Autor findById(Long id) {
		return autorRepository.findById(id).orElse(null);
	}

	@Override
	public Autor findByIdJoinFetch(Long id) {
		return autorRepository.findByIdJoinFetch(id);
	}

	@Override
	public Autor findByNombre(String nombre) {
		return autorRepository.findByNombre(nombre);
	}

	@Override
	public Autor findByNombreJoinFeth(String nombre) {
		return autorRepository.findByNombreJoinFeth(nombre);
	}

	@Override
	public List<Autor> findAll() {
		return (List<Autor>) autorRepository.findAll();
	}

	@Override
	public List<Autor> findAllJoinFetch() {
		return autorRepository.findAllJoinFetch();
	}

	@Override
	public List<Autor> findByNombreContainingIgnoreCase(String nombre) {
		return autorRepository.findByNombreContainingIgnoreCase(nombre);
	}

	@Override
	public List<Autor> findByNombreContainingJoinFetch(String nombre) {
		return autorRepository.findByNombreContainingJoinFetch(nombre);
	}

	@Override
	public Autor update(Autor autor) {
		return autorRepository.save(autor);
	}

	@Override
	public void delete(Long id) {
		if(findById(id) != null) {
			autorRepository.deleteById(id);
		}
	}
}