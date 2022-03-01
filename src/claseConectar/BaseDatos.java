/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package claseConectar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import sistemaTienda.FacturaVenta;
import sistemaTienda.IngresoMisClientes;
import sistemaTienda.Articulos;
import sistemaTienda.Constantes;
import sistemaTienda.Principal;
import sistemaTienda.UsuarioLogueado;
import sistemaTienda.Usuarios;




public class BaseDatos {
    
    Connection cn;
    Statement consulta;
    
    public BaseDatos(){
        cn = conexion();      
     } 
    
     public Connection conexion()
    {
      Connection conect = null;  
      try {
          
           //Cargamos el Driver PostgresSql
          String driver = "org.postgresql.Driver";
          String ruta = "jdbc:postgresql://localhost:5432/SistemaTienda";
          String user = "postgres";
          String password = "default";
          Class.forName(driver);
          conect=(Connection) DriverManager.getConnection(ruta,user,password);
           //Cargamos el Driver MySQL
           //Class.forName("com.mysql.jdbc.Driver");
           //conect = DriverManager.getConnection("jdbc:mysql://localhost/tienda","root","");
           //JOptionPane.showMessageDialog(null, "conectado");
           //Cargamos el Driver Access
           //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           //Conectar en red base 
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=//servidor/bd_cw/cw.mdb";
           //Conectar Localmente
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/cwnetbeans/cw.mdb";
          //conect = DriverManager.getConnection(strConect,"",""); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;
  
    }
     
     //MIS USUARIOS
        public int agregarMiUsuario(String nombreUser, String password, String rol, Usuarios usuarios) {      
       int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("insert into usuarios (nombre,password,rol) values('"+nombreUser+"', '"+password+"','"+rol+"')");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode()==0){
                  if (comprobarExistenciaUsuario(nombreUser).equals("0")) { //El nombre existe
                      JOptionPane.showMessageDialog(usuarios,"El nombre " +nombreUser+ " ya existe","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje 
                  }else{
                      try { 
                        n=consulta.executeUpdate("UPDATE misclientes SET password='"+password+"',rol='"+rol+"',eliminado='0' WHERE nombre='"+nombreUser+"'");
                       } catch (SQLException ex2) {
                          Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex2);
                          }
                  }            
               }
        }
        return n;
    }
        
        
    public int modificarUsuario(String nombreUser, String password, String rol, String cod, Usuarios usuarios) {
               int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE usuarios SET nombre='"+nombreUser+"',password='"+password+"',rol='"+rol+"' WHERE id_user='"+cod+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode()==0){
                  if (comprobarExistenciaUsuario(nombreUser).equals("0")) { //El nombre existe
                      JOptionPane.showMessageDialog(usuarios,"El nombre " +nombreUser+ " ya existe","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje 
                  }else{
                      try { 
                        n=consulta.executeUpdate("delete from usuarios  WHERE id_user='"+cod+"'");
                        n=consulta.executeUpdate("UPDATE usuarios SET password='"+password+"',rol='"+rol+"',eliminado='0' WHERE nombre='"+nombreUser+"'");
                       } catch (SQLException ex2) {
                          Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex2);
                          }
                  }            
               }
        }
        return n;
      
    }
    
     public int eliminarUsuario(String cod) {       
        int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE  usuarios SET eliminado='1' WHERE id_user='"+cod+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

        
        
     public ResultSet listarDatosMisUsuarios(String valor) {    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_user,nombre,rol FROM usuarios WHERE eliminado='0' and CONCAT(lower(nombre) , lower(rol)) LIKE '%"+valor.toLowerCase()+"%' order by id_user");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
     
     public UsuarioLogueado obtenerUsuarioPorNombreYPassword(String nombreUsuario, String password) {
         UsuarioLogueado usuarioLogueado = null;
         try {
              consulta = cn.createStatement();
              ResultSet rs = consulta.executeQuery("SELECT nombre, rol from usuarios where eliminado='0' and lower(nombre) ='"+nombreUsuario.toLowerCase()+"' and password='"+password+"'");
              if (rs.next()){
                  usuarioLogueado = new UsuarioLogueado(rs.getString(1), rs.getString(2));         
               }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return usuarioLogueado; 
    }
     
     
     //MIS CLIENTES
    
     
  public DefaultComboBoxModel getLista(String cadenaEscrita){

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {

            String query = "SELECT Nombre FROM MISCLIENTES WHERE eliminado='0' and lower(Nombre) LIKE '" + cadenaEscrita.toLowerCase() + "%';";
            consulta = cn.createStatement();
            ResultSet rs = consulta.executeQuery(query);
            while (rs.next()) {
                modelo.addElement(rs.getString("Nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

     return modelo;

    }
  
    public ResultSet obtenerSaldoAFavor(String nombre) {
        ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT saldo_favor FROM misclientes where nombre='"+nombre+"' ");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

    public Float obtenerTodosSaldoAfavor(Date de, Date ha) {
         ResultSet rs = null;
         String saldo = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT sum(saldo_favor) FROM misclientes where fecha_saldo>='"+de+"' and fecha_saldo<='"+ha+"'  and eliminado='0' ");
              if (rs.next()){
                 saldo = rs.getString(1);
              }
             if (saldo==null || saldo==""){
                saldo="0";
              }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }


         return Float.parseFloat(saldo);
    }

    public void actualizarSaldoCliente(String nombre,Date fecha, String saldo) {
        try {
            consulta = cn.createStatement();
            consulta.executeUpdate("UPDATE misclientes SET fecha_saldo='"+fecha+"',saldo_favor='"+saldo+"' WHERE nombre='"+nombre+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarSaldoClientePorID(String id_Cliente,Date fecha, String saldo) {
        try {
            consulta = cn.createStatement();
            consulta.executeUpdate("UPDATE misclientes SET fecha_saldo='"+fecha+"',saldo_favor='"+saldo+"' WHERE id_mcliente='"+id_Cliente+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
    public ResultSet buscarNombreClientePorNombre(String valor) {    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT nombre FROM misclientes WHERE eliminado='0' and (lower(nombre) LIKE '%"+valor.toLowerCase()+"%')");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
    
    public ResultSet buscarIDClientePorNombre(String nombre) {    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_mcliente FROM misclientes WHERE eliminado='0' and nombre='"+nombre+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
    
    public ResultSet listarDatosMisClientes(String valor) {    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_mcliente,nombre,telefono,correo,saldo_favor FROM misclientes WHERE eliminado='0' and CONCAT(lower(nombre) , lower(telefono), id_mcliente) LIKE '%"+valor.toLowerCase()+"%' order by id_mcliente");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

   public int agregarMiCliente(String nombre, String telefono, String correo, IngresoMisClientes cli) {      
       int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("insert into misclientes (nombre,telefono,correo) values('"+nombre+"', '"+telefono+"','"+correo+"')");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode()==0){
                  if (comprobarExistenciaCliente(nombre).equals("0")) { //El nombre existe
                      JOptionPane.showMessageDialog(cli,"El nombre " +nombre+ " ya existe","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje 
                  }else{
                      try { 
                        n=consulta.executeUpdate("UPDATE misclientes SET telefono='"+telefono+"',correo='"+correo+"',eliminado='0' WHERE nombre='"+nombre+"'");
                       } catch (SQLException ex2) {
                          Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex2);
                          }
                  }            
               }
        }
        return n;
    }
        
    public int eliminarMiCliente(String cod) {       
        int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE  misclientes SET eliminado='1' WHERE id_mcliente='"+cod+"'");
            n=consulta.executeUpdate("UPDATE  facturas SET eliminado='1' WHERE id_mcliente='"+cod+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int modificarMiCliente(String nombre, String telefono, String correo, String cod, IngresoMisClientes cli) {
        int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE misclientes SET nombre='"+nombre+"',telefono='"+telefono+"',correo='"+correo+"' WHERE id_mcliente='"+cod+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode()==0){
                  if (comprobarExistenciaCliente(nombre).equals("0")) { //El nombre existe
                      JOptionPane.showMessageDialog(cli,"El nombre " +nombre+ " ya existe","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje 
                  }else{
                      try { 
                        n=consulta.executeUpdate("delete from misclientes  WHERE id_mcliente='"+cod+"'");
                        n=consulta.executeUpdate("UPDATE misclientes SET telefono='"+telefono+"',correo='"+correo+"',eliminado='0' WHERE nombre='"+nombre+"'");
                       } catch (SQLException ex2) {
                          Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex2);
                          }
                  }            
               }
        }
        return n;
      
    }
    
    public String  comprobarExistenciaCliente(String nombre) {    
         ResultSet rs = null;
         String eliminado="0";
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT eliminado FROM misclientes WHERE nombre='"+nombre+"'");
              if (rs.next()){
                  eliminado = rs.getString("eliminado"); 
              }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         return eliminado; 
    }
    
        public String  comprobarExistenciaUsuario(String nombre) {    
         ResultSet rs = null;
         String eliminado="0";
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT eliminado FROM usuarios WHERE nombre='"+nombre+"'");
              if (rs.next()){
                  eliminado = rs.getString("eliminado"); 
              }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         return eliminado; 
    }
    
     public  ResultSet buscarMiCliente(String mcliente) {
      ResultSet rs = null;
      try {
          consulta =(Statement) cn.createStatement();         
          rs = consulta.executeQuery("select id_mcliente from misclientes where '"+mcliente+"' = nombre"); //toma fila por fila       
      }catch(SQLException e){
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, e);
          } 
       return rs;
       }
     
     public ResultSet listarMisClientes(){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_mcliente,nombre,telefono,correo FROM misclientes where eliminado='0' order by nombre"); //toma fila por fila       
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
     }
     
 
     
     public ResultSet buscarCliente(String nombre){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("select id_mcliente from misclientes where nombre = '"+nombre+"'");
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
     }
     
       public ResultSet mostrarDatosCliente(String nombre){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("select id_mcliente,telefono,correo,saldo_favor from misclientes where nombre = '"+nombre+"'");
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
     }
     
     //FACTURA VENTA
      public ResultSet buscarNroFacturaVenta(){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT max(id_factura) FROM facturas");
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
     }
     
     public int registrarFacturaVenta(String numero, Date fecha, String forma,String comprobante, String descuento, String motivo,String total,String estado, String entregado, String porcentaje_tarjeta, String id_mcliente){
      int n=0;
         try { 
             if (porcentaje_tarjeta.isEmpty())
                 porcentaje_tarjeta="0";
            consulta = cn.createStatement();
            n= consulta.executeUpdate("insert into facturas (fecha,forma_pago,comprobante,descuento,motivo,total,estado,entregado,id_mcliente, porcentaje_tarjeta) values('"+fecha+"', '"+forma+"','"+comprobante+"','"+descuento+"','"+motivo+"','"+total+"','"+estado+"','"+entregado+"','"+id_mcliente+"','"+porcentaje_tarjeta+"')");
            n= consulta.executeUpdate("INSERT into pagos (id_factura,fecha,entregado,estado) values('"+numero+"','"+fecha+"','"+entregado+"','pagoInicial')");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
     }
     
    public int eliminarFacturaVenta (String id_factura){
         int n=0;
         try { 
            consulta = cn.createStatement();
            n= consulta.executeUpdate("UPDATE facturas SET eliminado='1' WHERE id_factura='"+id_factura+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return n; 
     }
         
    public ResultSet mostrarFacturasVentas(String con) {
         ResultSet rs = null;   
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
    

        
   public int agregarPago(String numero,String estado,String pago, String entregado){
       int n=0;
         try { 
            consulta = cn.createStatement();
            n= consulta.executeUpdate("UPDATE facturas SET estado='"+estado+"', entregado='"+entregado+"' WHERE id_factura = '"+numero+"'");
            n= consulta.executeUpdate("INSERT into pagos (id_factura,fecha,entregado,estado) values('"+numero+"','"+new Date()+"','"+pago+"','pagoAgregado')");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
   }
   
   public ResultSet mostrarDatosFacturaVenta(String id_factura){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT f.fecha,c.nombre,f.forma_pago,f.comprobante,f.descuento,f.motivo,f.entregado, f.total,c.saldo_favor, f.porcentaje_tarjeta FROM facturas as f inner join misclientes as c on f.id_mcliente = c.id_mcliente WHERE id_factura='"+id_factura+"'");
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
     }
   
     public ResultSet obtenerCostosDeFactura(String id_factura){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT sum(costo) FROM fa WHERE id_factura='"+id_factura+"'");
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
     }
     
     public String obtenerPromedioDeDescuentos(String id_factura){
             ResultSet rs,rs2= null;
             String descuento = "0";
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT DESCUENTO FROM FACTURAS WHERE ID_FACTURA='"+id_factura+"'");
              if (rs.next()){
                  descuento = rs.getString(1);
                  rs2 = consulta.executeQuery("SELECT AVG(DESCUENTO) FROM FA  WHERE DESCUENTO > 0 AND ID_FACTURA ='"+id_factura+"'");
                  if(rs2.next()){
                      if (rs2.getString(1)==null){
                       descuento = Float.toString( Float.parseFloat(descuento)+0);   
                      }else{
                      descuento = Float.toString( Float.parseFloat(descuento)+Float.parseFloat(rs2.getString(1)) );
                 }}
              }
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return descuento;
     }
     
     public ResultSet mostrarDatosFacturaProductoVenta(String id_factura) {
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("select a.id_articulo, a.nombre, a.marca, ta.nombre, t.nombre, f.cantidad, f.precio_venta,f.precio_venta_tarjeta,f.descuento,f.motivo,f.subtotal from articulos as a"+
              " inner join fa as f on f.id_articulo = a.id_articulo"+
              " inner join talle as t on f.id_talle = t.id_talle"+
              " inner join tipo_articulo as ta on a.id_tipo=ta.id_tipo"+
              " where f.id_factura ='"+id_factura+"' order by a.nombre");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
    }
         
     public int actualizarFacturaVenta(Date fecha, String forma,String comprobante, String descuento, String motivo,String total,String estado, String entregado, String porcentaje_tarjeta, String id_factura){
       int n=0;
         try { 
            if (porcentaje_tarjeta.isEmpty())
                 porcentaje_tarjeta="0";
            consulta = cn.createStatement();
            n= consulta.executeUpdate("UPDATE facturas SET fecha='"+fecha+"', forma_pago='"+forma+"',comprobante='"+comprobante+"', descuento='"+descuento+"',motivo='"+motivo+"',"
                    + " total='"+total+"', estado='"+estado+"',entregado='"+entregado+"',porcentaje_tarjeta='"+porcentaje_tarjeta+"' WHERE id_factura = '"+id_factura+"'");
            n= consulta.executeUpdate("DELETE FROM pagos WHERE id_factura='"+id_factura+"'");
            n= consulta.executeUpdate("INSERT into pagos (id_factura,fecha,entregado,estado) values('"+id_factura+"','"+fecha+"','"+entregado+"','pagoInicial')");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
     }
     

     

 
    
    
    //FACTURA-ARTICULOS
     public int agregarFacturaArticulo(String id_factura, String id_articulo, String talle, String tipo, String cant,String costo, String ganancia, String precioe,String preciot,String descuento,String motivo, String subtotal) {
         int n=0;
         try { 
             int id_talle = obtenerIdTallePorTipoYTalle(talle, tipo);
            consulta = cn.createStatement();
            n=consulta.executeUpdate("insert into fa (id_factura, id_articulo, id_talle, cantidad, costo, ganancia,precio_venta, precio_venta_tarjeta,descuento,motivo,subtotal) values('"+id_factura+"', '"+id_articulo+"', '"+id_talle+"', '"+cant+"','"+costo+"','"+ganancia+"','"+precioe+"','"+preciot+"','"+descuento+"',"
                    + " '"+motivo+"' ,'"+subtotal+"')");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
         }
     
    
     
     public int eliminarArticulosFacturaVenta (String id_factura, String comprobante){
         int n=0;
         try { 
            consulta = cn.createStatement();
            ResultSet rs;
            String tipo = "";
             if (comprobante.equals("Factura")){
                    tipo="-"; //Si el comprobante era de factura suma al stock
             }        
            rs = devolverStockArticulosFacturaVenta(id_factura);
            String id_articulo, cantidad, id_talle;
            while (rs.next()){
              id_articulo=rs.getString(1);
              id_talle = rs.getString(2);
              cantidad=tipo+rs.getString(3);
              actualizarStockArticulosPorIdTalle(cantidad, id_articulo, id_talle); //Devuelve el Stock de artículos
            }
            n= consulta.executeUpdate("DELETE FROM fa WHERE id_factura='"+id_factura+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n; 
     }  
    

     
           
      public ResultSet devolverStockArticulosFacturaVenta(String id_factura) {
              ResultSet rs = null;
         try {
              consulta = cn.createStatement();
             
              rs = consulta.executeQuery("SELECT id_articulo, id_talle, cantidad from FA where id_factura='"+id_factura+"' "); //toma fila por fila
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
      }
     
     
      public int obtenerCantidadVendidaDeFacturaPorArticuloYIdTalle(String id_articulo, String id_talle, String id_factura){
         ResultSet rs = null;
         int cantidad=0;
         try { 
            consulta = cn.createStatement();
            rs = consulta.executeQuery("SELECT cantidad FROM fa WHERE id_articulo='"+id_articulo+"' and id_factura='"+id_factura+"' and id_talle='"+id_talle+"'  ");
            if (rs.next()){
                  cantidad = Integer.parseInt(rs.getString(1));                 
            }                 
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);        
               }       
        return cantidad;
       }
      
      public int obtenerCantidadVendidaDeFacturaPorArticuloTalleTipoYFactura(String id_articulo, String talle, String tipo, String id_factura){
          int id_talle = obtenerIdTallePorTipoYTalle(talle, tipo);
        return obtenerCantidadVendidaDeFacturaPorArticuloYIdTalle( id_articulo,  Integer.toString(id_talle),  id_factura);
       }

    //ARTICULOS
      
      
    public boolean siArticuloSeFacturo(String id_articulo) {
        ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_articulo from FA where id_articulo='"+id_articulo+"'");
              return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true; 
    }
 
       
    public ResultSet obtenerCantidadPorArticuloYTalle(String id_factura) {
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_articulo, id_talle, cantidad from FA where id_factura='"+id_factura+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
     
    public ResultSet listarArticulos(String con) {
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

    public int agregarArticulo(String nombre, String marca, String costo,String ganancia,String precioe,String preciot, String tipoArticulo, Articulos pro) {
         int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("insert into articulos (nombre, marca, costo, ganancia,precio_venta, precio_venta_tarjeta, id_tipo) values('"+nombre+"', '"+marca+"', '"+costo+"','"+ganancia+"','"+precioe+"','"+preciot+"', (Select id_tipo from tipo_articulo where nombre='"+tipoArticulo+"'  ))");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return n;
        
        
    }

    public int eliminarArticulo(String cod) {
         int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE articulos SET eliminado = '1' WHERE id_articulo='"+cod+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int modificarArticulo(int idArticulo,String nombre, String marca, String costo,String ganancia,String precioe,String preciot, String tipoArticulo, Articulos pro) {       
         int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE articulos SET nombre='"+nombre+"',marca='"+marca+"',costo='"+costo+"',ganancia='"+ganancia+"',"
                    + "precio_venta='"+precioe+"',precio_venta_tarjeta='"+preciot+"', id_tipo=(Select id_tipo from tipo_articulo where nombre='"+tipoArticulo+"')"
                            + " WHERE id_articulo='"+idArticulo+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode()==0){
                  if (comprobarExistenciaArticulo(nombre).equals("0")) { //El nombre existe
                      JOptionPane.showMessageDialog(pro,"El nombre " +nombre+ " ya existe","Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje 
                  }else{
                      try { 
                        n=consulta.executeUpdate("delete from articulos  WHERE id_articulo='"+idArticulo+"'");
                        n=consulta.executeUpdate("UPDATE articulos SET marca='"+marca+"',costo='"+costo+"',ganancia='"+ganancia+"',precio_venta='"+precioe+"',precio_venta_tarjeta='"+preciot+"',id_tipo=(Select id_tipo from tipo_articulo where nombre='"+tipoArticulo+"') eliminado='0' WHERE nombre='"+nombre+"'");
                       } catch (SQLException ex2) {
                          Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex2);
                          }
                  }            
               }
        }
        return n;
    
    }
    
        public String  comprobarExistenciaArticulo(String nombre) {    
         ResultSet rs = null;
         String eliminado="0";
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT eliminado FROM articulos WHERE nombre='"+nombre+"'");
              if (rs.next()){
                  eliminado = rs.getString("eliminado"); 
              }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         return eliminado; 
    }
    
      
        public String obtenerCostoArticulo(String id_articulo){
         ResultSet rs = null;
         String costo="0";
         try { 
            consulta = cn.createStatement();
            rs = consulta.executeQuery("SELECT costo FROM articulos WHERE id_articulo='"+id_articulo+"' ");
            if (rs.next()){
                  costo = rs.getString(1);                 
            }                 
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);        
               }       
        return costo;
       }
        
        
         public String obtenerStockArticulo(String id_articulo, String id_talle){
         ResultSet rs = null;
         String stock=null;
         try { 
            consulta = cn.createStatement();
            rs = consulta.executeQuery("SELECT stock FROM talle_articulos WHERE id_articulo='"+id_articulo+"' and id_talle = '"+id_talle+"'");
            if (rs.next()){
                  stock = rs.getString(1);                 
            }                 
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);        
               }       
        return stock;
       }
         
      public String obtenerStockArticuloPorTalleYTipo(String id_articulo, String talle, String tipo){
           int id_talle = obtenerIdTallePorTipoYTalle(talle, tipo);
           return obtenerStockArticulo(id_articulo, String.valueOf(id_talle));      
       }  
               
       public int actualizarStockArticulosPorTipoYTalle(String cant,String id_articulo, String tipo, String talle){
         int id_talle = obtenerIdTallePorTipoYTalle(talle, tipo);
         return actualizarStockArticulosPorIdTalle(cant, id_articulo, String.valueOf(id_talle));   
       }
       
       public int actualizarStockArticulosPorIdTalle(String cant,String id_articulo, String id_talle){
         int n=0;
         ResultSet rs = null;
         int stock=0;
         try {            
            consulta = cn.createStatement();      
                  stock = Integer.parseInt(obtenerStockArticulo(id_articulo, id_talle))-Integer.parseInt(cant);
                  if (stock<0){
                      stock=0;
                  }
                  n=consulta.executeUpdate("UPDATE talle_articulos SET stock='"+stock+"'  "
                          + "WHERE id_articulo='"+id_articulo+"' and id_talle ='"+id_talle+"'");                                                
            }       
          catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);        
               }       
        return n;
       }
       
       
       
   public ResultSet buscarUltimoArticulo(){
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT max(id_articulo) FROM articulos");
  
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
     }
   
      public int CantidadDeArticulos(String description) {       
     
         try { 
            consulta = cn.createStatement();        
            ResultSet rs=consulta.executeQuery("Select count(*) from talle_articulos where id_articulo in (Select id_articulo from articulos where eliminado='0' and lower(nombre) LIKE '%" + description.toLowerCase() + "%');");
             if (rs.next()){
                  return Integer.parseInt(rs.getString(1));                 
            }  
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
   
   public int ActualizarPreciosDeArticulos(String description, Float precio) {       
        int n=0;
        Float precioVentaTarjeta = precio +(Constantes.PORCENTAJE_TARJETA_DEFECTO*precio/100);  
         try { 
            consulta = cn.createStatement();        
            n=consulta.executeUpdate("UPDATE articulos SET precio_venta= '"+precio+"', precio_venta_tarjeta = '"+precioVentaTarjeta+"', ganancia=(("+precio+" * 100 / costo)-100) where eliminado='0' and lower(nombre) LIKE '%" + description.toLowerCase() + "%';");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

        

//TALLES
    public ResultSet listarTallesPorTipo(String con) {
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
    
    public ResultSet obtenerTallesPorArticulo(String idArticulo) {
        String con = "SELECT t.nombre, ta.stock, ta.id_talle FROM talle_articulos as ta inner join talle as t on ta.id_talle = t.id_talle where ta.id_articulo="+idArticulo+" order by t.id_talle;";
        ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

    
     public int eliminarTallesDeArticulos(Integer idArticulo){
         int n=0;
        try {        
            consulta = cn.createStatement();
            n= consulta.executeUpdate("DELETE FROM talle_articulos WHERE id_articulo='"+idArticulo+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n; 
     }  
     
    public int agregarTalleDeArticulo(int idArticulo, String tipoArticulo, String talle, String stock) {
        int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("INSERT INTO talle_articulos(id_articulo, id_talle, stock) VALUES ("+idArticulo+", (SELECT id_talle FROM talle where nombre='"+talle+"' and id_tipo = (select id_tipo from tipo_articulo where nombre ='"+tipoArticulo+"')), "+stock+") ");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
      public int obtenerIdTallePorTipoYTalle(String talle, String tipo) {
        int n=0;
         try { 
            consulta = cn.createStatement();
            ResultSet rs = consulta.executeQuery("select id_talle from talle where nombre='"+talle+"' and id_tipo = (select id_tipo from tipo_articulo where nombre='"+tipo+"')");
            if (rs.next()){
                 n= Integer.parseInt(rs.getString(1));
            }  
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    

   
//CAJA
     public ResultSet obtenerFechaMinimaYMaxima(){    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT min(fecha), max(fecha) from pagos where id_factura in (select id_factura from facturas where eliminado='0')");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;   
    }
     
      public ResultSet obtenerFechaMinimaYMaxima(String id_cliente){    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT min(fecha), max(fecha) from facturas where eliminado='0' and id_mcliente='"+id_cliente+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;   
    }

//ESTADISTICAS DE CLIENTES

     
    public ResultSet mostrarEstadisticasClientes(String con) {
         ResultSet rs = null;   
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

//DEUDORES
   public ResultSet mostrarDeudores(String con) {
         ResultSet rs = null;   
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

   //ACREEDORES
     public ResultSet mostrarAcreedores(String con) {
         ResultSet rs = null;   
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }










       
}
