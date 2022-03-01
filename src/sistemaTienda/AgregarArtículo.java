/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemaTienda;

import claseConectar.Dominio;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ezenic
 */
public class AgregarArtículo extends javax.swing.JInternalFrame {
    private DefaultTableModel model;

    Principal p;
    String id_factura;
    FacturaVenta factura=null; //Al agregar nueva factura
    DetalleFacturaVenta detfactura=null; //Al modificar factura
    
    public AgregarArtículo(Principal p, FacturaVenta factura) {  
        this.p = p;
        this.factura = factura;
        initComponents();
        configuracionEstandarComponentes();
        cargar("");
        this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));   

    }
    
    //Para detalleFacturaVenta
    public AgregarArtículo(Principal p, DetalleFacturaVenta detfactura, String id_factura) {
        this.p = p;
        this.id_factura=id_factura;
        this.detfactura = detfactura;
        initComponents();
        configuracionEstandarComponentes();
        cargar("");
        this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));   

    }
    
    private void configuracionEstandarComponentes() {
        UsuarioLogueado usuarioLogueado = this.p.usuarioLogueado;
          if (!usuarioLogueado.getPermisos().contains("PUEDE CARGAR ARTICULOS"))
              cargarNuevoArticulo.setVisible(false);      
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        mostrar = new javax.swing.JButton();
        buscar_nombre = new javax.swing.JTextField();
        descripcionRadio = new javax.swing.JRadioButton();
        marcaRadio = new javax.swing.JRadioButton();
        cargarNuevoArticulo = new javax.swing.JButton();
        codigoRadio = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tproductos = new javax.swing.JTable();
        cantidad = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("AGREGAR ARTÏCULOS");
        setToolTipText("");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Producto"));

        mostrar.setText("Mostrar Todos");
        mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarActionPerformed(evt);
            }
        });

        buscar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_nombreActionPerformed(evt);
            }
        });
        buscar_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar_nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar_nombreKeyTyped(evt);
            }
        });

        buttonGroup1.add(descripcionRadio);
        descripcionRadio.setText("Buscar por Descripción");
        descripcionRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(marcaRadio);
        marcaRadio.setText("Buscar por Marca ");
        marcaRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaRadioActionPerformed(evt);
            }
        });

        cargarNuevoArticulo.setText("Cargar Nuevo Artículo");
        cargarNuevoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarNuevoArticuloActionPerformed(evt);
            }
        });

        buttonGroup1.add(codigoRadio);
        codigoRadio.setSelected(true);
        codigoRadio.setText("Buscar por Código");
        codigoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionRadio)
                            .addComponent(marcaRadio))
                        .addGap(18, 18, 18)
                        .addComponent(buscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(codigoRadio))
                .addGap(108, 108, 108)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(cargarNuevoArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(codigoRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descripcionRadio)
                    .addComponent(buscar_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(marcaRadio)
                .addGap(17, 17, 17))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(mostrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cargarNuevoArticulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Productos sin agregar"));

        tproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Artículo", "Descripción", "Marca", "Tipo", "Precio Efectivo", "Precio Tarjeta (20%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tproductos.getTableHeader().setReorderingAllowed(false);
        tproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tproductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tproductos);

        cantidad.setText("Cantidad de artículos sin agregar: 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cantidad)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidad)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
        void cargar(String valor) {
        try{

            String [] registros= new String[6];
            model= (DefaultTableModel) tproductos.getModel();
            model = Dominio.eliminarTabla(model); 
            String consulta ="";                                
            if (codigoRadio.isSelected()){
                consulta="select a.id_articulo, a.nombre, a.marca, ta.nombre, a.precio_venta, a.precio_venta_tarjeta from articulos as a inner join tipo_articulo as ta on ta.id_tipo=a.id_tipo  WHERE CONCAT(a.id_articulo) LIKE '%"+valor+"%' and a.eliminado='0' order by a.id_articulo asc";              
            }
            if (descripcionRadio.isSelected()){
              consulta="select a.id_articulo, a.nombre, a.marca, ta.nombre, a.precio_venta, a.precio_venta_tarjeta from articulos as a inner join tipo_articulo as ta on ta.id_tipo=a.id_tipo  WHERE lower(a.nombre) LIKE '%"+valor.toLowerCase()+"%' and a.eliminado='0' order by a.id_articulo asc";       
            }
            if (marcaRadio.isSelected()){
              consulta="select a.id_articulo, a.nombre, a.marca, ta.nombre, a.precio_venta, a.precio_venta_tarjeta from articulos as a inner join tipo_articulo as ta on ta.id_tipo=a.id_tipo  WHERE lower(a.marca) LIKE '%"+valor.toLowerCase()+"%' and a.eliminado='0' order by a.id_articulo asc";       
            }
            ResultSet rs = this.p.bd.listarArticulos(consulta);
            String id_articulo,stock;
            while(rs.next()){
                    registros[0]=rs.getString(1);  //id_articulo   
                    registros[1]=rs.getString(2); //nombre
                    registros[2]=rs.getString(3);  //Marca
                    registros[3]=rs.getString(4); //Tipo de articulo
                    registros[4]=Dominio.A2Decimales(rs.getString(5));  //Precio efectivo
                    registros[5]=Dominio.A2Decimales(rs.getString(6));  //Precio tarjeta                   
                    model.addRow(registros);                                                   
                }
            tproductos.setModel(model);
            }catch(Exception e){
                System.out.println(e.getMessage());
                 }
          cantidad.setText("Cantidad de Productos sin agregar: "+tproductos.getRowCount());
     
    }

    
    
    
    private void mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarActionPerformed
        cargar("");
    }//GEN-LAST:event_mostrarActionPerformed

    private void buscar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_nombreActionPerformed

    }//GEN-LAST:event_buscar_nombreActionPerformed

    private void buscar_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_nombreKeyReleased
         cargar(buscar_nombre.getText());
    }//GEN-LAST:event_buscar_nombreKeyReleased

    private void buscar_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_nombreKeyTyped
   

          
    }//GEN-LAST:event_buscar_nombreKeyTyped

    private void descripcionRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripcionRadioActionPerformed

    }//GEN-LAST:event_descripcionRadioActionPerformed

    private void marcaRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaRadioActionPerformed

    private void cargarNuevoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarNuevoArticuloActionPerformed
        try {
            Articulos pro = new Articulos(this.p,this);
            this.add(pro);
            this.p.escritorio.add(pro);
            pro.show();
            pro.setMaximum(true);// TODO add your handling code here:
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AgregarArtículo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cargarNuevoArticuloActionPerformed

    private void codigoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoRadioActionPerformed

    
    boolean validateStock(AgregarArticuloPorTalle tallesDeArticulos){
        if (this.factura != null && this.factura.getComprobante().getSelectedItem().toString().equals(Constantes.NOTA_DE_CREDITO)){
            return true;
        }
        if (this.detfactura != null && this.detfactura.getComprobante().getSelectedItem().toString().equals(Constantes.NOTA_DE_CREDITO)){
            return true;
        }
        
        int filas = tallesDeArticulos.getTableTalles().getRowCount();
        String talle = "", cantidad;
        try{
            for (int j=0;j<filas;j++){       
               int stock = Integer.parseInt(tallesDeArticulos.getModel().getValueAt(j, 1).toString());                                               
               cantidad = tallesDeArticulos.getModel().getValueAt(j, 2) != null && !tallesDeArticulos.getModel().getValueAt(j, 2).toString().isEmpty()? tallesDeArticulos.getModel().getValueAt(j, 2).toString() : "0";                    
               talle = tallesDeArticulos.getModel().getValueAt(j, 0).toString();
               if (Integer.parseInt(cantidad) > stock){
                   JOptionPane.showMessageDialog(this,"Stock insuficiente para el talle " + talle,"Advertencia", JOptionPane.WARNING_MESSAGE);
                   return false;
               }
            }
         }catch(NumberFormatException ex){ // handle your exception
                  JOptionPane.showMessageDialog(this,"Error en la cantidad ingresada para el talle "+ talle+". Ingrese números enteros","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje 
            return false;
         }
        return true;
         
    }
    
    private void tproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tproductosMouseClicked
        if (evt.getClickCount()==2 && !evt.isConsumed()){
           model =  (DefaultTableModel) tproductos.getModel();
           int[] arrayElementos = tproductos.getSelectedRows();
           String nombre;
           for (int i=0; i< tproductos.getSelectedRowCount();i++){
               int fila = arrayElementos[i];           
               String idArticulo = model.getValueAt(fila,0).toString();
               nombre = model.getValueAt(fila,1).toString();
               AgregarArticuloPorTalle tallesDeArticulos = null;
               if (this.factura !=null){
                    tallesDeArticulos = new AgregarArticuloPorTalle(this.p, idArticulo, nombre, this.factura);
               }
                if (this.detfactura !=null){
                    tallesDeArticulos = new AgregarArticuloPorTalle(this.p, idArticulo, nombre, this.detfactura);
               } 
               
               if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null, tallesDeArticulos, "Control de Stock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "default")){
                   int filas = tallesDeArticulos.getTableTalles().getRowCount();  
                   if (validateStock(tallesDeArticulos)){
                     for (int j=0;j<filas;j++){                      
                            String cantidad = tallesDeArticulos.getModel().getValueAt(j, 2) != null && !tallesDeArticulos.getModel().getValueAt(j, 2).toString().isEmpty()? tallesDeArticulos.getModel().getValueAt(j, 2).toString() : "0";                    
                            if (Integer.parseInt(cantidad) > 0){
                                 String marca = model.getValueAt(fila,2).toString();
                                 String tipo = model.getValueAt(fila, 3).toString();
                                 String precioEfectivo = model.getValueAt(fila, 4).toString();
                                 String precioTarjeta =  model.getValueAt(fila, 5).toString();                                 
                                 String talle = tallesDeArticulos.getModel().getValueAt(j, 0).toString();                               
                                 if (this.factura !=null){
                                    this.factura.ActualizarTablaArticulos(idArticulo,nombre, marca,tipo, talle,cantidad,precioEfectivo,precioTarjeta);
                                 }
                                 if (this.detfactura !=null){
                                    this.detfactura.ActualizarTablaArticulos(idArticulo,nombre, marca,tipo, talle,cantidad,precioEfectivo,precioTarjeta);
                                }   
                        }                                          
                    }       
                   }                 
               }
            cargar("");
         }
        }
        
    }//GEN-LAST:event_tproductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_nombre;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cantidad;
    private javax.swing.JButton cargarNuevoArticulo;
    private javax.swing.JRadioButton codigoRadio;
    private javax.swing.JRadioButton descripcionRadio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton marcaRadio;
    private javax.swing.JButton mostrar;
    private javax.swing.JTable tproductos;
    // End of variables declaration//GEN-END:variables

}
