package dao;

public class Sentencia {
	//SENTENCIAS AUTORES//
	
	/**
	 * Inserta un nuevo autor
	 */
	public static final String INSERT_AUTOR_PROCEDURE = "CALL pInsertAutor(?, ?, ?, ?)";
	/**
	 * Elimina un autor
	 */
	public static final String DELETE_AUTOR = "call peliminarAutor(?, ?, ?)";
	/**
	 * Selecciona un autor por nombre, apellido y segundo apellido
	 */
	public static final String SELECT_AUTOR = "SELECT * FROM autores WHERE nombreAutor = ? and apellidoAutor = ? and apellidoDosAutor = ?";
	/**
	 * Actualiza el nombre del autor
	 */
	public static final String UPDATE_AUTOR_NAME = "CALL pActualizarNombreAutor(?, ?, ?, ?)";
	/**
	 * Actualiza el apellido del autor
	 */
	public static final String UPDATE_AUTOR_APELLIDO = "CALL pActualizarApellidoAutor(?, ?, ?, ?)";
	/**
	 * Actualiza el segundo apellido del autor
	 */
	public static final String UPDATE_AUTOR_APELLIDO_DOS = "CALL pActualizarApellidoDosAutor(?, ?, ?, ?)";
	/**
	 * Actualiza el pais del autor
	 */
	public static final String UPDATE_AUTOR_PAIS = "CALL pActualizarPaisAutor(?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_NOMBRE_APELLIDO = "CALL pActualizarNombreApellido(?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_NOMBRE_SEGAPELLIDO = "CALL pActualizarNombreSegundoApellido(?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_NOM_PAIS = "CALL pActualizarNombrePais(?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_APELLIDOS = "CALL pActualizarApellidos(?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_APELLIDO_PAIS = "CALL pActualizarApellidoPais(?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_SEGAPELLIDO_PAIS = "CALL pActualizarSegundoApellidoPais(?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_TODO = "CALL pActualizarTodoAutor(?, ?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_TODO_SINPAIS = "CALL pActualizarTodoSinPais(?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_TODO_SINSEGAPELLIDO = "CALL pActulizarTodoSinSegundoApellido(?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_TODO_SINAPELLIDO = "CALL pActualizarTodoSinApellido(?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_AUTOR_TODO_SINNOMBRE = "CALL pActualizarTodoSinNombre(?, ?, ?, ?, ?, ?)";
	
	
	
	/**
	 * Imprime todos los autores de la tabla
	 */
	public static final String PRINT_AUTORES = "SELECT * FROM autores";
	
	/**
	 * Busca el patron de los autores por nombre
	 */
	public static final String PRINT_AUTOR_NAME = "SELECT * FROM autores WHERE nombreAutor LIKE ?";
	/**
	 * Busca el patron de los autores por apellido
	 */
	public static final String PRINT_AUTOR_LASTNAME = "SELECT * FROM autores WHERE apellidoAutor LIKE ?";
	/**
	 * Busca el patron de los autores por segundo apellido
	 */
	public static final String PRINT_AUTOR_SECONDNAME = "SELECT * FROM autores WHERE apellidoDosAutor LIKE ?";
	/**
	 * Busca el patron de los autores por pais
	 */
	public static final String PRINT_AUTOR_COUNTRY = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ?";
	/**
	 * Busca el patron de los autores por apellidos
	 */
	public static final String PRINT_AUTOR_LASTSECONDNAME = "SELECT * FROM autores WHERE apellidoAutor LIKE ? AND apellidoDosAutor LIKE ?";
	/**
	 * Busca el patron de los autores por nombre y apellido
	 */
	public static final String PRINT_AUTOR_NAMLASN = "SELECT * FROM autores WHERE nombreAutor LIKE ? and apellidoAutor LIKE ?";
	/**
	 * Busca el patron de los autores por nombre, apellido y segundo apellido
	 */
	public static final String PRINT_AUTOR_NAMLASNSECN = "SELECT * FROM autores WHERE nombreAutor LIKE ? and apellidoAutor LIKE ? and apellidoDosAutor LIKE ?";
	
	/**
	 * Busca el patron segun el nombre y el pais
	 */
	public static final String PRINT_AUTOR_NAM_COUNT = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ? AND nombreAutor LIKE ?";
	
	/**
	 * Busca segun el apellido y el pais
	 */
	public static final String PRINT_AUTOR_LASTNAME_COUNT = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ? AND apellidoAutor LIKE ?";
	
	/**
	 * Busca segun el segundo apellido y pais
	 */
	public static final String PRINT_AUTOR_SECONDNAME_COUNT = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ? AND apellidoDosAutor LIKE ?";
	
	/**
	 * Busca segun el nombre, el primer apellido y el pais
	 */
	public static final String PRINT_AUTOR_NAME_LASTNAME_COUNTRY = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ? AND nombreAutor LIKE ? AND apellidoAutor LIKE ?";
	
	/**
	 * Busca segun el nombre y el segundo nombre, y el pais
	 */
	public static final String PRINT_AUTOR_NAME_SECONDNAME_COUNTRY = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ? AND nombreAutor LIKE ? AND apellidoDosAutor LIKE ?";
	
	/**
	 * Busca segun los dos apellidos y el pais.
	 */
	public static final String PRINT_AUTOR_LASTSECONDNAME_COUNTRY = "SELECT * FROM autores INNER JOIN paises ON autores.pais = paises.idPais WHERE nombrePais = ? AND apellidoAutor LIKE ? AND apellidoDosAutor ?";
	
	public static final String PRINT_COUNTRYS = "SELECT * FROM paises";
	


	
	//SENTENCIAS LIBROS//
	
	/**
	 * Inserta un nuevo libro
	 */
	public static final String INSERT_LIBRO = "CALL pInsertarNuevoLibro(?, ?, ?, ?, ?, ?, ?)";
	/**
	 * Elimina un nuevo libro
	 */
	public static final String DELETE_LIBRO = "CALL pELiminarLibro(?)";
	/**
	 * Actualiza el titulo de un libro
	 */
	public static final String UPDATE_LIBRO_TITULO = "CALL pActualizarTituloLibro(?, ?)";
	/**
	 * Actualiza el nombre del autor de un libro
	 */
	public static final String UPDATE_LIBRO_NOMBRE_AUT = "CALL pActualizarNombreAutLibro(?, ?)";
	/**
	 * Actualiza el apellido de un autor de un libro
	 */
	public static final String UPDATE_LIBRO_APELLIDO_AUT = "CALL pActualizarApellidoAutLibro(?, ?)";
	/**
	 * Actualiza el segundo apellido de un autor de un libro
	 */
	public static final String UPDATE_LIBRO_APELLIDO_DOS_AUT = "CALL pActualizarSegApellidoAutLibro(?, ?)";
	/**
	 * Actualiza las páginas de un libro
	 */
	public static final String UPDATE_LIBRO_PAGINAS = "CALL pActualizarPaginasLibro(?, ?)";
	/**
	 * Actualiza el género de un libro
	 */
	public static final String UPDATE_LIBRO_GENERO = "CALL pActualizarGeneroLibro(?, ?)";
	/**
	 * Selecciona un libro con el mismo ISBN
	 */
	public static final String SELECT_LIBRO = "SELECT * FROM libros where ISBN = ?";
	/**
	 * Selecciona un ISBN en base a un titulo de libro
	 */
	public static final String SELECT_TITULO_LIBRO = "SELECT ISBN FROM libros WHERE tituloLibro = ?";
	
	public static final String PRINT_LIBROS = "SELECT * FROM libros";
	/**
	 * Imprime los libros que contengan el mismo patron de ISBN
	 */
	public static final String PRINT_LIBRO_ISBN = "SELECT * FROM libros WHERE ISBN LIKE ?";
	/**
	 * Imprime los libros que contegan el mismo patron del titulo
	 */
	public static final String PRINT_LIBRO_TITULO = "SELECT * FROM libros WHERE tituloLibro LIKE ?";
	
}
