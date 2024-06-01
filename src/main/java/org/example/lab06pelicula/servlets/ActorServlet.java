package org.example.lab06pelicula.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.example.lab06pelicula.model.beans.Actor;
import org.example.lab06pelicula.model.beans.Pelicula;
import org.example.lab06pelicula.model.daos.ActorDao;
import org.example.lab06pelicula.model.daos.PeliculaDao;


@WebServlet("/actorServlet")
public class ActorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el idPelicula de los parámetros de la solicitud
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));

        // Crear una instancia de ActorDao para obtener los actores de la película
        ActorDao actorDao = new ActorDao();
        ArrayList<Actor> listaActores = actorDao.actorEnPeli(idPelicula);

        // Obtener el título de la película correspondiente al idPelicula
        PeliculaDao peliculaDao = new PeliculaDao();
        Pelicula pelicula = peliculaDao.buscarId(idPelicula);
        String tituloPelicula = (pelicula != null) ? pelicula.getTitulo() : "Título no disponible";

        // Establecer el título de la película y la lista de actores
        request.setAttribute("pelicula", pelicula);
        request.setAttribute("tituloPelicula", tituloPelicula);
        request.setAttribute("listaActores", listaActores);

        // Redireccionar a la vista listaActores.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaActores.jsp");
        dispatcher.forward(request, response);
    }
}
