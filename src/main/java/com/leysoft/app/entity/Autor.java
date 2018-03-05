package com.leysoft.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "autores")
public class Autor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Libro> libros;

	public Autor() {
		this.libros = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
}