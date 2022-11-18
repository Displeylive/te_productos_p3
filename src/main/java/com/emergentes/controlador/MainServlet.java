package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.Coneccion_bd_almacen;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";

            ArrayList<Producto> listpro = new ArrayList<Producto>();

            Coneccion_bd_almacen canal = new Coneccion_bd_almacen();

            Connection conn = canal.conectar();
            PreparedStatement ps = null;
            ResultSet rs;

            if (op.equals("list")) {
                //optennert lista de reguistros
                String sql = "select * from productos";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Producto p = new Producto();

                    p.setId(rs.getInt("id"));
                    p.setProducto(rs.getString("producto"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setCantidad(rs.getInt("cantidad"));
                    listpro.add(p);

                }
                request.setAttribute("listpro", listpro);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }
            if (op.equals("nuevo")) {
                // incercion de nebo reguistro
                Producto pro = new Producto();
                request.setAttribute("prod", pro);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("modificar")) {
                Producto pro = new Producto();
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "update productos set producto = 'cocaquinasol' ,precio = '12.5',cantidad = '200' where id = 5;";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                request.setAttribute("prod", pro);
                request.getRequestDispatcher("editar.jsp").forward(request, response);

            }
            if (op.equals("eliminar")) {
                //eleiminar
                int id = Integer.parseInt(request.getParameter("id"));

                String sql = "delete  from productos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
                response.sendRedirect("MainServlet");

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String producto = request.getParameter("producto");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            Producto prod = new Producto();
            prod.setId(id);
            prod.setProducto(producto);
            prod.setPrecio(precio);
            prod.setCantidad(cantidad);

            Coneccion_bd_almacen canal = new Coneccion_bd_almacen();

            Connection conn = canal.conectar();
            PreparedStatement ps = null;

            if (id == 0) {

                //nuevo registro
                String sql = "insert into productos (producto,precio,cantidad) values (?,?,?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, prod.getProducto());
                ps.setDouble(2, prod.getPrecio());
                ps.setInt(3, prod.getCantidad());

                ps.executeUpdate();

                response.sendRedirect("MainServlet");

            }
        } catch (SQLException e) {
            System.out.println("Error en SQL " + e.getMessage());

        }
    }
}
