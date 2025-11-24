/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.VentaDAO;
import java.awt.*;
import javax.swing.*;
import ui.PanelClientes;
import ui.PanelInventario;
import ui.PanelOrdenVenta;
import ui.PanelPedidos;
import ui.PanelReportes;
/**
 *
 * @author PORTATIL
 */
public class DashboardForm extends javax.swing.JFrame {

    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    private PanelInventario panelInventario;
    private PanelClientes panelClientes;
    private PanelPedidos panelPedidos;
    private PanelOrdenVenta panelOrdenVenta;
    private PanelReportes panelReportes;

    public DashboardForm() {

        setTitle("Dashboard - Prototipo Titinventary");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -----------------------------
        // PANEL LATERAL (MENÚ)
        // -----------------------------
        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(13, 71, 161));
        panelMenu.setPreferredSize(new Dimension(250, 700));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // -----------------------------
        // TITULO SUPERIOR - "Prototipo"
        // -----------------------------
        JLabel lblTituloArriba = new JLabel("Prototipo");
        lblTituloArriba.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTituloArriba.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTituloArriba.setForeground(Color.WHITE);
        panelMenu.add(lblTituloArriba);

        panelMenu.add(Box.createRigidArea(new Dimension(0, 10))); // espacio

        // -----------------------------
        // LOGO EN LA PARTE SUPERIOR
        // -----------------------------
        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        try {
            ImageIcon icon = new ImageIcon("src/ui/logo.png");
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
            
        } catch (Exception e) {
            System.out.println("No se pudo cargar el logo.");
        }

        panelMenu.add(lblLogo);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // -----------------------------
        // TITULO INFERIOR - "TITINVENTARY"
        // -----------------------------
        JLabel lblTituloAbajo = new JLabel("TITINVENTARY");
        lblTituloAbajo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTituloAbajo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloAbajo.setForeground(Color.WHITE);
        panelMenu.add(lblTituloAbajo);

        panelMenu.add(Box.createRigidArea(new Dimension(0, 20))); // espacio debajo

        // -----------------------------
        // OPCIONES PRINCIPALES
        // -----------------------------
        panelMenu.add(crearBotonConIcono("INVENTARIO", "src/ui/icons/inventario.png", "PANEL_INVENTARIO"));
        panelMenu.add(crearBotonConIcono("CLIENTES", "src/ui/icons/clientes.png", "PANEL_CLIENTES"));
        panelMenu.add(crearBotonConIcono("REPORTES", "src/ui/icons/reportes.png", "PANEL_REPORTES"));

        // -----------------------------
        // MENU DESPLEGABLE PARA VENTAS
        // -----------------------------
        panelMenu.add(crearMenuVentas());

        panelMenu.add(Box.createVerticalGlue());
        

        // -----------------------------
        // BOTÓN REGRESAR
        // -----------------------------
        JButton btnRegresar = crearBotonInferiorEstilizado("Regresar", "src/ui/icons/back_arrow.png");
        btnRegresar.addActionListener(e -> cardLayout.show(panelContenido, "HOME"));
        panelMenu.add(Box.createVerticalStrut(20));
        panelMenu.add(btnRegresar);

