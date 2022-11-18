
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coneccion_bd_almacen {
    static  String driver = "com.mysql.jdbc.Driver";
    static  String url = "jdbc:mysql://localhost:3306/bd_almacen";
    static  String usuario = "root";
    static  String password = "";
    
    Connection conn = null;

    public Coneccion_bd_almacen() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
            if(conn != null){
                System.out.println("conexion ok: "+ conn);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error de coneccion " +e.getMessage());
        } catch (SQLException ex){
            System.out.println("Error de SQL" + ex.getMessage());
        }
        
    }
    public Connection conectar()
    {
        return conn;
    }
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Coneccion_bd_almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public int obtieneId(){
       int idaux =0;
       for(producto item : producto){
           idaux = item.getId();
       }
       return idaux + 1;
   }
   public int ubicarTarea(int id){
       int pos = -1;
       Iterator<tarea> it =lista.iterator();
       
       while(it.hasNext()){
           ++pos;
           tarea aux = it.next();
           if(aux.getId() == id){
               break;
           }
       }
       return pos;*/
}
