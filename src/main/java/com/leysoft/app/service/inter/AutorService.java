package com.leysoft.app.service.inter;

import java.util.List;

import com.leysoft.app.entity.Autor;

public interface AutorService {
	
	public Autor save(Autor autor);
	
	public Autor findById(Long id);
	
	public Autor findByIdJoinFetch(Long id);
	
	public Autor findByNombre(String nombre);
	
	public Autor findByNombreJoinFeth(String nombre);
	
	public List<Autor> findAll();
	
	public List<Autor> findAllJoinFetch();
	
	public List<Autor> findByNombreContainingIgnoreCase(String nombre);
	
	public List<Autor> findByNombreContainingJoinFetch(String nombre);
	
	public Autor update(Autor autor);
	
	public void delete(Long id);
}