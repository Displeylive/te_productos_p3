<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>


<%
    List<Producto> listpro = (List<Producto>) request.getAttribute("listpro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                background-image: url("https://png.pngtree.com/png-clipart/20190516/original/pngtree-abstract-smoke-png-transparent-background-png-image_3573121.jpg");
            }
        </style>
    <h2><center><br>PARCTICA 3 TEM-742
            <br>NOMBRE: MARCO ANTONIO TAMBO ARCANI
            <br>CARNET: 8373276
            </h2></center>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body><center>
        <h1>lista de Productos</h1>
        <p> <a href="MainServlet?op=nuevo">Nuevo Producto</a></p>
        <br><br>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>PRODUCTO</th>            
                <th>PRECIO</th>
                <th>CANTIDAD</th>
                <th>MODIFICAR</th>
                <th>ELIMINAR</th>
            </tr>  
            <c:forEach var="item" items="${listpro}">
                <tr>
                    <td>${item.id} </td>
                    <td>${item.producto}</td>
                    <td>${item.precio}</td>
                    <td>${item.cantidad}</td>
                    <td><a href="MainServlet?op=modificar&id=${item.getId()}">modificar</a></td>
                    <td><a href="MainServlet?op=eliminar&id=${item.getId()}"
                           onclick="return confirm('esta seguro de eliminar ?')">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table> 
    </body>
</html>
