/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemaTienda;

import claseConectar.Dominio;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ezenic
 */
public class Usuarios extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    Principal p;
    
    public Usuarios( Principal p) {
       
            initComponents();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            tusuarios.setDefaultRenderer(String.class, centerRenderer);
            
            this.p = p;
            cargar("");  
            this.setLocation((p.getSize().width/2)-(this.getWidth()/2), (p.getSize().height/2)-(this.getHeight()/2));
    

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        mostrar = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        crollpane = new javax.swing.JScrollPane();
        tusuarios = new javax.swing.JTable();
        cantidad = new javax.swing.JLabel();
        panelUsuario = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        nombreLabel = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        paswordLabel = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        repitePasswordLabel = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        rol = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        repitePassword = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setTitle("REGISTRO DE USUARIOS");
        setName(""); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        mostrar.setText("Mostrar Todos");
        mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarActionPerformed(evt);
            }
        });

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        jLabel2.setText("BUSCAR");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Usuarios"));

        tusuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Usuario", "Nombre de Usuario", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tusuarios.getTableHeader().setReorderingAllowed(false);
        tusuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tusuariosMouseMoved(evt);
            }
        });
        tusuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tusuariosMouseClicked(evt);
            }
        });
        tusuarios.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tusuariosComponentShown(evt);
            }
        });
        crollpane.setViewportView(tusuarios);

        cantidad.setText("Cantidad de Clientes: 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crollpane)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cantidad)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(crollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidad)
                .addContainerGap())
        );

        cantidad.getAccessibleContext().setAccessibleName("Cantidad de Usuarios: 0");

        panelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Usuario"));

        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        nombreLabel.setText("Nombre de usuario");

        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setEnabled(false);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        paswordLabel.setText("Contraseña");

        nombre.setColumns(5);
        nombre.setEnabled(false);
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });

        repitePasswordLabel.setText("Repite Contraseña");

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Save).jpg"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setEnabled(false);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Save).jpg"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setEnabled(false);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modificar.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "EMPLEADO" }));
        rol.setEnabled(false);

        jLabel5.setText("Rol");

        password.setEnabled(false);
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordKeyTyped(evt);
            }
        });

        repitePassword.setEnabled(false);
        repitePassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                repitePasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                repitePasswordKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelUsuarioLayout = new javax.swing.GroupLayout(panelUsuario);
        panelUsuario.setLayout(panelUsuarioLayout);
        panelUsuarioLayout.setHorizontalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUsuarioLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreLabel)
                            .addComponent(paswordLabel)
                            .addComponent(repitePasswordLabel))
                        .addGap(18, 18, 18)
                        .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(password)
                            .addComponent(repitePassword))))
                .addGap(49, 49, 49)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(rol, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modificar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addComponent(actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        panelUsuarioLayout.setVerticalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuarioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUsuarioLayout.createSequentialGroup()
                        .addComponent(guardar)
                        .addGap(13, 13, 13)
                        .addComponent(eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actualizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelUsuarioLayout.createSequentialGroup()
                        .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreLabel)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paswordLabel)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(repitePasswordLabel)
                            .addComponent(repitePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevo)
                            .addComponent(cancelar)
                            .addComponent(modificar))))
                .addContainerGap())
        );

        nombreLabel.getAccessibleContext().setAccessibleName("Nombre de Usuario");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mostrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mostrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel2.getAccessibleContext().setAccessibleName("Lista de Usuarios");
        jPanel2.getAccessibleContext().setAccessibleDescription("Lista de Usuarios");
        panelUsuario.getAccessibleContext().setAccessibleName("Registrar Usuario");
        panelUsuario.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("REGISTROS DE USUARIOS");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void principal(){
      nuevo.setEnabled(true);
      nombre.setEnabled(false);
      nombre.setText("");
      password.setText("");
      repitePassword.setText("");
      password.setEnabled(false);
      guardar.setEnabled(false);
      repitePassword.setEnabled(false);
      buscar.setEnabled(true);
      mostrar.setEnabled(true);
      modificar.setEnabled(true);
      eliminar.setEnabled(true);
      actualizar.setEnabled(false);
      rol.setEnabled(false);
      tusuarios.setEnabled(true);
      cancelar.setEnabled(false);
    }
    
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    
    
    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
          cancelar.setEnabled(true);
          nuevo.setEnabled(false);
          guardar.setEnabled(true);
          nombre.requestFocus();
          nombre.setEnabled(true);
          nombre.setText("");
          password.setEnabled(true);
          rol.setEnabled(true);
          password.setText("");
          repitePassword.setEnabled(true);
          repitePassword.setText("");
          tusuarios.setEnabled(false);
          eliminar.setEnabled(false);
          modificar.setEnabled(false);
          buscar.setEnabled(false);
          mostrar.setEnabled(false);

    
        
    }//GEN-LAST:event_nuevoActionPerformed

     private  void cargar(String valor){
    String []Registros=new String[5];
    model= (DefaultTableModel) tusuarios.getModel();
    model = Dominio.eliminarTabla(model); 
        try {             
              ResultSet rs = this.p.bd.listarDatosMisUsuarios(valor);
              while(rs.next())
              {
                  Registros[0]= rs.getString("id_user");
                  Registros[1]= rs.getString("nombre");   
                  Registros[2]= rs.getString("rol");   
                  model.addRow(Registros);
              }
              tusuarios.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    cantidad.setText("Cantidad de Usuarios: "+tusuarios.getRowCount());
   // tclientes = Dominio.centrarDatos(tclientes);
    
     
  }
    

    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
       nuevoUsuario(); 
    }//GEN-LAST:event_guardarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
      cargar(buscar.getText()) ;      // TODO add your handling code here:
    }//GEN-LAST:event_buscarKeyReleased

    private void mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarActionPerformed
      cargar("");        // TODO add your handling code here:
    }//GEN-LAST:event_mostrarActionPerformed

    private void tusuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tusuariosMouseClicked
       if (evt.getClickCount()==2){
           modificarUsuario();
       }                                 
    }//GEN-LAST:event_tusuariosMouseClicked

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
          principal();
    }//GEN-LAST:event_cancelarActionPerformed

    private void tusuariosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tusuariosComponentShown

    }//GEN-LAST:event_tusuariosComponentShown

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
      
        if (tusuarios.getSelectedRow()== -1) {
           JOptionPane.showMessageDialog(this,"No ha seleccionado ningún usuario","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
       }else{
            if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, "Desea eliminar al usuario seleccionado?" )){
              int fila= tusuarios.getSelectedRow();
              String cod="";
              cod=tusuarios.getValueAt(fila, 0).toString();        
              int n=this.p.bd.eliminarUsuario(cod);
                if(n>0){
                    JOptionPane.showMessageDialog(this, "Usuario eliminado con Exito.");
                    cargar("");
              
                }else{
                    JOptionPane.showMessageDialog(this, "No se ha podido eliminar al usuario.");
                }   
                principal();
        } 
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void modificarUsuario(){
         if (tusuarios.getSelectedRow()== -1) {
           JOptionPane.showMessageDialog(this,"No ha seleccionado ningún usuario","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
       }else{
              cancelar.setEnabled(true);
              nuevo.setEnabled(false);
              guardar.setEnabled(true);
              nombre.requestFocus();
              password.setEnabled(true);
              nombre.setEnabled(true);
              rol.setEnabled(true);
              repitePassword.setEnabled(true);
              tusuarios.setEnabled(false);
              eliminar.setEnabled(false);
              guardar.setEnabled(false);
              modificar.setEnabled(false);
              actualizar.setEnabled(true);
              buscar.setEnabled(false);
              mostrar.setEnabled(false);
              int fila= tusuarios.getSelectedRow(); 
              String nombreUser =  tusuarios.getValueAt(fila, 1).toString();
              String rol =  tusuarios.getValueAt(fila, 2).toString();
              this.nombre.setText(nombreUser);  
              this.rol.setSelectedItem(rol);
        }
    }
    
    
    
    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
          modificarUsuario();
    }//GEN-LAST:event_modificarActionPerformed

    
   private void actualizarUsuario(){
      if (validarDatos()){
              int fila= tusuarios.getSelectedRow();
              String cod="";
              cod=tusuarios.getValueAt(fila, 0).toString(); 
              int n=this.p.bd.modificarUsuario(nombre.getText(),password.getText(),rol.getSelectedItem().toString(),cod,this);
              if(n>0){
                 JOptionPane.showMessageDialog(null, "Usuario modificado con Exito.");
                 cargar("");
                 principal();
              }
        }
   }
    
    
    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
           actualizarUsuario();
    }//GEN-LAST:event_actualizarActionPerformed

    private boolean validarDatos(){
     boolean validar = false;
     String texto=nombre.getText();
     String password = String.valueOf(this.password.getPassword());
     String repitePassword = String.valueOf(this.repitePassword.getPassword());
     if(texto.length()==0 || password.length()==0 || repitePassword.length()==0){          
            JOptionPane.showMessageDialog(this,"los campos "+nombreLabel.getText() +", "+paswordLabel.getText() +" y "+repitePasswordLabel.getText()+" no deben ser vacio","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
      }
     else{
       if(!password.equals(repitePassword)){          
            JOptionPane.showMessageDialog(this,"Las contraseñas deben ser iguales","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
       }else
            validar=true;
       }
    return validar;
    } 
 
    
    private void nuevoUsuario() {
       if (validarDatos()){
           int n=this.p.bd.agregarMiUsuario(nombre.getText(),password.getText(),rol.getSelectedItem().toString(),this);
           if(n>0){
               JOptionPane.showMessageDialog(null, "Usuario Guardado con Exito");
               principal();
           }
           cargar(""); 
        }
    }
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
     if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
               actualizarUsuario();
            }
            if (guardar.isEnabled()){
                nuevoUsuario();
            }
}
    }//GEN-LAST:event_nombreKeyReleased

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
         if (nombre.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void tusuariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tusuariosMouseMoved
            int col = tusuarios.columnAtPoint(new Point(evt.getX(), evt.getY()));
            if (col == 1) {             
                tusuarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else{
                tusuarios.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }        // TODO add your handling code here:
    }//GEN-LAST:event_tusuariosMouseMoved

    private void passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyReleased
            if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
               actualizarUsuario();
            }
            if (guardar.isEnabled()){
                nuevoUsuario();
            }
}
    }//GEN-LAST:event_passwordKeyReleased

    private void passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyTyped
        if (String.valueOf(this.password.getPassword()).length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_passwordKeyTyped

    private void repitePasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_repitePasswordKeyReleased
             if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
               actualizarUsuario();
            }
            if (guardar.isEnabled()){
                nuevoUsuario();
            }
}
    }//GEN-LAST:event_repitePasswordKeyReleased

    private void repitePasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_repitePasswordKeyTyped
         if (String.valueOf(this.repitePassword.getPassword()).length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_repitePasswordKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JTextField buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel cantidad;
    private javax.swing.JScrollPane crollpane;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton modificar;
    private javax.swing.JButton mostrar;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JButton nuevo;
    private javax.swing.JPanel panelUsuario;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel paswordLabel;
    private javax.swing.JPasswordField repitePassword;
    private javax.swing.JLabel repitePasswordLabel;
    private javax.swing.JComboBox<String> rol;
    private javax.swing.JTable tusuarios;
    // End of variables declaration//GEN-END:variables




 
}