package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import dao.Controller;
import dao.Sentencia;
import model.Autor;

public class ControlAutores {
	private ArrayList<Autor> autores;
	private Controller conexion;
	
	public ControlAutores() throws SQLException {
		this.conexion = new Controller();
		this.autores = new ArrayList<>();
	}
	
	public ArrayList<Autor> getAutores() {
		return this.autores;
	}
	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	
	/**
	 * Inserta un nuevo autor. Devuelve un entero, un 0 si la consulta se ha llevado a cabo o un 1 se se ha producido 
	 * cualquier error
	 * @param nombreAutor
	 * @param apellidoAutor
	 * @param apellidoAutorDos
	 * @param pais
	 * @throws SQLException
	 */
	public int insercionAutor(String nombreAutor, String apellidoAutor, String apellidoAutorDos, String pais) throws SQLException{
		
		try {
			PreparedStatement consulta =  this.conexion.getConexion().prepareStatement(Sentencia.INSERT_AUTOR_PROCEDURE);
			consulta.setString(1, nombreAutor);
			consulta.setString(2, apellidoAutor);
			consulta.setString(3, apellidoAutorDos);
			consulta.setString(4, pais);
			consulta.executeUpdate();
			autores.add(new Autor(nombreAutor, apellidoAutor, apellidoAutorDos, convertirPaisNumero(pais)));
			return 0;
		}catch(SQLException e) {
			if(e.getSQLState().equals("45000")) {
				System.out.println("Ya existe el autor!");
			}else {
				System.out.println("No se ha podido guardar el nuevo autor en la biblioteca!");
			}
			e.printStackTrace();
			return 1;
		}
	}
	
