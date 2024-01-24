package com.petSmile.ReservaHoras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

	private int id;
    private String nombreUsuario;
    private String password;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Usuario guardar(Usuario usuario) throws SQLException {
        ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
        Connection connection = conexion.getConexion();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO usuario (nombreUsuario, password) VALUES (?, ?)");
        statement.setString(1, usuario.getNombreUsuario());
        statement.setString(2, usuario.getPassword());

        statement.executeUpdate();

        statement.close();
        connection.close();

        return usuario;
    }

    public static Usuario obtenerPorNombreUsuario(String nombreUsuario) throws SQLException {
        ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
        Connection connection = conexion.getConexion();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE nombreUsuario = ?");
        statement.setString(1, nombreUsuario);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNombreUsuario(resultSet.getString("nombreUsuario"));
            usuario.setPassword(resultSet.getString("password"));

            return usuario;
        }

        return null;
    }

    public static void actualizar(Usuario usuario) throws SQLException {
        ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
        Connection connection = conexion.getConexion();

        PreparedStatement statement = connection.prepareStatement("UPDATE usuario SET nombreUsuario = ?, password = ? WHERE id = ?");
        statement.setString(1, usuario.getNombreUsuario());
        statement.setString(2, usuario.getPassword());
        statement.setInt(3, usuario.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public static void eliminar(int id) throws SQLException {
        ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
        Connection connection = conexion.getConexion();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM usuario WHERE id = ?");
        statement.setInt(1, id);

        statement.executeUpdate();

        statement.close();
}
    
}
