/*
 * La empresa Microsoft ha contratado nuestros servicios para desarrollar un
 * completo sistema de gestión de recursos humanos en Java. Su objetivo
 * principal es automatizar el proceso de registro, seguimiento y cálculo de
 * salarios de los empleados.
 * 
 * Toda la información se debe generar por consola:
 * - Nombre del empleado
 * - Apellido del empleado
 * - Fecha de ingreso
 * - Fecha de salida
 * - Sexo
 * - Ciudad
 * - Posición
 * - Salario base
 * 
 * El sistema debe tener la capacidad de generar la siguiente información
 * - Salario final
 * - Antigüedad laboral
 * - Rotación
 * 
 * El sistema debe contar con una jerarquía de clases bien estructurada que
 * incluya una interfaz 'Empleado', la cual deberá tener métodos clave como
 * 'calcularSalario', rotación recomendada y Antigüedad Laboral.
 * 
 * Calculo de antigüedad laboral: fecha actual – fecha de ingreso
 * Salario final: Salario base * 0.83
 * Rotación recomendada: Si el puesto es de Ingeniero && La antigüedad es
 * mayor o igual a 2 años => Rotación habilitada
 * Rotación recomendada: Si el puesto es de Ingeniero && La antigüedad es
 * menor o igual a 2 años => Rotación no habilitada
 * Además de la gestión de empleados, el sistema debe generar la información de
 * los empleados en un fichero de salida con formato .json
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
//import org.json.JSONObject;
import org.json.simple.JSONObject;

public class ModeloExamen2 {

	public static void main(String[] args) {
		Empleado empleado = new Empleado();
		System.out.println(empleado.toJSON());
		empleado.jsonToFile("salida.json");
	}

}

interface iEmpleado {
	double calcularSalario();
	int antiguedadLaborarl();
	String rotacionRecomendada();
}

class Empleado implements iEmpleado {
	private String nombre;
	private String apellido;
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	private String sexo;
	private String ciudad;
	private String posicion;
	private double salarioBase;
	
	public Empleado() {
		this.input();
	}
	
	public Empleado(String nombre, String apellido, LocalDate fechaIngreso, LocalDate fechaSalida, String sexo, String ciudad, String posicion, double salarioBase) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.sexo = sexo;
		this.ciudad = ciudad;
		this.posicion = posicion;
		this.salarioBase = salarioBase;
	}
	
	public Empleado(String nombre, String apellido, int diaIngreso, int mesIngreso, int anoIngreso, int diaSalida, int mesSalida, int anoSalida, String sexo, String ciudad, String posicion, double salarioBase) {
		LocalDate fechaIngreso = LocalDate.of(anoIngreso, mesIngreso, diaIngreso);
		LocalDate fechaSalida = LocalDate.of(anoSalida, mesSalida, diaSalida);
	
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.sexo = sexo;
		this.ciudad = ciudad;
		this.posicion = posicion;
		this.salarioBase = salarioBase;
	}
	
	private void input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce el nombre del empleado: ");
		this.nombre = sc.next();
		System.out.print("Introcue el apellido del empleado: ");
		this.apellido = sc.next();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.print("Introduce la fecha de ingreso del empleado (dd/MM/yyyy): ");
		this.fechaIngreso = LocalDate.parse(sc.next(), formatter);
		System.out.print("Introduce la fecha de salida del empleado (dd/MM/yyyy) \n"
				+ "(Si el empleado sigue en la empresa, introducir 01/01/0001): ");
		this.fechaSalida = LocalDate.parse(sc.next(), formatter);
		
		System.out.print("Introduce el sexo del empleado (H = hombre, M = mujer): ");
		this.sexo = sc.next();
		System.out.print("Introduce la ciudad del empleado: ");
		this.ciudad = sc.next();
		System.out.print("Introduce la posicion del empleado: ");
		this.posicion = sc.next();
		System.out.print("Introduce el salario del empleado: ");
		this.salarioBase = sc.nextDouble();
		
		System.out.println();
		
		sc.close();
	}
	
	public double calcularSalario() {
		return this.salarioBase * 0.8;
	}
	
	public int antiguedadLaborarl() {
		if (this.fechaSalida == LocalDate.of(0001, 01, 01)) {
			return (int) ChronoUnit.DAYS.between(this.fechaIngreso, LocalDate.now());
		} else {
			return (int) ChronoUnit.DAYS.between(this.fechaIngreso, this.fechaSalida);
		}
	}
	
	public String rotacionRecomendada() {
		if (this.posicion.toLowerCase().equals("ingeniero") && this.antiguedadLaborarl() >= 365 * 2) {
			return "Rotación habilitada";
		} else {
			return "Rotación no habilitada";
		}
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public LocalDate getFechaIngreso() {
		return this.fechaIngreso;
	}
	
	public LocalDate getFechaSalida() {
		return this.fechaSalida;
	}
	
	public String getSexo() {
		return this.sexo;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public String getPosicion() {
		return this.posicion;
	}
	
	public double getSalarioBase() {
		return this.salarioBase;
	}
	
	@Override
	public String toString() {
		String output = "";
		
		output += "Nombre: " + this.getNombre() + "\n";
		output += "Apellido: " + this.getApellido() + "\n";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		output += "Fecha de ingreso: " + this.getFechaIngreso().format(formatter) + "\n";
		
		if (this.fechaSalida == LocalDate.of(0001, 01, 01)) {
			output += "Fecha de salida: Sigue en la empresa \n";
		} else {
			output += "Fecha de salida: " + this.getFechaSalida().format(formatter) + "\n";
		}
		
		if (this.getSexo().toLowerCase().equals("h") || this.getSexo().toLowerCase().equals("hombre")) {
			output += "Sexo: Hombre \n";
		} else if (this.getSexo().toLowerCase().equals("m") || this.getSexo().toLowerCase().equals("mujer")) {
			output += "Sexo: Mujer \n";
		} else {
			output += "Sexo: Otro \n";
		}
		
		output += "Ciudad: " + this.getCiudad() + "\n";
		output += "Posición: " + this.getPosicion() + "\n";
		output += "Salario base: " + this.getSalarioBase() + "\n";
		
		output += "Salario: " + this.calcularSalario() + "\n";
		output += "Antigüedad laboral: " + this.antiguedadLaborarl() + "\n";
		output += "Rotación recomendada: " + this.rotacionRecomendada();
		
		return output;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("Nombre", this.getNombre());
		json.put("Apellido", this.getApellido());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		json.put("Fecha ingreso", this.getFechaIngreso().format(formatter));
		json.put("Fecha salida" , this.getFechaSalida().format(formatter));
		
		json.put("Ciudad", this.getCiudad());
		json.put("Posicion", this.getPosicion());
		json.put("Salario base", Double.toString(this.getSalarioBase()));

		json.put("Salario", Double.toString(this.calcularSalario()));
		json.put("Antigüedad laboral", Integer.toString(this.antiguedadLaborarl()));
		json.put("Rotación recomendada", this.rotacionRecomendada());
		
		return json;
	}
	
	public void jsonToFile(String fileName) {
		try {
			File file = new File(fileName);
        	FileWriter fw = new FileWriter(file, true);
        	BufferedWriter bw = new BufferedWriter(fw);
        	
        	bw.write(this.toJSON().toJSONString());
        	
        	bw.close();
        	fw.close();
		} catch (IOException e) {
			System.err.println("Error al escribir a fichero");
			e.printStackTrace();
		}
	}
}
