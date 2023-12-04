/*
 * Nos contrataron de la Biblioteca Nacional de España para
 * lograr automatizar su sistema de prestación de libros, hoy
 * en día el mismo es un archivo plano que almacena la
 * información de los prestamos de libros a los ciudadanos a
 * lo largo del País. En base a esto, han querido desarrollar
 * un sistema que los ayude a gestionar el inventario, los
 * prestamos de libros, el vencimiento de estos, y los usuarios
 * también.
 * 
 * La biblioteca presta los libros por 10 días, a partir del día 11
 * empieza a contar los días de entrega vencida, el sistema
 * debe poder informar cuantos días lleva vencida la entrega,
 * cuanto dinero debe abonar en base a los días de
 * vencimiento y si el cliente entra en mora o no.
 * 
 * El programa debe contar con la siguiente estructura:
 * - Clases y Herencia
 * - Interfaces
 * - Manejo de excepciones de archivos
 * 
 * ➢ “Días de atraso”: Calcular los días de atraso en base a
 * Fecha de hoy - fecha estipulada de entrega
 * ➢ “Estado Prestamo”: indique si el préstamo esta
 * vencido o no, si el préstamo esta vencido, entonces
 * poner “VENCIDO” si no lo está, porque todavía no se
 * llegaron a los 10 días, informar “EN REGLA”
 * ➢ “Dinero Adeudado”: Calcular el costo que debe abonar
 * el usuario al momento de devolver el libro: por cada
 * día se cobra 1 euro.
 * ➢ “Cliente moroso”: SI/NO, si el estado del préstamo es
 * vencido, entonces, el cliente es moroso, si no lo esta,
 * entonces no lo es.
 * (¡) NO SE PUEDE USAR DO-WHILE (¡)
 * 
 * El sistema debe generar un txt de salida informando
 * todos los campos que obtiene del txt de entrada, y,
 * además, informar los campos agregados.
 * El mismo se debe llamar
 * “inventario_y_vencimientos_fechaHoy”
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloExamen {
    public static void main(String[] args) {
    	String fileInput = "Biblioteca_Nacional_de_España.txt";
    	String fileOuput = "inventario_y_vencimientos_fechaHoy.txt";
    	
    	try {
    		File file = new File(fileInput);
	    	Scanner reader = new Scanner(file);
	    	String data = "";
	    	
	        while (reader.hasNextLine()) {
	        	data += reader.nextLine() + "\n";
	        }
	        reader.close();
	        
	        ArrayList<Prestamo> prestamos = new ArrayList<>();
	        for (int i=0; i<data.split("\n").length/12; i++) {
	        	String nombre = data.split("\n")[12*i+1];
	        	String autor = data.split("\n")[12*i+3];
	        	String fechaSolicitud = data.split("\n")[12*i+5];
	        	String fechaEntregaEstipulada = data.split("\n")[12*i+7];
	        	String estado = data.split("\n")[12*i+9];
	        	int DNI = Integer.valueOf(data.split("\n")[12*i+11]);
	        		        	
	        	prestamos.add(new Prestamo(nombre, autor, fechaSolicitud, fechaEntregaEstipulada, estado, DNI));
	        }
	        
        	FileWriter fw = new FileWriter(fileOuput, false);
        	BufferedWriter writer = new BufferedWriter(fw);
        	
            for (Prestamo prestamo: prestamos) {
            	writer.write(prestamo.toString() + "\n\n");
            	System.out.println(prestamo.toString() + "\n");
            }
            
            writer.close();
            fw.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
        
    }
}

class Libro {
	private String nombre;
	private String autor;
	
	public Libro(String nombre, String autor) {
		this.nombre = nombre;
		this.autor = autor;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getAutor() {
		return this.autor;
	}
}

interface calculadorPrestamo {
    String obtenerEstadoPrestamo();
}

class Prestamo implements calculadorPrestamo {
    private Libro libro;
    private LocalDate fechaSolicitud;
    private LocalDate fechaEntregaEstipulada;
    private String estado;
    private int DNI;

    public Prestamo(Libro libro, LocalDate fechaSolicitud, LocalDate fechaEntregaEstipulada, String estado, int DNI) {
        this.libro = libro;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntregaEstipulada = fechaEntregaEstipulada;
        this.estado = estado;
        this.DNI = DNI;
    }

    public Prestamo(Libro libro, String fechaSolicitud, String fechaEntregaEstipulada, String estado, int DNI) {
        this.libro = libro;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fechaSolicitud = LocalDate.parse(fechaSolicitud, formatter);
        this.fechaEntregaEstipulada = LocalDate.parse(fechaEntregaEstipulada, formatter);
        this.estado = estado;
        this.DNI = DNI;
    }
    
    public Prestamo(String nombre, String autor, LocalDate fechaSolicitud, LocalDate fechaEntregaEstipulada, String estado, int DNI) {
    	this.libro = new Libro(nombre, autor);
    	this.fechaSolicitud = fechaSolicitud;
    	this.fechaEntregaEstipulada = fechaEntregaEstipulada;
        this.estado = estado;
    	this.DNI = DNI;
    }
    
    public Prestamo(String nombre, String autor, String fechaSolicitud, String fechaEntregaEstipulada, String estado, int DNI) {
    	this.libro = new Libro(nombre, autor);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fechaSolicitud = LocalDate.parse(fechaSolicitud, formatter);
        this.fechaEntregaEstipulada = LocalDate.parse(fechaEntregaEstipulada, formatter);
        this.estado = estado;
    	this.DNI = DNI;
    }

    public int calcularDiasAtraso() {
    	LocalDate fechaHoy = LocalDate.now();
    	int output = (int) ChronoUnit.DAYS.between(fechaEntregaEstipulada, fechaHoy);
        return output;
    }

    public String obtenerEstadoPrestamo() {
        int diasAtraso = calcularDiasAtraso();
        if (diasAtraso > 0) {
        	return "Vencido";
        } else {
        	return "En regla";
        }
    }

    public boolean esClienteMoroso() {
        return this.obtenerEstadoPrestamo().equals("Vencido");
    }

    public String getLibroNombre() {
        return this.libro.getNombre();
    }
    
    public String getLibroAutor() {
    	return this.libro.getAutor();
    }

    public LocalDate getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public LocalDate getFechaEntregaEstipulada() {
        return this.fechaEntregaEstipulada;
    }
    
    public String getEstado() {
    	return this.estado;
    }
    
    public int getDNI() {
    	return this.DNI;
    }
    
    @Override
    public String toString() {
    	String output = "";
    	
    	output += "Nombre Libro: " + this.getLibroNombre() + "\n";
    	output += "Autor: " + this.getLibroAutor() + "\n";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	output += "Fecha Solicitud: " + this.getFechaSolicitud().format(formatter) + "\n";
    	output += "Fecha Entrega Estipulada: " + this.getFechaEntregaEstipulada().format(formatter) + "\n";
    	output += "Estado: " + this.getEstado() + "\n";
    	output += "Estado Prestamo: " + this.obtenerEstadoPrestamo() + "\n";
    	output += "Días de Atraso: " + this.calcularDiasAtraso() + "\n";
    	if (this.esClienteMoroso()) {
    		output += "Cliente Moroso: Sí";
    	} else {
    		output += "Cliente Moroso: No";
    	}
    	
    	return output;
    }
}
