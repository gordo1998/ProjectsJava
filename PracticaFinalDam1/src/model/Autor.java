package model;

public class Autor {
	private String nombre;
	private String apellido;
	private String segundoApellido;
	private int pais;
	
	public Autor(String nombre, String apellido, String segundoApellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.segundoApellido = segundoApellido;
	}
	
	public Autor(String nombre, String apellido, String segundoApellido, int pais) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.segundoApellido = segundoApellido;
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + this.nombre + " Apellido: " + this.apellido + " Segundo Apellido: " + this.segundoApellido;
	}
}
