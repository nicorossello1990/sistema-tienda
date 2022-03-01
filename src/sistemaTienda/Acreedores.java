/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaTienda;

import claseConectar.Dominio;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author nico_
 */
public class Acreedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form Deudores
     */
     Principal p;

    Acreedores(Principal p) {
        this.p = p;
        initComponents();
        Dominio.centrarDatos(tclientes);
        this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));   
        cargar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tclientes = new javax.swing.JTable();
        cantidad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tot = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ACREEDORES");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Clientes Acreedores"));
        jPanel2.setAutoscrolls(true);

        tclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Desde", "Saldo a Favor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tclientes.getTableHeader().setReorderingAllowed(false);
        tclientes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tclientesMouseMoved(evt);
            }
        });
        tclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tclientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tclientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tclientesMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(tclientes);

        cantidad.setText("Cantidad de Clientes Acreedores: 0");

        jLabel3.setText("TOTAL: ");

        tot.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 752, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1326, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(449, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidad))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addGap(35, 35, 35)))
        );

        jLabel1.setText("Buscar por nombre de Cliente");

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     public void cargar(){

    String []Registros=new String[3];
    DefaultTableModel model = (DefaultTableModel) tclientes.getModel();
    model = Dominio.eliminarTabla(model); 
        try {
              String consulta="";
              ResultSet rscliente = null;             
              consulta+="SELECT nombre, fecha_saldo, saldo_favor from misclientes where lower(nombre) LIKE '%"+buscar.getText().toLowerCase()+"%' and saldo_favor>0 ";                                
              rscliente = this.p.bd.mostrarAcreedores(consulta); //toma fila por fila 
              float total=0;
              while(rscliente.next())
              {                
                  Registros[0]= rscliente.getString(1); //nombre  
                  Registros[1]= Dominio.formatofechaJCalender.format( Dominio.formatofechaBD.parse(rscliente.getString(2))); //desde 
                  Registros[2]= Dominio.A2Decimales(rscliente.getString(3));   //total
                  total+= Float.parseFloat(Registros[2]);
                  model.addRow(Registros);      
              }
              tclientes.setModel(model);
              tot.setText(Dominio.A2Decimales(Float.toString(total)));
          
            
        } catch (SQLException ex) {
            Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
             Logger.getLogger(Deudores.class.getName()).log(Level.SEVERE, null, ex);
         }
    cantidad.setText("Cantidad de Clientes Acreedores: "+tclientes.getRowCount());
     }
     
    private void tclientesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseMoved

    }//GEN-LAST:event_tclientesMouseMoved

    private void tclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseClicked


    }//GEN-LAST:event_tclientesMouseClicked

    private void tclientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseEntered

    }//GEN-LAST:event_tclientesMouseEntered

    private void tclientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseExited

    }//GEN-LAST:event_tclientesMouseExited

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped
        if (buscar.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_buscarKeyTyped

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        cargar();
    }//GEN-LAST:event_buscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tclientes;
    private javax.swing.JTextField tot;
    // End of variables declaration//GEN-END:variables
}
