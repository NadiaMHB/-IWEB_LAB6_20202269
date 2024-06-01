package org.example.lab06pelicula.model.daos;

import org.example.lab06pelicula.model.beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class PeliculaDao {

    public ArrayList<Pelicula> listar() {
        ArrayList<Pelicula> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "1234";

        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre " +
                "FROM pelicula p " +
                "JOIN genero g ON p.idGenero = g.idGenero";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getInt("boxOffice"));
                pelicula.setGenero(rs.getString("nombre"));

                lista.add(pelicula);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Pelicula buscarId(int idPelicula) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "1234";

        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.nombre " +
                "FROM pelicula p " +
                "JOIN genero g ON p.idGenero = g.idGenero " +
                "WHERE p.idPelicula = ?";

        Pelicula pelicula = null;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getInt("boxOffice"));
                pelicula.setGenero(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pelicula;
    }
}
