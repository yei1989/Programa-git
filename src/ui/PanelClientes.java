/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.ClienteDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Cliente;


/**
 * PanelClientes: tabla con clientes + Crear, Consultar, Modificar
 */

public class PanelClientes extends javax.swing.JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar, txtNombres, txtApellidos, txtDocumento; 
    private JButton btnCrear, btnBuscar, btnModificar, btnGuardar, btnEliminar;
    private ClienteDAO clienteDAO;
    private String modoActual = "";

    public PanelClientes() {
        
        setLayout(null);
        clienteDAO = new ClienteDAO();
        
        //Botones Superiores
        btnCrear = new JButton("Crear Cliente");
        btnCrear.setBounds(20, 10, 140, 30);
        btnCrear.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnCrear.setLayout(null);
        btnCrear.setBackground(new java.awt.Color(13, 71, 161));
        btnCrear.setForeground(java.awt.Color.WHITE);
        add(btnCrear);

        btnBuscar = new JButton("Consultar Cliente");
        btnBuscar.setBounds(170, 10, 160, 30);
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnBuscar.setLayout(null);
        btnBuscar.setBackground(new java.awt.Color(13, 71, 161));
        btnBuscar.setForeground(java.awt.Color.WHITE);
        add(btnBuscar);

        btnModificar = new JButton("Modificar Cliente");
        btnModificar.setBounds(340, 10, 160, 30);
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnModificar.setLayout(null);
        btnModificar.setBackground(new java.awt.Color(13, 71, 161));
        btnModificar.setForeground(java.awt.Color.WHITE);
        add(btnModificar);
        
        btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setBounds(510, 10, 160, 30);
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new java.awt.Color(13, 71, 161));
        btnEliminar.setForeground(java.awt.Color.WHITE);
        add(btnEliminar);
        
        //Panel Form
        JPanel panelForm = new JPanel(null);
        panelForm.setBounds(20, 50, 760, 100);
        panelForm.setBorder(BorderFactory.createTitledBorder("CLIENTES"));
        add(panelForm);

        panelForm.add(new JLabel("Nombres:")).setBounds(10, 20, 80, 25);
        txtNombres = new JTextField();
        txtNombres.setBounds(100, 20, 200, 25);
        panelForm.add(txtNombres);

        panelForm.add(new JLabel("Apellidos:")).setBounds(320, 20, 80, 25);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(410, 20, 200, 25);
        panelForm.add(txtApellidos);

        panelForm.add(new JLabel("Documento:")).setBounds(10, 60, 80, 25);
        txtDocumento = new JTextField();
        txtDocumento.setBounds(100, 60, 200, 25);
        panelForm.add(txtDocumento);
        
        JPanel panelFormDos = new JPanel(null);
        panelFormDos.setBounds(20, 70, 760, 130);
        panelFormDos.setBorder(BorderFactory.createTitledBorder("Clientes"));
        add(panelFormDos);


        txtBuscar = new JTextField();
        txtBuscar.setBounds(10, 90, 300, 25);
        panelFormDos.add(txtBuscar);

        // Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(320, 90, 100, 25);
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnGuardar.setLayout(null);
        btnGuardar.setBackground(new java.awt.Color(13, 71, 161));
        btnGuardar.setForeground(java.awt.Color.WHITE);
        panelFormDos.add(btnGuardar);
        
        

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Documento");
                
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(20, 240, 760, 350);
        add(sp);

        // acciones
        btnCrear.addActionListener(e -> selectMode("CLI_CREAR"));
        btnBuscar.addActionListener(e -> selectMode("CLI_CONS"));
        btnModificar.addActionListener(e -> selectMode("CLI_MOD"));

        btnGuardar.addActionListener(e -> {
            if (modoActual.equals("CLI_CREAR")) {
                
                boolean ok = clienteDAO.crearCliente(
                        
                        txtNombres.getText(),
                        txtApellidos.getText(),
                        txtDocumento.getText()
                        
                );        
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Cliente creado");
                    loadData();
                    clearForm();
                }
            } else if (modoActual.equals("CLI_MOD")) {
                int r = tabla.getSelectedRow();
                if (r >= 0) {
                    int id = Integer.parseInt(String.valueOf(modelo.getValueAt(r, 0)));
                    boolean ok = clienteDAO.modificarCliente(
                            id, 
                            txtNombres.getText(), 
                            txtApellidos.getText(), 
                            txtDocumento.getText()
                            
                    );
                    if (ok) {
                        JOptionPane.showMessageDialog(this, "Cliente modificado");
                        loadData();
                        clearForm();
                    }
                } else JOptionPane.showMessageDialog(this, "Selecciona una fila");
            } else if (modoActual.equals("CLI_CONS")) {
                String criterio = txtBuscar.getText();
                DefaultTableModel dm = clienteDAO.buscarClientesTableModel(criterio);
                if (dm != null) {
                    tabla.setModel(dm);
                    modelo = dm;
                }
            }
        });
        
        btnEliminar.addActionListener(e -> {

            int r = tabla.getSelectedRow();
            if (r < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar");
                return;
            }

            int id = Integer.parseInt(modelo.getValueAt(r, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas eliminar este cliente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = clienteDAO.eliminarCliente(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Error eliminando el cliente");
                }
            }
        });

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int r = tabla.getSelectedRow();
                    if (r >= 0) {
                        int id = Integer.parseInt(modelo.getValueAt(r, 0).toString());

                        Cliente cli = (Cliente) clienteDAO.obtenerClientePorId(id);

                        if (cli != null) {
                            txtNombres.setText(cli.getNombres());
                            txtApellidos.setText(cli.getApellidos());
                            txtDocumento.setText(cli.getDocumento());
                            selectMode("CLI_MOD");
                        }
                    }
                }
            }
        });

        selectMode("CLI_CONS");
        loadData();
    }

    public void selectMode(String mode) {
        this.modoActual = mode;
        
        boolean editable = !mode.equals("CLI_CONS");

        txtNombres.setEnabled(editable);
        txtApellidos.setEnabled(editable);
        txtDocumento.setEnabled(editable);
        txtBuscar.setEnabled(mode.equals("CLI_CONS"));

        if (mode.equals("CLI_CREAR")) {
            btnGuardar.setText("Crear");
            clearForm();
        } else if (mode.equals("CLI_MOD")) {
            btnGuardar.setText("Modificar");
        } else {
            btnGuardar.setText("Buscar");
            clearForm();
        }
    }
        
    private void clearForm() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDocumento.setText("");
        txtBuscar.setText("");
    }

    public void loadData() {
        DefaultTableModel dm = clienteDAO.listarClientesTableModel();
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
