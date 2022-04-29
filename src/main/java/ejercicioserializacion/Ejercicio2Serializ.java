/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioserializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alumno Tarde
 */
public class Ejercicio2Serializ {

    /**
     * @param args the command line arguments
     */
    static Scanner entrada =  new Scanner(System.in);
    static ArrayList <Cliente> listaClient = new ArrayList<Cliente>();
    public static void main(String[] args) {
        do{
        }while(!seleccion(menu()));
    }
    public static int menu() {
        int seleccion=10;
        boolean salir=false;
        System.out.println("");
        System.out.println("\t           .:MENÚ CLIENTES:.");
        System.out.println("\t ____________________________________");
        System.out.println("\t|                                    |");
        System.out.println("\t|   1.-Crear fichero con 5 clientes  |");
        System.out.println("\t|   2.-Añadir nuevo cliente          |");
        System.out.println("\t|   3.-Modificar cliente             |");
        System.out.println("\t|   4.-Borrar cliente                |");
        System.out.println("\t|   5.-Consultar datos de cliente    |");
        System.out.println("\t|   0.-Salir                         |");
        System.out.println("\t|____________________________________|");
        System.out.println("");
        do{
            System.out.println("Introduzca selección: ");
            try{
                seleccion = entrada.nextInt();
                salir=true;
            }catch(InputMismatchException e){
                System.out.println("ERROR: "+e.getClass().getName() + e.getMessage());//Con esto podemos ver que mensaje por defecto tiene este tipo de error                
                salir=false;
                entrada.nextLine();
            }    
        }while(!salir);        
        return seleccion;
    }
    public static boolean seleccion(int opcion){
        boolean salir= false;
        
        switch (opcion){
            case 0:{
                System.out.println("Has seleccionado salir del programa");
                guardar();
                salir =  true;
                break;
            }
            case 1:{
                System.out.println("Has seleccionado crear fichero con 5 clientes");
                entrada.nextLine();//PARA QUE NO REVIENTE
                //llamada crear fichero con 5 clientes
                crearFichClientes();
                break;
            }
            case 2:{
                System.out.println("Has seleccionado añadir cliente");
                entrada.nextLine();//Para que haga cosas raraas al pedir nombr enuevo
                //llamada añadir cliente
                addCliente();                
                break;
            }
            case 3:{
                System.out.println("Has seleccionado modificar cliente");
                //llamada a modificar cliente
                modCliente();
                break;
            }
            case 4:{
                System.out.println("Has seleccionado borrar cliente");
                //llamada a borrar cliente
                borrarCliente();
                break;
                
            }
            case 5:{
                 System.out.println("Has seleccionado consultar datos cliente");
                //llamada a consultardatoscli
                //lecturaObjetos("C:\\Users\\Alumno Tarde\\Desktop\\clientesProg\\Clientes.bin");
                consultarClientes();
                break;
            }
            case 6:{
                for (int i = 0; i < listaClient.size(); i++) {
                    System.out.println(listaClient.get(i).toString() + " Pos en array" + listaClient.indexOf(listaClient.get(i)));
                }
                break;
            }
            case 7:{
                //opcion oculta recoger objetos del fichero al array
                lecturaObjetos("C:\\Users\\Alumno Tarde\\Desktop\\clientesProg\\Clientes.bin");
                break;
            }
            default :{
                System.out.println("Seleccion fuera de rango o invalida");
                break;
            }            
        }
        return salir;    
    }
    public static void porDefectClientes(){
        listaClient.add(new Cliente("Nico","calle rio"));
        listaClient.add(new Cliente("Will", "calle awa"));
        listaClient.add(new Cliente("Guille", "calle aire"));
        listaClient.add(new Cliente("Mario", "calle fuego"));
        listaClient.add(new Cliente("Diego", "calle planta"));        
    }
    public static void guardar(){        
        try{
            File recuperando_fichero =new File("C:\\Users\\Alumno Tarde\\Desktop\\clientesProg\\Clientes.bin");
            recuperando_fichero.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
        escrituraObjetos("C:\\Users\\Alumno Tarde\\Desktop\\clientesProg\\Clientes.bin");        
    }
    /*
    Llamamos a los clientes por defecto y luego escribimos en fichero. 
    Metodo de Opcion1
    */
    public static void crearFichClientes(){
        porDefectClientes();
        //String ruta = pideCompruebaRuta("ruta a guardar");
        String rutaFicheroCli = "C:\\Users\\Alumno Tarde\\Desktop\\clientesProg\\Clientes.bin";
        escrituraObjetos(rutaFicheroCli);
    }
    /*
    Pedimos los datos necesarios para un nuevo cliente
    Añadimos a listaClient
    Metodo de Opcion2
    */
    public static void addCliente(){
        String nombre =  pedirCadena("Nombre de Cliente");
        String direcc = pedirCadena("Direccion de Cliente");
        if(listaClient.size()!=0){
            listaClient.get(0).setCodigoAux(listaClient.size());
        }
        
        listaClient.add(new Cliente(nombre,direcc));
    }
    /*
    Pedimos codigo cliente a modificar y conseguimos posicion
    Si no esta no hacemos nada
    Si esta el cliente le damos un nuevo nombre y direccion
    Metodo de Opcion3
    */
    public static void modCliente(){
        int posCli = conseguirCli();
        entrada.nextLine();//Para que no se trague el salto al introducir nuevo Nombre al Cliente 
        if(posCli<0){
            System.out.println("Volviendo al menu principal..");
        }
        else{
            String nombre =  pedirCadena("Nuevo nombre de Cliente");
            String direcc = pedirCadena("Nueva direccion de Cliente");            
            listaClient.get(posCli).setNombre(nombre);
            listaClient.get(posCli).setDireccion(direcc);
        }        
    }
    /*
    Pedimos codigo cliente y conseguimos posicion
    Si esta lo eliminamos y damos mensaje de confirmacion
    Metodo de Opcion4
    */
    public static void borrarCliente(){
        int posCli = conseguirCli();
        if(posCli<0){
            System.out.println("Volviendo al menu principal..");
        }
        else{
            listaClient.remove(posCli);
            System.out.println("Cliente borrado");
        }
    }
    /*
     Llamamos a metodo de conseguir cliente
     Si se introduce codigo correcto se mostrará informacion.
    Metodo de Opcion5
    */
    public static void consultarClientes(){
        int posCli = conseguirCli();
        if(posCli<0){
            System.out.println("Volviendo al menu principal..");
        }
        else{
            System.out.println(listaClient.get(posCli).toString());
        }
    }
    /*
        Escribimos los objetos de listaClientes en un archivo
    */
    public static void escrituraObjetos(String ruta){
        
        try {
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(new FileOutputStream(ruta));
            escribiendo_fichero.writeObject(listaClient);
            escribiendo_fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
        Lectura de fichero y recogida de objetos en el arraylist statico de clientes
    */
    public static void lecturaObjetos(String ruta){
     try{
            ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(ruta));
            listaClient = (ArrayList<Cliente>) recuperando_fichero.readObject();
            for (Cliente e : listaClient) {
                System.out.println(e);
            }
            recuperando_fichero.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
     
    }
    /*
        Metodo para usar en los do whiles donde haya que controlar que el usuario meta un dato correcto
        Con este metodo podremos salir del dowhile
    */
    public static boolean preguntaCont(){
        boolean salir=false;
        boolean resp=false;
        String aux;
        do{
            System.out.println("¿Intentarlo de nuevo? [si|no]");
            aux = entrada.nextLine();
            if(aux.equalsIgnoreCase("si")){
                resp = false;
                salir=true;
            }
            else if(aux.equalsIgnoreCase("no")){
                resp = true;
                salir=true;
            }
            else{
                System.out.println("Error al introducir opcion");
            }
        }while(!salir);
        return resp;                
    }
    /*
    Metodo para pedir un directorio y comprovar que exista
    */
    public static String pideCompruebaRuta(String ruta){
        String elemento;
        File ficheroAux;
        boolean salir = false;
        do{
            System.out.println("Introducir " + ruta + " : ");
            elemento = entrada.nextLine();
            ficheroAux = new File (elemento);
            salir = ficheroAux.exists();
            System.out.println((salir ? "" : "Directorio no existe"));
        }while(!salir);
        return elemento;
    }
    /*
    Metodo que pide una cadena y la devuelve
    */
    public static String pedirCadena(String pedir){
        String cadena;        
        System.out.println("Introducir " + pedir + ": ");
        cadena = entrada.nextLine();
        return cadena;
    }
    /*
    Metodo que pide un entero y lo devuelve. Tiene try catch para evitar problemas en la entrada
    */
    public static int pedirNum(String pedir){
        int numero=0;
        boolean salir=false;        
        do{
            System.out.println("Introducir " + pedir + ": ");
            try{                
                numero = entrada.nextInt();
                salir=true;
            }catch(InputMismatchException e){
                System.out.println("Error al introducir el valor");
                salir=false;
                entrada.nextLine();
            }
        }while(!salir);        
        return numero;
    }
    /*
        Devolvemos la posicion en el ArrayList que tenga el codigo del cliente
        dado
    */
    public static int encontrarPosicion(int codigoCli){
        int posLista=-1;
        for (int i = 0; i < listaClient.size(); i++) {
            if(codigoCli == listaClient.get(i).getCodigo()){
                posLista=i;
            }
        }
        return posLista;
    }
    /*
        Comprobar que existe el cliente con el codigo dado
    */
    public static boolean estaCli(int codigoCli){
        boolean esta=false;
        for (int i = 0; i < listaClient.size(); i++) {
            if(codigoCli == listaClient.get(i).getCodigo()){
                esta=true;
            }
        }        
        return esta;
    }
    /*
        Metodo que devolvera la posicion de un cliente en el array list.        
        Primero preguntara por el codigo de cliente: si no esta avisara y preguntara si seguir o no
        Segundo: si esta conseguimos la posicion y al final la devolvemos.
    */
    public static int conseguirCli(){
        boolean salir=false;
        boolean respuestaCont=false;
        int codigoCli = -1;
        do{
            codigoCli=pedirNum("Codigo del Cliente");
            salir= estaCli(codigoCli);
            if(!salir){
                System.out.println("Cliente con codigo " + codigoCli+" no existe");                
                entrada.nextLine();
                respuestaCont=preguntaCont();                
                salir=respuestaCont;
            }
        }while(!salir);
        if(respuestaCont){
            codigoCli=-1;
        }
        else{
            codigoCli = encontrarPosicion(codigoCli);
        }
        return codigoCli;
    }
}
