package com.leysoft.app.assembler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.leysoft.app.controller.ApiLibroController;
import com.leysoft.app.entity.Libro;
import com.leysoft.app.resource.LibroResource;

@Component
public class LibroResourceAssembler extends ResourceAssemblerSupport<Libro, LibroResource> {

	public LibroResourceAssembler() {
		super(ApiLibroController.class, LibroResource.class);
	}

	@Override
	public LibroResource toResource(Libro entity) {
		LibroResource resource = new LibroResource(entity);
		ControllerLinkBuilder linkAll = linkTo(methodOn(ApiLibroController.class).list());
		resource.add(linkAll.withRel("all"));
		return resource;
	}

	@Override
	public List<LibroResource> toResources(Iterable<? extends Libro> entities) {
		List<LibroResource> resources = new ArrayList<>();
		entities.forEach(libro -> {
			LibroResource resource = new LibroResource(libro);
			ControllerLinkBuilder linkSelf = linkTo(methodOn(ApiLibroController.class).detail(libro.getId()));
			resource.add(linkSelf.withSelfRel());
			resources.add(resource);
		});
		return resources;
	}
}