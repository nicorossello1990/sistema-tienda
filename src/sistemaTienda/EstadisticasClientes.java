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
public class EstadisticasClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form EstadisticasClientes
     */
   Principal p;

    EstadisticasClientes(Principal p) {
        this.p = p;
        initComponents();
        Dominio.centrarDatos(tclientes);
        this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));   
        cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cantidad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalGanancias = new javax.swing.JTextField();
        totalPagaron = new javax.swing.JTextField();
        totalCompras = new javax.swing.JTextField();
        totalCantCompras = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tclientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        gastan = new javax.swing.JRadioButton();
        ganancias = new javax.swing.JRadioButton();
        nombre = new javax.swing.JRadioButton();
        pagaron = new javax.swing.JRadioButton();
        compras = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ESTADÍSTICAS DE CLIENTES");
        setToolTipText("");
        setAutoscrolls(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/excel.png"))); // NOI18N
        jButton1.setText("Exportar a Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Clientes Deudores"));

        cantidad.setText("Cantidad de Facturas de Ventas: 0");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("TOTAL: ");

        totalGanancias.setEnabled(false);

        totalPagaron.setEnabled(false);

        totalCompras.setEnabled(false);
        totalCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalComprasActionPerformed(evt);
            }
        });

        totalCantCompras.setEnabled(false);
        totalCantCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCantComprasActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        tclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Total Cant. Compras", "Total Compras", "Total Ganancias", "Total Pagos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cantidad)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(totalCantCompras)
                        .addGap(48, 48, 48)
                        .addComponent(totalCompras)
                        .addGap(43, 43, 43)
                        .addComponent(totalGanancias)
                        .addGap(47, 47, 47)
                        .addComponent(totalPagaron)
                        .addGap(53, 53, 53))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(348, Short.MAX_VALUE)
                .addComponent(cantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalGanancias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalPagaron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalCantCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGap(56, 56, 56)))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenar por"));

        buttonGroup1.add(gastan);
        gastan.setText("Mas gastan");
        gastan.setAutoscrolls(true);
        gastan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gastanActionPerformed(evt);
            }
        });

        buttonGroup1.add(ganancias);
        ganancias.setText("Mas ganancias producen");
        ganancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gananciasActionPerformed(evt);
            }
        });

        buttonGroup1.add(nombre);
        nombre.setSelected(true);
        nombre.setText("Nombre de Cliente");
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        buttonGroup1.add(pagaron);
        pagaron.setText("Mas pagaron");
        pagaron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagaronActionPerformed(evt);
            }
        });

        buttonGroup1.add(compras);
        compras.setText("Mas cantidad compras");
        compras.setAutoscrolls(true);
        compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(nombre)
                .addGap(53, 53, 53)
                .addComponent(compras)
                .addGap(44, 44, 44)
                .addComponent(gastan)
                .addGap(43, 43, 43)
                .addComponent(ganancias)
                .addGap(38, 38, 38)
                .addComponent(pagaron)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagaron)
                    .addComponent(ganancias)
                    .addComponent(gastan)
                    .addComponent(compras)
                    .addComponent(nombre))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
      public void cargar(){
          
      tclientes.getColumnModel().getColumn(1).setCellRenderer(new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, final Object value, boolean arg2,
                boolean arg3, int arg4, int arg5) {
            final JLabel lab = new JLabel("<html><a href=\"" + value + "\">" + value + "</a>");
            return lab;
        }
    }); 
    String []Registros=new String[6];
    DefaultTableModel model = (DefaultTableModel) tclientes.getModel();
    model = Dominio.eliminarTabla(model); 
        try {
              String consulta="";
              ResultSet rsfactura = null;
              String ordenar="";
                  if (gastan.isSelected()){
                      ordenar=" order by total desc";
                  }else{
                     if (ganancias.isSelected()){
                        ordenar=" order by ganancias desc";
                     }else{
                        if (pagaron.isSelected()){
                             ordenar=" order by pagados desc";
                         }else{
                            if (compras.isSelected()){
                               ordenar=" order by cantidad desc";
                            }else{  
                             if (nombre.isSelected()){
                               ordenar=" order by c.nombre asc";
                              }
                            } 
                        }
                        
                   }
                   }                
                  consulta+="select c.nombre, count(*) as cantidad ,sum(fac.total) as total, sum(fac.total) - (select sum(f.costo) from fa as f where f.id_factura in"
                          + " (select id_factura from facturas as fact where fact.eliminado='0' and fact.id_mcliente in (select cl.id_mcliente from misclientes"
                          + "  as cl where c.nombre=cl.nombre))) as ganancias, sum(fac.entregado) as pagados  "
                          + "from facturas as fac inner join misclientes as c on c.id_mcliente=fac.id_mcliente where fac.eliminado='0' group by c.nombre "+ordenar;                                
              rsfactura = this.p.bd.mostrarEstadisticasClientes(consulta); //toma fila por fila 
              float total=0,ganancias=0,pagaron=0;
              int cantidadCompras=0;
              while(rsfactura.next())
              {                
                  Registros[0]= rsfactura.getString(1); //nombre  
                  Registros[1]= rsfactura.getString(2);   //cantidadCompras
                  Registros[2]= Dominio.A2Decimales(rsfactura.getString(3)); //compras
                  Registros[3]= Dominio.A2Decimales(rsfactura.getString(4));  //ganancias
                  Registros[4]= Dominio.A2Decimales(rsfactura.getString(5));  //pagaron
                  total+= Float.parseFloat(Registros[2]);
                  ganancias+= Float.parseFloat(Registros[3]);
                  pagaron+= Float.parseFloat(Registros[4]);
                  cantidadCompras+= Integer.parseInt(Registros[1]);
                  model.addRow(Registros);      
              }
              tclientes.setModel(model);
              totalCompras.setText(Dominio.A2Decimales(Float.toString(total)));
              totalGanancias.setText(Dominio.A2Decimales(Float.toString(ganancias)));
              totalPagaron.setText(Dominio.A2Decimales(Float.toString(pagaron)));
              totalCantCompras.setText(Integer.toString(cantidadCompras));
            
        } catch (SQLException ex) {
            Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
    cantidad.setText("Cantidad de Clientes: "+tclientes.getRowCount());
     }
         
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
                filaTitulo.createCell(1).setCellValue("Total Cant. Compras");
                filaTitulo.createCell(2).setCellValue("Total Compras");
                filaTitulo.createCell(3).setCellValue("Total Ganancias");
                filaTitulo.createCell(4).setCellValue("Total Pagos");
                XSSFRow filasDatos;
                int i;
                for (i=0;i<tclientes.getRowCount();i++){
                    filasDatos = hoja.createRow((i+2));
                    for (int j=0;j<tclientes.getColumnCount();j++){
                        filasDatos.createCell(j).setCellValue(tclientes.getValueAt(i, j).toString());
                    }
                }
                XSSFRow filaFinal = hoja.createRow(i+3); //Creo una fila
                filaFinal.createCell(0).setCellValue("TOTAL");
                filaFinal.createCell(1).setCellValue(totalCantCompras.getText());
                filaFinal.createCell(2).setCellValue(totalCompras.getText());
                filaFinal.createCell(3).setCellValue(totalGanancias.getText());
                filaFinal.createCell(4).setCellValue(totalPagaron.getText());
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
                fc.cargar("", null, null, null);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(EstadisticasClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tclientesMouseClicked

    private void tclientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseEntered

    }//GEN-LAST:event_tclientesMouseEntered

    private void tclientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tclientesMouseExited

    }//GEN-LAST:event_tclientesMouseExited

    private void gastanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastanActionPerformed
        cargar();
    }//GEN-LAST:event_gastanActionPerformed

    private void gananciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gananciasActionPerformed
        cargar();
    }//GEN-LAST:event_gananciasActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        cargar();
    }//GEN-LAST:event_nombreActionPerformed

    private void pagaronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagaronActionPerformed
        cargar();
    }//GEN-LAST:event_pagaronActionPerformed

    private void totalCantComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCantComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCantComprasActionPerformed

    private void comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprasActionPerformed
        cargar();
    }//GEN-LAST:event_comprasActionPerformed

    private void totalComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalComprasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cantidad;
    private javax.swing.JRadioButton compras;
    private javax.swing.JRadioButton ganancias;
    private javax.swing.JRadioButton gastan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton nombre;
    private javax.swing.JRadioButton pagaron;
    private javax.swing.JTable tclientes;
    private javax.swing.JTextField totalCantCompras;
    private javax.swing.JTextField totalCompras;
    private javax.swing.JTextField totalGanancias;
    private javax.swing.JTextField totalPagaron;
    // End of variables declaration//GEN-END:variables
}
