<%@page import="com.emergentes.modelo.Producto"%>
<%
    Producto prod = (Producto) request.getAttribute("prod");
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>NUEVO PRODUCTO</h1>
        <form action="MainServlet" method="post">           
            <input type="hidden" name="id" value="${prod.id}">

            <table>

                <tr>
                    <td>PRODUCTO </td>
                    <td><input type="text" name="producto" value="${prod.producto}" /></td>
                </tr>

                <tr>
                    <td>PRECIO </td>
                    <td><input type="text" name="precio" value="${prod.precio}" /></td>
                </tr>
                <tr>
                    <td>CANTIDAD </td>
                    <td><input type="text" name="cantidad" value="${prod.cantidad}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="ENVIAR" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
