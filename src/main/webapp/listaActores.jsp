<%@ page import="org.example.lab06pelicula.model.beans.Pelicula" %>
<%@ page import="org.example.lab06pelicula.model.beans.Actor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
    String titulo = pelicula.getTitulo();
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= titulo %></title>
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
<h1><%= titulo %></h1>
<table>
    <thead>
    <tr>
        <th>idActor</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Año Nacimiento</th>
        <th> Ganador Premio Oscar</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Iterar sobre la lista de actores y mostrar sus atributos
        ArrayList<Actor> listaActores = (ArrayList<Actor>) request.getAttribute("listaActores");
        for (Actor actor : listaActores) {
    %>
    <tr>
        <td><%= actor.getIdActor() %></td>
        <td><%= actor.getNombre() %></td>
        <td><%= actor.getApellido() %></td>
        <td><%= actor.getAnoNacimiento() %></td>
        <td><%= actor.getPremioOscar() == 1 ? "True" : "False" %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
