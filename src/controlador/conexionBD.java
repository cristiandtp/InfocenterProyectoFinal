package controlador;
// primero
import java.sql.*;
public class conexionBD {
	
	//segundo creo metodo publico de conexion
	public Connection abrirBaseDatos() {
		String urlDB = "jdbc:mysql://localhost:3306/festividades";
		String user = "proyectoA";
		String pw = "1234";
		
		try {
			Connection accesoDB = DriverManager.getConnection(urlDB, user, pw);
			System.out.println("conectado");
			return accesoDB;
		}catch (SQLException e) {
			System.out.println("No conectado");
			return null;
		}
	}
	
}