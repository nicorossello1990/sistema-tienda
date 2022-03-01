/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemaTienda;


import claseConectar.Dominio;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Ezenic
 */
public class FacturaVenta extends javax.swing.JInternalFrame implements OperacionesFacturas{

    Principal p;
    DefaultTableModel model;
  
    
    public FacturaVenta(Principal p) {
        
        
        initComponents();
        this.nuevoPorcentajeTarjeta.setText(String.valueOf(Constantes.PORCENTAJE_TARJETA_DEFECTO));  
        tarticulos.getColumnModel().getColumn(0).setPreferredWidth(150);
        this.p = p;      
        this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));       
        
           nombre.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {

                String cadenaEscrita = nombre.getEditor().getItem().toString();


                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    nombre.setModel(p.bd.getLista(cadenaEscrita));
                    if (nombre.getItemCount() > 0) {
                        //nombre.getEditor().setItem(cadenaEscrita);
                        nombre.showPopup();     
                        if (evt.getKeyCode() != 8){
                           ((JTextComponent)nombre.getEditor().getEditorComponent()).select(cadenaEscrita.length(),nombre.getEditor().getItem().toString().length());
                        }
                    } else {
                        nombre.addItem(cadenaEscrita);
                    }
                }
                 
            }
        });
      
        ActualizarNroFactura();
        actualizarNombresClientes("");
        actualizarSaldo();
        nombre.setSelectedItem(Constantes.CONSUMIDOR_FINAL);



    }


        
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        descuentoArticulo = new javax.swing.JTextField();
        motivoArticulo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
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
        jpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        num = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comprobante = new javax.swing.JComboBox();
        calender = new com.toedter.calendar.JDateChooser();
        nombre = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        descuento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        forma = new javax.swing.JComboBox();
        numero = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        motivo = new javax.swing.JTextArea();
        saldoText = new javax.swing.JLabel();
        saldo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        nuevoPorcentajeTarjeta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jPopupMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPopupMenu1.setLabel("");

        jMenuItem1.setText("Eliminar Artículo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Modificar Cantidad");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        descuentoArticulo.setText("0");
        descuentoArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                descuentoArticuloMouseReleased(evt);
            }
        });
        descuentoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoArticuloActionPerformed(evt);
            }
        });
        descuentoArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descuentoArticuloKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descuentoArticuloKeyReleased(evt);
            }
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

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("NUEVA FACTURA DE VENTA");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Save).jpg"))); // NOI18N
        registrar.setText("Registrar Factura");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Artículos"));

        tarticulos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Artículo", "Descripción", "Marca", "Tipo", "Talle", "Cantidad", "Precio Efectivo", "Precio Tarjeta", "Descuento (%)", "Motivo", "Subtotal"
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarticulosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarticulosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarticulosKeyTyped(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1315, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(cantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(total)
                    .addComponent(pagoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantidad)
                    .addComponent(jLabel5)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(apagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pagoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Factura"));

        jLabel1.setText("Cliente");

        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num.setText("Número");

        jLabel3.setText("Comprobante");

        jLabel4.setText("Fecha");

        comprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Nota de Crédito" }));
        comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprobanteActionPerformed(evt);
            }
        });

        calender.setDate(new Date());
        calender.setMinSelectableDate(new java.util.Date(-62135755099000L));

        nombre.setEditable(true);
        nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nombreMouseReleased(evt);
            }
        });
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
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

        numero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numero.setText("numero");

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

        saldoText.setText("Saldo A Favor");

        saldo.setText("0");
        saldo.setEnabled(false);

        jButton1.setText("Abrir Calculadora");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nuevoPorcentajeTarjeta.setEnabled(false);
        nuevoPorcentajeTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nuevoPorcentajeTarjetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nuevoPorcentajeTarjetaKeyTyped(evt);
            }
        });

        jLabel10.setText("%");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(num)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpanelLayout.createSequentialGroup()
                                        .addComponent(comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(111, 111, 111)
                                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(saldoText)))
                                    .addGroup(jpanelLayout.createSequentialGroup()
                                        .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nuevoPorcentajeTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)))
                                .addGap(35, 35, 35)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(saldo, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(descuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numero)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num)
                    .addComponent(numero))
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saldoText)
                            .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoPorcentajeTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(registrar, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                .addGap(355, 355, 355))
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
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        actualizarSaldo();
        realizarCalculos();
    }//GEN-LAST:event_nombreActionPerformed

    private void tarticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarticulosKeyReleased

    }//GEN-LAST:event_tarticulosKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  
        AgregarArtículo pro = new AgregarArtículo(this.p, this);
        this.add(pro);
         this.p.escritorio.add(pro);
         pro.show(); 
    }//GEN-LAST:event_jButton2ActionPerformed

   
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
              apagar.setText(Dominio.A2Decimales(Float.toString(Float.parseFloat(total.getText())- Float.parseFloat(saldo.getText().toString()))));
              if (Float.parseFloat(total.getText())<Float.parseFloat(saldo.getText())) {
                  pagoCliente.setText("0");
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
    
    
    private void nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreKeyPressed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
     

                
    }//GEN-LAST:event_nombreKeyTyped

    private void nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreMouseClicked
        
    }//GEN-LAST:event_nombreMouseClicked

    private void nombreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreMouseReleased
  //     ActualizarNombresClientes(buscar.getText());  
    }//GEN-LAST:event_nombreMouseReleased

    private void tarticulosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tarticulosMouseReleased

        
    }//GEN-LAST:event_tarticulosMouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       if (tarticulos.getSelectedRow()== -1){
           JOptionPane.showMessageDialog(this,"No ha seleccionado ningún item","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
         }else{
           model =  (DefaultTableModel) tarticulos.getModel();
           int[] arrayElementos = tarticulos.getSelectedRows();
           for (int i=0; i< arrayElementos.length;i++){    
               model.removeRow(arrayElementos[i]-i);
           }
           tarticulos.setModel(model); 
           cantidad.setText("Cantidad de Items: "+tarticulos.getRowCount());  
           realizarCalculos();
      }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

     
    private boolean validarDatos() {
    boolean validar = false;  
    if (nombre.getSelectedItem()==null){
        JOptionPane.showMessageDialog(this,"No ha seleccionado ningún cliente para la factura","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
    }else{
        ResultSet rs = this.p.bd.buscarCliente(nombre.getSelectedItem().toString());
        try {
            if(!rs.next()){
                JOptionPane.showMessageDialog(this,"El cliente seleccionado no existe","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
            }else{              
                validar=true;
                }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    return validar;
    }

    
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
       boolean validar=true;
       if (validarDatos() && validarFecha() && validarStock()){      
          try{
             ResultSet rst = this.p.bd.buscarIDClientePorNombre(nombre.getSelectedItem().toString());
             String estado="PAGADO";
             float saldoNuevo=0;
             if (rst.next()){                     
                         String id_factura = this.numero.getText(); 
                         model =  (DefaultTableModel) tarticulos.getModel();
                         int filas = tarticulos.getRowCount();
                         if (filas==0){
                            JOptionPane.showMessageDialog(this,"No ingresó ningún artículo","Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                           if (JOptionPane.OK_OPTION == JOptionPane.showInternalConfirmDialog(this, "¿Registrar Factura?" )){   
                                String id_cliente = rst.getString("id_mcliente");            
                                Date fecha= calender.getDate();
                                if (descuento.getText().equals(""))
                                    descuento.setText("0");
                                if (comprobante.getSelectedItem().toString().equals("Nota de Crédito")){
                                    saldoNuevo = Float.parseFloat(saldo.getText())- Float.parseFloat(total.getText());
                                }else{
                                if (Float.parseFloat(saldo.getText())+Float.parseFloat(pagoCliente.getText())<Float.parseFloat(total.getText())){ //Si el saldo + lo entregado no supera el monto total la factura queda inpaga
                                     estado="INPAGO";
                                     pagoCliente.setText(Float.toString(Float.parseFloat(pagoCliente.getText()) + Float.parseFloat(saldo.getText())));
                                }else{
                                    if (Float.parseFloat(saldo.getText())+Float.parseFloat(pagoCliente.getText())>Float.parseFloat(total.getText())){ //Si el saldo + lo entregado supera el monto total al cliente le queda dinero a favor                  
                                       saldoNuevo = Float.parseFloat(saldo.getText())+ Float.parseFloat(pagoCliente.getText()) - Float.parseFloat(total.getText()); 
                                       pagoCliente.setText(total.getText());
                                    }else{
                                        pagoCliente.setText(Float.toString(Float.parseFloat(saldo.getText())+ Float.parseFloat(pagoCliente.getText())));
                                    }             
                                } 
                                }
                               int n=this.p.bd.registrarFacturaVenta(numero.getText(),fecha, forma.getSelectedItem().toString(),comprobante.getSelectedItem().toString(), descuento.getText(),motivo.getText(),total.getText(),estado,Dominio.A2Decimales(pagoCliente.getText()), nuevoPorcentajeTarjeta.getText(), id_cliente);
                               if (n>0){  
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
                                       this.p.bd.actualizarStockArticulosPorTipoYTalle(cant,id_articulo, tipo, talle);                                
                                   }else{
                                       costo = costo*-1;
                                       ganancia= ganancia*-1;
                                       this.p.bd.actualizarStockArticulosPorTipoYTalle("-"+cant,id_articulo, tipo, talle);
                                   }
                                   this.p.bd.agregarFacturaArticulo(id_factura, id_articulo, talle, tipo, cant,Dominio.A2Decimales(Float.toString(costo)),Dominio.A2Decimales(Float.toString(ganancia)), precioe,preciot, descuentoProducto,motivo,subtotal); 
                                  }
                                  if (Float.parseFloat(saldo.getText())>0 || saldoNuevo>0 ){
                                      this.p.bd.actualizarSaldoCliente(nombre.getSelectedItem().toString(),new Date(),Float.toString(saldoNuevo));
                                   }
                                  if (JOptionPane.OK_OPTION == JOptionPane.showInternalConfirmDialog(this, "Factura Registrada con éxito. ¿Desea agregar una nueva factura de venta?" )){
                                    limpiarFactura();            
                                    }
                                   else{
                                      this.dispose();
                                   }                                                                             
                              } else{
                                 JOptionPane.showMessageDialog(this,"Error. No ingresó ningún artículo","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
                               }  
                          }}
                 }           
             }          
           catch(SQLException e){
              
            Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, e);
          }catch(NumberFormatException e){
               JOptionPane.showMessageDialog(this,"Error. No ingresó ningún artículo","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
          }
                    

       }

      
    }//GEN-LAST:event_registrarActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    
    }//GEN-LAST:event_formMouseClicked

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
               String stock = this.p.bd.obtenerStockArticuloPorTalleYTipo(id_articulo, talle, tipo); //stock actual                                              
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
        realizarCalculos();        // TODO add your handling code here:
    }//GEN-LAST:event_formaActionPerformed

    private void descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoKeyReleased
       realizarCalculos();
    }//GEN-LAST:event_descuentoKeyReleased

    private void tarticulosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarticulosKeyTyped

    }//GEN-LAST:event_tarticulosKeyTyped

    private void tarticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarticulosKeyPressed

    }//GEN-LAST:event_tarticulosKeyPressed

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

    private void descuentoArticuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoArticuloKeyReleased

    }//GEN-LAST:event_descuentoArticuloKeyReleased

    private void descuentoArticuloMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descuentoArticuloMouseReleased
        realizarCalculos();

    }//GEN-LAST:event_descuentoArticuloMouseReleased

    private void descuentoArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descuentoArticuloKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoArticuloKeyPressed

    private void motivoArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_motivoArticuloKeyTyped
        if (motivoArticulo.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_motivoArticuloKeyTyped

    private void descuentoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoArticuloActionPerformed
        realizarCalculos();
    }//GEN-LAST:event_descuentoArticuloActionPerformed

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased


    }//GEN-LAST:event_nombreKeyReleased

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apagarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            Runtime rt = Runtime.getRuntime();
            Process p= rt.exec("calc");
            p.waitFor();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apagar;
    private com.toedter.calendar.JDateChooser calender;
    private javax.swing.JLabel cantidad;
    private javax.swing.JComboBox comprobante;
    private javax.swing.JTextField descuento;
    private javax.swing.JTextField descuentoArticulo;
    private javax.swing.JComboBox forma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpanel;
    private javax.swing.JTextArea motivo;
    private javax.swing.JTextField motivoArticulo;
    private javax.swing.JComboBox nombre;
    private javax.swing.JTextField nuevoPorcentajeTarjeta;
    private javax.swing.JLabel num;
    private javax.swing.JLabel numero;
    private javax.swing.JTextField pagoCliente;
    private javax.swing.JButton registrar;
    private javax.swing.JTextField saldo;
    private javax.swing.JLabel saldoText;
    private javax.swing.JTable tarticulos;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables


  /*  public void ActualizarNombresClientes(String valor)  {      
        //getNombre().removeAllItems()
        
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();;
        try {            
            ResultSet rs = this.p.bd.buscarNombreClientePorNombre(valor);
            while (rs.next()){
                modelo.addElement(rs.getString("nombre"));               
            }
            nombre.setModel(modelo);
            /*if (nombre.getItemCount() > 0) {
                 nombre.getEditor().setItem(valor);            
                 nombre.addItem(valor);
                 nombre.showPopup(); 
            } else {
                   nombre.addItem(valor);
                   nombre.getEditor().setItem(valor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }*/


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
    
    public javax.swing.JComboBox getNombre() {
        return nombre;
    }

    


    private void limpiarFactura() {
       model = (DefaultTableModel) tarticulos.getModel();
       tarticulos.setModel(Dominio.eliminarTabla(model));   
       total.setText("");
       descuento.setText("");
       motivo.setText("");
       cantidad.setText("Cantidad de Items: 0");
       calender.setDate(new Date());
       forma.setSelectedIndex(0);
       pagoCliente.setText("");
       apagar.setText("");
       comprobante.setSelectedIndex(0);
       ActualizarNroFactura();
       actualizarSaldo();
       nombre.setSelectedItem(Constantes.CONSUMIDOR_FINAL);
       
    }

    private void ActualizarNroFactura() {
       ResultSet rs = this.p.bd.buscarNroFacturaVenta();
       int nro=0;       
        try {
            if (rs.next()){
                if (rs.getString(1) ==null){
                    nro=1;
                }else{
                    nro= Integer.parseInt(rs.getString(1))+1;
                } 
            }} catch (SQLException ex) {
            Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        numero.setText(Integer.toString(nro));
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
            JOptionPane.showMessageDialog(this,"Formato de fecha inválido. Formato admitido dd/mm/aaaa","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje 
        }
        
        return validar;
    }
    
    private boolean validarStock(){
        if (this.getComprobante().getSelectedItem().toString().equals(Constantes.FACTURA)){
             model =  (DefaultTableModel) tarticulos.getModel();
            int filas = tarticulos.getRowCount();                   
            for (int i=0;i<filas;i++){ 
              String id_articulo =model.getValueAt(i, 0).toString();
              String des = model.getValueAt(i, 1).toString();
              String tipo = model.getValueAt(i, 3).toString();
              String talle = model.getValueAt(i, 4).toString();
              Integer cant =  Integer.parseInt(model.getValueAt(i, 5).toString());
              Integer stock = Integer.parseInt(this.p.bd.obtenerStockArticuloPorTalleYTipo(id_articulo, talle,  tipo)); 
              if (cant > stock){             
                JOptionPane.showMessageDialog(this,"El Stock es insuficiente para el articulo "+des+" talle " + talle + ". Actualmente hay " + stock + " articulos", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
              }
            }                                                             
        }
        return true;
    }
            
    

    private void actualizarNombresClientes(String valor) {
                  nombre.setModel(p.bd.getLista(valor));
                  if (nombre.getItemCount() > 0) {    
                         ((JTextComponent)nombre.getEditor().getEditorComponent()).select(valor.length(),nombre.getEditor().getItem().toString().length());          

    }}
    
    private void actualizarSaldo(){
                 ResultSet rst = p.bd.obtenerSaldoAFavor(nombre.getSelectedItem().toString());
                  try {
                    if (rst.next()){
                        if (rst.getString(1)==null){
                            saldo.setText("0");
                        }else{
                          saldo.setText(rst.getString(1));                           
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }    
    }

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

    public JComboBox getComprobante() {
        return comprobante;
    }

   



}
