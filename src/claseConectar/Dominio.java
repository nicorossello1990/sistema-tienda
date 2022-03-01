package claseConectar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.DefaultDesktopManager;
import javax.swing.DesktopManager;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class Dominio {
   
    static public Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize(); 
    static public int DOM_NOMBRES = 50;
    public static int DOM_NUMERO = 15;
    public static int DOM_CUIT = 13;
    public static int DOM_PORCENTAJE = 5;
    public static SimpleDateFormat formatofechaBD= new SimpleDateFormat("yyyy-MM-dddd");
    public static SimpleDateFormat formatofechaJCalender= new SimpleDateFormat("dd/MM/YYYY");
    public static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public static DefaultTableModel eliminarTabla(DefaultTableModel model) {
       int filas = model.getRowCount();
       int i=0;
       while (i<filas){
          model.removeRow(i);
          filas--;     
       }
       return model;
    }

    public static JTable centrarDatos(JTable table) {
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
      table.setDefaultRenderer(String.class, centerRenderer);
      return table;

    }
    

        
    public static String A2Decimales(String dato) {
         String cadenaNueva="";
         String nuevoDato = (new DecimalFormat("#.##").format(Double.parseDouble(dato))).toString();
         for (int i=0;i<nuevoDato.length();i++){
            if (nuevoDato.charAt(i)==','){
                cadenaNueva+=".";
            } else {
                cadenaNueva+=nuevoDato.charAt(i);
            }
        }
        return cadenaNueva;
    }
       
    static DesktopManager manager = new DefaultDesktopManager() {
        /** This moves the <code>JComponent</code> and repaints the damaged areas. */
        @Override
        public void setBoundsForFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {
            boolean didResize = (f.getWidth() != newWidth || f.getHeight() != newHeight);
            if (!inBounds((JInternalFrame) f, newX, newY, newWidth, newHeight)) return;
            f.setBounds(newX, newY, newWidth, newHeight);
            if(didResize) {
                f.validate();
            } 
        }

        protected boolean inBounds(JInternalFrame f, int newX, int newY, int newWidth, int newHeight) {
            if (newX < 0 || newY < 0) return false;
            if (newX + newWidth > f.getDesktopPane().getWidth()) return false;
            if (newY + newHeight > f.getDesktopPane().getHeight()) return false;
            return true;
        }

    };
    
   public static JDesktopPane configurarEscritorio(JDesktopPane escritorio){
       escritorio.setDesktopManager(manager);
       return escritorio;
   }
    
}
