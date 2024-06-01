package org.example.lab06pelicula.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.lab06pelicula.model.beans.Pelicula;
import org.example.lab06pelicula.model.daos.PeliculaDao;

@WebServlet(name = "PeliculaServlet", value = "/PeliculaServlet")
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de películas desde PeliculaDao
        PeliculaDao peliculaDao = new PeliculaDao();
        List<Pelicula> peliculas = peliculaDao.listar();

        // Ordenar las películas por rating y box office en orden descendente
        peliculas.sort((peli1, peli2) -> {
            int ratingComparison = Double.compare(peli2.getRating(), peli1.getRating());
            if (ratingComparison != 0) {
                return ratingComparison; // Orden descendente por rating
            } else {
                return Double.compare(peli2.getBoxOffice(), peli1.getBoxOffice()); // Orden descendente por box office
            }
        });

        // Implementar la funcionalidad de búsqueda
        String terminoBusqueda = request.getParameter("terminoBusqueda");
        if (terminoBusqueda != null && !terminoBusqueda.isEmpty()) {
            List<Pelicula> peliculasFiltradas = new ArrayList<>();
            for (Pelicula pelicula : peliculas) {
                if (pelicula.getTitulo().toLowerCase().contains(terminoBusqueda.toLowerCase())) {
                    peliculasFiltradas.add(pelicula);
                }
            }
            peliculas = peliculasFiltradas;
        }

        // Preparar los datos para la vista
        request.setAttribute("peliculas", peliculas);

        // Redirigir a la vista (listaPeliculas.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaPeliculas.jsp");
        dispatcher.forward(request, response);
    }
}
