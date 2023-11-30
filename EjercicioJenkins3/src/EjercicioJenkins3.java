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
