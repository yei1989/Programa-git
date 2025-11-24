/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

public class PanelPedidos extends javax.swing.JPanel {

    public PanelPedidos() {
        setLayout(null);

        JLabel lbl = new JLabel("Toma de Pedido", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setBounds(0, 20, 800, 40);
        add(lbl);

        // Aquí debes integrar selección de productos (tabla), cliente, cantidad, agregar a carrito y calcular total.
        // Por ahora añadimos botones base:
        JButton btnAgregar = new JButton("Agregar producto al pedido");
        btnAgregar.setBounds(20, 80, 220, 30);
        add(btnAgregar);

        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.setBounds(260, 80, 160, 30);
        add(btnFinalizar);
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
