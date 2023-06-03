/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES;

import Entidades.Crear_UsuarioEntidad;
import java.sql.CallableStatement;
import java.sql.Statement;
import Entidades.Crear_UsuarioEntidad;
import javax.swing.JOptionPane;
import ventanas.CrearUsuario;

/**
 *
 * @author herna
 */
public class InsertarUsuarios extends CLASES.Conection {
    
    public int Guardar(Crear_UsuarioEntidad C){
        
        int respuesta = 0;
            try { 
               
             String procedimiento = "{call InsertarUsuario(?, ?, ?)}";
            CallableStatement statement = Conection.getConexion().prepareCall(procedimiento);
            statement.setString(1, C.getNombres());
            statement.setString(2, C.getCorreo());
            statement.setString(3, C.getContraseña());
            
            boolean resultado = statement.execute();
            
            statement.close();
            Conection.getConexion().close();
            
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente.");
               
               
            }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Datos no insertados."+e.toString());
            
            }
            return respuesta;
    
    
    }
    
}
