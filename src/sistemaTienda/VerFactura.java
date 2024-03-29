/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaTienda;

import claseConectar.Dominio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nico_
 */
public class VerFactura extends javax.swing.JInternalFrame {

    Principal p;
    String id_factura;
    String nombre;
    DefaultTableModel model;
    
    VerFactura(Principal p, String numero) {
        initComponents();       
        this.p = p;      
        this.id_factura = numero;
        Dominio.centrarDatos(tarticulos1);
        this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));   
        this.mostrarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel1 = new javax.swing.JPanel();
        cliente1 = new javax.swing.JLabel();
        numero1 = new javax.swing.JLabel();
        comprobante = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        descuento = new javax.swing.JLabel();
        motivo = new javax.swing.JLabel();
        forma = new javax.swing.JLabel();
        sucursal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tarticulos1 = new javax.swing.JTable();
        total = new javax.swing.JLabel();
        cantidad = new javax.swing.JLabel();
        pago = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("DETALLE FACTURA");

        jpanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Factura"));

        cliente1.setText("Cliente");

        numero1.setText("Número");

        comprobante.setText("Comprobante");

        fecha.setText("Fecha");

        descuento.setText("Descuento");

        motivo.setText("Motivo");

        forma.setText("Forma de Pago");

        sucursal.setText("Sucursal");

        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
        jpanel1.setLayout(jpanel1Layout);
        jpanel1Layout.setHorizontalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sucursal)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numero1)
                            .addComponent(cliente1)
                            .addComponent(comprobante)
                            .addComponent(forma))
                        .addGap(92, 92, 92)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(motivo)
                            .addComponent(descuento)
                            .addComponent(fecha))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel1Layout.setVerticalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero1)
                    .addComponent(fecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliente1)
                    .addComponent(descuento))
                .addGap(12, 12, 12)
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comprobante)
                    .addComponent(motivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sucursal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Artículos"));

        tarticulos1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tarticulos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Artículo", "Descripción", "Marca", "Tipo", "Talle", "Cantidad", "Precio Efectivo", "Precio Tarjeta", "Descuento", "Motivo", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tarticulos1.getTableHeader().setReorderingAllowed(false);
        tarticulos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tarticulos1MouseReleased(evt);
            }
        });
        tarticulos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarticulos1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tarticulos1);

        total.setText("TOTAL");

        cantidad.setText("Cantidad de Items: 0");

        pago.setText("PAGO CLIENTE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(cantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pago, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(76, 76, 76))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1650, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cantidad)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(total)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pago, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   public void mostrarDatos() {
      try {
          ResultSet rst = this.p.bd.mostrarDatosFacturaVenta(id_factura);
          if (rst.next()){
              numero1.setText("Número: "+id_factura);
              fecha.setText("Fecha: "+Dominio.formatofechaJCalender.format(Dominio.formatofechaBD.parse(rst.getString(1))));
              cliente1.setText("Cliente: "+rst.getString(2));
              forma.setText("Forma de Pago: "+rst.getString(3));//forma de pago
              comprobante.setText("Comprobante: "+rst.getString(4));//comprobante
              descuento.setText("Descuento: "+Dominio.A2Decimales(rst.getString(5)));//descuento
              motivo.setText("Motivo: "+rst.getString(6));//motivo
              pago.setText("PAGO CLIENTE: "+Dominio.A2Decimales(rst.getString(7)));//pago cliente
              total.setText("TOTAL: "+Dominio.A2Decimales(rst.getString(8)));//total
              sucursal.setText("Sucursal: "+ rst.getString(11));
              rst = this.p.bd.mostrarDatosFacturaProductoVenta(id_factura);
              model = (DefaultTableModel) tarticulos1.getModel();
              while (rst.next()){
                  model.addRow(new Object[]{rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),
                  Dominio.A2Decimales(rst.getString(7)),Dominio.A2Decimales(rst.getString(8)),Dominio.A2Decimales(rst.getString(9)),
                  rst.getString(10),Dominio.A2Decimales(rst.getString(11))});
             }
              tarticulos1.setModel(model);
              cantidad.setText("Cantidad de Items: "+tarticulos1.getRowCount());
        }              
      } catch (SQLException ex) {
          Logger.getLogger(VerFactura.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ParseException ex) {
            Logger.getLogger(VerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    private void tarticulos1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tarticulos1MouseReleased

    }//GEN-LAST:event_tarticulos1MouseReleased

    private void tarticulos1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarticulos1KeyReleased

    }//GEN-LAST:event_tarticulos1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cantidad;
    private javax.swing.JLabel cliente1;
    private javax.swing.JLabel comprobante;
    private javax.swing.JLabel descuento;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel forma;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JLabel motivo;
    private javax.swing.JLabel numero1;
    private javax.swing.JLabel pago;
    private javax.swing.JLabel sucursal;
    private javax.swing.JTable tarticulos1;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