	public int eliminarAutor(String nombreAutor, String apellidoAutor, String apellidoAutorDos) throws SQLException{
			
			try {
				//NO HAREMOS LA COMPROBACIÓN PORQUE EN LA INTERFAZ GRÁFICA NOS ENCARGAREMOS DE QUE SOLO APAREZCAN LOS QUE EXISTEN//
	//			if (!comprobarExistRegistrosAutor(nombreAutor, apellidoAutor, apellidoAutorDos)) {
	//				throw new SQLException("No existe el autor que se desea eliminar!!");
	//			}
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.DELETE_AUTOR);
				consulta.setString(1, nombreAutor);
				consulta.setString(2, apellidoAutor);
				consulta.setString(3, apellidoAutorDos);
				consulta.executeUpdate();
				for (int i = 0; i < autores.size(); i++) {
					if (autores.get(i).getNombre().equals(nombreAutor) && autores.get(i).getApellido().equals(apellidoAutor) && autores.get(i).getSegundoApellido().equals(apellidoAutorDos)) {
						autores.remove(i);
						break;
					}
				}
				return 0;
			}catch(SQLException e) {
				System.out.println("No se ha eliminado correctamente el autor!!");
				e.printStackTrace();
				return 1;
			}
	}
	
	
	/**
	 * Actualiza el nombre de un autor existente. Si el autor no existe en la base de datos se informará, si ya existe el autor por el que
	 * se quiere actualizar también dará error. El primer parámetro es el nombre que se quiere cambiar, el segundo el nombre del autor,
	 * el tercero el apellido y el cuarto el segundo apellido. Devuelve un entero, un 0 si la consulta se ha llevado a cabo o un 1 se se ha producido 
	 * cualquier error 
	 * @param nombreNuevo
	 * @param nombreAutor
	 * @param apellidoAutor
	 * @param apellidoAutorDos
	 * @throws SQLException
	 */
	public int actualizarAutorNombre(String nombreNuevo, String nombreAutor, String apellidoAutor, String apellidoAutorDos) throws SQLException {
		
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_NAME);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, nombreAutor);
			consulta.setString(3, apellidoAutor);
			consulta.setString(4, apellidoAutorDos);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreAutor) && autor.getApellido().equals(apellidoAutor) && autor.getSegundoApellido().equals(apellidoAutorDos)) {
					autor.setNombre(nombreNuevo);
					break;
				}
			}
			System.out.println("Se ha actulizado el nombre correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido actualizr el nombre correctamente!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * Actualiza el apellido de un autor. El primer parámetro es el apellido que se quiere cambiar, el segundo el nombre del autor,
	 * el tercero el apellido y el cuarto el segundo apellido. Devuelve un entero, un 0 si la consulta se ha llevado a cabo o un 1 se se ha producido 
	 * cualquier error
	 * @param apellidoNuevo
	 * @param nombreAutor
	 * @param apellidoAutor
	 * @param apellidoAutorDos
	 * @throws SQLException
	 */
	public int actualizarAutorApellido(String apellidoNuevo, String nombreAutor, String apellidoAutor, String apellidoAutorDos) throws SQLException {
		
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_APELLIDO);
			consulta.setString(1, apellidoNuevo);
			consulta.setString(2, nombreAutor);
			consulta.setString(3, apellidoAutor);
			consulta.setString(4, apellidoAutorDos);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreAutor) && autor.getApellido().equals(apellidoAutor) && autor.getSegundoApellido().equals(apellidoAutorDos)) {
					autor.setApellido(apellidoNuevo);
					break;
				}
			}
			System.out.println("Se ha actualizado el apellido correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido actualizar el apellido correctamente!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * Actualiza el segundo apellido de un autor. El primer parámetro es el segundo apellido que se quiere que se quiere reemplazar, 
	 * el segundo el nombre del autor, el tercero el apellido y el cuarto el segundo apellido. Devuelve un entero, un 0 si la consulta 
	 * se ha llevado a cabo o un 1 se se ha producido cualquier error
	 * @param apellidoDosNuevo
	 * @param nombreAutor
	 * @param apellidoAutor
	 * @param apellidoAutorDos
	 * @return
	 * @throws SQLException
	 */
	public int actualizarAutorApellidoDos(String apellidoDosNuevo, String nombreAutor, String apellidoAutor, String apellidoAutorDos) throws SQLException {
		
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_APELLIDO_DOS);
			consulta.setString(1, apellidoDosNuevo);
			consulta.setString(2, nombreAutor);
			consulta.setString(3, apellidoAutor);
			consulta.setString(4, apellidoAutorDos);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreAutor) && autor.getApellido().equals(apellidoAutor) && autor.getSegundoApellido().equals(apellidoAutorDos)) {
					autor.setSegundoApellido(apellidoDosNuevo);
					break;
				}
			}
			System.out.println("Se ha actualizado el segundo apellido correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido actualizar el segundo apellido correctamente!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * Actualiza el pais de un un autor. El primer parámetro es el pais que se quiere cambiar, el segundo el nombre del autor,
	 * el tercero el apellido y el cuarto el segundo apellido. Devuelve un entero, un 0 si la actualización se ha llevado a cabo o un 1 se se ha producido 
	 * cualquier error
	 * @param paisNuevo
	 * @param nombreAutor
	 * @param apellidoAutor
	 * @param apellidoAutorDos
	 * @return
	 * @throws SQLException
	 */
	public int actualizarPais(String paisNuevo, String nombreAutor, String apellidoAutor, String apellidoAutorDos) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_PAIS);
			consulta.setString(1, paisNuevo);
			consulta.setString(2, nombreAutor);
			consulta.setString(3, apellidoAutor);
			consulta.setString(4, apellidoAutorDos);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreAutor) && autor.getApellido().equals(apellidoAutor) && autor.getSegundoApellido().equals(apellidoAutorDos)) {
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha actualizado el pais correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido actualizar el pais!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarNombreApellido(String nombreNuevo, String apellidoNuevo, String nombreActual, String apellidoActual, String apellidoDosActual)  throws SQLException{
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_NOMBRE_APELLIDO);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, apellidoNuevo);
			consulta.setString(3, nombreActual);
			consulta.setString(4, apellidoActual);
			consulta.setString(5, apellidoDosActual);
			consulta.executeQuery();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setNombre(nombreNuevo);
					autor.setApellido(apellidoNuevo);
					break;
				}
			}
			System.out.println("Se ha actualizado correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizr la actualizacion!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarNombreSegApellido(String nombreNuevo, String apellidoDosNuevo, String nombreActual, String apellidoActual, String apellidoDosActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_NOMBRE_SEGAPELLIDO);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, apellidoDosNuevo);
			consulta.setString(3, nombreActual);
			consulta.setString(4, apellidoActual);
			consulta.setString(5, apellidoDosActual);
			consulta.executeQuery();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setNombre(nombreNuevo);
					autor.setSegundoApellido(apellidoDosActual);
					break;
				}
			}
			System.out.println("Se ha actualizado correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizr la actualizacion!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarNombrePais(String nombreNuevo, String paisNuevo, String nombreActual, String apellidoActual, String apellidoDosActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_NOM_PAIS);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, paisNuevo);
			consulta.setString(3, nombreActual);
			consulta.setString(4, apellidoActual);
			consulta.setString(5, apellidoDosActual);
			consulta.executeQuery();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setNombre(nombreNuevo);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha actualizado correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizr la actualizacion!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarApellidos(String apellidoNuevo, String apellidoDosNuevo, String nombreActual, String apellidoActual, String apellidoDosActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_APELLIDOS);
			consulta.setString(1, apellidoNuevo);
			consulta.setString(2, apellidoDosNuevo);
			consulta.setString(3, nombreActual);
			consulta.setString(4, apellidoActual);
			consulta.setString(5, apellidoDosActual);
			consulta.executeQuery();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setApellido(apellidoNuevo);
					autor.setSegundoApellido(apellidoDosNuevo);
					break;
				}
			}
			System.out.println("Se ha actualizado correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizr la actualizacion!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarApellidoPais(String apellidoNuevo, String paisNuevo, String nombreActual, String apellidoActual, String apellidoDosActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_APELLIDO_PAIS);
			consulta.setString(1, apellidoNuevo);
			consulta.setString(2, paisNuevo);
			consulta.setString(3, nombreActual);
			consulta.setString(4, apellidoActual);
			consulta.setString(5, apellidoDosActual);
			consulta.executeQuery();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setApellido(apellidoDosActual);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha actualizado correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizr la actualizacion!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarSegApellidoPais(String apellidoDosNuevo, String paisNuevo, String nombreActual, String apellidoActual, String apellidoDosActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_SEGAPELLIDO_PAIS);
			consulta.setString(1, apellidoDosNuevo);
			consulta.setString(2, paisNuevo);
			consulta.setString(3, nombreActual);
			consulta.setString(4, apellidoActual);
			consulta.setString(5, apellidoDosActual);
			consulta.executeQuery();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setSegundoApellido(apellidoDosNuevo);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha actualizado correctamente!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizr la actualizacion!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarTodoSinPais(String nombreNuevo, String apellidoNuevo, String segundoApellidoNuevo, String nombreActual, String apellidoActual, String segundoApellidoActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_TODO_SINPAIS);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, apellidoNuevo);
			consulta.setString(3, segundoApellidoNuevo);
			consulta.setString(4, nombreActual);
			consulta.setString(5, apellidoActual);
			consulta.setString(6, segundoApellidoActual);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(segundoApellidoActual)) {
					autor.setNombre(nombreNuevo);
					autor.setApellido(apellidoNuevo);
					autor.setSegundoApellido(segundoApellidoNuevo);
					break;
				}
			}
			System.out.println("Se ha realizado la actualización!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizar la actualización!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarTodoSinSegApellido(String nombreNuevo, String apellidoNuevo, String paisNuevo, String nombreActual, String apellidoActual, String segundoApellidoActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_TODO_SINSEGAPELLIDO);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, apellidoNuevo);
			consulta.setString(3, paisNuevo);
			consulta.setString(4, nombreActual);
			consulta.setString(5, apellidoActual);
			consulta.setString(6, segundoApellidoActual);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(segundoApellidoActual)) {
					autor.setNombre(nombreNuevo);
					autor.setApellido(apellidoNuevo);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha realizado la actualización!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizar la actualización!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarTodoSinApellido(String nombreNuevo, String apellidoDosNuevo, String paisNuevo, String nombreActual, String apellidoActual, String segundoApellidoActual)  throws SQLException{
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_TODO_SINAPELLIDO);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, apellidoDosNuevo);
			consulta.setString(3, paisNuevo);
			consulta.setString(4, nombreActual);
			consulta.setString(5, apellidoActual);
			consulta.setString(6, segundoApellidoActual);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(segundoApellidoActual)) {
					autor.setNombre(nombreNuevo);
					autor.setSegundoApellido(apellidoDosNuevo);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha realizado la actualización!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizar la actualización!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	public int actualizarTodoSinNombre(String apellidoNuevo, String segundoApellidoNuevo, String paisNuevo, String nombreActual, String apellidoActual, String segundoApellidoActual) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_TODO_SINNOMBRE);
			consulta.setString(1, apellidoNuevo);
			consulta.setString(2, segundoApellidoNuevo);
			consulta.setString(3, paisNuevo);
			consulta.setString(4, nombreActual);
			consulta.setString(5, apellidoActual);
			consulta.setString(6, segundoApellidoActual);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(segundoApellidoActual)) {
					autor.setApellido(apellidoNuevo);
					autor.setSegundoApellido(segundoApellidoNuevo);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("Se ha realizado la actualización!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizar la actualización!!");
			e.printStackTrace();
			return 1;
		}
	}
	
	
	public int actualizarTodo(String nombreNuevo, String apellidoNuevo, String apellidoDosNuevo, String paisNuevo, String nombreActual, String apellidoActual, String apellidoDosActual)throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_AUTOR_TODO);
			consulta.setString(1, nombreNuevo);
			consulta.setString(2, apellidoNuevo);
			consulta.setString(3, apellidoDosNuevo);
			consulta.setString(4, paisNuevo);
			consulta.setString(5, nombreActual);
			consulta.setString(6, apellidoActual);
			consulta.setString(7, apellidoDosActual);
			consulta.executeUpdate();
			for(Autor autor: autores) {
				if (autor.getNombre().equals(nombreActual) && autor.getApellido().equals(apellidoActual) && autor.getSegundoApellido().equals(apellidoDosActual)) {
					autor.setNombre(nombreNuevo);
					autor.setApellido(apellidoNuevo);
					autor.setSegundoApellido(apellidoDosNuevo);
					autor.setPais(convertirPaisNumero(paisNuevo));
					break;
				}
			}
			System.out.println("La actualizacion ha sido exitosa!");
			return 0;
		}catch(SQLException e) {
			System.out.println("No se ha podido realizar la actualización!");
			e.printStackTrace();
			return 1;
		}
		
	}
	
	/**
	 * Retorna un array de autores. Imprime todos los autores, crea tantos objetos de la clase autores como registros de ellos existan. 
	 */
	public ArrayList<Autor> crearObjetosAutores ()  throws SQLException{
		try {
			Statement consulta = this.conexion.getConexion().createStatement();
			ResultSet respuesta = consulta.executeQuery(Sentencia.PRINT_AUTORES);
			while (respuesta.next()) {
				String nombre = respuesta.getString("nombreAutor");
				String primerApellido = respuesta.getString("apellidoAutor");
				String segundoApellido = respuesta.getString("apellidoDosAutor");
				int pais = respuesta.getInt("pais");
				this.autores.add(new Autor(nombre, primerApellido, segundoApellido, pais));
			}
			System.out.println(this.autores);
			return this.autores;
		}catch(SQLException e) {
			System.out.println("No se han podido obtener los datos de la base de datos!");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public ArrayList<String> imprimirAutorNomApellPrimSegun(String nombre, String apellido, String apellidoDos)  throws SQLException{
		ArrayList<String> porNomApellidos = new ArrayList<>();
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.PRINT_AUTOR_NAMLASNSECN);
			consulta.setString(1, nombre);
			consulta.setString(2, apellido);
			consulta.setString(3, apellidoDos);
			ResultSet resultado = consulta.executeQuery();
			String respuesta = "";
			while(resultado.next()) {
				respuesta += resultado.getString("nombreAutor");
				respuesta += resultado.getString("apellidoAutor");
				respuesta += resultado.getString("apellidoDosAutor");
				porNomApellidos.add(respuesta);
			}
			return porNomApellidos;
		}catch(SQLException e) {
			System.out.println("No se han podido imprimir los autores!");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sirve para buscar el indice del objeto que contiene el mismo nombre, apellido y segundo apellido. Retorna un entero
	 * @param nombre
	 * @param apellido
	 * @param segundoApellido
	 * @return
	 */
	public int devolverIndiceAutor(String nombre, String apellido, String segundoApellido)  throws SQLException{
		int numero = 0;
		for(int i = 0; i < autores.size(); i++) {
			if(autores.get(i).getNombre() == nombre && autores.get(i).getApellido() == apellido && autores.get(i).getSegundoApellido() == segundoApellido) {
				numero = autores.indexOf(autores.get(i));
				break;
			}
		}
		return numero;
	}
	
	
	/**
	 * Comprueba que el autor exista en la base de datos. Se le pasa por parámetro el nombre, apellido y segundo apellido. Si el 
	 * autor existe devolverá true, en caso contrario devolverá un false.
	 * @param nombreAutor
	 * @param apellidoAutor
	 * @param apellidoAutorDos
	 * @return
	 * @throws SQLException
	 */
	public boolean comprobarExistRegistrosAutor(String nombreAutor, String apellidoAutor, String apellidoAutorDos) throws SQLException {
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.SELECT_AUTOR);
			consulta.setString(1, nombreAutor);
			consulta.setString(2, apellidoAutor);
			consulta.setString(3, apellidoAutorDos);
			ResultSet respuesta = consulta.executeQuery();
			return respuesta.next();
		}catch(SQLException e) {
			System.out.println("No se ha podido generar la consulta de manera exitosa!");
			return false;
		}
	}
	
	
	private String [][] listadoAutores(int registrosTotales){
		registrosTotales = (int) Math.ceil(registrosTotales/(double) 10);
		String [][] informacionAutores = new String [registrosTotales][10];
		return informacionAutores;
	}
	
	public ArrayList<String> listadoPaises()  throws SQLException{
		ArrayList<String> paises = new ArrayList<>();
		try {
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.PRINT_COUNTRYS);
			ResultSet resultado = consulta.executeQuery();
			String respuesta = "";
			while(resultado.next()) {
				respuesta = resultado.getString("nombrePais");
				paises.add(respuesta);
			}
			return paises;
		}catch(SQLException e) {
			System.out.println("No se han podido exportar los datos de los paises");
			e.printStackTrace();
			return null;
		}
	}
	
	public int convertirPaisNumero(String pais) {
		try {
			String sentencia = "SELECT idPais FROM paises WHERE nombrePais = ?";
			PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
			consulta.setString(1, pais);
			ResultSet respuesta = consulta.executeQuery();
			if(respuesta.next()) {
				return respuesta.getInt("idPais");
			}else {
				return -1;
			}
		}catch(SQLException e) {
			System.out.println("No se ha podido convertir el String de pais a ID");
			e.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<String> soloAutores(){
		ArrayList<String> soloAutores = new ArrayList<>();
		String strAutor = "";
		System.out.println("Inicio...");
		System.out.println(this.autores);
		for (Autor autorr: this.autores) {
			System.out.println("Autor: " + strAutor);
			strAutor += autorr.getNombre() + " ";
			strAutor += autorr.getApellido() + " ";
			strAutor += autorr.getSegundoApellido();
			soloAutores.add(strAutor);

		}
		return soloAutores;
	}
}
