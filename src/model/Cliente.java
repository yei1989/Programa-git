/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



public class Cliente {
    
    private int id;
    private String nombres;
    private String apellidos;
    private String documento;
    
    public Cliente(int id, String nombres, String apellidos, String documento) {

        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
    }

    public int getId() { return id; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDocumento() { return documento; }
}
