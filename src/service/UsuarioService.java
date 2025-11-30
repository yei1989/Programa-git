/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UsuarioDAO;
import model.Usuario;

/**
 *
 * @author PORTATIL
 */
public class UsuarioService {
    
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean login(String correo, String contrasenia) {
        return usuarioDAO.autenticar(correo, contrasenia);
    }

    public boolean registrar(Usuario u) {

        // Validación mínima
        if (u.getCorreo() == null || u.getCorreo().isEmpty()) return false;
        if (u.getDocumentoUsuario() == null || u.getDocumentoUsuario().isEmpty()) return false;
        if (u.getPrimerNombre() == null || u.getPrimerNombre().isEmpty()) return false;
        if (u.getPrimerApellido() == null || u.getPrimerApellido().isEmpty()) return false;

        return usuarioDAO.registrar(u);
    }
}
