/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import javax.swing.JPanel;
import dao.ProductoDAO;
import util.ImagenUtil;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author PORTATIL
 */
public class PanelImagenProducto extends JPanel {
    
    private JLabel lblImagen;
    private JButton btnCargar;

    private int id_Producto;

    public PanelImagenProducto(int id_Producto) {
        this.id_Producto = id_Producto;

        setLayout(null);
        setPreferredSize(new Dimension(220, 260));
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.GRAY, 1));

        lblImagen = new JLabel("Sin Imagen", SwingConstants.CENTER);
        lblImagen.setBounds(20, 20, 180, 180);
        lblImagen.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        add(lblImagen);

        btnCargar = new JButton("Cargar Imagen");
        btnCargar.setBounds(40, 210, 140, 30);
        btnCargar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnCargar.setLayout(null);
        btnCargar.setBackground(new java.awt.Color(13, 71, 161));
        btnCargar.setForeground(java.awt.Color.WHITE);
        add(btnCargar);

        btnCargar.addActionListener(e -> cargarImagen());

        mostrarImagenGuardada();
    }
    public void setIdProducto(int id){
        this.id_Producto = id;
        mostrarImagenGuardada();
    }

    private void cargarImagen() {
        
        if (id_Producto <= 0) {
            JOptionPane.showMessageDialog(this, "Primero debes crear un producto.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen del producto");

        int opcion = chooser.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File archivo = chooser.getSelectedFile();

            try {
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(img));

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedImage bImage = ImageIO.read(archivo);
                ImageIO.write(bImage, "jpg", baos);
                byte[] fotoEnBytes = baos.toByteArray();

                // Aquí se usa id_Producto y YA NO debe ser 0
                ProductoDAO dao = new ProductoDAO();
                boolean ok = dao.actualizarFotoProducto(id_Producto, fotoEnBytes);

                if (ok) {
                    JOptionPane.showMessageDialog(this, "Imagen guardada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error guardando la imagen.");
                }

        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + e.getMessage());
        }
    }
}

    private void mostrarImagenGuardada() {
        
        if (id_Producto <= 0) return; // << AGREGAR ESTO, evita intentos con ID = 0

        ProductoDAO dao = new ProductoDAO();
        byte[] datos = dao.obtenerFotoProducto(id_Producto);

        if (datos != null) {
            ImageIcon icon = new ImageIcon(datos);
            Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);

            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText("");
        }
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
