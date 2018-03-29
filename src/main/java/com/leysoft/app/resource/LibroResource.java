package com.leysoft.app.resource;

import java.util.Date;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.ResourceSupport;

import com.leysoft.app.controller.ApiAutorController;
import com.leysoft.app.entity.Libro;

public class LibroResource extends ResourceSupport {
	
	private String titulo;
	
	private Date fechaPublicacion;
	
	private String autor;

	public LibroResource(Libro libro) {
		this.titulo = libro.getTitulo();
		this.fechaPublicacion = libro.getFechaPublicacion();
		this.autor = libro.getAutor().getNombre();
	}

	public String getTitulo() {
		return titulo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public String getAutor() {
		return autor;
	}
}