package org.example.lab06pelicula.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.example.lab06pelicula.model.beans.Pelicula;
import org.example.lab06pelicula.model.daos.PeliculaDao;

@WebServlet("/detallesServlet")
public class DetallesServlet extends HttpServlet {
    private PeliculaDao peliculaDao = new PeliculaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPelicula = request.getParameter("idPelicula");

        if (idPelicula != null) {
            int id = Integer.parseInt(idPelicula);
            Pelicula pelicula = peliculaDao.buscarId(id);
            request.setAttribute("pelicula", pelicula);
            request.getRequestDispatcher("/viewPelicula.jsp").forward(request, response);
        } else {
            response.sendRedirect("listaPeliculas.jsp"); // En caso de que no haya idPelicula, redirigir a la lista
        }
    }
}
