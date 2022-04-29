/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioserializacion;

import java.io.Serializable;

/**
 *
 * @author Alumno Tarde
 */
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1;
    private static int codigoAux= 0;
    private String nombre;
    private String direccion;
    private int codigo;

    public Cliente(String nombre, String direccion) {
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigo = codigoAux;
        codigoAux++;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public static void setCodigoAux(int codigoAux) {
        Cliente.codigoAux = codigoAux;
    }
    
    @Override 
    public String toString(){
        return("Cliente " + this.codigo + " Nombre: " + this.nombre + " Direccion: " + this.direccion);
    }
    
}
