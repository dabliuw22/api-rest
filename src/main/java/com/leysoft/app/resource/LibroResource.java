package com.leysoft.app.resource;

import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.ResourceSupport;

import com.leysoft.app.controller.ApiAutorController;
import com.leysoft.app.entity.Libro;

public class LibroResource extends ResourceSupport {
	
	private String titulo;
	
	private Date fechaPublicacion;
	
	private AutorResource autor;

	public LibroResource(Libro libro) {
		this.titulo = libro.getTitulo();
		this.fechaPublicacion = libro.getFechaPublicacion();
		this.autor = new AutorResource(libro.getAutor());
		autor.add(linkTo(methodOn(ApiAutorController.class).detail(libro.getAutor().getId())).withRel("self"));
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