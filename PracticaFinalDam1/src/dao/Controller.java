package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Controller {
	//YOU HAVE TO PUT YOUR BASEDATA ACCES
	private final String URL = "jdbc:mysql://localhost:3306/bibliotecaJava";//CHANGE PORT IF YOU USE OTHER.
	private final String USER = "";//PUT YOUR USER
	private final String PASSWORD = "";//PUT YOUR PASSWORD IF YOU HAVE. IF YOU DON'T HAVE, PASS
	//Esta es una clase COMPUESTA por otra clase llamada Connection, ya que sin esta clase la conexión a la base de datos no es posible.
	private Connection conexion;
	/**
	 * Constructor que inicializa la variable conexion, vinculando una URL, un USER y un PASSWORD para conectarse a la base de datos
	 * @throws SQLException
	 */
	public Controller () throws SQLException{
		try {
			this.conexion = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
			System.out.println("La conexion ha sido exitosa!");
		}catch(SQLException e) {
			System.out.println("No se ha podido establecer conexión con la base de datos!");
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return this.conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}	
