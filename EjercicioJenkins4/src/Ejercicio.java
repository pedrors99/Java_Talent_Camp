/*
 * Ejercicios de Jenkins - Actividad ° 4:
 * Nos contrataron de "Doña Clotilde" que es una empresa de seguridad,ya que quieren generar un sistema de control de calidad de empleados. El mismo consiste en chequear
 * el horario de ingreso y de egreso de los empleados de la empresa. En base al puesto del empleado se contemplan distintas franjas horarias de acceso.
 * 
 * Posición: CEO
 * Horario estipulado de ingreso: 7am
 * Horario estipulado de egreso: 6pm
 * 
 * Posición: Jefe
 * Horario estipulado de ingreso: 8am
 * Horario estipulado de egreso: 6pm
 * 
 * Posición: Ingeniero
 * Horario estipulado de ingreso: 9am
 * Horario estipulado de egreso: 6pm
 * 
 * Posición: Analista
 * Horario estipulado de ingreso: 9am
 * Horario estipulado de egreso: 5pm
 * 
 * Posición: Scrum Master
 * Horario estipulado de ingreso: 9am
 * Horario estipulado de egreso: 6pm
 * 
 * Posición: RRHH
 * Horario estipulado de ingreso: 9am
 * Horario estipulado de egreso: 6pm
 * 
 * Si estas franjas horarias no se respetan, se generan infracciones salariales. Donde segun el puesto se descuenta un % del sueldo anual a cada empleado. 
 * Si Posicion = CEO Entonces se le descuenta 0.01
 * Si Posicion = Jefe Entonces se le descuenta 0.05
 * Si Posicion = Ingeniero Entonces se le descuenta 0.15
 * Si Posicion = Analista Entonces se le descuenta 0.20
 * Si Posicion = Scrum Master Entonces se le descuenta 0.10
 * Si Posicion = RRHH Entonces se le descuenta 0.09
 * 
 * El programa debe solicitar por consola los siguientes datos:
 * - Nombre
 * - Posicion
 * - Sueldo 
 * - Horario de Ingreso
 * En base a los datos ingresados se debe informar quien tiene una infracción. Hacer 1 para cada posición, como minimo.
 * 
 * SECCION JENKINS:
 * Generar un Jenkinsfile desde el programa de Java que informe cuales fueron los empleados que tienen infracción y la posición qué ocupan en la empresa para mostrarlos por consola de Jenkins.
 * Para esto deben subir el proyecto a Github y escanear el Jenkinsfile. 
 * 
 * Entregable:
 * - Link del repositorio de Github
 * - Captura de pantalla de la consola de Jenkins. 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio {

	public static void main(String[] args) {
		ArrayList<Empleado> empleados = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce el número de empleados: ");
		int n = sc.nextInt();
		
		for (int i=0; i<n; i++) {
			System.out.println();
			
			System.out.print("Introduce el nombre del empleado " + (i+1) + ": ");
			String nombre = sc.next();
			
			System.out.print("Introduce la posición del empleado " + (i+1) + ": ");
			String posicion = sc.next();
			
			System.out.print("Introduce el sueldo del empleado " + (i+1) + ": ");
			double sueldo = sc.nextDouble();
			
			System.out.print("Introduce la hora de entrada del empleado " + (i+1) + " (formato 24h): ");
			int horaEntrada = sc.nextInt();
			
			System.out.print("Introduce la hora de salida del empleado " + (i+1) + " (formato 24h): ");
			int horaSalida = sc.nextInt();
			
			empleados.add(new Empleado(nombre, posicion, sueldo, horaEntrada, horaSalida));
		}		
		sc.close();
		
		StringBuilder jenkinsfile = new StringBuilder();
		
		jenkinsfile.append("pipeline {\n");
		jenkinsfile.append("\tagent any\n");
		jenkinsfile.append("\tstages {\n");
		jenkinsfile.append("\t\tstage(\"Ejercicio Jenkins 4\") {\n");
		jenkinsfile.append("\t\t\tsteps {\n");
		
		for(Empleado empleado: empleados) {
			if (empleado.posicion.equals("No valida")) {
				jenkinsfile.append("\t\t\t\tprint \"" + empleado.nombre + " no tiene una posicion valida.\"\n");
			} else {			
				if (empleado.infraccion()) {
					jenkinsfile.append("\t\t\t\tprint \"" + empleado.nombre + " ha cometido una infraccion.\"\n");
				} else {
					jenkinsfile.append("\t\t\t\tprint \"" + empleado.nombre + " no ha cometido una infraccion.\"\n");
				}
			}
		}
		
		jenkinsfile.append("\t\t\t}\n");
		jenkinsfile.append("\t\t}\n");
		jenkinsfile.append("\t}\n");
		jenkinsfile.append("}\n");
		
		String fileName = "Jenkinsfile";
		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file, false);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(jenkinsfile.toString());
	          
	        bw.close();
	        fw.close();
		} catch (IOException e) {
			System.err.println("Error al escribir a fichero.");
			e.printStackTrace();
		}
		
	}
}

class Empleado {
	Map<String, Integer> horarioIngreso;
	Map<String, Integer> horarioEgreso;
	Map<String, Double> costeInfraccion;
	
	String nombre;
	String posicion;
	double sueldo;
	int horaIngreso;
	int horaEgreso;
	
	public Empleado (String nombre, String posicion, double sueldo, int horaIngreso, int horaEgreso) {
		this.nombre = nombre;
		this.posicion = posicion;
		this.sueldo = sueldo;
		this.horaIngreso = horaIngreso;
		this.horaEgreso = horaEgreso;
		
		this.horarioIngreso = new HashMap<>();
		this.horarioEgreso = new HashMap<>();
		this.costeInfraccion = new HashMap<>();
		
		this.horarioIngreso.put("CEO", 7);
		this.horarioEgreso.put("CEO", 18);
		this.costeInfraccion.put("CEO", 0.01);
		
		this.horarioIngreso.put("Jefe", 8);
		this.horarioEgreso.put("Jefe", 18);
		this.costeInfraccion.put("Jefe", 0.05);
		
		this.horarioIngreso.put("Ingeniero", 9);
		this.horarioEgreso.put("Ingeniero", 18);
		this.costeInfraccion.put("Ingeniero", 0.15);
		
		this.horarioIngreso.put("Analista", 9);
		this.horarioEgreso.put("Analista", 17);
		this.costeInfraccion.put("Analista", 0.2);
		
		this.horarioIngreso.put("Scrum Master", 9);
		this.horarioEgreso.put("Scrum Master", 18);
		this.costeInfraccion.put("Scrum Master", 0.1);
		
		this.horarioIngreso.put("RRHH", 9);
		this.horarioEgreso.put("RRHH", 18);
		this.costeInfraccion.put("RRHH", 0.09);
		
		if (!this.costeInfraccion.containsKey(this.posicion)) {
			this.posicion = "No válida";
		}
	}
	
	boolean infraccion() {
		if (this.posicion.equals("No válida")) {
			return false;
		}
		
		boolean entradaCorrecta = this.horaIngreso <= this.horarioIngreso.get(this.posicion);
		boolean salidaCorrecta = this.horaEgreso >= this.horarioEgreso.get(this.posicion);
		return entradaCorrecta && salidaCorrecta;
	}
	
	double sueldoFinal() {
		if (this.posicion.equals("No válida")) {
			return this.sueldo;
		}
		
		if (this.infraccion()) {
			return this.sueldo * (1 - this.costeInfraccion.get(this.posicion));
		} else {
			return this.sueldo;
		}
	}
}
