/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.*;

/**
 *
 * @author PORTATIL
 */
public class ConexionBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/prototipo_titinventary";
    private static final String USER = "yei";
    private static final String PASS = "1234";

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASS);
            
            return con ;
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
