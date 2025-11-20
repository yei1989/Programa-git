/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author PORTATIL
 */
public class UsuarioDAO {
    
    public boolean autenticar(String correo, String contrasenia) {

        String sql = "SELECT * FROM Usuario WHERE correoelectronico = ? AND contrasenia = ?";

        try {
            Connection con = (Connection) ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasenia);  // si usas hash: HashUtil.sha256(contrasenia)

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error en autenticación: " + e.getMessage());
            return false;
        }
    }
    
    //REGISTRO
    public boolean registrar(Usuario u) {
    String sql = "INSERT INTO Usuario "
            + "(id_tipodocumento, documento_usuario, primernombre_usuario, primerapellido_usuario, "
            + "id_genero, id_tipousuario, id_ciudad_usuario, contrasenia, correoelectronico) "
            + "VALUES (1, ?, ?, ?, 1, 1, 1, ?, ?)";

    try {
        Connection con = ConexionBD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, u.getDocumentoUsuario());
        ps.setString(2, u.getPrimerNombre());
        ps.setString(3, u.getPrimerApellido());
        ps.setString(4, u.getContrasenia());
        ps.setString(5, u.getCorreo());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        System.out.println("Error registrando usuario: " + e.getMessage());
        return false;
    }
}


    // LOGIN
    public Usuario login(String documento, String contrasenia) {
        String sql = "SELECT * FROM usuario WHERE documento_usuario = ? AND contrasenia = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ps.setString(2, contrasenia);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setDocumento(rs.getString("documento_usuario"));
                u.setPrimerNombre(rs.getString("primernombre_usuario"));
                u.setPrimerApellido(rs.getString("primerapellido_usuario"));
                u.setCorreo(rs.getString("correoelectronico"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Validar que no exista documento repetido
    public boolean existeDocumento(String documento) {
        String sql = "SELECT id_usuario FROM usuario WHERE documento_usuario = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
   
}
