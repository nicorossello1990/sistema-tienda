/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemaTienda;

import claseConectar.Dominio;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ezenic
 */
public class DetalleFacturaVenta extends javax.swing.JInternalFrame implements OperacionesFacturas {

  Principal p;
  String id_factura;
  String nombreCliente;
  DefaultTableModel model;
  BuscarFacturaVenta factura = null;
  String numeroActual;

    DetalleFacturaVenta(BuscarFacturaVenta factura, Principal p, String id_factura) {
          initComponents();
          this.factura = factura;
          this.id_factura = id_factura;
          this.p = p;
          this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));
          this.mostrarDatos();    
          realizarCalculos();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        JMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        descuentoArticulo = new javax.swing.JTextField();
        motivoArticulo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
        jpanel = new javax.swing.JPanel();
        cliente = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comprobante = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        descuento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        forma = new javax.swing.JComboBox();
        calender = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        motivo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        saldo = new javax.swing.JTextField();
        nuevoPorcentajeTarjeta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        sucursal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tarticulos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        cantidad = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pagoCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        apagar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pagado = new javax.swing.JTextField();

        JMenuItem1.setText("Eliminar Artículo");
        JMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(JMenuItem1);

        jMenuItem2.setText("Modificar Cantidad");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        descuentoArticulo.setText("jTextField1");
        descuentoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoArticuloActionPerformed(evt);
            }
        });
        descuentoArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoArticuloKeyTyped(evt);
            }
        });

        motivoArticulo.setText("jTextField1");
        motivoArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                motivoArticuloKeyTyped(evt);
            }
        });

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("DETALLE FACTURA VENTA");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Save).jpg"))); // NOI18N
        registrar.setText("Actualizar Factura");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        jpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Factura"));

        cliente.setText("Cliente");

        numero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numero.setText("Número");

        jLabel3.setText("Comprobante");

        jLabel4.setText("Sucursal");

        comprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Nota de Crédito" }));
        comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprobanteActionPerformed(evt);
            }
        });

        jLabel7.setText("Descuento al Total (%)");

        descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descuentoKeyTyped(evt);
            }
        });

        jLabel8.setText("Motivo");

        jLabel9.setText("Forma de Pago");

        forma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarjeta" }));
        forma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formaActionPerformed(evt);
            }
        });

        calender.setDate(new Date());
        calender.setMinSelectableDate(new java.util.Date(-62135755099000L));

        motivo.setColumns(20);
        motivo.setLineWrap(true);
        motivo.setRows(5);
        motivo.setWrapStyleWord(true);
        motivo.setAutoscrolls(false);
        motivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                motivoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(motivo);

        jLabel1.setText("Saldo a favor");

        saldo.setEnabled(false);

        nuevoPorcentajeTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nuevoPorcentajeTarjetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nuevoPorcentajeTarjetaKeyTyped(evt);
            }
        });

        jLabel11.setText("%");

        jLabel12.setText("Fecha");

        sucursal.setText("S");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cliente)
                    .addComponent(numero)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nuevoPorcentajeTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sucursal))
                        .addGap(64, 64, 64)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(descuento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addComponent(numero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoPorcentajeTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(15, 15, 15)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(sucursal))
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Artículos"));

        tarticulos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Artículo", "Descripción", "Marca", "Tipo", "Talle", "Cantidad", "Precio Efectivo", "Precio Tarjeta", "Descuento", "Motivo (%)", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tarticulos.setComponentPopupMenu(jPopupMenu1);
        tarticulos.getTableHeader().setReorderingAllowed(false);
        tarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tarticulosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tarticulosMouseReleased(evt);
            }
        });
        tarticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarticulosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tarticulos);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.JPG"))); // NOI18N
        jButton2.setText("Agregar Artículo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("TOTAL FACTURA");

        total.setEnabled(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        cantidad.setText("Cantidad de Items: 0");

        jLabel2.setText("PAGO CLIENTE");

        pagoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoClienteActionPerformed(evt);
            }
        });
        pagoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pagoClienteKeyTyped(evt);
            }
        });

        jLabel6.setText("TOTAL A PAGAR");

        apagar.setEnabled(false);
        apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarActionPerformed(evt);
            }
        });

        jLabel10.setText("TOTAL PAGADO");

        pagado.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1624, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(cantidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(apagar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(pagoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(pagado, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(registrar, javax.swing.GroupLayout.DEFAULT_SIZE, 1606, Short.MAX_VALUE)
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
//Procesar Subotales
    private void realizarCalculos() {
    int i=0;
        model =  (DefaultTableModel) tarticulos.getModel();
        int filas = tarticulos.getRowCount();
        float totalFactura=0;
        float descuentoArticulo=0;
        float subtotal,importe;
        int cantidad;
         while (i<filas){ 
                    cantidad = Integer.parseInt(model.getValueAt(i, 5).toString());
                    if (forma.getSelectedItem().toString().equals("Efectivo")){              
                        importe=Float.parseFloat(model.getValueAt(i, 6).toString());
                     }else{
                        importe=Float.parseFloat(model.getValueAt(i, 7).toString());
                     }      
                    if (!model.getValueAt(i, 8).toString().equals("")){
                        descuentoArticulo = Float.parseFloat(model.getValueAt(i, 8).toString());
                    }else{
                        model.setValueAt("0", i, 8);
                    }
                    subtotal = (importe*cantidad);
                    subtotal = subtotal - (descuentoArticulo*subtotal/100);//Subtotal con descuento
                    totalFactura += subtotal;
                    model.setValueAt(subtotal, i, 10);
                    i++;   
         }               
             String desc="0";
             if (!descuento.getText().equals("")){
                 desc=descuento.getText();
             }
              total.setText(Dominio.A2Decimales(Float.toString(totalFactura - (Float.parseFloat(desc)*totalFactura/100))));
              if (Float.parseFloat(pagado.getText())<0){
                  pagado.setText("0");
              }
              apagar.setText(Dominio.A2Decimales(Float.toString(Float.parseFloat(total.getText())- Float.parseFloat(saldo.getText().toString())-Float.parseFloat(pagado.getText()) )));
              if (Float.parseFloat(apagar.getText())<=0) {
                  pagoCliente.setText("0");
                  apagar.setText("0");
              }else{
                  pagoCliente.setText(apagar.getText());
              }        
              if (comprobante.getSelectedItem().toString().equals("Nota de Crédito")){
                         totalFactura = totalFactura*(-1);
                         total.setText(Float.toString(totalFactura));
                         pagoCliente.setText(total.getText());
                         apagar.setText("");
              } 
                        
             
         tarticulos.setModel(model);       
    }
    
    public void mostrarDatos() {
      try {
          ResultSet rst = this.p.bd.mostrarDatosFacturaVenta(id_factura);
          if (forma.getSelectedItem().toString().equals("Tarjeta"))
             this.nuevoPorcentajeTarjeta.setEnabled(true);
           else
             this.nuevoPorcentajeTarjeta.setEnabled(false);  
          if (rst.next()){
              model = (DefaultTableModel) tarticulos.getModel();
              tarticulos.setModel(Dominio.eliminarTabla(model)); 
              numero.setText("Número: "+id_factura);
              calender.setDate(Dominio.formatofechaBD.parse(rst.getString(1)));//fecha
              cliente.setText("Cliente: "+rst.getString(2));
              nombreCliente = rst.getString(2);
               pagoCliente.setText(Dominio.A2Decimales(rst.getString(7)));//pago
              pagado.setText(Dominio.A2Decimales(rst.getString(7)));
              saldo.setText(Dominio.A2Decimales(rst.getString(9)));//saldo
              nuevoPorcentajeTarjeta.setText(rst.getString(10));//porcentaje_tarjeta
              forma.setSelectedItem(rst.getString(3));//forma de pago         
              comprobante.setSelectedItem(rst.getString(4));//comprobante
              comprobanteAnterior=rst.getString(4); //Variable para devolver o agregar stock luego
              descuento.setText(Dominio.A2Decimales(rst.getString(5)));//descuento
              motivo.setText(rst.getString(6));//motivo
              total.setText(Dominio.A2Decimales(rst.getString(8)));//total
              sucursal.setText(rst.getString(11)); //sucursal
              totalAnterior =Float.parseFloat(total.getText());
              rst = this.p.bd.mostrarDatosFacturaProductoVenta(id_factura);
              tarticulos.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor (descuentoArticulo));
              tarticulos.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor (motivoArticulo));  
              model = (DefaultTableModel) tarticulos.getModel();
              while (rst.next()){
                  model.addRow(new Object[]{rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5), rst.getString(6),
                  Dominio.A2Decimales(rst.getString(7)),Dominio.A2Decimales(rst.getString(8)),
                  Dominio.A2Decimales(rst.getString(9)),rst.getString(10),Dominio.A2Decimales(rst.getString(11))});
             }
              tarticulos.setModel(model);
              cantidad.setText("Cantidad de Items: "+tarticulos.getRowCount());
        }              
      } catch (SQLException ex) {
          Logger.getLogger(DetalleFacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (ParseException ex) {
                  Logger.getLogger(DetalleFacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
   
  
    
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
       boolean validar=true;
       if (validarFecha() && validarStock()){      
             String estado="PAGADO";
             float saldoNuevo=0;
             Date fecha= calender.getDate();                    
             model =  (DefaultTableModel) tarticulos.getModel();
             int filas = tarticulos.getRowCount();  
             if (filas==0){
                 JOptionPane.showMessageDialog(this,"No ingresó ningún artículo","Error", JOptionPane.ERROR_MESSAGE);
              }else{
                 if (JOptionPane.OK_OPTION == JOptionPane.showInternalConfirmDialog(this, "¿Actualizar Factura?" )){             
                    if (descuento.getText().equals(""))
                                    descuento.setText("0");
                    if (comprobante.getSelectedItem().toString().equals("Nota de Crédito")){
                        if (!comprobante.getSelectedItem().toString().equals(comprobanteAnterior)){
                            saldoNuevo = Float.parseFloat(saldo.getText())- Float.parseFloat(total.getText());
                        }else{
                            if(totalAnterior<Float.parseFloat(total.getText())){
                                saldoNuevo = Float.parseFloat(saldo.getText())- (Float.parseFloat(total.getText()) - totalAnterior) ;
                            }else{
                                if(totalAnterior>Float.parseFloat(total.getText())){
                                    saldoNuevo = Float.parseFloat(saldo.getText())- (Float.parseFloat(total.getText()) - totalAnterior) ;
                                }                                              
                            }
                        }                       
                    }else{
                    if (Float.parseFloat(saldo.getText())+Float.parseFloat(pagado.getText())+Float.parseFloat(pagoCliente.getText())<Float.parseFloat(total.getText())){ //Si el saldo + lo entregado no supera el monto total la factura queda inpaga
                                     estado="INPAGO";
                                     pagoCliente.setText(Float.toString(Float.parseFloat(pagoCliente.getText()) + Float.parseFloat(saldo.getText())+ Float.parseFloat(pagado.getText())));
                    }else{
                       if (Float.parseFloat(saldo.getText())+Float.parseFloat(pagado.getText())+Float.parseFloat(pagoCliente.getText())>Float.parseFloat(total.getText())){ //Si el saldo + lo entregado supera el monto total al cliente le queda dinero a favor                  
                           saldoNuevo = Float.parseFloat(saldo.getText())+ Float.parseFloat(pagado.getText())+ Float.parseFloat(pagoCliente.getText()) - Float.parseFloat(total.getText()); 
                            pagoCliente.setText(total.getText());
                       }else{
                              pagoCliente.setText(Float.toString(Float.parseFloat(saldo.getText())+ Float.parseFloat(pagoCliente.getText())+Float.parseFloat(pagado.getText())));
                         }             
                     } 
                  }
                  String sucursalText = sucursal.getText();
                  int n=this.p.bd.actualizarFacturaVenta(fecha, forma.getSelectedItem().toString(),comprobante.getSelectedItem().toString(), descuento.getText(),motivo.getText(),total.getText(),estado,Dominio.A2Decimales(pagoCliente.getText()),this.nuevoPorcentajeTarjeta.getText(),this.id_factura);
                  if (n>0){  
                         this.p.bd.eliminarArticulosFacturaVenta(id_factura,comprobanteAnterior);
                         float costo, ganancia;
                         for (int i=0;i<filas;i++){ 
                                String id_articulo =model.getValueAt(i, 0).toString();
                                String des = model.getValueAt(i, 1).toString();
                                String tipo = model.getValueAt(i, 3).toString();
                                String talle = model.getValueAt(i, 4).toString();
                                String cant =  model.getValueAt(i, 5).toString();
                                String precioe= model.getValueAt(i, 6).toString();
                                String preciot= model.getValueAt(i, 7).toString();  
                                String descuentoProducto= Dominio.A2Decimales(model.getValueAt(i, 8).toString());  
                                String motivo= model.getValueAt(i, 9).toString();  
                                String subtotal = model.getValueAt(i, 10).toString();                                 
                                costo = Float.parseFloat(this.p.bd.obtenerCostoArticulo(id_articulo))*Integer.parseInt(cant);
                                ganancia= Float.parseFloat(subtotal) -costo;
                                if (comprobante.getSelectedItem().toString().equals("Factura")){
                                    this.p.bd.actualizarStockArticulosPorTipoTalleYSucursal(cant,id_articulo, tipo, talle, sucursalText);         
                                }else{
                                       costo = costo*-1;
                                       ganancia= ganancia*-1;
                                       this.p.bd.actualizarStockArticulosPorTipoTalleYSucursal("-"+cant,id_articulo, tipo, talle, sucursalText);
                                }
                                this.p.bd.agregarFacturaArticulo(id_factura, id_articulo, talle, tipo, cant,Dominio.A2Decimales(Float.toString(costo)),Dominio.A2Decimales(Float.toString(ganancia)), precioe,preciot, descuentoProducto,motivo,subtotal);                             
                              }
                             this.p.bd.actualizarSaldoCliente(nombreCliente,new Date(),Float.toString(saldoNuevo));
                             this.factura.cargar("", null, null);
                             JOptionPane.showMessageDialog(this, "Factura actualizada con Exito");      
                             this.dispose();
                            }                            
                     else{
                       JOptionPane.showMessageDialog(this,"Error. No se pudo actualizar la factura","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
                   } }
                 } 
               
       }
      
    }//GEN-LAST:event_registrarActionPerformed

    private void JMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItem1ActionPerformed
       if (tarticulos.getSelectedRow()== -1){
           JOptionPane.showMessageDialog(this,"No ha seleccionado ningún item","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
         }else{
           model =  (DefaultTableModel) tarticulos.getModel();
           int[] arrayElementos = tarticulos.getSelectedRows();
           for (int i=0; i< arrayElementos.length;i++){  
               model.removeRow(arrayElementos[i]-i);
           }
           tarticulos.setModel(model); 
           this.cantidad.setText("Cantidad de Items: "+tarticulos.getRowCount());  
           realizarCalculos();
            }
    }//GEN-LAST:event_JMenuItem1ActionPerformed

    private void descuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyTyped
        if (descuento.getText().length() == Dominio.DOM_PORCENTAJE){
            evt.consume();
        }else{
            char l = evt.getKeyChar();
            if (((l<'0' || l>'9') && (l!='.')) || (l=='.' && descuento.getText().indexOf('.')!=-1)){
                evt.consume();
            }
        }
    }//GEN-LAST:event_descuentoKeyTyped

    private void tarticulosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tarticulosMouseReleased
           realizarCalculos();
    }//GEN-LAST:event_tarticulosMouseReleased

    private void tarticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarticulosKeyReleased

    }//GEN-LAST:event_tarticulosKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        AgregarArtículo pro = new AgregarArtículo(this.p, this, id_factura);
        this.add(pro);
         this.p.escritorio.add(pro);
         pro.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      if (tarticulos.getSelectedRow()== -1){
           JOptionPane.showMessageDialog(this,"No ha seleccionado ningún item","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
         }else{
           model =  (DefaultTableModel) tarticulos.getModel();
           int[] arrayElementos = tarticulos.getSelectedRows();               
           for (int i=0; i< tarticulos.getSelectedRowCount();i++){
               int fila = arrayElementos[i]; 
                String id_articulo = model.getValueAt(fila, 0).toString();
               String nom = model.getValueAt(fila,1).toString();
               String tipo = model.getValueAt(fila,3).toString();
               String talle = model.getValueAt(fila,4).toString();           
               String cantidadVieja= model.getValueAt(fila, 5).toString(); //cantidad que habia antes
               String stock = String.valueOf(Integer.parseInt(this.p.bd.obtenerStockArticuloPorTalleTipoYSucursal(id_articulo, talle, tipo, sucursal.getText())) + this.p.bd.obtenerCantidadVendidaDeFacturaPorArticuloTalleTipoYFactura(id_articulo, talle, tipo, id_factura)); //Suma el stock que habia a la factura ya hecha si es que existe  
               nom = model.getValueAt(fila,1).toString();                                    
               String cantidadNueva=JOptionPane.showInputDialog(this, "Agregue la cantidad deseada para el articulo "+nom+", talle: "+talle+". Stock Disponible: "+stock, "Control de Stock", JOptionPane.QUESTION_MESSAGE);
               try{
                if (cantidadNueva!=null){
                     if ((Integer.parseInt(cantidadNueva)==0) || ((cantidadNueva.equals("")))){
                       JOptionPane.showMessageDialog(this,"No ha ingresado cantidad","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
                     }else{
                          if (this.getComprobante().getSelectedItem().toString().equals(Constantes.FACTURA) && Integer.parseInt(cantidadNueva) > Integer.parseInt(stock)){
                             JOptionPane.showMessageDialog(this, "Stock insuficiente para el talle " + talle,"Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
                         }else{
                              model.setValueAt(cantidadNueva, fila, 5); //Modificar cantidad
                              realizarCalculos();
                         }                  
                      }
                    }
              }catch(NumberFormatException ex){ // handle your exception
                  JOptionPane.showMessageDialog(this,"Error en la cantidad ingresada. Ingrese números enteros","Advertencia", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje                 
                  }
           }
           tarticulos.setModel(model);        
           }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void pagoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagoClienteKeyTyped

        if (pagoCliente.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }else{
            char l = evt.getKeyChar();
            if (((l<'0' || l>'9') && (l!='.')) || (l=='.' && pagoCliente.getText().indexOf('.')!=-1)){
                evt.consume();
            }
        }
    }//GEN-LAST:event_pagoClienteKeyTyped

    private void motivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_motivoKeyTyped
         if (motivo.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_motivoKeyTyped

    private void comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprobanteActionPerformed
     if (comprobante.getSelectedItem().toString().equals("Nota de Crédito"))     
   {
      pagoCliente.setEnabled(false);
   }else{
       pagoCliente.setEnabled(true);
   }
       
      realizarCalculos();
    }//GEN-LAST:event_comprobanteActionPerformed

    private void formaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formaActionPerformed
         if (forma.getSelectedItem().toString().equals("Tarjeta")){
             this.nuevoPorcentajeTarjeta.setEnabled(true);
        }else{
              this.nuevoPorcentajeTarjeta.setEnabled(false);  
              this.nuevoPorcentajeTarjeta.setText(String.valueOf(Constantes.PORCENTAJE_TARJETA_DEFECTO));
              actualizarPreciosDeTarjeta();
        }

        realizarCalculos();
    }//GEN-LAST:event_formaActionPerformed

    private void actualizarPreciosDeTarjeta(){
        model =  (DefaultTableModel) tarticulos.getModel();     
        float precioEfectivo;
        float precioTarjeta;
        float nuevoPorcentajeTarjeta=0;
        if (!this.nuevoPorcentajeTarjeta.getText().isEmpty())
            nuevoPorcentajeTarjeta = Float.parseFloat(this.nuevoPorcentajeTarjeta.getText());
        int filas = tarticulos.getRowCount();
        for (int i=0; i<filas; i++){ 
           precioEfectivo = Float.parseFloat(model.getValueAt(i, 6).toString());
           precioTarjeta = precioEfectivo +(nuevoPorcentajeTarjeta * precioEfectivo/100);
           model.setValueAt(precioTarjeta, i, 7);     
        } 
    }
        
    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
         realizarCalculos();
    }//GEN-LAST:event_descuentoKeyReleased

    private void tarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tarticulosMouseClicked
        realizarCalculos();
    }//GEN-LAST:event_tarticulosMouseClicked

    private void descuentoArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoArticuloKeyTyped
     if (descuentoArticulo.getText().length() == Dominio.DOM_PORCENTAJE){
            evt.consume();
         }else{ 
               char l = evt.getKeyChar();            
               if (((l<'0' || l>'9') && (l!='.')) || (l=='.' && descuentoArticulo.getText().indexOf('.')!=-1)){
                 evt.consume();                                    
          }
         }  
    }//GEN-LAST:event_descuentoArticuloKeyTyped

    private void motivoArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_motivoArticuloKeyTyped
       if (motivoArticulo.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
         }        // TODO add your handling code here:
    }//GEN-LAST:event_motivoArticuloKeyTyped

    private void descuentoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoArticuloActionPerformed
       realizarCalculos();
    }//GEN-LAST:event_descuentoArticuloActionPerformed

    private void pagoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagoClienteActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apagarActionPerformed

    private void nuevoPorcentajeTarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPorcentajeTarjetaKeyTyped
         if (nuevoPorcentajeTarjeta.getText().length() == Dominio.DOM_PORCENTAJE){
            evt.consume();
        }else{
            char l = evt.getKeyChar();            
            if (((l<'0' || l>'9') && (l!='.')) || (l=='.' && nuevoPorcentajeTarjeta.getText().indexOf('.')!=-1)){
                evt.consume();                
            }       
        }
    }//GEN-LAST:event_nuevoPorcentajeTarjetaKeyTyped

    private void nuevoPorcentajeTarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPorcentajeTarjetaKeyReleased
       actualizarPreciosDeTarjeta();
       realizarCalculos();
    }//GEN-LAST:event_nuevoPorcentajeTarjetaKeyReleased


       
    public void ActualizarTablaArticulos(String cod,String nom,String mar,String tipo, String talle, String can, String pree,String pret){
       model = (DefaultTableModel) tarticulos.getModel();
       tarticulos.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor (descuentoArticulo));
       tarticulos.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor (motivoArticulo));
       model.addRow(new Object[]{cod, nom , mar,tipo, talle, can,pree,pret,"0","",0});
       tarticulos.setModel(model);
       cantidad.setText("Cantidad de Items: "+tarticulos.getRowCount()); 
       if (forma.getSelectedItem().toString().equals("Tarjeta")){
         actualizarPreciosDeTarjeta();
        }
       realizarCalculos();
    }
   
     private boolean validarFecha() {
        boolean validar=true;
        try{
         String fecha= Dominio.formatofechaJCalender.format(calender.getDate());
         if (fecha.length()!=10){
            validar=false;}
   
        }     catch(NullPointerException e){
            Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, e);
            validar=false;           
          }
         

        if (validar==false){
            JOptionPane.showMessageDialog(this,"Formato de fecha invalido. Formato admitido dd/mm/aaaa","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
        }
        
        return validar;
    }
     
      public JComboBox getComprobante() {
        return comprobante;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMenuItem1;
    private javax.swing.JTextField apagar;
    private com.toedter.calendar.JDateChooser calender;
    private javax.swing.JLabel cantidad;
    private javax.swing.JLabel cliente;
    private javax.swing.JComboBox comprobante;
    private javax.swing.JTextField descuento;
    private javax.swing.JTextField descuentoArticulo;
    private javax.swing.JComboBox forma;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpanel;
    private javax.swing.JTextArea motivo;
    private javax.swing.JTextField motivoArticulo;
    private javax.swing.JTextField nuevoPorcentajeTarjeta;
    private javax.swing.JLabel numero;
    private javax.swing.JTextField pagado;
    private javax.swing.JTextField pagoCliente;
    private javax.swing.JButton registrar;
    private javax.swing.JTextField saldo;
    private javax.swing.JLabel sucursal;
    private javax.swing.JTable tarticulos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
    private String comprobanteAnterior;
    private float totalAnterior;

    @Override
    public boolean existeArticuloYTalle(String idArticulo, String talle) {
         model =  (DefaultTableModel) tarticulos.getModel();
        int filas = tarticulos.getRowCount();
        String idArticuloTabla;
        String talleTabla;
        for (int i=0; i<filas; i++){ 
            idArticuloTabla = model.getValueAt(i, 0).toString();
            talleTabla = model.getValueAt(i, 4).toString();
            if (idArticuloTabla.equals(idArticulo) && talleTabla.equals(talle)){
                return true;
            }        
         }
        return false;
    }
    
    private boolean validarStock(){
        if (!this.p.bd.existeLaSucursal(sucursal.getText())){
            JOptionPane.showMessageDialog(this,"La factura no se puede editar debido a que la sucursal ha sido eliminada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (this.getComprobante().getSelectedItem().toString().equals(Constantes.FACTURA)){
             model =  (DefaultTableModel) tarticulos.getModel();
            int filas = tarticulos.getRowCount();                   
            for (int i=0;i<filas;i++){ 
              String id_articulo =model.getValueAt(i, 0).toString();
              String des = model.getValueAt(i, 1).toString();
              String tipo = model.getValueAt(i, 3).toString();
              String talle = model.getValueAt(i, 4).toString();
              Integer cant =  Integer.parseInt(model.getValueAt(i, 5).toString());
              Integer stock = Integer.parseInt(this.p.bd.obtenerStockArticuloPorTalleTipoYSucursal(id_articulo, talle,  tipo, sucursal.getText())); 
              Integer stockAnterior = this.p.bd.obtenerCantidadVendidaDeFacturaPorArticuloTalleTipoYFactura(id_articulo, talle, tipo, id_factura);
              Integer stockTotal = stock + stockAnterior;
              if (cant > stockTotal){                         
                JOptionPane.showMessageDialog(this,"El Stock es insuficiente para el articulo "+des+" talle " + talle + ". Actualmente hay " + stockTotal + " articulos", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
              }
            }                                                             
        }
        return true;
    }

    public JLabel getSucursal() {
        return sucursal;
    }
    
    
}
