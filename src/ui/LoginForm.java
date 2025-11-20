/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import service.UsuarioService;

/**
 *
 * @author PORTATIL
 */
public class LoginForm extends javax.swing.JFrame {
    
    private final JTextField txtCorreo;
    private final JPasswordField txtContrasenia;
    private final JButton btnLogin;
    UsuarioService usuarioService = new UsuarioService();
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        
        setTitle("Login Titinventary");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        
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

        JLabel lblLogin = new JLabel("Iniciar Sesión");
        lblLogin.setBounds(150, 80, 300, 40);
        lblLogin.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblLogin.setForeground(new java.awt.Color(33, 33, 33));
        panelDer.add(lblLogin);
        
        // Labels y campos

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(80, 170, 150, 25);
        lblCorreo.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(80, 200, 330, 32);
        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 16));
        txtCorreo.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtCorreo);
        
        

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(80, 250, 150, 25);
        lblPass.setFont(new java.awt.Font("Segoe UI", 0, 16));
        panelDer.add(lblPass);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(80, 280, 330, 32);
        txtContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 16));
        txtContrasenia.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        panelDer.add(txtContrasenia);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(150, 360, 200, 40);
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 20));
        btnLogin.setBackground(new java.awt.Color(25, 118, 210));
        btnLogin.setForeground(java.awt.Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDer.add(btnLogin);

        btnLogin.addActionListener(e -> login());

        setLocationRelativeTo(null);
        
        //Boton de Registro
        JButton btnRegistro = new JButton("¿No tienes una Cuenta? Regístrate");
        btnRegistro.setBounds(150, 410, 200, 30);
        btnRegistro.setFont(new java.awt.Font("Segoe UI", 5, 13));
        btnRegistro.setBorder(null);
        btnRegistro.setContentAreaFilled(false); // lo convierte en estilo link
        btnRegistro.setForeground(new java.awt.Color(25, 118, 210));
        btnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        //accion del "link"
        btnRegistro.addActionListener(e -> {
        // Cierra este formulario
            this.dispose();

    // Abre el formulario de registro
            RegisterForm registerForm = new RegisterForm();
            registerForm.setVisible(true);
        });
        
        panelDer.add(btnRegistro);
    }
    
    private void login() {
        String correo = txtCorreo.getText();
        String pass = new String(txtContrasenia.getPassword());

        if (usuarioService.login(correo, pass)) {
            JOptionPane.showMessageDialog(this, "Bienvenido al sistema");
            // Ir al Dashboard
            new DashboardForm().setVisible(true);

            dispose(); // cierra el login
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
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
        new LoginForm().setVisible(true);            }

          
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

