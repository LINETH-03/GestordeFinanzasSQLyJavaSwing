/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author herna
 */
public class Conection {
    // Variables de conexión

  public static Connection getConexion(){

        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
    "databaseName=BDAnalisis_Sistemas;user=sa;password=root;" +
    "encrypt=true;trustServerCertificate=true;" +
    "loginTimeout=30;";
         
          try{
              //String cadena = "jdbc:sqlserver://localhost:"+puerto+";"+"databaseName="+bd;
             Connection conectar = DriverManager.getConnection(connectionUrl);
                JOptionPane.showMessageDialog(null, "se conectó a la base de datos");
             return conectar;
           
              
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos, ERROR:"+ e.toString());
            System.out.print("Error al conectar a la base de datos, ERROR:"+ e.toString());
            return null;
        
        }
          
       

  
  }  

    
}
