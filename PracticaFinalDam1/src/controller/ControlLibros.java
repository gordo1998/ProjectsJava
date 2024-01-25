package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import dao.Controller;
import dao.Sentencia;
import model.Libro;

public class ControlLibros {
	private ArrayList<Libro> libros;
	private Controller conexion;
	
	public ControlLibros() throws SQLException {
		this.libros = new ArrayList<>();
		this.conexion = new Controller();
	}
	
	public ArrayList<Libro> getLibros() {
		return libros;
	}
	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}
	
	
	//FUNCIONES GESTIÓN TABLA LIBROS//
	
		/**
		 * Inserta un nuevo libro a la base de datos libros. Devuelve un 0 si se ha ejecutado la inserción, un 1 si ha ocurrido cualquier error,
		 * y un 2 si el libro que se quiere insertar ya existe.
		 * @param isbn
		 * @param titulo
		 * @param nombreAut
		 * @param apellidoAut
		 * @param apellidoDosAut
		 * @param numPag
		 * @param genero
		 * @return
		 * @throws SQLException
		 */
		public int insertarNuevoLibro(String isbn, String titulo, String nombreAut, String apellidoAut, String apellidoDosAut, int numPag, String genero) throws SQLException {
			try {
//				if(comprobarExistRegistrosLibro(isbn)) {
//					throw new SQLException("El libro que quieres añadir ya existe!!");
//				}
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.INSERT_LIBRO);
				consulta.setString(1, isbn);
				consulta.setString(2, titulo);
				consulta.setString(3, nombreAut);
				consulta.setString(4, apellidoAut);
				consulta.setString(5, apellidoDosAut);
				consulta.setInt(6, numPag);
				consulta.setString(7, genero);
				consulta.executeUpdate();
				libros.add(new Libro(isbn, titulo, nombreAut, apellidoAut, apellidoDosAut, numPag, convertirGeneroNumero(genero)));
				System.out.println("Se ha anyadido correctamente el libro!");
				return 0;
			}catch(SQLException e) {
				if(e.getSQLState().equals("45000")) {
					System.out.println("Error: El libro con el mismo ISBN ya existe!");
					e.printStackTrace();
					return 2;
				}else {
					System.out.println("No se ha podido hacer la insercion");
					e.printStackTrace();
					return 1;
				}
			}
		}
		
		//Me gustaría que primero se seleccione el titulo y luego se haga un select
		
		/**
		 * Elimina un libro. Devuelve un 0 si se ha ejecutado la sentencia con éxito, un 1 si ha ocurrido cualquier error y
		 * no se ha hecho la eliminación, y un 2 si el libro que se quiere insertar no existe.
		 * @param ISBN
		 */
		public int eliminarLibro(String ISBN) throws SQLException {
			try {
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.DELETE_LIBRO);
				consulta.setString(1, ISBN);
				consulta.executeUpdate();
				for(int i = 0; i < libros.size(); i++) {
					if (libros.get(i).getIsbn() == ISBN) {
						libros.remove(i);
						break;
					}
				}
				System.out.println("Se ha eliminado exitosamente el libro!");
				return 0;
			}catch(SQLException e) {
				if(e.getSQLState().equals("45000")) {
					System.out.println("El libro con el ISBN ingresado no existe!");
					e.printStackTrace();
					return 2;
				}else {
					System.out.println("No se ha podido eliminar el libro seleccionado");
					e.printStackTrace();
					return 1;
				}
			}
		}
		/**
		 * Actualiza los valores del libro pasados por parámetro
		 * @param valForm
		 */
		public int actualizacionLibro(String ISBN, Map<String, Object> valForm) {
			//Cramos una variable de la clase StringBuilder para manipular su tamaño y contenido a voluntad.
			StringBuilder sentencia = new StringBuilder("UPDATE libros SET ");
			//Creamos un ArrayList donde guardaremos los valores de cada valor
			ArrayList<Object> valores = new ArrayList<>();
			//Creamos un ArrayList donde guardaremos los valores de cada campo
			ArrayList<String> campos = new ArrayList<>();
			//valForm.entrySet() nos saca todas las entradas para el mapa. Con Map.Entry accedemos una por una, en cada iteración accedemos a 
			//una y la llamamos entry. Strin es el tipo de clave mientras que Objecto es el tipo de valor
			for(Map.Entry<String, Object> entry: valForm.entrySet()) {
				//sacamos la clave
				String campo = entry.getKey();
				//Sacamos el valor
				Object valor = entry.getValue();
				System.out.println("- valor: " + entry.getValue());
				//Por cada iteración agrandamos la sentencia con la clave indicada.
				sentencia.append(campo).append(" = ?, ");
				//Ahora hemos de introducir el valor en el ArrayList valores
				valores.add(valor);
				campos.add(campo);
			}
			//Una vez llegados aquí lo que falta para completar la sentencia es eliminar la coma para posteriormente intriducir el WHERE
			sentencia.delete(sentencia.length() - 2, sentencia.length());
			//Terminamos de completar la sentencia
			sentencia.append(" WHERE ISBN = ?");
			//Por ultimo añadimos el ISBN en el ArrayList
			valores.add(ISBN);
			
			//Convertimos el StringBuilder en String
			String sentenciaActualizar = sentencia.toString();
			//Ahora probamos de lanzar la sentencia y actualizar
			try {
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentenciaActualizar);
				//Vamos a iterar por cada uno de los valores del ArrayList para sustituirlos por cada interrogante.
				for (int i = 0; i < valores.size(); i++) {
					consulta.setObject(i + 1, valores.get(i));
				}
				consulta.executeUpdate();
				int indiceLibro = 0;
				for (int i = 0; i < libros.size(); i++) {
					if(libros.get(i).getIsbn() == ISBN) {
						break;
					}
					indiceLibro++;
				}
				if(campos.contains("tituloLibro")) {
					libros.get(indiceLibro).setTitulo((String) valores.get(indiceLibro));
				}
				if(campos.contains("AutorNombre")) {
					libros.get(indiceLibro).setNombreAutor((String) valores.get(indiceLibro));
				}
				if(campos.contains("AutorApellido")) {
					libros.get(indiceLibro).setApellidoAutor((String) valores.get(indiceLibro));
				}
				
				if(campos.contains("AutorApellidoDos")) {
					libros.get(indiceLibro).setApellidoDosAutor((String) valores.get(indiceLibro));
				}
				if(campos.contains("numeroPaginas")) {
					libros.get(indiceLibro).setNumeroPaginas((int) valores.get(indiceLibro));
				}
				if(campos.contains("Genero")) {
					libros.get(indiceLibro).setGenero(convertirGeneroNumero((String) valores.get(indiceLibro)));
				}
				return 0;
				
			}catch(SQLException e) {
				System.out.println("Ha ocurrido un error!");
				e.printStackTrace();
				return 1;
			}
		}
		
		public ArrayList<String> generos(){
			ArrayList<String> generos = new ArrayList<>();
			try {
				String sentencia = "SELECT * FROM generos";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				ResultSet resultado = consulta.executeQuery();
				while(resultado.next()) {
					generos.add(resultado.getString("tipo"));
				}
				return generos;
			}catch(SQLException e) {
				return null;
			}
		}
		
		/**
		 * Actualiza el nombre de un libro. Devuelve un 0 si la actualización se ha ejecutado con éxito, un 1 si 
		 * ha ocurrido cualquier error y no se ha llevado a cabo la actualización, y un 2 cuando el libro que se quiere actualizar no existe
		 * @param titulo
		 * @param ISBN
		 * @return
		 * @throws SQLException
		 */
		public int actualizarTituloLibro(String titulo, String ISBN) throws SQLException {
			try {
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.UPDATE_LIBRO_TITULO);
				consulta.setString(1, titulo);
				consulta.setString(2, ISBN);
				consulta.executeUpdate();
				System.out.println("El titulo del libro se ha actualizado!");
				return 0;
			}catch(SQLException e) {
				if(e.getSQLState().equals("45000")) {
					System.out.println("El libro no existe!");
					e.printStackTrace();
					return 2;
				}else {
					System.out.println("No se ha podido realizar la actualizacion!");
					e.printStackTrace();
					return 1;
				}
			}
		}
		
		
		public void crearObjetosLibros() throws SQLException {
			//Eliminamos los espacios en blanco
			ArrayList<Libro> libro = new ArrayList<>();
			try {
				String sentencia = "SELECT * FROM libros";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				ResultSet resultado = consulta.executeQuery();
				while(resultado.next()) {
					String ISBN = resultado.getString("ISBN");
					String titulo = resultado.getString("tituloLibro");
					String nombre = resultado.getString("AutorNombre");
					String apellido = resultado.getString("AutorApellido");
					String segundoApellido = resultado.getString("AutorApellidoDos");
					int numPaginas = resultado.getInt("numeroPaginas");
					int genero = resultado.getInt("Genero");
					libro.add(new Libro(ISBN, titulo, nombre, apellido, segundoApellido, numPaginas, genero));
				}
			}catch(SQLException e) {
				System.out.println("No se ha podido realizar la impresion!");
				e.printStackTrace();
			}
		}
		
		public void imprimirLibrosISBN(String ISBN) {
			ISBN = ISBN.trim().replaceAll("\\s+", "");
			try {
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.PRINT_LIBRO_ISBN);
				consulta.setString(1, ISBN + "%");
				ResultSet resultado = consulta.executeQuery();
				while(resultado.next()) {
					System.out.println("- " + resultado.getString("TituloLibro"));
				}
				System.out.println("Impresion finalizada!");
			}catch(SQLException e) {
				System.out.println("No se ha podido realizar la impresion!");
				e.printStackTrace();
			}
		}
		
		
		/**
		 * Comprueba que el titulo insertado exista. Si el libro con el titulo insertado no existe devolverá null, en caso contrario
		 * devolverá el ISBN.
		 * @param TituloLibro
		 * @return
		 * @throws SQLException
		 */
		public String comprobarExistTituloLibro(String TituloLibro) throws SQLException {
			try {
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.SELECT_TITULO_LIBRO);
				consulta.setString(1, TituloLibro);
				ResultSet respuesta = consulta.executeQuery();			
				if(respuesta.next()) {
					return respuesta.getString("ISBN");
				}else {
					return null;
				}
			}catch(SQLException e) {
				System.out.println("No se ha podido hacer la comprobación");
				e.printStackTrace();
				return null;
			}
		}
		
		/**
		 * Comprueba que exista un libro pasándole por parámetro el ISBN. Retorna un 0 si existe el libro, un 1 no existe y un -1 si ha ocurrido un error
		 * @param isbn
		 * @return
		 * @throws SQLException
		 */
		public int comprobarExistRegistrosISBN(String isbn) throws SQLException {
			try {
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(Sentencia.SELECT_LIBRO);
				consulta.setString(1, isbn);
				ResultSet resultado = consulta.executeQuery();
				if (resultado.next()) {
					return 0;
				}else {
					return 1;
				}
			}catch(SQLException e) {
				return -1;
			}
		}
		
		/**
		 * Comprueba que exista el libro pasandolo por parametro el titulo. Retorna un 0 si el libro existe, un 1 si no existe y un -1 si ha ocurrido un error
		 * @param titulo
		 * @return
		 * @throws SQLException
		 */
		public int comprobarExistRegistrosTitulo(String titulo) throws SQLException{
			try {
				String sentencia = "SELECT * FROM libros WHERE tituloLibro = ?";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				consulta.setString(1, titulo);
				ResultSet resultado = consulta.executeQuery();
				if (resultado.next()) {
					return 0;
				}else {
					return 1;
				}
					
			}catch(SQLException e) {
				return -1;
			}
		}
		
		/**
		 * Devuelve el id correspondiente del género. Retorna -1 si ha ocurrido un error.
		 * @param genero
		 * @return
		 */
		public int convertirGeneroNumero(String genero) {
			try {
				String sentencia = "SELECT * FROM generos WHERE tipo = ?"; 
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				consulta.setString(1, genero);
				ResultSet resultado = consulta.executeQuery();
				if (resultado.next()) {
					return resultado.getInt("idGenero");
				}else {
					return -2;
				}
			}catch(SQLException e) {
				System.out.println("No se ha podido convertir el genero en numero");
				e.printStackTrace();
				return -1;
			}
		}
		
		public ArrayList<String> seleccionarLibroAutor(String nombre, String apellido, String segundoApellido) {
			ArrayList<String> valoresLibro = new ArrayList<>();
			System.out.println("holiwis");
			try {
				String sentencia = "SELECT * from libros where AutorNombre = ? and AutorApellido = ? and AutorApellidoDos = ?";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				consulta.setString(1, nombre);
				consulta.setString(2, apellido);
				consulta.setString(3, segundoApellido);
				ResultSet resultado = consulta.executeQuery();
				while(resultado.next()) {
					valoresLibro.add(resultado.getString("tituloLibro"));
					System.out.println("El mundo " + resultado.getString("tituloLibro"));
				}
				return valoresLibro;
				
			}catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<String> seleccionarLibroGenero(int genero){
			ArrayList<String> valoresGenero = new ArrayList<>();
			try {
				String sentencia = "select * from libros where Genero = ?";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				consulta.setInt(1, genero);
				ResultSet resultado = consulta.executeQuery();
				while(resultado.next()) {
					valoresGenero.add(resultado.getString("tituloLibro"));
					System.out.println("el resultado es " + resultado.getString("tituloLibro"));
				}
				return valoresGenero;
			}catch(SQLException e){
				return null;
			}
		}
		
		public ArrayList<String> seleccionarLibroAutorGenero(int genero, String nombre, String apellido, String segundoApellido){
			ArrayList<String> valoresGeneroAutor = new ArrayList<>();
			try {
				String sentencia = "select * from libros where Genero = ? and AutorNombre = ? and AutorApellido = ? and AutorApellidoDos = ?";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				consulta.setInt(1, genero);
				consulta.setString(2, nombre);
				consulta.setString(3, apellido);
				consulta.setString(4, segundoApellido);
				ResultSet resultado = consulta.executeQuery();
				while(resultado.next()) {
					valoresGeneroAutor.add(resultado.getString("tituloLibro"));
				}
				return valoresGeneroAutor;
			}catch(SQLException e) {
				return null;
			}
		}
		
		public ArrayList<String> seleccionarTodo(){
			ArrayList<String> todo = new ArrayList<>();
			try {
				String sentencia = "select * from libros";
				PreparedStatement consulta = this.conexion.getConexion().prepareStatement(sentencia);
				ResultSet resultado = consulta.executeQuery();
				while (resultado.next()) {
					todo.add(resultado.getString("tituloLibro"));
				}
				return todo;
			}catch(SQLException e) {
				return null;
			}
		}

}
