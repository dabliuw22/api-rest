package com.leysoft.app.service.inter;

import java.util.List;

import com.leysoft.app.entity.Autor;
import com.leysoft.app.entity.Libro;

public interface LibroService {
	
	public Libro save(Libro libro);
	
	public Libro findById(Long id);
	
	public Libro findByTitulo(String titulo);
	
	public List<Libro> findAll();
	
	public List<Libro> findByTituloContainingIgnoreCase(String titulo);
	
	public List<Libro> findByAutor(Autor autor);
	
	public Libro update(Libro libro);
	
	public void delete(Long id);
}