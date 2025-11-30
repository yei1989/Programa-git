/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import javax.swing.*;
import model.Usuario;
import service.UsuarioService;
/**
 *
 * @author PORTATIL
 */
public class RegisterForm extends javax.swing.JFrame {
    
    private JTextField txtCorreo, txtDocumento, txtPrimerNombre, txtPrimerApellido;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar, btnVolver;
    
    UsuarioService usuarioService = new UsuarioService();
    /**
     * Creates new form RegisterForm
     */
    public RegisterForm() {
        setTitle("Registro de Usuario");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // -----------------------------------------
        // PANEL IZQUIERDO (COLOR PRINCIPAL)
        // -----------------------------------------
        
        javax.swing.JPanel panelIzq = new javax.swing.JPanel();
        panelIzq.setLayout(null);
        panelIzq.setBackground(new java.awt.Color(13, 71, 161)); // Azul moderno
        panelIzq.setBounds(0, 0, 350, 550);
        add(panelIzq);
        
        JLabel lblTitulo2 = new JLabel("Prototipo");
        lblTitulo2.setBounds(140, 140, 270, 30);
        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 4, 16));
        lblTitulo2.setForeground(java.awt.Color.WHITE);
        panelIzq.add(lblTitulo2);
        
        //Espacio para el Logo
        JLabel lblLogo = new JLabel ();
        lblLogo.setBounds (110, 180, 130, 130); //ubicacion del logo
        
        //cargar imagen del Logo
        lblLogo.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon("src/ui/logo.png"));
        panelIzq.add(lblLogo);
        
        
        JLabel lblTitulo = new JLabel("TITINVENTARY");
        lblTitulo.setBounds(70, 330, 300, 40);
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28));
        lblTitulo.setForeground(java.awt.Color.WHITE);
        panelIzq.add(lblTitulo);

        JLabel lblSub = new JLabel("Sistema de Gestion de Inventarios");
        lblSub.setBounds(80, 360, 200, 40);
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblSub.setForeground(java.awt.Color.WHITE);
        panelIzq.add(lblSub);
        
        // -----------------------------------------
        // PANEL DERECHO (FORMULARIO)
        // -----------------------------------------
        
        javax.swing.JPanel panelDer = new javax.swing.JPanel();
        panelDer.setLayout(null);
        panelDer.setBounds(350, 0, 500, 550);
        panelDer.setBackground(new java.awt.Color(245, 245, 245));
        add(panelDer);

        JLabel lblTituloDos = new JLabel("Crear Cuenta");
        lblTituloDos.setBounds(190, 30, 150, 25);
        lblTituloDos.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTituloDos.setForeground(new java.awt.Color(33, 33, 33));
        panelDer.add(lblTituloDos);
        
        // Labels y campos
        
        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(60, 80, 120, 25);
        lblDocumento.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(180, 80, 180, 25);
        txtDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12));
        txtDocumento.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtDocumento);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(60, 120, 120, 25);
        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblNombre);

        txtPrimerNombre = new JTextField();
        txtPrimerNombre.setBounds(180, 120, 180, 25);
        txtPrimerNombre.setFont(new java.awt.Font("Segoe UI", 0, 12));
        txtPrimerNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtPrimerNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(60, 160, 120, 25);
        lblApellido.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblApellido);

        txtPrimerApellido = new JTextField();
        txtPrimerApellido.setBounds(180, 160, 180, 25);
        txtPrimerApellido.setFont(new java.awt.Font("Segoe UI", 0, 12));
        txtPrimerApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtPrimerApellido);
        
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(60, 200, 120, 25);
        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(180, 200, 180, 25);
        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 10));
        txtCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtCorreo);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(60, 240, 120, 25);
        lblPass.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblPass);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(180, 240, 180, 25);
        txtContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 12));
        txtContrasenia.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtContrasenia);

        // Botón registrar
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(130, 340, 120, 35);
        btnRegistrar.setBackground(new java.awt.Color(25, 118, 210));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 20));
        btnRegistrar.setForeground(java.awt.Color.WHITE);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDer.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrar());

        // Botón volver
        btnVolver = new JButton("Volver");
        btnVolver.setIcon(new ImageIcon(getClass().getResource("/ui/icons/back_arrow.png")));
        
        btnVolver.setBounds(280, 340, 120, 35);
        btnVolver.setFont(new java.awt.Font("Segoe UI", 5, 13));
        btnVolver.setBorder(null);
        btnVolver.setContentAreaFilled(false); // lo convierte en estilo link
        btnVolver.setForeground(new java.awt.Color(25, 118, 210));
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDer.add(btnVolver);

        btnVolver.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
    }

    private void registrar() {
            // Validar
        if (txtCorreo.getText().isEmpty() ||
            txtDocumento.getText().isEmpty() ||
            txtPrimerNombre.getText().isEmpty() ||
            txtPrimerApellido.getText().isEmpty() ||
            txtContrasenia.getPassword().length == 0) {

            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        Usuario u = new Usuario();
        u.setCorreo(txtCorreo.getText().trim());
        u.setDocumentoUsuario(txtDocumento.getText().trim());
        u.setPrimerNombre(txtPrimerNombre.getText().trim());
        u.setPrimerApellido(txtPrimerApellido.getText().trim());
        u.setContrasenia(new String(txtContrasenia.getPassword()));

        boolean registrado = usuarioService.registrar(u);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");

            // Abrir SOLO login (esto es lo correcto)
            new LoginForm().setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Error registrando usuario");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
