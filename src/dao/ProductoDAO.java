/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class ProductoDAO {

    public int contarProductos() {
        String sql = "SELECT COUNT(*) FROM producto";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error contando productos: " + e.getMessage());
        }
        return 0;
    }
    //listar Productos
    public DefaultTableModel listarProductosTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre o Descripcion");
        model.addColumn("Referencia");
        model.addColumn("Precio");
        model.addColumn("stock");
        
        
        String sql = "SELECT id_producto, nombre_producto, referencia, precio_detal_unidad, stock FROM producto";
        
        
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                
                Object[] row = new Object[]{
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("referencia"),
                    rs.getBigDecimal("precio_detal_unidad"),
                    rs.getInt("stock")
                                            
                };
                model.addRow(row);
            }
            return model;
            
        } catch (Exception e) {
            
            System.out.println("Error listarProductosTableModel: " + e.getMessage());
            return model;
        }
    }
    //Buscar Productos
    public DefaultTableModel buscarProductosTableModel(String criterio) {
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id_producto");
        model.addColumn("nombre");
        model.addColumn("referencia");
        model.addColumn("precio_detal_unidad");
        model.addColumn("stock");
        String sql = "SELECT id_producto, nombre_producto, referencia, precio_detal_unidad, stock FROM producto WHERE nombre_producto LIKE ? OR referencia LIKE ?";
        
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, "%" + criterio + "%");
            ps.setString(2, "%" + criterio + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                Object[] row = new Object[]{
                    
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("referencia"),
                    rs.getBigDecimal("precio_detal_unidad"),
                    rs.getInt("stock")
                };
                model.addRow(row);
            }
            rs.close();
            return model;
            
        } catch (Exception e) {
            
            System.out.println("Error buscarProductosTableModel: " + e.getMessage());
            return model;
        }
    }
    //crear Productos
    public int crearProducto(String nombre, String referencia, String precio, String stock, String material) {
        String sql = "INSERT INTO producto(nombre_producto, referencia, precio_detal_unidad, stock, id_material) "
                   + "VALUES (?, ?, ?, ?, (SELECT id_material FROM material WHERE nombre_material = ?))";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombre);
            ps.setString(2, referencia);
            ps.setString(3, precio);
            ps.setString(4, stock);
            ps.setString(5, material);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);   // ← ID generado por el INSERT
                }
            }

        } catch (Exception e) {
            System.out.println("Error crearProducto: " + e.getMessage());
        }

        return -1; // error
    }

    //Modificar Productos
    public boolean modificarProducto(int id_producto, String nombre, String referencia, String precio, String stock, String material) {
        String sql = "UPDATE producto SET nombre_producto=?, referencia=?, precio_detal_unidad=?, stock=?, id_material=(SELECT id_material FROM material WHERE nombre_material=?) WHERE id_producto=?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, referencia);
            ps.setString(3, precio);
            ps.setString(4, stock);
            ps.setString(5, material);
            ps.setInt(6, id_producto);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error modificarProducto: " + e.getMessage());
            return false;
        }
    }

 
    public List<String> listarMateriales() {
        List<String> lista = new ArrayList<>();

        String sql = "SELECT nombre_material FROM material";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("nombre_material"));
            }

        } catch (Exception e) {
            System.out.println("Error cargando materiales: " + e.getMessage());
        }
        return lista;
    }
    public boolean actualizarFotoProducto(int idProducto, byte[] imagenBytes) {

        String sql = "UPDATE producto SET foto = ? WHERE id_producto = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            System.out.println("Guardando imagen en id = " + idProducto);

            ps.setBytes(1, imagenBytes);
            ps.setInt(2, idProducto);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error guardando imagen: " + e.getMessage());
            return false;
        }
    }
    public byte[] obtenerFotoProducto(int id_Producto) {

        String sql = "SELECT foto FROM producto WHERE id_producto = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_Producto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBytes("foto");
            }

        } catch (Exception e) {
            System.out.println("Error leyendo imagen: " + e.getMessage());
        }

        return null;
    }
}