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
    
        public int agregarMiUsuario(String nombreUser, String password, String rol, String sucursal, Usuarios usuarios) {      
       int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("insert into usuarios (nombre,password,rol, id_sucursal) values('"+nombreUser+"', '"+password+"','"+rol+"', (select id_sucursal from sucursales where nombre = '"+sucursal+"'));");
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
        
        
    public int modificarUsuario(String nombreUser, String password, String rol, String sucursal, String cod, Usuarios usuarios) {
               int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("UPDATE usuarios SET nombre='"+nombreUser+"',password='"+password+"',rol='"+rol+"', id_sucursal=(select id_sucursal from sucursales where nombre = '"+sucursal+"') WHERE id_user='"+cod+"'");
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
              rs = consulta.executeQuery("SELECT u.id_user as id_user, u.nombre as nombre, u.rol as rol, s.nombre as sucursal FROM usuarios as u inner join sucursales as s on u.id_sucursal = s.id_sucursal WHERE u.eliminado='0' and CONCAT(lower(u.nombre) , lower(u.rol)) LIKE '%"+valor.toLowerCase()+"%' order by u.id_user");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
     
     public UsuarioLogueado obtenerUsuarioPorNombreYPassword(String nombreUsuario, String password) {
         UsuarioLogueado usuarioLogueado = null;
         try {
              consulta = cn.createStatement();
              ResultSet rs = consulta.executeQuery("SELECT u.nombre, u.rol, s.nombre from usuarios as u inner join sucursales as s on u.id_sucursal = s.id_sucursal where u.eliminado='0' and lower(u.nombre) ='"+nombreUsuario.toLowerCase()+"' and u.password='"+password+"'");
              if (rs.next()){
                  usuarioLogueado = new UsuarioLogueado(rs.getString(1), rs.getString(2), rs.getString(3));         
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
     
     public int registrarFacturaVenta(String numero, Date fecha, String forma,String comprobante, String descuento, String motivo,String total,String estado, String entregado, String porcentaje_tarjeta, String id_mcliente, String nombreSucursal){
      int n=0;
         try { 
             if (porcentaje_tarjeta.isEmpty())
                 porcentaje_tarjeta="0";
            consulta = cn.createStatement();
            int id_sucursal = obtenerIdSucursalPorNombre(nombreSucursal);
            n= consulta.executeUpdate("insert into facturas (id_factura, fecha,forma_pago,comprobante,descuento,motivo,total,estado,entregado,id_mcliente, porcentaje_tarjeta, id_sucursal) values('"+numero+"', '"+fecha+"', '"+forma+"','"+comprobante+"','"+descuento+"','"+motivo+"','"+total+"','"+estado+"','"+entregado+"','"+id_mcliente+"','"+porcentaje_tarjeta+"', '"+id_sucursal+"')");
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
              rs = consulta.executeQuery("SELECT f.fecha,c.nombre,f.forma_pago,f.comprobante,f.descuento,f.motivo,f.entregado, f.total,c.saldo_favor, f.porcentaje_tarjeta, s.nombre FROM facturas as f inner join misclientes as c on f.id_mcliente = c.id_mcliente inner join sucursales as s on s.id_sucursal = f.id_sucursal WHERE id_factura='"+id_factura+"'");
  
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
            rs = obtenerCantidadPorArticuloYTalle(id_factura);
            String id_articulo, cantidad, id_talle, id_sucursal;
            while (rs.next()){
              id_articulo=rs.getString(1);
              id_talle = rs.getString(2);
              cantidad=tipo+rs.getString(3);
              id_sucursal = rs.getString(4);
              actualizarStockArticulosPorIdTalleYIdSucursal(cantidad, id_articulo, id_talle, id_sucursal); //Devuelve el Stock de artículos
            }
            n= consulta.executeUpdate("DELETE FROM fa WHERE id_factura='"+id_factura+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n; 
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
              rs = consulta.executeQuery("SELECT far.id_articulo, far.id_talle, far.cantidad, f.id_sucursal from FA as far inner join facturas as f on f.id_factura = far.id_factura where far.id_factura='"+id_factura+"' ");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
    
    public ResultSet obtenerTipoDeArticuloPorIdArticulo(int id_articulo) {
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("Select nombre from tipo_articulo where id_tipo = (select id_tipo from articulos where id_articulo = '"+id_articulo+"');");
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
        
        
         public String obtenerStockArticulo(String id_articulo, String id_talle, String id_sucursal){
         ResultSet rs = null;
         String stock="0";
         try { 
            consulta = cn.createStatement();
            rs = consulta.executeQuery("SELECT stock FROM talle_articulos WHERE id_articulo='"+id_articulo+"' and id_talle = '"+id_talle+"' and id_sucursal='"+id_sucursal+"';");
            if (rs.next()){
                  stock = rs.getString(1);                 
            }                 
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);        
               }       
        return stock;
       }
         
      public String obtenerStockArticuloPorTalleTipoYSucursal(String id_articulo, String talle, String tipo, String sucursal){
           int id_talle = obtenerIdTallePorTipoYTalle(talle, tipo);
           int id_sucursal = obtenerIdSucursalPorNombre(sucursal);
           return obtenerStockArticulo(id_articulo, String.valueOf(id_talle), String.valueOf(id_sucursal));      
       }  
               
       public int actualizarStockArticulosPorTipoTalleYSucursal(String cant,String id_articulo, String tipo, String talle, String sucursal){
         int id_talle = obtenerIdTallePorTipoYTalle(talle, tipo);
          int id_sucursal = obtenerIdSucursalPorNombre(sucursal);
         return actualizarStockArticulosPorIdTalleYIdSucursal(cant, id_articulo, String.valueOf(id_talle), String.valueOf(id_sucursal));   
       }
       
       public int actualizarStockArticulosPorIdTalleYIdSucursal(String cant,String id_articulo, String id_talle, String id_sucursal){
         int n=0;
         ResultSet rs = null;
         int stock=0;
         try {            
            consulta = cn.createStatement();      
                  stock = Integer.parseInt(obtenerStockArticulo(id_articulo, id_talle, id_sucursal))-Integer.parseInt(cant);
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
   
    public int ActualizarCostosDeArticulos(String description, Float costo) {       
        int n=0; 
         try { 
            consulta = cn.createStatement();        
            n=consulta.executeUpdate("UPDATE articulos SET costo= '"+costo+"', ganancia=((precio_venta * 100 / "+costo+")-100) where eliminado='0' and lower(nombre) LIKE '%" + description.toLowerCase() + "%';");
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
    
    public ResultSet obtenerTallesPorArticuloYSucursal(String idArticulo, String sucursal) {
        String con = "SELECT t.nombre, ta.stock, ta.id_talle FROM talle_articulos as ta inner join talle as t on ta.id_talle = t.id_talle where ta.id_articulo="+idArticulo+" and ta.id_sucursal = (select id_sucursal from sucursales where nombre = '"+sucursal+"') order by t.id_talle;";
        ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }

    
     public int eliminarTallesDeArticulos(Integer idArticulo, String sucursal){
         int n=0;
         String querySucursal = sucursal != null ? "and id_sucursal = (select id_sucursal from sucursales where nombre ='"+sucursal+"')" : "";
        try {        
            consulta = cn.createStatement();
            n= consulta.executeUpdate("DELETE FROM talle_articulos WHERE id_articulo='"+idArticulo+"' "+querySucursal+";");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n; 
     }  
     
    public int agregarTalleDeArticulo(int idArticulo, String tipoArticulo, String talle, String sucursal, String stock) {
        int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("INSERT INTO talle_articulos(id_articulo, id_talle, id_sucursal, stock) VALUES ("+idArticulo+", (SELECT id_talle FROM talle where nombre='"+talle+"' and id_tipo = (select id_tipo from tipo_articulo where nombre ='"+tipoArticulo+"')), (select id_sucursal from sucursales where nombre = '"+sucursal+"'), "+stock+") ");
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
     
     // SUCURSALES
     public ResultSet listarDatosSucursales() {    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_sucursal,nombre FROM sucursales where eliminado = '0' order by id_sucursal");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
     
     public ResultSet listarDatosSucursalesTotales() {    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT id_sucursal,nombre FROM sucursales order by id_sucursal");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
     
     public boolean existeLaSucursal(String sucursal) {         
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT * from sucursales where nombre ='"+sucursal+"' and eliminado ='0' ");
              return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false; 
    }
     
      public int CantidadDeSucursales() {       
     
         try { 
            consulta = cn.createStatement();        
            ResultSet rs=consulta.executeQuery("Select count(*) from sucursales;");
             if (rs.next()){
                  return Integer.parseInt(rs.getString(1));                 
            }  
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
      
     public int agregarSucursal(String nombre) {      
       int n=0;
         try { 
            consulta = cn.createStatement();
            n=consulta.executeUpdate("INSERT INTO public.sucursales( nombre) VALUES ('"+nombre+"');");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
     
     public int eliminarSucursal(String idSucursal) {      
       int n=0;
         try { 
            consulta = cn.createStatement();
            consulta.executeUpdate("DELETE from talle_articulos WHERE id_sucursal='"+idSucursal+"'");
            n=consulta.executeUpdate("update sucursales set eliminado = '1' WHERE id_sucursal='"+idSucursal+"'");
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
     
       public int obtenerIdSucursalPorNombre(String nombre) {
        int n=0;
         try { 
            consulta = cn.createStatement();
            ResultSet rs = consulta.executeQuery("select id_sucursal from sucursales where nombre='"+nombre+"';");
            if (rs.next()){
                 n= Integer.parseInt(rs.getString(1));
            }  
         } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
       
       
   //ESTADISTICAS DE ARTICULOS
        public ResultSet mostrarEstadisticasDeArticulos(String con) {
         ResultSet rs = null;   
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery(con);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs; 
    }
        
     public ResultSet obtenerFechaMinimaYMaximaDeFacturas(){    
         ResultSet rs = null;
         try {
              consulta = cn.createStatement();
              rs = consulta.executeQuery("SELECT min(fecha), max(fecha) from facturas where eliminado='0';");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;   
    }
        
       
}
