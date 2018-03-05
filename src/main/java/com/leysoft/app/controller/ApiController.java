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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leysoft.app.entity.Autor;
import com.leysoft.app.entity.Libro;
import com.leysoft.app.exception.NotFoundException;
import com.leysoft.app.service.inter.AutorService;
import com.leysoft.app.service.inter.LibroService;


@RequestMapping(value = {"/api"})
@RestController
public class ApiController {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping(value = {"/autor"})
	public ResponseEntity<List<Autor>> listAutor() {
		return new ResponseEntity<List<Autor>>(autorService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/autor/search"})
	public ResponseEntity<List<Autor>> searchAutor(@RequestParam("nombre") String nombre) {
		return new ResponseEntity<List<Autor>>(autorService.findByNombreContainingIgnoreCase(nombre), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/autor/{id}"})
	public ResponseEntity<Autor> detailAutor(@PathVariable("id") Long id) {
		Autor autor = autorService.findById(id);
		if(autor == null) {
			throw new NotFoundException("id - " + id);
		}
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/autor"})
	public ResponseEntity<Void> createAutor(@Valid @RequestBody Autor autor) {
		Autor currentAutor = autorService.save(autor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(currentAutor.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = {"/autor/{id}"})
	public ResponseEntity<Void> updateAutor(@Valid @RequestBody Autor autor, @PathVariable("id") Long id) {
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
	public void deleteAutor(@PathVariable("id") Long id) {
		Autor autor = autorService.findById(id);
		if(autor == null) {
			throw new NotFoundException("id - " + id);
		}
		autorService.delete(id);
	}
	
	@GetMapping(value = {"/libro"})
	public ResponseEntity<List<Libro>> listLibro() {
		return new ResponseEntity<List<Libro>>(libroService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/libro/search"})
	public ResponseEntity<List<Libro>> searchLibro(@RequestParam("titulo") String titulo) {
		return new ResponseEntity<List<Libro>>(libroService.findByTituloContainingIgnoreCase(titulo), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/libro/{id}"})
	public ResponseEntity<Libro> detailLibro(@PathVariable("id") Long id) {
		Libro libro = libroService.findById(id);
		if(libro == null) {
			throw new NotFoundException("id - " + id);
		}
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/libro"})
	public ResponseEntity<Void> createLibro(@Valid @RequestBody Libro libro) {
		Libro currentLibro = libroService.save(libro);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(currentLibro.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = {"/libro/{id}"})
	public ResponseEntity<Void> updateLibro(@Valid @RequestBody Libro libro, @PathVariable("id") Long id) {
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
	public void deleteLibro(@PathVariable("id") Long id) {
		Libro libro = libroService.findById(id);
		if(libro == null) {
			throw new NotFoundException("id - " + id);
		}
		libroService.delete(id);
	}
}