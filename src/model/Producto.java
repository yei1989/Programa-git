/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PORTATIL
 */
public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double precio;
    private byte[] foto; // <--- NUEVO

    public Producto() { }

    // Getters y setters normales...

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
