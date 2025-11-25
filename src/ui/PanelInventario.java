/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.ProductoDAO;
import model.Producto; // si tienes modelo Producto, opcional
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * PanelInventario: muestra tabla de productos y tres botones: Crear, Buscar, Modificar
 */

public class PanelInventario extends javax.swing.JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar, txtNombre, txtReferencia, txtPrecio, txtStock;
    private JComboBox<String> cbMaterial;
    private JButton btnCrear, btnBuscar, btnModificar, btnGuardar;
    private ProductoDAO productoDAO;
    
    private String modoActual = ""; // "INV_CREAR","INV_BUSCAR","INV_MOD"
    
    private PanelImagenProducto panelImagen;

    public PanelInventario() {
        setLayout(null);
        productoDAO = new ProductoDAO();

        // Barra superior: botones
        btnCrear = new JButton("Crear Producto");
        btnCrear.setBounds(20, 10, 140, 30);
        btnCrear.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnCrear.setLayout(null);
        btnCrear.setBackground(new java.awt.Color(13, 71, 161));
        btnCrear.setForeground(java.awt.Color.WHITE);
        add(btnCrear);

        btnBuscar = new JButton("Buscar Producto");
        btnBuscar.setBounds(170, 10, 140, 30);
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnBuscar.setLayout(null);
        btnBuscar.setBackground(new java.awt.Color(13, 71, 161));
        btnBuscar.setForeground(java.awt.Color.WHITE);
        add(btnBuscar);

        btnModificar = new JButton("Modificar Producto");
        btnModificar.setBounds(320, 10, 160, 30);
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnModificar.setLayout(null);
        btnModificar.setBackground(new java.awt.Color(13, 71, 161));
        btnModificar.setForeground(java.awt.Color.WHITE);
        add(btnModificar);

        // Formulario (arranca oculto)
        JPanel panelForm = new JPanel(null);
        panelForm.setBounds(20, 50, 760, 100);
        panelForm.setBorder(BorderFactory.createTitledBorder("INVENTARIO"));
        add(panelForm);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 20, 80, 25);
        panelForm.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 25);
        panelForm.add(txtNombre);

        JLabel lblRef = new JLabel("Referencia:");
        lblRef.setBounds(320, 20, 80, 25);
        panelForm.add(lblRef);

        txtReferencia = new JTextField();
        txtReferencia.setBounds(410, 20, 120, 25);
        panelForm.add(txtReferencia);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 60, 80, 25);
        panelForm.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 60, 200, 25);
        panelForm.add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(320, 60, 120, 25);
        panelForm.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(410, 60, 120, 25);
        panelForm.add(txtStock);
        
        JLabel lblMaterial = new JLabel("Material:");
        lblMaterial.setBounds(550, 20, 120, 25);
        panelForm.add(lblMaterial);

        cbMaterial = new JComboBox<>();
        cbMaterial.setBounds(620, 20, 120, 25);
        panelForm.add(cbMaterial);
        
        JPanel panelFormDos = new JPanel(null);
        panelFormDos.setBounds(20, 70, 760, 130);
        panelFormDos.setBorder(BorderFactory.createTitledBorder("Producto"));
        add(panelFormDos);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(320, 90, 100, 25);
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnGuardar.setLayout(null);
        btnGuardar.setBackground(new java.awt.Color(13, 71, 161));
        btnGuardar.setForeground(java.awt.Color.WHITE);
        panelFormDos.add(btnGuardar);
        
        txtBuscar = new JTextField();
        txtBuscar.setBounds(10, 90, 300, 25);
        panelFormDos.add(txtBuscar);

        
        // Tabla
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(20, 200, 760, 450);
        add(sp);

        // inicializar columnas (si DAO no provee modelo)
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Referencia");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Material");
        
        // ----------------------------------------------------------
        // PANEL DE IMAGEN (NUEVO)
        // ----------------------------------------------------------
        panelImagen = new PanelImagenProducto(-1);
        panelImagen.setBounds(800, 50, 220, 260);
        add(panelImagen);

        // acciones
        btnCrear.addActionListener(e -> {
            selectMode("INV_CREAR");
        });

        btnBuscar.addActionListener(e -> {
            selectMode("INV_BUSCAR");
        });

        btnModificar.addActionListener(e -> {
            selectMode("INV_MOD");
        });

        btnGuardar.addActionListener(e -> {
            
            if (modoActual.equals("INV_CREAR")) {

                String nombre = txtNombre.getText();
                String referencia = txtReferencia.getText();
                String precio = txtPrecio.getText();
                String stock = txtStock.getText();
                String material = cbMaterial.getSelectedItem().toString();

                //️ Ahora obtenemos el ID generado
                int idNuevo = productoDAO.crearProducto(nombre, referencia, precio, stock, material);

                if (idNuevo > 0) {

                    JOptionPane.showMessageDialog(this, "Producto creado");

                    // 👉 Actualizar el panel de imagen con el nuevo ID
                    remove(panelImagen);
                    panelImagen = new PanelImagenProducto(idNuevo);
                    panelImagen.setBounds(800, 50, 220, 260);
                    add(panelImagen);
                    repaint();

                    loadData();
                    clearForm();

                } else {
                    JOptionPane.showMessageDialog(this, "Error creando el producto.");
                }
                
            } else if (modoActual.equals("INV_BUSCAR")) {
                
                String criterio = txtBuscar.getText();
                loadDataFiltered(criterio);
            }
        });

        // doble click en fila => cargar formulario para modificar
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                int r = tabla.getSelectedRow();
        
                // -------------------------------------
                // MOSTRAR FOTO AL SELECCIONAR PRODUCTO
                // -------------------------------------
                if (r >= 0) {
                    int id_producto = Integer.parseInt(
                        String.valueOf(modelo.getValueAt(r, 0))
                    );

                    // Refrescar panel de imagen
                    remove(panelImagen);
                    panelImagen = new PanelImagenProducto(id_producto);
                    panelImagen.setBounds(800, 50, 220, 260);
                    add(panelImagen);
                    repaint();
                }

                // -------------------------------------
                // DOBLE CLIC PARA MODIFICAR PRODUCTO
                // -------------------------------------
                if (evt.getClickCount() == 2) {

                    txtNombre.setText(String.valueOf(modelo.getValueAt(r, 1)));
                    txtReferencia.setText(String.valueOf(modelo.getValueAt(r, 2)));
                    txtPrecio.setText(String.valueOf(modelo.getValueAt(r, 3)));
                    txtStock.setText(String.valueOf(modelo.getValueAt(r, 4)));

                    String material = String.valueOf(modelo.getValueAt(r, 5));
                    cbMaterial.setSelectedItem(material);

                    selectMode("INV_MOD");
                }
            }
        });

        // carga inicial
        selectMode("INV_BUSCAR"); // muestra búsqueda por defecto y tabla
        loadData();
        cargarMateriales();
    }
    
    public void selectMode(String mode) {
        
        this.modoActual = mode;
        // habilitar/mostrar campos según modo
        if (mode.equals("INV_CREAR")) {
            
            enableForm(true);
            txtBuscar.setEnabled(false);
            btnGuardar.setText("Crear");
            clearForm();
            
        } else if (mode.equals("INV_MOD")) {
            
            enableForm(true);
            txtBuscar.setEnabled(false);
            btnGuardar.setText("Modificar");
            
        } else {
            // BUSCAR
            
            enableForm(true);
            txtBuscar.setEnabled(true);
            btnGuardar.setText("Buscar");
            clearForm();
        }
    }

    private void enableForm(boolean v) {
        
        txtNombre.setEnabled(v);
        txtReferencia.setEnabled(v);
        txtPrecio.setEnabled(v);
        txtStock.setEnabled(v);
        txtBuscar.setEnabled(v);
        btnGuardar.setEnabled(true);
    }

    private void clearForm() {
        
        txtNombre.setText("");
        txtReferencia.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtBuscar.setText("");
    }

    public void loadData() {
        
        DefaultTableModel dm = productoDAO.listarProductosTableModel();
        
        if (dm != null) {
            tabla.setModel(dm);
            modelo = dm;
        } else {
            // si no existe ese helper, dejar la tabla como está
        }
    }
    
    private void cargarMateriales() {
        cbMaterial.removeAllItems();

        java.util.List<String> lista = productoDAO.listarMateriales();

        for (String m : lista) {
            cbMaterial.addItem(m);
        }
    }
    
    public void loadDataFiltered(String criterio) {
        
        DefaultTableModel dm = productoDAO.buscarProductosTableModel(criterio);
        
        if (dm != null) {
            tabla.setModel(dm);
            modelo = dm;
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