        // -----------------------------
        // BOTÓN CERRAR SESIÓN
        // -----------------------------
        JButton btnCerrarSesion = crearBotonInferiorEstilizado("Cerrar Sesión", "src/ui/icons/logout.png");
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(null, "Sesión finalizada.");
            System.exit(0);
        });
        panelMenu.add(Box.createVerticalStrut(10));
        panelMenu.add(btnCerrarSesion);

        // -----------------------------
        // PANEL CONTENIDO CENTRAL
        // -----------------------------
        panelInventario = new PanelInventario();
        panelClientes = new PanelClientes();
        panelPedidos = new PanelPedidos();
        panelOrdenVenta = new PanelOrdenVenta();
        panelReportes = new PanelReportes();

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        panelContenido.add(crearPanelHome(), "HOME");
        panelContenido.add(panelInventario, "PANEL_INVENTARIO");
        panelContenido.add(panelClientes, "PANEL_CLIENTES");
        panelContenido.add(panelPedidos, "PANEL_PEDIDOS");
        panelContenido.add(panelOrdenVenta, "PANEL_ORDEN");
        panelContenido.add(panelReportes, "PANEL_REPORTES");

        add(panelMenu, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        cardLayout.show(panelContenido, "HOME");
        setLocationRelativeTo(null);
    }

    // ------------------------------------------------------------------------
    // BOTÓN DIRECTO CON ÍCONO
    // ------------------------------------------------------------------------
    private JButton crearBotonConIcono(String texto, String iconPath, String destino) {

        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);

        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(13, 71, 161));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- ICONO ---
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
            btn.setIconTextGap(12);
        } catch (Exception e) {
            System.out.println("No se pudo cargar icono: " + iconPath);
        }

        // Acción
        btn.addActionListener(e -> cardLayout.show(panelContenido, destino));
        return btn;
    }

    // ------------------------------------------------------------------------
    // MENÚ DESPLEGABLE PARA VENTAS
    // ------------------------------------------------------------------------
    private JPanel crearMenuVentas() {

        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(new Color(13, 71, 161));

        JButton btnVentas = crearBotonConIcono("VENTAS", "src/ui/icons/ventas.png", "");
        btnVentas.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel subMenu = new JPanel();
        subMenu.setLayout(new BoxLayout(subMenu, BoxLayout.Y_AXIS));
        subMenu.setBackground(new Color(13, 71, 161));
        subMenu.setVisible(false);

        JButton btnPedido = crearBotonConIcono("Pedido", "src/ui/icons/pedido.png", "PANEL_PEDIDOS");
        JButton btnOrden = crearBotonConIcono("Orden de Venta", "src/ui/icons/orden.png", "PANEL_ORDEN");

        subMenu.add(btnPedido);
        subMenu.add(btnOrden);

        btnVentas.addActionListener(e -> subMenu.setVisible(!subMenu.isVisible()));

        contenedor.add(btnVentas);
        contenedor.add(subMenu);

        return contenedor;
    }

    // ------------------------------------------------------------------------
    // ESTILO PARA BOTONES INFERIORES
    // ------------------------------------------------------------------------
    private JButton crearBotonInferiorEstilizado(String texto, String iconPath) {

        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Bordes redondeados
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Ícono
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
            btn.setIconTextGap(10);
        } catch (Exception e) {
            System.out.println("Error cargando icono: " + iconPath);
        }

        // Efecto hover (blanco → fucsia)
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(255, 0, 128));  // Fucsia
                btn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        });

        btn.setAlignmentX(Component.LEFT_ALIGNMENT);

        return btn;
    }

    // ------------------------------------------------------------------------
    // PANEL HOME
    // ------------------------------------------------------------------------
    private JPanel crearPanelHome() {
        JPanel p = new JPanel(null);

        JLabel titulo = new JLabel("Bienvenido al Dashboard", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBounds(0, 20, 800, 40);
        p.add(titulo);

        ProductoDAO productoDAO = new ProductoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        VentaDAO ventaDAO = new VentaDAO();

        int totalProductos = productoDAO.contarProductos();
        int totalClientes = clienteDAO.contarClientes();
        int ventasHoy = ventaDAO.ventasHoy();

        p.add(new PanelCardSmall("Total Productos", String.valueOf(totalProductos), 30));
        p.add(new PanelCardSmall("Total Clientes", String.valueOf(totalClientes), 260));
        p.add(new PanelCardSmall("Ventas Hoy", String.valueOf(ventasHoy), 490));

        return p;
    }

    // ------------------------------------------------------------------------
    // TARJETAS ESTADÍSTICAS
    // ------------------------------------------------------------------------
    private class PanelCardSmall extends JPanel {
        public PanelCardSmall(String titulo, String valor, int x) {
            setLayout(null);
            setBackground(new Color(25, 118, 210));
            setBounds(x, 100, 200, 120);
            setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

            JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblTitulo.setBounds(10, 10, 180, 30);
            add(lblTitulo);

            JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
            lblValor.setForeground(Color.WHITE);
            lblValor.setFont(new Font("Segoe UI", Font.BOLD, 28));
            lblValor.setBounds(10, 50, 180, 40);
            add(lblValor);
        }
    }
    
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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
