/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import CLASES.Conection;
import CLASES.DatosCompartidos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ventanas.LOGIN;

/**
 *
 * @author herna
 */
public class Ingresos extends javax.swing.JFrame  {
    
     private String nombreAnterior;
       private String contraseñaAnterior;
       
       public Ingresos(String name, String pass) {
         String nombreAnterior = DatosCompartidos.name;
        String contraseñaAnterior = DatosCompartidos.pass;
        
       

        // Resto del código del constructor de NuevaVentana
    }
       
    

    /**
     * Creates new form Ingresos
     */
    public Ingresos() {
        initComponents();
    }
    
    
  
    public void ingresosinsertar(){
        // Obtener los valores de las cajas anteriores
    String nombreAnterior = DatosCompartidos.name;
    String contraseñaAnterior = DatosCompartidos.pass;

// Obtener los valores para los nuevos ingresos
double cantidad = Double.parseDouble(txtcantidad.getText());
Date fecha = Date.valueOf(txtfecha.getText());
String descripcion = txtdescripcion.getText();
String divisa = txtdivisa.getText();

// Realizar la consulta directa
try {
    // Obtener la conexión desde la clase Connection
    Connection connection = Conection.getConexion();

    // Construir la consulta SQL para llamar al procedimiento almacenado
    String query = "{CALL InsertarIngreso(?, ?, ?, ?, ?, ?)}";

    // Crear el CallableStatement y establecer los parámetros
    CallableStatement statement = connection.prepareCall(query);
    statement.setString(1, nombreAnterior);
    statement.setString(2, contraseñaAnterior);
    statement.setDouble(3, cantidad);
    statement.setDate(4, fecha);
    statement.setString(5, descripcion);
    statement.setString(6, divisa);

    // Ejecutar la consulta
    statement.execute();

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(null, "Ingreso registrado correctamente.");

    // Cerrar los recursos
    statement.close();
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
}

    
    
    
    
    }
    
    
    public void mostrartablaingresos(){
        
    /*    String nombreAnterior = DatosCompartidos.name;
        String contraseñaAnterior = DatosCompartidos.pass;
        try {
    // Obtener la conexión desde la clase Connection
    Connection connection = Conection.getConexion();

    // Construir la consulta SQL
    String query = "SELECT * FROM Ingreso INNER JOIN CrearUsuario ON Ingreso.IdUsuario = CrearUsuario.IdUsuario WHERE CrearUsuario.Nombres = ? AND CrearUsuario.Contraseña = ?";

    // Crear el PreparedStatement y establecer los parámetros
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, nombreAnterior);
    statement.setString(2, contraseñaAnterior);

    // Ejecutar la consulta y obtener los resultados
    ResultSet resultSet = statement.executeQuery();

    // Obtener el modelo de la tabla
    DefaultTableModel model = (DefaultTableModel) TablaIngresos.getModel();

    // Limpiar el contenido de la tabla
    model.setRowCount(0);

    // Recorrer los resultados y agregar las filas al modelo
    while (resultSet.next()) {
        Object[] row = new Object[5];
        row[0] = resultSet.getInt("Id_Ingreso");
        row[1] = resultSet.getDouble("Cantidad");
        row[2] = resultSet.getDate("Fecha");
        row[3] = resultSet.getString("Descripcion_Ingreso");
        row[4] = resultSet.getString("Divisa");
        model.addRow(row);
    }

    // Cerrar los recursos
    resultSet.close();
    statement.close();
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
}
*/
    
    
     String nombreAnterior = DatosCompartidos.name;
        String contraseñaAnterior = DatosCompartidos.pass;
    try {
    // Obtener la conexión desde la clase Connection
    Connection connection = Conection.getConexion();

    // Construir la consulta SQL
    String query = "SELECT * FROM Ingreso WHERE IdUsuario IN (SELECT IdUsuario FROM CrearUsuario WHERE Nombres = ? AND Contraseña = ?)";

    // Crear el PreparedStatement y establecer los parámetros
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, nombreAnterior);
    statement.setString(2, contraseñaAnterior);

    // Ejecutar la consulta y obtener los resultados
    ResultSet resultSet = statement.executeQuery();

    // Obtener el modelo de la tabla
    DefaultTableModel model = (DefaultTableModel) TablaIngresos.getModel();

    // Limpiar el contenido de la tabla
    model.setRowCount(0);

    // Recorrer los resultados y agregar las filas al modelo
    while (resultSet.next()) {
        Object[] row = new Object[5];
        row[0] = resultSet.getInt("Id_Ingreso");
        row[1] = resultSet.getDouble("Cantidad");
        row[2] = resultSet.getDate("Fecha");
        row[3] = resultSet.getString("Descripcion_Ingreso");
        row[4] = resultSet.getString("Divisa");
        model.addRow(row);
    }

    // Cerrar los recursos
    resultSet.close();
    statement.close();
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
}


    
    
    
    }
    
    
    
    
  public void tablaaa(){
      
      try {
    // Obtener la conexión desde la clase Connection
    Connection connection = Conection.getConexion();

    // Obtener los metadatos de la tabla Ingreso
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet resultSet = metaData.getColumns(null, null, "Ingreso", null);

    // Crear una lista para almacenar los nombres de columna
    List<String> columnNames = new ArrayList<>();

    // Obtener los nombres de columna de la tabla Ingreso
    while (resultSet.next()) {
        String columnName = resultSet.getString("COLUMN_NAME");
        columnNames.add(columnName);
    }

    // Crear un modelo de tabla personalizado con los nombres de columna
    DefaultTableModel model = new DefaultTableModel(columnNames.toArray(), 0) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Obtener los tipos de datos de las columnas de la tabla Ingreso
            try {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnType = metaData.getColumnType(columnIndex + 1);

                // Devolver la clase correspondiente al tipo de dato de la columna
                switch (columnType) {
                    case Types.INTEGER:
                        return Integer.class;
                    case Types.DOUBLE:
                        return Double.class;
                    case Types.DATE:
                        return Date.class;
                    // Agregar más tipos de datos según sea necesario
                    default:
                        return Object.class;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return Object.class;
            }
        }
    };

    // Establecer el modelo de tabla en la JTable
    TablaIngresos.setModel(model);

    // Realizar la consulta para obtener los datos de la tabla Ingreso
    String query = "SELECT * FROM Ingreso WHERE IdUsuario IN (SELECT IdUsuario FROM CrearUsuario WHERE Nombres = ? AND Contraseña = ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, nombreAnterior);
    statement.setString(2, contraseñaAnterior);
    ResultSet dataResultSet = statement.executeQuery();

    // Agregar los datos de la consulta al modelo de tabla
    while (dataResultSet.next()) {
        Object[] rowData = new Object[columnNames.size()];
        for (int i = 0; i < columnNames.size(); i++) {
            rowData[i] = dataResultSet.getObject(i + 1);
        }
        model.addRow(rowData);
    }

    // Cerrar los recursos
    resultSet.close();
    statement.close();
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        txtdivisa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnregistraringreso = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaIngresos = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar Ingreso");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cantidad");

        txtcantidad.setText(" ");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Descripción");

        txtdescripcion.setText(" ");
        txtdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescripcionActionPerformed(evt);
            }
        });

        txtdivisa.setText(" ");
        txtdivisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdivisaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Divisa");

        btnregistraringreso.setText("Registrar");
        btnregistraringreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistraringresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnregistraringreso)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtdivisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(txtdescripcion, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(82, 82, 82)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtdivisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnregistraringreso)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel7.setText("INGRESOS");

        jButton3.setText("Mostrar Ingresos ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        TablaIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TablaIngresos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(178, 178, 178)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButton2)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionActionPerformed

    private void txtdivisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdivisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdivisaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        dispose();
    
    // Abrir la ventana anterior (ventana anteriormente abierta)
    Home ventanaAnterior = new Home();
    ventanaAnterior.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnregistraringresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistraringresoActionPerformed
        // TODO add your handling code here:
             
        ingresosinsertar();
             tablabonita();
    }//GEN-LAST:event_btnregistraringresoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     tablabonita();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private void tablabonita(){
        
        
        String nombreAnterior = DatosCompartidos.name;
        String contraseñaAnterior = DatosCompartidos.pass;

// Realizar la consulta para obtener los datos relacionados
String query = "SELECT * FROM Ingreso WHERE IdUsuario IN (SELECT IdUsuario FROM CrearUsuario WHERE Nombres = ? AND Contraseña = ?)";
try {
    Connection connection = Conection.getConexion();
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, nombreAnterior);
    statement.setString(2, contraseñaAnterior);
    ResultSet resultSet = statement.executeQuery();

    // Crear un modelo de tabla
    DefaultTableModel model = new DefaultTableModel();

    // Configurar las columnas y encabezados
    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnCount = metaData.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
        model.addColumn(metaData.getColumnName(i));
    }

    // Recorrer los resultados y agregar las filas al modelo
    while (resultSet.next()) {
        Object[] row = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            row[i - 1] = resultSet.getObject(i);
        }
        model.addRow(row);
    }

    // Asignar el modelo a la tabla
    TablaIngresos.setModel(model);

    // Cerrar los recursos
    resultSet.close();
    statement.close();
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
}

    
    
    
    
    
    
    }
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
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaIngresos;
    private javax.swing.JButton btnregistraringreso;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtdivisa;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables
}
