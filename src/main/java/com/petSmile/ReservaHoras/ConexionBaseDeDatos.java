package com.petSmile.ReservaHoras;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

	
	private Connection conexion;

    public ConexionBaseDeDatos() throws SQLException {
        this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas", "root", "1234");
    }

    public Connection getConexion() {
        return conexion;
    }
}
