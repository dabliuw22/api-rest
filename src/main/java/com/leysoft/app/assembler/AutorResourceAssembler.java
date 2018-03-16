package com.leysoft.app.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.leysoft.app.controller.ApiAutorController;
import com.leysoft.app.entity.Autor;
import com.leysoft.app.resource.AutorResource;

@Component
public class AutorResourceAssembler extends ResourceAssemblerSupport<Autor, AutorResource> {

	public AutorResourceAssembler() {
		super(ApiAutorController.class, AutorResource.class);
	}

	@Override
	public AutorResource toResource(Autor entity) {
		AutorResource resource = new AutorResource(entity);
		ControllerLinkBuilder linkAll = linkTo(methodOn(ApiAutorController.class).list());
		resource.add(linkAll.withRel("all"));
		return resource;
	}

	@Override
	public List<AutorResource> toResources(Iterable<? extends Autor> entities) {
		List<AutorResource> resources = new ArrayList<>();
		entities.forEach(autor -> {
			AutorResource resource = new AutorResource(autor);
			ControllerLinkBuilder linkSelf = linkTo(methodOn(ApiAutorController.class).detail(autor.getId()));
			resource.add(linkSelf.withSelfRel());
			resources.add(resource);
		});
		return resources;
	}
}