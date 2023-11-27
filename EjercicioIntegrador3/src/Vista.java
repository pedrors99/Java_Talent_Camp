import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Vista {
	private Scanner sc;
	private double gananciaBruta;
	private double gananciaNeta;
	private double perdidaProyectada;
	private ArrayList<Integer> diasRestantes;
	
	public Vista() {
		sc = new Scanner(System.in).useLocale(Locale.ENGLISH);
	}
	
	public ModeloStock solicitarInformacion() {
		ModeloStock modelo = new ModeloStock();
		
		System.out.println();
		
		System.out.print("Introduce los detalles del producto: ");
		String detalles = sc.next();
		modelo.setDetalles(detalles);
		
		System.out.print("Introduce el color del producto: ");
		String color = sc.next();
		modelo.setColor(color);
		
		System.out.print("Introduce el peso del producto (kg): ");
		double peso = sc.nextDouble();
		modelo.setPeso(peso);
		
		System.out.print("Introduce el precio del producto (€): ");
		double precio = sc.nextDouble();
		modelo.setPrecio(precio);
		
		System.out.print("Introduce las unidades vendidas: ");
		modelo.setUnidadesVendidas(sc.nextInt());
		
		System.out.print("Introduce el día de elaboración: ");
		int dia = sc.nextInt();
		System.out.print("Introduce el mes de elaboración: ");
		int mes = sc.nextInt();
		System.out.print("Introduce el año de elaboración: ");
		int ano = sc.nextInt();
		modelo.setFechaElaboracion(dia, mes, ano);
		
		System.out.print("Introduce el día de vencimiento: ");
		dia = sc.nextInt();
		System.out.print("Introduce el mes de vencimiento: ");
		mes = sc.nextInt();
		System.out.print("Introduce el año de vencimiento: ");
		ano = sc.nextInt();
		modelo.setFechaVencimiento(dia, mes, ano);
		
		return modelo;
	}
	
	public int numeroProductos() {		
		System.out.print("Introduce el número de productos que vas a introducir: ");
		int output = sc.nextInt();
		
		return output;
	}
	
	public void printStock(ArrayList<ModeloStock> productos) {
		for (int i=0; i<productos.size(); i++) {
			System.out.println();
			System.out.println("Detalles " + (i+1) + ": " + productos.get(i).getDetalles());
			System.out.println("Color " + (i+1) + ": " + productos.get(i).getColor());
			System.out.println("Peso " + (i+1) + ": " + productos.get(i).getPeso());
			System.out.println("Precio " + (i+1) + ": " + productos.get(i).getPrecio());
			System.out.println("Unidades vendidas " + (i+1) + ": " + productos.get(i).getUnidadesVendidas());
			System.out.println("Fecha elaboración " + (i+1) + ": " + productos.get(i).getFechaElaboracion());
			System.out.println("Fecha vencimiento " + (i+1) + ": " + productos.get(i).getFechaVencimiento());
		}
	}
	
	public void printFinanciera() {
		System.out.println();
		System.out.println("Ganancias Brutas: " + this.gananciaBruta);
		System.out.println("Ganancias Netas: " + this.gananciaNeta);
		System.out.println("Pérdidas Proyectadas: " + this.perdidaProyectada);
		for (int i=0; i<diasRestantes.size(); i++) {
			System.out.println("Días restantes producto " + (i+1) + ": " + this.diasRestantes.get(i));
		}
	}
	
	public void setInformacion(double gananciaBruta, double gananciaNeta, double perdidaProyectada, ArrayList<Integer> diasRestantes) {
		this.gananciaBruta = gananciaBruta;
		this.gananciaNeta = gananciaNeta;
		this.perdidaProyectada = perdidaProyectada;
		this.diasRestantes = diasRestantes;
	}
	
	public void closeScanner() {
		this.sc.close();
	}
}
