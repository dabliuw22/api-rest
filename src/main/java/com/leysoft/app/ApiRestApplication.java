package com.leysoft.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leysoft.app.entity.Autor;
import com.leysoft.app.entity.Libro;
import com.leysoft.app.service.inter.AutorService;
import com.leysoft.app.service.inter.LibroService;

@SpringBootApplication
public class ApiRestApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;
	
	@Autowired
	private AutorService autorService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			Autor autor1 = new Autor();
			autor1.setNombre("Autor1");
			autorService.save(autor1);
			Autor autor2 = new Autor();
			autor2.setNombre("Autor2");
			autorService.save(autor2);
			Libro libro1 = new Libro();
			libro1.setTitulo("Libro1");
			libro1.setFechaPublicacion(new Date());
			libro1.setAutor(autor1);
			libroService.save(libro1);
			Libro libro2 = new Libro();
			libro2.setTitulo("Libro2");
			libro2.setFechaPublicacion(new Date());
			libro2.setAutor(autor1);
			libroService.save(libro2);
	}
}