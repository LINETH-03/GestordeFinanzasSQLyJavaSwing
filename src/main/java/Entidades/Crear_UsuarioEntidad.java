/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author herna
 */
public class Crear_UsuarioEntidad {
    
    private String Nombres;
    private String Correo;
    private String Contraseña;

    public Crear_UsuarioEntidad() {
    }

    public Crear_UsuarioEntidad(String Nombres, String Correo, String Contraseña) {
        this.Nombres = Nombres;
        this.Correo = Correo;
        this.Contraseña = Contraseña;
    }
    
    
    

    public String getNombres() {
        return Nombres;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    
    
    
}
