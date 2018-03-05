package com.leysoft.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.leysoft.app.entity.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {
	
	public Libro findByTitulo(String titulo);
	
	public List<Libro> findByTituloContainingIgnoreCase(String titulo);
}