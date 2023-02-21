
/*
 * IngresoProductos.java
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */
package sistemaTienda;


import claseConectar.Dominio;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Articulos extends javax.swing.JInternalFrame {
    Principal p;
    DefaultTableModel model;
    AgregarArtículo agregar=null;
    private String codigoArticulo = "0";
    

    /** Creates new form IngresoProductos */
    public Articulos(Principal p) {
  
            initComponents();
            this.p = p;
            this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));
            cargar("");
            principal();        
            initSucursales();

    }

    public Articulos(Principal p,AgregarArtículo agregar) {
   
            initComponents();
            this.agregar=agregar;
            this.p = p;
            this.setLocation((this.p.getSize().width/2)-(this.getWidth()/2), (this.p.getSize().height/2)-(this.getHeight()/2));
            cargar("");
            principal();
            initSucursales();

    }
    
     private void initSucursales() {
            try {
            ResultSet rs = this.p.bd.listarDatosSucursales();
            while(rs.next())
            {       
                sucursalSelectEdit.addItem(rs.getString("nombre"));
                sucursalSelectSearch.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }                  
    }
     
    
    
     public void principal(){
      nuevo.setEnabled(true);
      nombre.setEnabled(false);
      marca.setEnabled(false);
      costo.setEnabled(false);
      ganancia.setEnabled(false);
      tipo.setEnabled(false);
      nombre.setText("");
      marca.setText("");
      costo.setText("");
      ganancia.setText("");
      preciot.setText("");
      precioe.setText("");
      guardar.setEnabled(false);
      nombre.setText("");
      buscar.setEnabled(true);
      modificar.setEnabled(true);
      eliminar.setEnabled(true);
      actualizar.setEnabled(false);
      tarticulos.setEnabled(true);
      cancelar.setEnabled(false);
       ttalles.setVisible(false);
       sucursalSelectEdit.setEnabled(false);
       actualizarCostos.setEnabled(true);
        actualizarPrecios.setEnabled(true);
     }
    
    void mostrarTalles(String accion){
         try{
            String [] registros= new String[3];
            model= (DefaultTableModel) ttalles.getModel();
            model = Dominio.eliminarTabla(model);  
            ttalles.setVisible(true);
            String tipoArticulo = tipo.getSelectedItem().toString();      
            String consulta;
            String consultaInicial = "SELECT t.nombre, 0 FROM talle as t where t.id_tipo = (select id_tipo from tipo_articulo where nombre='"+tipoArticulo+"') order by t.id_talle;";
            ResultSet rs;
            if (accion.equals(Constantes.ACCION_EDICION)){
                int fila= tarticulos.getSelectedRow();
                String idArticulo= tarticulos.getValueAt(fila, 0).toString();
                consulta= "SELECT t.nombre, a.stock FROM talle as t inner join talle_articulos as a on a.id_talle = t.id_talle and a.id_articulo="+idArticulo+" inner join sucursales as s on s.id_sucursal = a.id_sucursal where t.id_tipo = (select id_tipo from tipo_articulo where nombre='"+tipoArticulo+"') and s.nombre = '"+sucursalSelectEdit.getSelectedItem().toString()+"' order by t.id_talle;";
                rs = this.p.bd.listarTallesPorTipo(consulta); 
                if (!rs.isBeforeFirst()){
                    rs = this.p.bd.listarTallesPorTipo(consultaInicial); 
                }
            }else{
                 rs = this.p.bd.listarTallesPorTipo(consultaInicial);
            }
            while(rs.next()){      
                registros[0]=rs.getString(1); //Talle  
                registros[1]=rs.getString(2); //Stock          
                model.addRow(registros);      
                }
            ttalles.setModel(model);
        }catch(Exception e){
            System.out.println(e.getMessage());
             }
        
    }
    
  
    void cargar(String valor) {
        try{
            String [] registros= new String[11];
            model= (DefaultTableModel) tarticulos.getModel();
            model = Dominio.eliminarTabla(model);  
            String querySucursal = "";
            if (!sucursalSelectSearch.getSelectedItem().toString().equals("Todas")) {
               querySucursal = "and suc.nombre = '"+sucursalSelectSearch.getSelectedItem().toString()+"'";
            }
           
            String consulta="select a.id_articulo, a.nombre, a.marca, t.nombre, tal.nombre, ta.stock, a.costo, a.ganancia,a.precio_venta,a.precio_venta_tarjeta, suc.nombre "
                    + " from articulos as a inner join tipo_articulo as t on t.id_tipo = a.id_tipo "
                    + " inner join talle_articulos as ta on ta.id_articulo = a.id_articulo "
                    + " inner join talle as tal on tal.id_talle = ta.id_talle "    
                    + " inner join sucursales as suc on suc.id_sucursal = ta.id_sucursal "             
                    + " WHERE CONCAT (a.id_articulo, lower(a.nombre), lower(a.marca)) LIKE '%"+valor.toLowerCase()+"%' and a.eliminado='0' "+querySucursal+" order by suc.nombre, a.id_articulo asc, tal.id_talle asc;";
            ResultSet rs = this.p.bd.listarArticulos(consulta);
             while(rs.next()){
                String id_articulo = rs.getString(1);
                registros[0]=id_articulo; //id_articulo
                registros[1]=rs.getString(2); //descripcion
                registros[2]=rs.getString(3); //marca
                registros[3]=rs.getString(4); //tipo
                registros[4]=rs.getString(5); //talle
                registros[5]=rs.getString(6); //stock              
                registros[6]=Dominio.A2Decimales(rs.getString(7)); //costo  
                registros[7]=Dominio.A2Decimales(rs.getString(8)); //ganancia
                registros[8]=Dominio.A2Decimales(rs.getString(9)); //precio efectivo
                registros[9]=Dominio.A2Decimales(rs.getString(10)); //precio tarjeta 
                registros[10]=rs.getString(11); //sucursal 
                model.addRow(registros);      
                }
            tarticulos.setModel(model);
            }catch(Exception e){
                System.out.println(e.getMessage());
                 }
            totalp.setText("Cantidad de Productos: "+tarticulos.getRowCount());
     
    }



   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        titl = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        nuevo = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        costo = new javax.swing.JTextField();
        precioe = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ganancia = new javax.swing.JTextField();
        marca = new javax.swing.JTextField();
        preciot = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ttalles = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        sucursalSelectEdit = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tarticulos = new javax.swing.JTable();
        totalp = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        actualizarPrecios = new javax.swing.JButton();
        actualizarCostos = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        sucursalSelectSearch = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO DE ARTICULOS");
        setToolTipText("");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        titl.setBorder(javax.swing.BorderFactory.createTitledBorder("Alta/Modificación de Artículos"));
        titl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                titlKeyReleased(evt);
            }
        });

        jLabel2.setText("Descripción");

        jLabel3.setText("Costo");

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

        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });

        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setEnabled(false);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modificar.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
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

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16 (Save).jpg"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setEnabled(false);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costoActionPerformed(evt);
            }
        });
        costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                costoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                costoKeyTyped(evt);
            }
        });

        precioe.setEditable(false);
        precioe.setEnabled(false);
        precioe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioeActionPerformed(evt);
            }
        });
        precioe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                precioeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precioeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioeKeyTyped(evt);
            }
        });

        jLabel6.setText("Marca");

        jLabel7.setText("Ganancia (%)");

        jLabel9.setText("Precio de Venta Efectivo");

        jLabel10.setText("Precio de Venta Tarjeta");

        ganancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gananciaActionPerformed(evt);
            }
        });
        ganancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gananciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gananciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gananciaKeyTyped(evt);
            }
        });

        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });
        marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                marcaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                marcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                marcaKeyTyped(evt);
            }
        });

        preciot.setEditable(false);
        preciot.setEnabled(false);
        preciot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preciotActionPerformed(evt);
            }
        });
        preciot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                preciotKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                preciotKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                preciotKeyTyped(evt);
            }
        });

        jLabel11.setText("Tipo de Articulo");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ropa", "Jean", "Calzado" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane3.setAutoscrolls(true);

        ttalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Talle", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ttalles.setRowHeight(20);
        ttalles.getTableHeader().setReorderingAllowed(false);
        ttalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ttallesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ttalles);

        jLabel5.setText("Sucursal");

        sucursalSelectEdit.setToolTipText("");
        sucursalSelectEdit.setEnabled(false);
        sucursalSelectEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucursalSelectEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titlLayout = new javax.swing.GroupLayout(titl);
        titl.setLayout(titlLayout);
        titlLayout.setHorizontalGroup(
            titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlLayout.createSequentialGroup()
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlLayout.createSequentialGroup()
                        .addComponent(nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(titlLayout.createSequentialGroup()
                        .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(titlLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombre)
                            .addComponent(costo)
                            .addComponent(tipo, 0, 100, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(marca, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(ganancia)
                    .addComponent(precioe)
                    .addComponent(preciot))
                .addGap(26, 26, 26)
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actualizar))
                .addGap(55, 55, 55)
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(titlLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(sucursalSelectEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(786, 786, 786)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlLayout.setVerticalGroup(
            titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlLayout.createSequentialGroup()
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, titlLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(titlLayout.createSequentialGroup()
                                .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(ganancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(precioe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(preciot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                            .addGroup(titlLayout.createSequentialGroup()
                                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(19, 19, 19)
                                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar)
                    .addComponent(nuevo))
                .addGap(30, 30, 30))
            .addGroup(titlLayout.createSequentialGroup()
                .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(guardar)
                        .addGap(15, 15, 15)
                        .addComponent(eliminar)
                        .addGap(24, 24, 24)
                        .addComponent(modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actualizar))
                    .addGroup(titlLayout.createSequentialGroup()
                        .addGroup(titlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(sucursalSelectEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel4.setText("BUSCAR:");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Productos de Venta"));
        jPanel2.setAutoscrolls(true);

        jScrollPane2.setAutoscrolls(true);

        tarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Marca", "Tipo", "Talle", "Stock", "Costo", "% Ganancia", "$ Efectivo", "$ Tarjeta", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tarticulos.getTableHeader().setReorderingAllowed(false);
        tarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tarticulosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tarticulos);
        if (tarticulos.getColumnModel().getColumnCount() > 0) {
            tarticulos.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        totalp.setText("Cantidad de Artículos: 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
                    .addGap(32, 32, 32)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 439, Short.MAX_VALUE)
                .addComponent(totalp))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
        );

        jLabel1.setText("FILTRAR POR SUCURSAL:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizaciones masivas"));

        actualizarPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modificar.png"))); // NOI18N
        actualizarPrecios.setText("Actualizar Precios");
        actualizarPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarPreciosActionPerformed(evt);
            }
        });

        actualizarCostos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modificar.png"))); // NOI18N
        actualizarCostos.setText("Actualizar Costos");
        actualizarCostos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarCostosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(actualizarPrecios)
                .addGap(40, 40, 40)
                .addComponent(actualizarCostos)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actualizarPrecios)
                    .addComponent(actualizarCostos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        sucursalSelectSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        sucursalSelectSearch.setToolTipText("");
        sucursalSelectSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucursalSelectSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(sucursalSelectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titl, javax.swing.GroupLayout.PREFERRED_SIZE, 1706, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(sucursalSelectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
// TODO add your handling code here:
    cargar(buscar.getText());
}//GEN-LAST:event_buscarKeyReleased

     private boolean validarDatos(){
     boolean validar = true;
       String nom=nombre.getText();
       String cos=costo.getText();
       String gan=ganancia.getText();
       if((nom.length()==0) || (cos.length()==0) || (gan.length()==0)){          
            JOptionPane.showMessageDialog(this,"Los campos "+jLabel2.getText()+", "+jLabel3.getText()+", "+jLabel7.getText()+", no deben ser vacíos.","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
            validar= false;
        }          
       return validar;
       } 

     
  void guardarTallesDeArticulos(int idArticulo, String sucursal){
    String tipoArticulo = tipo.getSelectedItem().toString();                  
    int filas = ttalles.getRowCount();
    model= (DefaultTableModel) ttalles.getModel();
    for (int i=0;i<filas;i++){ 
        String talle =model.getValueAt(i, 0).toString();
        String stock = model.getValueAt(i, 1) != null && !model.getValueAt(i, 1).toString().isEmpty()? model.getValueAt(i, 1).toString() : "0";
        this.p.bd.agregarTalleDeArticulo(idArticulo, tipoArticulo, talle, sucursal, stock);
     }      
  }   
    
    
  private void nuevoArticulo() {
        if (validarDatos()){                    
              int n=this.p.bd.agregarArticulo(nombre.getText(),marca.getText(),Dominio.A2Decimales(costo.getText()),Dominio.A2Decimales(ganancia.getText()),precioe.getText(),preciot.getText(), tipo.getSelectedItem().toString(),this);
              if(n>0){
                  try {
                      ResultSet rs = this.p.bd.buscarUltimoArticulo();
                      if (rs.next()){
                           int idArticulo = Integer.parseInt(rs.getString(1));
                           String sucursal = sucursalSelectEdit.getSelectedItem().toString();
                           guardarTallesDeArticulos(idArticulo, sucursal);
                      }                            
                      JOptionPane.showMessageDialog(this, "Producto Guardado con Exito");
                      if(agregar!=null){
                          agregar.cargar("");
                      }
                  } catch (SQLException ex) {
                      Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  }
               principal();
               cargar("");
              }  

          }  
        
        
    
    private void modificarArticulo(){    
        if (tarticulos.getSelectedRow()== -1) {
            JOptionPane.showMessageDialog(this,"No ha seleccionado ningún artículo","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }else{
            cancelar.setEnabled(true);
            nuevo.setEnabled(false);
            guardar.setEnabled(true);
            nombre.requestFocus();
            nombre.setEnabled(true);
            marca.setEnabled(true);
            costo.setEnabled(true);
            ganancia.setEnabled(true);
            tarticulos.setEnabled(false);
            eliminar.setEnabled(false);
            guardar.setEnabled(false);
            modificar.setEnabled(false);
            actualizar.setEnabled(true);
            buscar.setEnabled(false);        
            int fila= tarticulos.getSelectedRow();
            String id_articulo = tarticulos.getValueAt(fila, 0).toString();
            if (!this.p.bd.siArticuloSeFacturo(id_articulo)){
                tipo.setEnabled(true);
            }
            //tipo.setEnabled(true);
            String cod = tarticulos.getValueAt(fila, 0).toString();         
            String nom =  tarticulos.getValueAt(fila, 1).toString();
            String mar = tarticulos.getValueAt(fila, 2).toString();
            String tip = tarticulos.getValueAt(fila, 3).toString();         
            String cos = tarticulos.getValueAt(fila, 6).toString();
            String gan = tarticulos.getValueAt(fila, 7).toString();
            String pree = tarticulos.getValueAt(fila, 8).toString();
            String pret = tarticulos.getValueAt(fila, 9).toString(); 
            String sucursal = tarticulos.getValueAt(fila, 10).toString();
            setCodigoArticulo(cod);
            nombre.setText(nom);
            marca.setText(mar);
            costo.setText(cos);
            ganancia.setText(gan);
            precioe.setText(pree);
            preciot.setText(pret);
            ttalles.setVisible(true);
            tipo.setSelectedItem(tip);
            sucursalSelectEdit.setEnabled(true);
            sucursalSelectEdit.setSelectedItem(sucursal);
            mostrarTalles(Constantes.ACCION_EDICION);
        }
    }
    
   private void actualizarArticulo(){
        if (validarDatos()){                     
            try {
                int fila= tarticulos.getSelectedRow();
                int idArticulo = Integer.parseInt(tarticulos.getValueAt(fila, 0).toString());
                String sucursal = sucursalSelectEdit.getSelectedItem().toString();
                String tipoActual = tipo.getSelectedItem().toString();
                ResultSet rs = this.p.bd.obtenerTipoDeArticuloPorIdArticulo(idArticulo);
                String tipoAnterior = tipoActual;
                if (rs.next()){
                   tipoAnterior = rs.getString(1);
                }
        
                this.p.bd.modificarArticulo(idArticulo,nombre.getText(),marca.getText(),costo.getText(),ganancia.getText(),precioe.getText(),preciot.getText(), tipoActual,this);
                this.p.bd.eliminarTallesDeArticulos(idArticulo, tipoAnterior.equals(tipoActual) ? sucursal : null);
                guardarTallesDeArticulos(idArticulo, sucursal);
                JOptionPane.showMessageDialog(null, "Articulo modificado con Exito.");
                if(agregar!=null){
                    agregar.cargar("");
                }
                cargar("");
                principal();
            } catch (SQLException ex) {
                Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          
   }
        
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
      
    }//GEN-LAST:event_formKeyReleased

    private void tarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tarticulosMouseClicked
      if (evt.getClickCount()==2){
          modificarArticulo();
      }
    }//GEN-LAST:event_tarticulosMouseClicked

    private void titlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titlKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_titlKeyReleased

    private void ttallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttallesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ttallesMouseClicked

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        mostrarTalles(Constantes.ACCION_NUEVO);
    }//GEN-LAST:event_tipoActionPerformed

    private void preciotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciotKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_preciotKeyTyped

    private void preciotKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciotKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_preciotKeyReleased

    private void preciotKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciotKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_preciotKeyPressed

    private void preciotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preciotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preciotActionPerformed

    private void marcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_marcaKeyTyped
        if (marca.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_marcaKeyTyped

    private void marcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_marcaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaKeyReleased

    private void marcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_marcaKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
                actualizarArticulo();
            }
            if (guardar.isEnabled()){
                nuevoArticulo();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_marcaKeyPressed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaActionPerformed

    private void gananciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gananciaKeyTyped
        if (ganancia.getText().length() == Dominio.DOM_PORCENTAJE){
            evt.consume();
        }else{
            char l = evt.getKeyChar();
            if (((l<'0' || l>'9') && (l!='.')) || (l=='.' && ganancia.getText().indexOf('.')!=-1)){
                evt.consume();
            }
        }
    }//GEN-LAST:event_gananciaKeyTyped

    private void gananciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gananciaKeyReleased
        float gan;
        float cos;
        if (!ganancia.getText().equals("")){
            gan=Float.valueOf(ganancia.getText());
        }
        else{
            gan=0;
        }

        if (!costo.getText().equals("")){
            cos=Float.valueOf(costo.getText());
        }else{
            cos=0;
        }
        try{
            float efectivo =cos + (gan*cos/100);
            float tarjeta = efectivo +(Constantes.PORCENTAJE_TARJETA_DEFECTO*efectivo/100);
            precioe.setText(Dominio.A2Decimales(Float.toString(efectivo)));
            preciot.setText(Dominio.A2Decimales(Float.toString(tarjeta)));
        }
        catch (NumberFormatException ex){

        }
    }//GEN-LAST:event_gananciaKeyReleased

    private void gananciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gananciaKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
                actualizarArticulo();
            }
            if (guardar.isEnabled()){
                nuevoArticulo();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_gananciaKeyPressed

    private void gananciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gananciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gananciaActionPerformed

    private void precioeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_precioeKeyTyped

    private void precioeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_precioeKeyReleased

    private void precioeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioeKeyPressed

    private void precioeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioeActionPerformed

    private void costoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costoKeyTyped
        if (costo.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }else{
            char l = evt.getKeyChar();
            if (((l<'0' || l>'9') && (l!='.')) || (l=='.' && costo.getText().indexOf('.')!=-1)){
                evt.consume();
            }
        }
    }//GEN-LAST:event_costoKeyTyped

    private void costoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costoKeyReleased

        float gan;
        float cos;
        if (!ganancia.getText().equals("")){
            gan=Float.valueOf(ganancia.getText());
        }
        else{
            gan=0;
        }

        if (!costo.getText().equals("")){
            cos=Float.valueOf(costo.getText());
        }else{
            cos=0;
        }
        try{
            float efectivo =cos + (gan*cos/100);
            float tarjeta = efectivo +(Constantes.PORCENTAJE_TARJETA_DEFECTO*efectivo/100);
            precioe.setText(Dominio.A2Decimales(Float.toString(efectivo)));
            preciot.setText(Dominio.A2Decimales(Float.toString(tarjeta)));
        }
        catch (NumberFormatException ex){

        }
    }//GEN-LAST:event_costoKeyReleased

    private void costoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costoKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
                actualizarArticulo();
            }
            if (guardar.isEnabled()){
                nuevoArticulo();
            }
        }
    }//GEN-LAST:event_costoKeyPressed

    private void costoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costoActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        nuevoArticulo();
    }//GEN-LAST:event_guardarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        actualizarArticulo();
    }//GEN-LAST:event_actualizarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        modificarArticulo();
    }//GEN-LAST:event_modificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed

        if (tarticulos.getSelectedRow()== -1) {
            JOptionPane.showMessageDialog(this,"No ha seleccionado ningún artículo","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
        }else{
            if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, "Desea eliminar al producto seleccionado?" )){
                int fila= tarticulos.getSelectedRow();
                String cod="";
                cod=tarticulos.getValueAt(fila, 0).toString();
                int n=this.p.bd.eliminarArticulo(cod);
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Producto eliminado con Exito.");
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha podido eliminar al producto.");
                }
                cargar("");
                if(agregar!=null){
                    agregar.cargar("");
                }
                principal();
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        principal();
    }//GEN-LAST:event_cancelarActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        cancelar.setEnabled(true);
        nuevo.setEnabled(false);
        guardar.setEnabled(true);
        nombre.requestFocus();
        nombre.setEnabled(true);
        marca.setEnabled(true);
        costo.setEnabled(true);
        sucursalSelectEdit.setEnabled(true);
        sucursalSelectEdit.setSelectedIndex(0);
        ganancia.setEnabled(true);
        tipo.setEnabled(true);
        eliminar.setEnabled(false);
        modificar.setEnabled(false);
        buscar.setEnabled(false);
        actualizarCostos.setEnabled(false);
        actualizarPrecios.setEnabled(false);
        setCodigoArticulo("0");
        mostrarTalles(Constantes.ACCION_NUEVO);
    }//GEN-LAST:event_nuevoActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        if (nombre.getText().length() == Dominio.DOM_NOMBRES){
            evt.consume();
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased

    }//GEN-LAST:event_nombreKeyReleased

    private void nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (actualizar.isEnabled()){
                actualizarArticulo();
            }
            if (guardar.isEnabled()){
                nuevoArticulo();
            }
        }
    }//GEN-LAST:event_nombreKeyPressed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
        nombre.transferFocus();
    }//GEN-LAST:event_nombreActionPerformed

    private void actualizarCostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarCostosActionPerformed
       ActualizarCostos actualizarCostos = new ActualizarCostos(this.p);
       boolean actualizado = false;
       while (!actualizado) {
        if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null, actualizarCostos, "Actualizar costos de artículos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "default")){
           String costo = actualizarCostos.getCosto().getText();
           String descripcion = actualizarCostos.getDescripcion().getText();
           
           if (costo.isEmpty() || descripcion.isEmpty()) {
              JOptionPane.showMessageDialog(this,"Los campos no deben ser vacios.","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
           }else{
               int n=this.p.bd.ActualizarCostosDeArticulos(descripcion,  Float.parseFloat(costo));
                if(n>0){
                    int cantidad = this.p.bd.CantidadDeArticulos(descripcion); 
                    JOptionPane.showMessageDialog(this, "Se han actualizado " + cantidad +  " artículos correctamente."); 
                    cargar("");
                }else{
                    JOptionPane.showMessageDialog(this, "No se ha actualizado ningún artículo.");
                } 
               actualizado = true;
           }
        }else {
           actualizado = true;  
        }
       }  
    }//GEN-LAST:event_actualizarCostosActionPerformed

    private void sucursalSelectEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucursalSelectEditActionPerformed
       if (sucursalSelectEdit.isEnabled()){
             if (getCodigoArticulo().equals("0")){
           mostrarTalles(Constantes.ACCION_NUEVO);
                }else {
                    mostrarTalles(Constantes.ACCION_EDICION);
                }
       }
     
    }//GEN-LAST:event_sucursalSelectEditActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void actualizarPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarPreciosActionPerformed
        ActualizarPrecios actualizarPrecio = new ActualizarPrecios(this.p);
       boolean actualizado = false;
       while (!actualizado) {
        if (JOptionPane.OK_OPTION == JOptionPane.showOptionDialog(null, actualizarPrecio, "Actualizar precios de artículos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, "default")){
           String precio = actualizarPrecio.getPrecio().getText();
           String descripcion = actualizarPrecio.getDescripcion().getText();
           
           if (precio.isEmpty() || descripcion.isEmpty()) {
              JOptionPane.showMessageDialog(this,"Los campos no deben ser vacios.","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
           }else{
               int n=this.p.bd.ActualizarPreciosDeArticulos(descripcion,  Float.parseFloat(precio));
                if(n>0){
                    int cantidad = this.p.bd.CantidadDeArticulos(descripcion); 
                    JOptionPane.showMessageDialog(this, "Se han actualizado " + cantidad +  " artículos correctamente."); 
                    cargar("");
                }else{
                    JOptionPane.showMessageDialog(this, "No se ha actualizado ningún artículo.");
                } 
               actualizado = true;
           }
        }else {
           actualizado = true;  
        }

      }
    }//GEN-LAST:event_actualizarPreciosActionPerformed

    private void sucursalSelectSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucursalSelectSearchActionPerformed
       cargar(buscar.getText());
    }//GEN-LAST:event_sucursalSelectSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton actualizarCostos;
    private javax.swing.JButton actualizarPrecios;
    private javax.swing.JTextField buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField costo;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField ganancia;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField marca;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JTextField precioe;
    private javax.swing.JTextField preciot;
    private javax.swing.JComboBox<String> sucursalSelectEdit;
    private javax.swing.JComboBox<String> sucursalSelectSearch;
    private javax.swing.JTable tarticulos;
    private javax.swing.JComboBox tipo;
    private javax.swing.JPanel titl;
    private javax.swing.JLabel totalp;
    private javax.swing.JTable ttalles;
    // End of variables declaration//GEN-END:variables

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

   


}
