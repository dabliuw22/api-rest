package com.leysoft.app.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leysoft.app.entity.Autor;
import com.leysoft.app.entity.Libro;
import com.leysoft.app.repository.LibroRepository;
import com.leysoft.app.service.inter.LibroService;

@Service
public class LibroServiceImp implements LibroService {

	@Autowired
	private LibroRepository libroRepository;
	
	@Override
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}

	@Override
	public Libro findById(Long id) {
		return libroRepository.findById(id).orElse(null);
	}

	@Override
	public Libro findByTitulo(String titulo) {
		return libroRepository.findByTitulo(titulo);
	}

	@Override
	public List<Libro> findAll() {
		return (List<Libro>) libroRepository.findAll();
	}

	@Override
	public List<Libro> findByTituloContainingIgnoreCase(String titulo) {
		return libroRepository.findByTituloContainingIgnoreCase(titulo);
	}
	
	@Override
	public List<Libro> findByAutor(Autor autor) {
		return libroRepository.findByAutor(autor);
	}

	@Override
	public Libro update(Libro libro) {
		return libroRepository.save(libro);
	}

	@Override
	public void delete(Long id) {
		if(findById(id) != null) {
			libroRepository.deleteById(id);
		}
	}
}