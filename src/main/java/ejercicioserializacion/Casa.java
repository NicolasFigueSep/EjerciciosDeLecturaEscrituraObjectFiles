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
public class Casa implements Serializable{
    private static final long serialVersionUID = 1;
    private String direccion;
    private int portal;
    private String piso;
    private String localidad;
    private int cp;
    private int m2;
    private int numHabitaciones;

    public Casa(String direccion, int portal, String piso, String localidad, int cp, int m2, int numHabitaciones) {
        this.direccion = direccion;
        this.portal = portal;
        this.piso = piso;
        this.localidad = localidad;
        this.cp = cp;
        this.m2 = m2;
        this.numHabitaciones = numHabitaciones;
    }
    @Override
    public String toString(){
        return("Casa: " + this.direccion + " Portal " + this.portal + " Piso "
                +this.piso + " Localidad " + this.localidad + " CP " + this.cp + " m2: " + this.m2 + " Numero Habitaciones" + this.numHabitaciones);
    }
    
}
