package com.leysoft.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.leysoft.app.entity.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long> {
	
	@Query(value = "select distinct a from Autor as a left join fetch a.libros as l where a.id = ?1")
	public Autor findByIdJoinFetch(Long id);
	
	public Autor findByNombre(String nombre);
	
	@Query(value = "select distinct a from Autor as a left join fetch a.libros as l where a.nombre = ?1")
	public Autor findByNombreJoinFeth(String nombre);
	
	@Query(value = "select distinct a from Autor as a left join fetch a.libros as l")
	public List<Autor> findAllJoinFetch();
	
	public List<Autor> findByNombreContainingIgnoreCase(String nombre);
	
	@Query(value = "select distinct a from Autor as a left join fetch a.libros as l where a.nombre like %?1%")
	public List<Autor> findByNombreContainingJoinFetch(String nombre);
}