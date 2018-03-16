package com.leysoft.app.resource;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.leysoft.app.entity.Libro;

public class LibroResource extends ResourceSupport {
	
	private String titulo;
	
	private Date fechaPublicacion;
	
	private AutorResource autor;

	public LibroResource(Libro libro) {
		this.titulo = libro.getTitulo();
		this.fechaPublicacion = libro.getFechaPublicacion();
		this.autor = new AutorResource(libro.getAutor());
	}

	public String getTitulo() {
		return titulo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public AutorResource getAutor() {
		return autor;
	}
}