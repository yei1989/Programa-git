/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.VentaDAO;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 * PanelReportes: tres botones que generan CSV según fecha ingresada
 */

public class PanelReportes extends javax.swing.JPanel {

    private final JTextField txtFecha;

    public PanelReportes() {
        setLayout(null);
        JLabel lbl = new JLabel("Reportes", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setBounds(0, 20, 800, 40);
        add(lbl);
        
        JLabel lblFecha = new JLabel("Fecha(YYYY-MM-DD):");
        lblFecha.setBounds(40, 80, 150, 25);
        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 12));
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(180, 80, 150, 30);
        txtFecha.setToolTipText("Formato: YYYY-MM-DD");
        add(txtFecha);

        JButton btnInv = new JButton("Reporte Inventario");
        btnInv.setBounds(650, 80, 140, 30); 
        btnInv.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnInv.setLayout(null);
        btnInv.setBackground(new java.awt.Color(13, 71, 161));
        btnInv.setForeground(java.awt.Color.WHITE);
        add(btnInv);

        JButton btnVen = new JButton("Reporte Ventas");
        btnVen.setBounds(500, 80, 130, 30);
        btnVen.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnVen.setLayout(null);
        btnVen.setBackground(new java.awt.Color(13, 71, 161));
        btnVen.setForeground(java.awt.Color.WHITE);
        add(btnVen);

        JButton btnCli = new JButton("Reporte Clientes");
        btnCli.setBounds(350, 80, 130, 30);
        btnCli.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnCli.setLayout(null);
        btnCli.setBackground(new java.awt.Color(13, 71, 161));
        btnCli.setForeground(java.awt.Color.WHITE);
        add(btnCli);

        btnInv.addActionListener(e -> generarReporteInventario());
        btnVen.addActionListener(e -> generarReporteVentas());
        btnCli.addActionListener(e -> generarReporteClientes());
    }

    private void generarReporteInventario() {
        try {
            ProductoDAO pdao = new ProductoDAO();
            DefaultTableModel dm = pdao.listarProductosTableModel();

            String nombreArchivo = "Reporte_Inventario.csv";

            boolean ok = util.ExcelExporter.exportToExcel(dm, nombreArchivo);

            if (ok) 
                JOptionPane.showMessageDialog(this, "Reporte generado: " + nombreArchivo);
            else 
                JOptionPane.showMessageDialog(this, "Error al generar archivo.");

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void generarReporteVentas() {
        try {
            String fecha = txtFecha.getText();
            VentaDAO vdao = new VentaDAO();

            DefaultTableModel dm = vdao.listarVentasPorFechaTableModel(fecha);

            String nombreArchivo = "Reporte_Ventas_" + fecha + ".csv";

            boolean ok = util.ExcelExporter.exportToExcel(dm, nombreArchivo);

            if (ok)
                JOptionPane.showMessageDialog(this, "Reporte generado: " + nombreArchivo);
            else
                JOptionPane.showMessageDialog(this, "Error al generar archivo.");

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void generarReporteClientes() {
        try {
            ClienteDAO cdao = new ClienteDAO();
            DefaultTableModel dm = cdao.listarClientesTableModel();

            String nombreArchivo = "Reporte_Clientes.csv";

            boolean ok = util.ExcelExporter.exportToExcel(dm, nombreArchivo);

            if (ok)
                JOptionPane.showMessageDialog(this, "Reporte generado: " + nombreArchivo);
            else
                JOptionPane.showMessageDialog(this, "Error al generar archivo.");

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // allows Dashboard to call selectMode if needed
    public void selectMode(String op) {
        // opcional: podrías pre-seleccionar subtipo en panelReportes
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
