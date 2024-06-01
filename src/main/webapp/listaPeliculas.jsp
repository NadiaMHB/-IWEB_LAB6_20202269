<%@ page import="java.util.List" %>
<%@ page import="org.example.lab06pelicula.model.beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Pelicula> lista = (List<Pelicula>) request.getAttribute("peliculas");
%>
<html>
<head>
    <title>Películas</title>
    <style>
        table {
            max-width: 80%;
            border-collapse: collapse;
            margin-left: 0;
            margin-right: auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Lista de Películas</h1>
<form method="get" action="PeliculaServlet">
    <input type="text" name="terminoBusqueda" placeholder="Buscar película...">
    <button type="submit">Buscar</button>
</form>
<table>
    <tr>
        <th>Título</th>
        <th>Director</th>
        <th>Año Publicación</th>
        <th>Rating</th>
        <th>BoxOffice</th>
        <th>Género</th>
        <th>Actores</th>
    </tr>

    <% for (Pelicula pelicula : lista) { %>
    <tr>
        <td><a href="detallesServlet?idPelicula=<%= pelicula.getIdPelicula() %>"><%= pelicula.getTitulo() %></a></td>
        <td><%= pelicula.getDirector() %></td>
        <td><%= pelicula.getAnoPublicacion() %></td>
        <td><%= pelicula.getFormattedRating() %></td>
        <td><%= pelicula.getFormattedBoxOffice() %></td>
        <td><%= pelicula.getGenero() %></td>
        <td><a href="actorServlet?idPelicula=<%= pelicula.getIdPelicula() %>">Ver actores</a></td>
    </tr>
    <% } %>

</table>
</body>
</html>
