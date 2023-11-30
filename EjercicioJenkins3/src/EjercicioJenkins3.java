/*
 * Ejercicios Jenkins - Actividad °3:
 * Desarrollar un proyecto de tipo Maven que contenga información sobre un usuario (la información es solicitada por consola). 
 * - Nombre
 * - Apellido
 * - Ciudad
 * - Edad
 * Y luego compilar el proyecto en Jenkins con el siguiente comando:
 * clean install
 * El proyecto debe estar subido a Github para su compilación.
 * Entregable:
 * - Captura de la consola de Jenkins.
 */

import java.util.Scanner;

public class EjercicioJenkins3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce tu apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Introduce tu edad: ");
        int edad = sc.nextInt();

        sc.close();
        System.out.println("Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: "+ edad);
    }
}
