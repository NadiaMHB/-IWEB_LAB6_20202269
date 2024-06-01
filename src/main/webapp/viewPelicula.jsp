<%@ page import="org.example.lab06pelicula.model.beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
%>
<html>
<head>
    <title><%= pelicula.getTitulo() %></title>
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
<h1><%= pelicula.getTitulo() %></h1>
<table>
    <tr>
        <th>ID</th>
        <td><%= pelicula.getIdPelicula() %></td>
    </tr>
    <tr>
        <th>Título</th>
        <td><%= pelicula.getTitulo() %></td>
    </tr>
    <tr>
        <th>Director</th>
        <td><%= pelicula.getDirector() %></td>
    </tr>
    <tr>
        <th>Año de Publicación</th>
        <td><%= pelicula.getAnoPublicacion() %></td>
    </tr>
    <tr>
        <th>Rating</th>
        <td><%= pelicula.getFormattedRating() %></td>
    </tr>
    <tr>
        <th>BoxOffice</th>
        <td><%= pelicula.getFormattedBoxOffice() %></td>
    </tr>
    <tr>
        <th>Género</th>
        <td><%= pelicula.getGenero() %></td>
    </tr>
    <tr>
        <th>Actores</th>
        <td><a href="actorServlet?idPelicula=<%= pelicula.getIdPelicula() %>">Ver Actores</a></td>
    </tr>
</table>
</body>
</html>
