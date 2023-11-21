/*
 * Ejercicio 13: 
 * Desarrollar un programa en Java que implemente 3 hilos, los cuales cada uno va a ser encargado de 
 * realizar distintas acciones. 
 * Hilo principal: 
 * Generar la información del sistema.
 * Hilo secundario 1:
 * Generar un directorio
 * Hilo secundario 2: 
 * Imprimir la información que genera el sistema en un archivo de salida tipo txt
 * Nos contraron del Ministerio de Geografica para desarrollarles un sistema que los ayude a contabilizar
 * la información de las distintas ciudades.Se debe ingresar por consola los siguientes campos:
 * - Ciudad
 * - Cantidad de habitantes
 * - Superficie 
 * El programa debe implementar al menos una Interfaz que se encargue de generar el campo: 
 * - "Proyección habitantes 2030"  = Cantidad de habitantes * 0.90
 * - Superficie ocupada= Superficie actual + 10000
 * Luego, generar el fichero de salida:
 * - Ciudad
 * - Cantidad de habitantes
 * - Superficie 
 * - Proyección habitantes 2030
 * - Superficie ocupada
 * Realizar para al menos 3 ciudades de España 
 * 
 * Entregables:
 * - Link de tu repositorio con el codigo del proyecto (SUBIRLO DESDE TU IDE A GITHUB). 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio13 {
	public static void main(String[] args) {
		String path = "tarea13.txt";
		Ciudad ciudad = new Ciudad();
		
		Thread hilo1 = new Thread(new TareaInput("Input", ciudad));
		Thread hilo2 = new Thread(new TareaFile("File", path));
		Thread hilo3 = new Thread(new TareaOutput("Output", path, ciudad));
		System.out.println();

		hilo1.setPriority(Thread.MAX_PRIORITY);
		hilo2.setPriority(Thread.MIN_PRIORITY);
		hilo3.setPriority(Thread.MIN_PRIORITY);
		
		hilo1.start();
		hilo2.start();
		try {
			hilo1.join();	// Espera al input
			hilo2.join();	// Espera a que el archivo esté creado
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		hilo3.start();
	}
}

interface Poblacion {
	int proyeccionHabitantes2030();
	double superficieOcupada();
}

class Ciudad implements Poblacion {
	private String nombre;
	private int poblacion;
	private double superficie;
	
	public void initialize() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el nombre de la ciudad: ");
		this.nombre = sc.nextLine();
		System.out.print("Introduce la población actual de " + this.nombre + ": ");
		this.poblacion = sc.nextInt();
		System.out.print("Introduce la superficie actual de " + this.nombre + ": ");
		this.superficie = sc.nextDouble();
		sc.close();
	}
	
	public int proyeccionHabitantes2030() {
		return (int) (this.poblacion * 0.9);
	}
	
	public double superficieOcupada() {
		return this.superficie + 10000;
	}
	
	@Override
	public String toString() {
		String output = "";
		output += "Ciudad: " + this.nombre + "\n";
		output += "Población: " + this.poblacion + "\n";
		output += "Superficie: " + this.superficie + "\n";
		output += "Proyección habitantes 2030: " + this.proyeccionHabitantes2030() + "\n";
		output += "Superficie ocupada: " + this.superficieOcupada();
		return output;
	}
}

class TareaInput implements Runnable {
	public String nombre;
	public volatile Ciudad ciudad;
	
	public TareaInput (String nombre, Ciudad ciudad) {
		this.ciudad = ciudad;
		this.nombre = nombre;
		System.out.println("Creada tarea encargada del input.");
	}
	
	public void run() {
		this.ciudad.initialize();
	}
}

class TareaFile implements Runnable {
	public String nombre;
	private String path;
	
	public TareaFile (String nombre, String path) {
		this.nombre = nombre;
		this.path = path;
		System.out.println("Creada tarea encargada de crear el archivo.");
	}
	
	public void run() {
		try {
			File myObj = new File(path);
			if (myObj.createNewFile()) {
				System.out.println("Archivo " + this.path + " creado.");
			} else {
				System.out.println("Archivo " + this.path + " ya existe.");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class TareaOutput implements Runnable {
	public String nombre;
	private String path;
	public volatile Ciudad ciudad;
	
	public TareaOutput (String nombre, String path, Ciudad ciudad) {
		this.nombre = nombre;
		this.path = path;
		this.ciudad = ciudad;
		System.out.println("Creada tarea encargada de output.");
	}
	
	public void run() {
        File file = new File(this.path);
        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ciudad.toString());
            System.out.println("\n" + ciudad.toString());
            bw.close();
            fw.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
}