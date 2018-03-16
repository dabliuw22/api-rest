package com.leysoft.app.resource;

import org.springframework.hateoas.ResourceSupport;

import com.leysoft.app.entity.Autor;

public class AutorResource extends ResourceSupport {
	
	private String nombre;

	public AutorResource(Autor autor) {
		this.nombre = autor.getNombre();
	}

	public String getNombre() {
		return nombre;
	}
}