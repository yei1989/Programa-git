/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
/**
 *
 * @author PORTATIL
 */
public class ClienteDAO {
    
       
    //Contar CLientes
    public int contarClientes() {
        String sql = "SELECT COUNT(*) FROM cliente";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1);

        } catch (Exception e) {
            System.out.println("Error contando clientes: " + e.getMessage());
        }
        return 0;
    }
    
    public DefaultTableModel listarClientesTableModel() {
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Cedula");
        model.addColumn("Tipo Cliente");
        
        String sql = 
            "SELECT c.id_cliente, c.nombres_cliente, c.apellidos_clientes, " +
            "c.documento_cliente, t.tipo_cliente " +
            "FROM cliente c " +
            "LEFT JOIN tipo_cliente t ON c.id_tipo_cliente = t.id_tipo_cliente";
              
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_cliente"),
                    rs.getString("nombres_cliente"),
                    rs.getString("apellidos_clientes"),
                    rs.getString("documento_cliente"),
                    rs.getNString("tipo_cliente")
                });
            }
            return model;
        } catch (Exception e) {
            System.out.println("Error listarClientesTableModel: " + e.getMessage());
            return model;
        }
    }
    
    //Buscar Clientes
    public DefaultTableModel buscarClientesTableModel(String criterio) {
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Cedula");
        
        String sql = "SELECT * FROM cliente WHERE nombres_cliente LIKE ? OR apellidos_clientes LIKE ? OR documento_cliente LIKE ? ";
        
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, "%" + criterio + "%");
            ps.setString(2, "%" + criterio + "%");
            ps.setString(3, "%" + criterio + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_cliente"),
                    rs.getString("nombres_cliente"),
                    rs.getString("apellidos_clientes"),
                    rs.getString("documento_cliente"),
                    
                });
            }
            rs.close();
            return model;
        } catch (Exception e) {
            System.out.println("Error buscarClientesTableModel: " + e.getMessage());
            return model;
        }
    }
    //Crear Cliente
    public boolean crearCliente(String nombres, String apellidos, String documento, String tipo_cliente) {
        
        String sql = "INSERT INTO cliente (nombres_cliente, apellidos_clientes, documento_cliente, " + 
                "id_tipodocumento_cliente, id_tipo_cliente) " + 
                "VALUES (?, ?, ?, 1, 1, ?, NOW())";
        
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, documento);
            ps.setInt(4, obtenerIdTipoCliente(tipo_cliente));
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            
            System.out.println("Error crearCliente: " + e.getMessage());
            return false;
        }
    }
    
    //Modificar CLiente
    public boolean modificarCliente(int id, String nombres, String apellidos, String documento, String tipo_cliente) {
        
        String sql = "UPDATE cliente SET nombres_cliente=?, apellidos_clientes=?, documento_cliente=?, " + 
                "id_tipo_cliente=? WHERE id_cliente=?";
        
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, documento);
            ps.setInt(4, obtenerIdTipoCliente(tipo_cliente));
            ps.setInt(5, id);
            
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            System.out.println("Error modificarCliente: " + e.getMessage());
            return false;
        }
    }
    // OBTENER CLIENTE POR ID (ÚTIL PARA CARGAR FORMULARIO)
    public Cliente obtenerClientePorId(int id) {

        String sql = "SELECT * FROM cliente WHERE id_cliente=?";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombres_cliente"),
                        rs.getString("apellidos_clientes"),
                        rs.getString("documento_cliente")
                );
            }

        } catch (Exception e) {
            System.err.println("Error obtenerClientePorId: " + e.getMessage());
        }

        return null;
    }
    // ELIMINAR CLIENTE 
    public boolean eliminarCliente(int id) {

        String sql = "DELETE FROM cliente WHERE id_cliente=?";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("Error eliminarCliente: " + e.getMessage());
            return false;
        }
    }
    
    public List<String> listarTipoCliente() {
        List<String> lista = new ArrayList<>();

        String sql = "SELECT tipo_cliente FROM tipo_cliente";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("tipo_cliente"));
            }

        } catch (Exception e) {
            System.out.println("Error cargando materiales: " + e.getMessage());
        }
        return lista;
    }
    
    private int obtenerIdTipoCliente(String tipo) {

        String sql = "SELECT id_tipocliente FROM tipo_cliente WHERE tipo_cliente=?";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            System.out.println("Error obteniendo id tipo cliente: " + e.getMessage());
        }

        return 1; // por defecto
    }
    public void llenarComboTipoCliente(JComboBox<String> combo) {
        String sql = "SELECT tipo_cliente FROM tipo_cliente";

        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            combo.removeAllItems();  // Limpia el combo

            while (rs.next()) {
                combo.addItem(rs.getString("tipo_cliente"));
            }

        } catch (Exception e) {
            System.out.println("Error llenarComboTipoCliente: " + e.getMessage());
        }
    }
    
}
