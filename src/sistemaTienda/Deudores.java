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
public class Deudores extends javax.swing.JInternalFrame {

    /**
     * Creates new form Deudores
     */
     Principal p;

    Deudores(Principal p) {
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
        jPanel1 = new javax.swing.JPanel();
        adeudan = new javax.swing.JRadioButton();
        moroso = new javax.swing.JRadioButton();
        canFacturas = new javax.swing.JRadioButton();
        nombre = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("DEUDORES");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Clientes Deudores"));
        jPanel2.setAutoscrolls(true);

        tclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Cantidad de Facturas Adeudadas", "Debe desde", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        cantidad.setText("Cantidad de Facturas de Ventas: 0");

        jLabel3.setText("TOTAL: ");

        tot.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 711, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(396, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cantidad))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addGap(35, 35, 35)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenar por"));

        buttonGroup1.add(adeudan);
        adeudan.setSelected(true);
        adeudan.setText("Mas adeudan");
        adeudan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adeudanActionPerformed(evt);
            }
        });

        buttonGroup1.add(moroso);
        moroso.setText("Mas moroso");
        moroso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                morosoActionPerformed(evt);
            }
        });

        buttonGroup1.add(canFacturas);
        canFacturas.setText("Mas facturas");
        canFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canFacturasActionPerformed(evt);
            }
        });

        buttonGroup1.add(nombre);
        nombre.setText("Nombre de Cliente");
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(adeudan)
                .addGap(18, 18, 18)
                .addComponent(moroso)
                .addGap(18, 18, 18)
                .addComponent(canFacturas)
                .addGap(18, 18, 18)
                .addComponent(nombre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adeudan)
                    .addComponent(moroso)
                    .addComponent(canFacturas)
                    .addComponent(nombre)))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/excel.png"))); // NOI18N
        jButton1.setText("Exportar a Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     public void cargar(){
        //Crea Hipervinculo en Columna Numero de Factura     
     tclientes.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, final Object value, boolean arg2,
                boolean arg3, int arg4, int arg5) {
            final JLabel lab = new JLabel("<html><a href=\"" + value + "\">" + value + "</a>");
            return lab;
        }
    });    
    String []Registros=new String[4];
    DefaultTableModel model = (DefaultTableModel) tclientes.getModel();
    model = Dominio.eliminarTabla(model); 
        try {
              String consulta="";
              ResultSet rsfactura = null;
              String ordenar="";
                  if (adeudan.isSelected()){
                      ordenar=" order by deudas desc";
                  }else{
                     if (moroso.isSelected()){
                        ordenar=" order by fecha_minima asc";
                     }else{
                         if (canFacturas.isSelected()){  
                            ordenar=" order by cantidad desc";
                      }else{
                        if (nombre.isSelected()){
                             ordenar=" order by c.nombre asc";
                         }
                        }
                   }
              }                
                  consulta+="SELECT c.nombre,count(*) as cantidad,  min(f.fecha) as fecha_minima, sum(f.total)-sum(f.entregado) as deudas"
                          + " from facturas as f inner join misclientes as c on c.id_mcliente=f.id_mcliente "
                          + "where estado='INPAGO' and lower(c.nombre) LIKE '%"+buscar.getText().toLowerCase()+"%' and f.eliminado='0' group by c.nombre "+ordenar;                                
              rsfactura = this.p.bd.mostrarDeudores(consulta); //toma fila por fila 
              float total=0;
              while(rsfactura.next())
              {                
                  Registros[0]= rsfactura.getString(1); //nombre  
                  Registros[1]= rsfactura.getString(2); //cantidad
                  Registros[2]= Dominio.formatofechaJCalender.format( Dominio.formatofechaBD.parse(rsfactura.getString(3))); //debe desde 
                  Registros[3]= Dominio.A2Decimales(rsfactura.getString(4));   //total
                  total+= Float.parseFloat(Registros[3]);
                  model.addRow(Registros);      
              }
              tclientes.setModel(model);
              tot.setText(Dominio.A2Decimales(Float.toString(total)));
          
            
        } catch (SQLException ex) {
            Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
             Logger.getLogger(Deudores.class.getName()).log(Level.SEVERE, null, ex);
         }
    cantidad.setText("Cantidad de Clientes Deudores: "+tclientes.getRowCount());
     }
     
    private void tclientesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseMoved

        int col = tclientes.columnAtPoint(new Point(evt.getX(), evt.getY()));
        if (col == 1) {
            tclientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else{
            tclientes.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_tclientesMouseMoved

    private void tclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseClicked

        int col = tclientes.columnAtPoint(new Point(evt.getX(), evt.getY()));
        if (col==1){
            try {
                int fila = tclientes.rowAtPoint(new Point(evt.getX(), evt.getY()));
                String nombre = (String) tclientes.getModel().getValueAt(fila, 0);
                FacturasCliente fc = new FacturasCliente(this,this.p,nombre);
                this.p.escritorio.add(fc);
                this.p.escritorio.setSelectedFrame(fc);
                fc.show();
                fc.setMaximum(true);
                fc.cargar(null, null, null, "INPAGO");
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Deudores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_tclientesMouseClicked

    private void tclientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseEntered

    }//GEN-LAST:event_tclientesMouseEntered

    private void tclientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseExited

    }//GEN-LAST:event_tclientesMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tclientes.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Error. La tabla esta vacía.", "Error", JOptionPane.ERROR_MESSAGE);
        }   else{
            //creamos un JFileChooser para elegir donde queremos guardar nuestro archivo
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar Archivo");
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                Thread t=  new Thread(){
                    public void run(){
                        JOptionPane.showMessageDialog(null, "Archivo creado con éxito", "Exportando a Excel. Por favor espere...", JOptionPane.INFORMATION_MESSAGE);
                    }
                };
                t.start();
                String file = chooser.getSelectedFile().toString().concat(".xlsx");
                XSSFWorkbook excel = new XSSFWorkbook(); //libro excel
                XSSFSheet hoja = excel.createSheet(); //Crea una hoj
                hoja.createRow(0); //Creo una fila
                XSSFRow filaTitulo = hoja.createRow(1); //Creo una fila
                filaTitulo.createCell(0).setCellValue("Cliente");
                filaTitulo.createCell(1).setCellValue("Cantidad de Facturas Adeudadas");
                filaTitulo.createCell(2).setCellValue("Debe desde");
                filaTitulo.createCell(3).setCellValue("Total");
                XSSFRow filasDatos;
                int i;
                for (i=0;i<tclientes.getRowCount();i++){
                    filasDatos = hoja.createRow((i+2));
                    for (int j=0;j<tclientes.getColumnCount();j++){
                        filasDatos.createCell(j).setCellValue(tclientes.getValueAt(i, j).toString());
                    }
                }
                XSSFRow filaFinal = hoja.createRow(i+3); //Creo una fila
                filaFinal.createCell(2).setCellValue("TOTAL");
                filaFinal.createCell(3).setCellValue(tot.getText());
                try {
                    excel.write(new FileOutputStream(file));
                    Desktop.getDesktop().open(new File(file));
                } catch (IOException ex) {
                    Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "No se pudo crear el Archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
                t.suspend();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void morosoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_morosoActionPerformed
        cargar();
    }//GEN-LAST:event_morosoActionPerformed

    private void adeudanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adeudanActionPerformed
        cargar();
    }//GEN-LAST:event_adeudanActionPerformed

    private void canFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canFacturasActionPerformed
        cargar();
    }//GEN-LAST:event_canFacturasActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        cargar();
    }//GEN-LAST:event_nombreActionPerformed

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
    private javax.swing.JRadioButton adeudan;
    private javax.swing.JTextField buscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton canFacturas;
    private javax.swing.JLabel cantidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton moroso;
    private javax.swing.JRadioButton nombre;
    private javax.swing.JTable tclientes;
    private javax.swing.JTextField tot;
    // End of variables declaration//GEN-END:variables
}
