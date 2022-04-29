/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioserializacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Alumno Tarde
 */
public class Ejercicio1Serializ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Casa[] viviendas = new Casa[3];
        String ruta = "C:\\Users\\Alumno Tarde\\Desktop\\viviendasProg\\viviendas.dat";
        viviendas[0] = new Casa("calle 2", 2, "1A", "torrejon", 28850, 70, 2);
        viviendas[1] = new Casa("calle 3", 1, "3B", "torrejon", 28850, 100, 3);
        viviendas[2] = new Casa("calle 1", 3, "4C", "torrejon", 28850, 130, 4);
        escrituraObjetos(ruta, viviendas);
        lecturaObjetos(ruta);
    }
    public static void escrituraObjetos(String ruta, Casa viviendas []){
        //Escribimos las viviendas en un fichero
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(ruta));
            escribiendo_fichero.writeObject(viviendas);
            escribiendo_fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void lecturaObjetos(String ruta){
        try{
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(ruta));
            Casa []  viviendasRecu = (Casa []) recuperando_fichero.readObject() ;
            for (Casa e : viviendasRecu) {
                System.out.println(e);
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
