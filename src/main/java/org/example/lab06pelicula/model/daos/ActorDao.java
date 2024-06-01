package org.example.lab06pelicula.model.daos;

import org.example.lab06pelicula.model.beans.Actor;

import java.sql.*;
import java.util.ArrayList;

public class ActorDao {

    public ArrayList<Actor> actorEnPeli(int idPelicula) {
        ArrayList<Actor> listaActores = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "1234";

        String sql = "SELECT a.idActor, a.nombre, a.apellido, a.anoNacimiento, a.premioOscar " +
                "FROM Actor a " +
                "JOIN Protagonistas p ON a.idActor = p.idActor " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPelicula);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor();
                actor.setIdActor(rs.getInt("idActor"));
                actor.setNombre(rs.getString("nombre"));
                actor.setApellido(rs.getString("apellido"));
                actor.setAnoNacimiento(rs.getString("anoNacimiento"));
                actor.setPremioOscar(rs.getInt("premioOscar"));

                listaActores.add(actor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaActores;
    }
}
