/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaTienda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nico
 */
public class UsuarioLogueado {
    private String nombreUsuario;
    private List<String> permisos;
    private String rol;
    private String sucursal;

    public UsuarioLogueado(String nombreUsuario, String rol, String sucursal) {
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
        this.permisos = buildPermisos(rol);
        this.sucursal = sucursal;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    
    

    private List<String> buildPermisos(String rol) {
        List<String> permisos = new ArrayList();
        switch (rol) {
            case Constantes.ROL_ADMIN:
                permisos.add("PUEDE VER ARCHIVO");
                permisos.add("PUEDE VER VENTAS");
                permisos.add("PUEDE VER ARTICULOS");
                permisos.add("PUEDE CARGAR ARTICULOS");
                permisos.add("PUEDE VER INFORMES");
                permisos.add("PUEDE VER CONFIGURACIONES");
                permisos.add("PUEDE VER DATOS SENSIBLES EN CAJA");
                permisos.add("PUEDE VER ADMIN");
                permisos.add("PUEDE VER ESTADISTICAS DE CLIENTES");
                permisos.add("PUEDE VER ESTADISTICAS DE ARTICULOS");
                break;
            case Constantes.ROL_EMPLEADO:            
                permisos.add("PUEDE VER ARCHIVO");
                permisos.add("PUEDE VER VENTAS");         
                permisos.add("PUEDE VER INFORMES");
                permisos.add("PUEDE VER CONFIGURACIONES");
                break;       
        }     
        return permisos;
    }
   
}
