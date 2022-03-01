/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemaTienda;


import claseConectar.BaseDatos;
import claseConectar.Dominio;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.eclipse.persistence.tools.file.FileUtil;

/**
 *
 * @author Ezenic
 */
public class Principal extends javax.swing.JFrame {
    String id_mcliente;
    String micliente;
    public UsuarioLogueado usuarioLogueado = null;
    BaseDatos bd;

    public Principal() {
        bd = new BaseDatos();
        initComponents();       
        configuracionEstandarComponentes();
        escritorio = Dominio.configurarEscritorio(escritorio);
        
        setExtendedState(Frame.MAXIMIZED_BOTH);
        ImageIcon image = new ImageIcon("src/Imagenes/ropa.png");
        setIconImage(image.getImage());       
        cambiarImagenFondo();
         this.fondo.setSize(Dominio.pantallaTamano.width, Dominio.pantallaTamano.height); 
         this.panelInicio.setLocation((this.fondo.getWidth()/2)-(this.panelInicio.getWidth()/2), (this.fondo.getHeight()/2)-(this.panelInicio.getHeight()/2));
        //Al ancho de la pantalla lo divido en 2 y le resto la mitad del ancho de mi ventana, con eso queda centrada en el eje X, para el eje Y es lo mismo pero con el alto: 
        //this.setLocation((Dominio.pantallaTamano.width/2)-(this.getWidth()/2), (Dominio.pantallaTamano.height/2)-(this.getHeight()/2));
      
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        panelInicio = new javax.swing.JPanel();
        iniciarSesionLabel = new javax.swing.JLabel();
        usuarioLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        misclientes = new javax.swing.JMenuItem();
        cerrarSesion = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        ventas = new javax.swing.JMenu();
        nuevaFactura = new javax.swing.JMenuItem();
        consultar = new javax.swing.JMenuItem();
        articulos = new javax.swing.JMenu();
        informes = new javax.swing.JMenu();
        caja = new javax.swing.JMenuItem();
        deudores = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        estadisticas = new javax.swing.JMenuItem();
        configuracion = new javax.swing.JMenu();
        cambiarImagen = new javax.swing.JMenuItem();
        tema = new javax.swing.JMenu();
        classic = new javax.swing.JMenuItem();
        windows = new javax.swing.JMenuItem();
        metal = new javax.swing.JMenuItem();
        admin = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema de Tienda");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        escritorio.setBackground(new java.awt.Color(204, 255, 204));
        escritorio.setAutoscrolls(true);

        panelInicio.setBackground(new java.awt.Color(153, 153, 255));
        panelInicio.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        panelInicio.setToolTipText("");

        iniciarSesionLabel.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        iniciarSesionLabel.setText("INICIO DE SESION");

        usuarioLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usuarioLabel.setText("Usuario");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordLabel.setText("Contraseña");

        nombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreUsuarioKeyPressed(evt);
            }
        });

        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Iniciar Sesión");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordLabel)
                            .addComponent(usuarioLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(password)
                            .addComponent(nombreUsuario)))
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(iniciarSesionLabel)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(iniciarSesionLabel)
                .addGap(34, 34, 34)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addComponent(usuarioLabel)
                        .addGap(18, 18, 18)
                        .addComponent(passwordLabel))
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        iniciarSesionLabel.getAccessibleContext().setAccessibleName("Iniciar Sesión");

        escritorio.add(panelInicio);
        panelInicio.setBounds(370, 220, 545, 252);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        escritorio.add(fondo);
        fondo.setBounds(0, 0, 1260, 710);

        jMenuBar1.setName("Sistema Contable"); // NOI18N

        archivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/archivo.png"))); // NOI18N
        archivo.setText("Archivo");
        archivo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                archivoMouseMoved(evt);
            }
        });
        archivo.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                archivoMenuSelected(evt);
            }
        });
        archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoActionPerformed(evt);
            }
        });

        misclientes.setText("Mis Clientes");
        misclientes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                misclientesMouseMoved(evt);
            }
        });
        misclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                misclientesActionPerformed(evt);
            }
        });
        archivo.add(misclientes);

        cerrarSesion.setText("Cerrar Sesión");
        cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionActionPerformed(evt);
            }
        });
        archivo.add(cerrarSesion);

        salir.setText("Salir");
        salir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                salirMouseMoved(evt);
            }
        });
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        archivo.add(salir);

        jMenuBar1.add(archivo);

        ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ventas.png"))); // NOI18N
        ventas.setText("Facturas de Ventas");
        ventas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ventasMouseMoved(evt);
            }
        });

        nuevaFactura.setText("Nueva Factura de Venta");
        nuevaFactura.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                nuevaFacturaMouseMoved(evt);
            }
        });
        nuevaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaFacturaActionPerformed(evt);
            }
        });
        ventas.add(nuevaFactura);

        consultar.setText("Consultar, Modificar, Eliminar Factura de Venta");
        consultar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                consultarMouseMoved(evt);
            }
        });
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        ventas.add(consultar);

        jMenuBar1.add(ventas);

        articulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ropa.png"))); // NOI18N
        articulos.setText("Artículos");
        articulos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                articulosMouseMoved(evt);
            }
        });
        articulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                articulosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                articulosMouseReleased(evt);
            }
        });
        articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                articulosActionPerformed(evt);
            }
        });
        jMenuBar1.add(articulos);

        informes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/formularios.png"))); // NOI18N
        informes.setText("Informes");
        informes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                informesMouseMoved(evt);
            }
        });
        informes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informesActionPerformed(evt);
            }
        });

        caja.setText("Caja");
        caja.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cajaMouseMoved(evt);
            }
        });
        caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaActionPerformed(evt);
            }
        });
        informes.add(caja);

        deudores.setText("Deudores");
        deudores.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                deudoresMouseMoved(evt);
            }
        });
        deudores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deudoresActionPerformed(evt);
            }
        });
        informes.add(deudores);

        jMenuItem1.setText("Acreedores");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        informes.add(jMenuItem1);

        estadisticas.setText("Estadísticas Clientes");
        estadisticas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                estadisticasMouseMoved(evt);
            }
        });
        estadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadisticasActionPerformed(evt);
            }
        });
        informes.add(estadisticas);

        jMenuBar1.add(informes);

        configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mantenimiento.png"))); // NOI18N
        configuracion.setText("Configuración");
        configuracion.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                configuracionMouseMoved(evt);
            }
        });

        cambiarImagen.setText("Cambiar Fondo de Pantalla");
        cambiarImagen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cambiarImagenMouseMoved(evt);
            }
        });
        cambiarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarImagenActionPerformed(evt);
            }
        });
        configuracion.add(cambiarImagen);

        tema.setText("Cambiar Tema");
        tema.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                temaMouseMoved(evt);
            }
        });
        tema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temaActionPerformed(evt);
            }
        });

        classic.setText("Windows Classic");
        classic.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                classicMouseMoved(evt);
            }
        });
        classic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classicActionPerformed(evt);
            }
        });
        tema.add(classic);

        windows.setText("Windows");
        windows.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                windowsMouseMoved(evt);
            }
        });
        windows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                windowsActionPerformed(evt);
            }
        });
        tema.add(windows);

        metal.setText("Metal");
        metal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                metalMouseMoved(evt);
            }
        });
        metal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metalActionPerformed(evt);
            }
        });
        tema.add(metal);

        configuracion.add(tema);

        jMenuBar1.add(configuracion);

        admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/admin-icon.png"))); // NOI18N
        admin.setText("Admin");
        admin.setToolTipText("");

        jMenuItem2.setText("Usuarios");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        admin.add(jMenuItem2);

        jMenuBar1.add(admin);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaFacturaActionPerformed
        try { 
           FacturaVenta fv= new FacturaVenta(this);
            escritorio.add(fv);
            escritorio.setSelectedFrame(fv);
            fv.show();  
            fv.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }//GEN-LAST:event_nuevaFacturaActionPerformed

    private void archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoActionPerformed
      
    }//GEN-LAST:event_archivoActionPerformed

    private void archivoMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_archivoMenuSelected
     
    }//GEN-LAST:event_archivoMenuSelected

    private void misclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_misclientesActionPerformed

        try {
            IngresoMisClientes cli= new IngresoMisClientes(this);
            escritorio.add(cli);
            escritorio.setSelectedFrame(cli);
            cli.show();
            cli.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_misclientesActionPerformed

    private void articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_articulosActionPerformed


    }//GEN-LAST:event_articulosActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
    if (JOptionPane.OK_OPTION == JOptionPane.showInternalConfirmDialog(this.escritorio, "¿Desea Salir?" )){
          this.dispose();         
       }   
    }//GEN-LAST:event_salirActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
          try {
            BuscarFacturaVenta buscar= new BuscarFacturaVenta(this);
            escritorio.add(buscar);
            escritorio.setSelectedFrame(buscar);
            buscar.show(); 
            buscar.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_consultarActionPerformed

    private void informesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informesActionPerformed
            Caja caja= new Caja(this);
            escritorio.add(caja);  
            escritorio.setSelectedFrame(caja);
            caja.show(); 
        
    }//GEN-LAST:event_informesActionPerformed

    private void articulosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_articulosMouseReleased
  
    }//GEN-LAST:event_articulosMouseReleased

    private void articulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_articulosMouseClicked
       try {
          Articulos  art = new Articulos(this);
          this.escritorio.add(art);
         this.escritorio.setSelectedFrame(art);
          art.show();
            art.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_articulosMouseClicked

    private void deudoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deudoresActionPerformed

        try {
              Deudores deu = new Deudores(this);
              this.escritorio.add(deu);
              this.escritorio.setSelectedFrame(deu);
              deu.show();
              deu.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deudoresActionPerformed

    private void cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaActionPerformed
  
        try {
            Caja caja = new Caja(this);
            this.escritorio.add(caja);
            this.escritorio.setSelectedFrame(caja);
            caja.show();
            caja.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cajaActionPerformed

    private void estadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadisticasActionPerformed
     try {
         EstadisticasClientes est = new EstadisticasClientes(this);
         this.escritorio.add(est);
         this.escritorio.setSelectedFrame(est);
         est.show();
         est.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_estadisticasActionPerformed

    private void archivoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivoMouseMoved
     archivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
              

    }//GEN-LAST:event_archivoMouseMoved

    private void ventasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasMouseMoved
        ventas.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_ventasMouseMoved

    private void articulosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_articulosMouseMoved
        articulos.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_articulosMouseMoved

    private void informesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_informesMouseMoved
         informes.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_informesMouseMoved

    private void salirMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseMoved
       salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_salirMouseMoved

    private void misclientesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_misclientesMouseMoved
        misclientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_misclientesMouseMoved

    private void nuevaFacturaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevaFacturaMouseMoved
        nuevaFactura.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_nuevaFacturaMouseMoved

    private void consultarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseMoved
       consultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_consultarMouseMoved

    private void cajaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaMouseMoved
        caja.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cajaMouseMoved

    private void deudoresMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deudoresMouseMoved
        deudores.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deudoresMouseMoved

    private void estadisticasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estadisticasMouseMoved
        estadisticas.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_estadisticasMouseMoved

    private void configuracionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionMouseMoved
        configuracion.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_configuracionMouseMoved

    private void cambiarImagenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cambiarImagenMouseMoved
       cambiarImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cambiarImagenMouseMoved

    private void cambiarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarImagenActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg","png");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Abrir archivo de Imagen");
        if (chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try {
                File archivo = chooser.getSelectedFile();
                File destino = new File("src/Imagenes/fondo.jpg");
                FileUtil.copy(new FileInputStream(archivo), new FileOutputStream(destino));


            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error "+ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error "+ex.getMessage());
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            cambiarImagenFondo();
        }
    }//GEN-LAST:event_cambiarImagenActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
           if (JOptionPane.OK_OPTION == JOptionPane.showInternalConfirmDialog(this.escritorio, "¿Desea Salir?" )){
             this.dispose();         
          }
    }//GEN-LAST:event_formWindowClosing

    private void classicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classicActionPerformed
         try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_classicActionPerformed

    private void metalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metalActionPerformed
         try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_metalActionPerformed

    private void temaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temaActionPerformed
       
    }//GEN-LAST:event_temaActionPerformed

    private void windowsMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowsMouseMoved
        windows.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_windowsMouseMoved

    private void temaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temaMouseMoved
          tema.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_temaMouseMoved

    private void classicMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classicMouseMoved
          classic.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_classicMouseMoved

    private void metalMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_metalMouseMoved
        metal.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_metalMouseMoved

    private void windowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_windowsActionPerformed
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_windowsActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Acreedores ac = new Acreedores(this);
            this.escritorio.add(ac);
            this.escritorio.setSelectedFrame(ac);
            ac.show();        
            ac.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
          try {
            Usuarios cli= new Usuarios(this);
            escritorio.add(cli);
            escritorio.setSelectedFrame(cli);
            cli.show();
            cli.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          iniciarSesion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void iniciarSesion(){
        if (validarInicio()){
          JOptionPane.showMessageDialog(null, "Usuario logueado con Exito");
          panelInicio.setVisible(false);       
          configuracionEstandarComponentes();
          this.setTitle("Sistema de Tienda. Usuario Logueado: "+this.usuarioLogueado.getNombreUsuario());
        }
    }
    private void cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarSesionActionPerformed
          
        if (JOptionPane.OK_OPTION == JOptionPane.showInternalConfirmDialog(this.escritorio, "¿Desea cerrar sesión?" )){
            usuarioLogueado = null;
            configuracionEstandarComponentes();
            for (JInternalFrame frame: escritorio.getAllFrames()){
                frame.dispose();
            }
            panelInicio.setVisible(true);     
            nombreUsuario.setText("");
            password.setText("");
            this.setTitle("Sistema de Tienda");
       }   
    }//GEN-LAST:event_cerrarSesionActionPerformed

    private void nombreUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreUsuarioKeyPressed
      if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
          iniciarSesion();
      }
    }//GEN-LAST:event_nombreUsuarioKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
      if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
          iniciarSesion();
      }
    }//GEN-LAST:event_passwordKeyPressed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

    }//GEN-LAST:event_formComponentResized

   private Boolean validarInicio(){
       boolean validar = true;
       String nombreUsuario= this.nombreUsuario.getText();
       String password = String.valueOf(this.password.getPassword());
       if( nombreUsuario.length()==0 || password.length()==0){          
            validar= false;
       }else{
           usuarioLogueado = this.bd.obtenerUsuarioPorNombreYPassword(nombreUsuario, password);
           if (usuarioLogueado == null){
               JOptionPane.showMessageDialog(this,"Usuario o contraseña inexistente","Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje         
               validar=false;
           }
       }          
       return validar;
   }
   
    public static void main(String args[]) {
       try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu admin;
    private javax.swing.JMenu archivo;
    private javax.swing.JMenu articulos;
    private javax.swing.JMenuItem caja;
    private javax.swing.JMenuItem cambiarImagen;
    private javax.swing.JMenuItem cerrarSesion;
    private javax.swing.JMenuItem classic;
    private javax.swing.JMenu configuracion;
    private javax.swing.JMenuItem consultar;
    private javax.swing.JMenuItem deudores;
    public javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem estadisticas;
    private javax.swing.JLabel fondo;
    private javax.swing.JMenu informes;
    private javax.swing.JLabel iniciarSesionLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem metal;
    private javax.swing.JMenuItem misclientes;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JMenuItem nuevaFactura;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenu tema;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JMenu ventas;
    private javax.swing.JMenuItem windows;
    // End of variables declaration//GEN-END:variables

    private void cambiarImagenFondo() {
        ImageIcon image = new ImageIcon("src/Imagenes/fondo.jpg");
        Image imagen2 = image.getImage();
        Image newImage = imagen2.getScaledInstance(Dominio.pantallaTamano.width, Dominio.pantallaTamano.height,Image.SCALE_SMOOTH);
        image = new ImageIcon(newImage);
        fondo.setIcon(image);
    }

    private void configuracionEstandarComponentes() {
        archivo.setVisible(false);
        articulos.setVisible(false);
        estadisticas.setVisible(false);
        ventas.setVisible(false);
        informes.setVisible(false);
        configuracion.setVisible(false);
        admin.setVisible(false);
        if (usuarioLogueado!=null){    
          if (usuarioLogueado.getPermisos().contains("PUEDE VER ARCHIVO"))
              archivo.setVisible(true);
          if (usuarioLogueado.getPermisos().contains("PUEDE VER VENTAS"))
              ventas.setVisible(true);
          if (usuarioLogueado.getPermisos().contains("PUEDE VER ARTICULOS"))
              articulos.setVisible(true);
          if (usuarioLogueado.getPermisos().contains("PUEDE VER INFORMES"))
              informes.setVisible(true);
          if (usuarioLogueado.getPermisos().contains("PUEDE VER CONFIGURACIONES"))
              configuracion.setVisible(true);
          if (usuarioLogueado.getPermisos().contains("PUEDE VER ADMIN"))
              admin.setVisible(true);
          if (usuarioLogueado.getPermisos().contains("PUEDE VER ESTADISTICAS DE CLIENTES"))
              estadisticas.setVisible(true);
      }
    }








    
}