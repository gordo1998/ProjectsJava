package model;

import java.util.ArrayList;

public class Libro {
	private String isbn;
	private String titulo;
	private Autor autor;
	private String nombreAutor;
	private String apellidoAutor;
	private String apellidoDosAutor;
	private int numeroPaginas;
	private int genero;
	
	public Libro(String isbn, String titulo, String nombreAutor, String apellidoAutor, String segundoApellidoAutor, int numeroPaginas, int genero) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.nombreAutor = nombreAutor;
		this.apellidoAutor = apellidoAutor;
		this.apellidoDosAutor = segundoApellidoAutor;
		this.numeroPaginas = numeroPaginas;
		this.genero = genero;
	}
	
	
	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getApellidoAutor() {
		return apellidoAutor;
	}

	public void setApellidoAutor(String apellidoAutor) {
		this.apellidoAutor = apellidoAutor;
	}

	public String getApellidoDosAutor() {
		return apellidoDosAutor;
	}

	public void setApellidoDosAutor(String apellidoDosAutor) {
		this.apellidoDosAutor = apellidoDosAutor;
	}

	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
}
