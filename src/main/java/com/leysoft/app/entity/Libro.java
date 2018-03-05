package com.leysoft.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity @Table(name = "libros")
public class Libro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column
	private String titulo;
	
	@Temporal(value = TemporalType.DATE)
	@Column
	private Date fechaPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "id_autor")
	private Autor autor;

	public Libro() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}