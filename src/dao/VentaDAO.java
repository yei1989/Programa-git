/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConexionBD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class VentaDAO {

    public int ventasHoy() {
        String sql = "SELECT COUNT(*) FROM orden_venta WHERE DATE(fecha_venta) = CURDATE()";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error contando ventas del día: " + e.getMessage());
        }
        return 0;
    }
    
    public DefaultTableModel listarVentasPorFechaTableModel(String fecha) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id_ordenventa");
        model.addColumn("fecha_venta");
        model.addColumn("total");
        String sql = "SELECT id_ordenventa, fecha_venta, total FROM orden_venta WHERE DATE(fecha_venta) = ?";
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fecha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_ordenventa"),
                    rs.getTimestamp("fecha_venta"),
                    rs.getBigDecimal("total")
                });
            }
            rs.close();
            return model;
        } catch (Exception e) {
            System.out.println("Error listarVentasPorFechaTableModel: " + e.getMessage());
            return model;
        }
    }
}
