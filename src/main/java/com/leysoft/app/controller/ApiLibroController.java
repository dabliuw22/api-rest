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

import com.leysoft.app.entity.Libro;
import com.leysoft.app.exception.NotFoundException;
import com.leysoft.app.service.inter.LibroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
public class ApiLibroController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping(value = {"/libro"})
	@ApiOperation(value = "get-libros", nickname = "get-libros")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<List<Libro>> list() {
		return new ResponseEntity<List<Libro>>(libroService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/libro/search"})
	@ApiOperation(value = "search-libros", nickname = "search-libros")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<List<Libro>> search(@RequestParam("titulo") String titulo) {
		return new ResponseEntity<List<Libro>>(libroService.findByTituloContainingIgnoreCase(titulo), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/libro/{id}"})
	@ApiOperation(value = "get-libro", nickname = "get-libro")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<Libro> detail(@PathVariable("id") Long id) {
		Libro libro = libroService.findById(id);
		if(libro == null) {
			throw new NotFoundException("id - " + id);
		}
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/libro"})
	@ApiOperation(value = "save-libro", nickname = "save-libro")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure")})
	public ResponseEntity<Void> create(@Valid @RequestBody Libro libro) {
		Libro currentLibro = libroService.save(libro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(currentLibro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "update-libro", nickname = "update-libro")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	@PutMapping(value = {"/libro/{id}"})
	public ResponseEntity<Void> update(@Valid @RequestBody Libro libro, @PathVariable("id") Long id) {
		Libro currentLibro = libroService.findById(id);
		if(currentLibro == null) {
			throw new NotFoundException("id - " + id);
		}
		currentLibro.setTitulo(libro.getTitulo());
		currentLibro.setFechaPublicacion(libro.getFechaPublicacion());
		currentLibro.setAutor(libro.getAutor());
		libroService.update(currentLibro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(currentLibro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = {"/libro/{id}"})
	@ApiOperation(value = "delete-libro", nickname = "delete-libro")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure")})
	public void delete(@PathVariable("id") Long id) {
		Libro libro = libroService.findById(id);
		if(libro == null) {
			throw new NotFoundException("id - " + id);
		}
		libroService.delete(id);
	}
}