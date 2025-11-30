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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author PORTATIL
 */
public class UsuarioDAO {
    
    // AUTENTICACIÓN  BCrypt(LOGIN)
    
    public boolean autenticar(String correo, String contrasenia) {

        String sql = "SELECT contrasenia FROM Usuario WHERE correoelectronico = ?";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashBD = rs.getString("contrasenia");

                // Validar con BCrypt
                return BCrypt.checkpw(contrasenia, hashBD);
            }

        } catch (SQLException e) {
            System.out.println("Error en autenticación: " + e.getMessage());
        }

        return false;
    }

    // REGISTRO (GUARDAR USUARIO)
    public boolean registrar(Usuario u) {

        String sql = "INSERT INTO Usuario "
                + "(id_tipodocumento, documento_usuario, primernombre_usuario, primerapellido_usuario, "
                + "id_genero, id_tipousuario, id_ciudad_usuario, contrasenia, correoelectronico) "
                + "VALUES (1, ?, ?, ?, 1, 1, 1, ?, ?)";

        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            // ENCRIPTAR CONTRASEÑA
            String hash = BCrypt.hashpw(u.getContrasenia(), BCrypt.gensalt());

            ps.setString(1, u.getDocumentoUsuario());
            ps.setString(2, u.getPrimerNombre());
            ps.setString(3, u.getPrimerApellido());
            ps.setString(4, hash);  // Guardamos el hash
            ps.setString(5, u.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error registrando usuario: " + e.getMessage());
            return false;
        }
    }

    // LOGIN POR DOCUMENTO (SI AÚN LO USAS)
    public Usuario login(String documento, String contrasenia) {

        String sql = "SELECT * FROM usuario WHERE documento_usuario = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, documento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashBD = rs.getString("contrasenia");

                // Validar contraseña
                if (!BCrypt.checkpw(contrasenia, hashBD)) {
                    return null; // Contraseña incorrecta
                }

                // Crear objeto Usuario
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

    // Validar documento repetido
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
