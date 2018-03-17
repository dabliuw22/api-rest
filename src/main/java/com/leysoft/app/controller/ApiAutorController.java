package com.leysoft.app.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leysoft.app.assembler.AutorResourceAssembler;
import com.leysoft.app.assembler.LibroResourceAssembler;
import com.leysoft.app.entity.Autor;
import com.leysoft.app.exception.NotFoundException;
import com.leysoft.app.resource.AutorResource;
import com.leysoft.app.resource.LibroResource;
import com.leysoft.app.service.inter.AutorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
public class ApiAutorController {
	
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private AutorResourceAssembler autorAssembler;

	@Autowired
	private LibroResourceAssembler libroAssembler;
	
	@GetMapping(value = {"/autor"})
	@ApiOperation(value = "get-autores", nickname = "get-autores")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<List<AutorResource>> list() {
		return new ResponseEntity<List<AutorResource>>(autorAssembler.toResources(autorService.findAll()), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/autor/search"})
	@ApiOperation(value = "search-autores", nickname = "search-autores")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<List<AutorResource>> search(@RequestParam("nombre") String nombre) {
		return new ResponseEntity<List<AutorResource>>(autorAssembler.toResources(autorService.findByNombreContainingIgnoreCase(nombre)), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/autor/{id}"})
	@ApiOperation(value = "get-autor", nickname = "get-autor")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<AutorResource> detail(@PathVariable("id") Long id) {
		Autor autor = autorService.findById(id);
		if(autor == null) {
			throw new NotFoundException("id - " + id);
		}
		AutorResource resource = autorAssembler.toResource(autor);
		return new ResponseEntity<AutorResource>(resource, HttpStatus.OK);
	}
	
	@GetMapping(value = {"/autor/{id}/libro"})
	@ApiOperation(value = "libros-autor", nickname = "libros-autor")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<List<LibroResource>> librosAutor(@PathVariable("id") Long id) {
		Autor autor = autorService.findByIdJoinFetch(id);
		if(autor == null) {
			throw new NotFoundException("id - " + id);
		}
		List<LibroResource> resources = libroAssembler.toResources(autor.getLibros());
		return new ResponseEntity<List<LibroResource>>(resources, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/autor"})
	@ApiOperation(value = "save-autor", nickname = "save-autor")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<Void> create(@Valid @RequestBody Autor autor) {
		Autor currentAutor = autorService.save(autor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(currentAutor.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = {"/autor/{id}"})
	@ApiOperation(value = "update-autor", nickname = "update-autor")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<Void> update(@Valid @RequestBody Autor autor, @PathVariable("id") Long id) {
		Autor currentAutor = autorService.findById(id);
		if(currentAutor == null) {
			throw new NotFoundException("id - " + id);
		}
		currentAutor.setNombre(autor.getNombre());
		currentAutor.setLibros(autor.getLibros());
		autorService.update(currentAutor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(currentAutor.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = {"/autor/{id}"})
	@ApiOperation(value = "delete-autor", nickname = "delete-autor")
	@ApiResponses(value = {@ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	public void delete(@PathVariable("id") Long id) {
		Autor autor = autorService.findById(id);
		if(autor == null) {
			throw new NotFoundException("id - " + id);
		}
		autorService.delete(id);
	}
}