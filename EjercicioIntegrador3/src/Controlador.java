import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
	private Vista vista;
	private ArrayList<ModeloStock> productos;
	
	public Controlador(Vista vista) {
		this.vista = vista;
		this.productos = new ArrayList<>();
	}
	
	public void Ejercicio() {
		Thread hilo1 = new Thread(new Input(this.vista, this.productos));
		Thread hilo2 = new Thread(new Calculos(this.vista, this.productos));
		Thread hilo3 = new Thread(new Output(this.vista, this.productos));
		
		hilo1.start();

		try {
			hilo1.join();
			hilo2.start();
			try {
				hilo2.join();
				hilo3.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Input implements Runnable {
	private Vista vista;
	private ArrayList<ModeloStock> productos;
	
	public Input(Vista vista, ArrayList<ModeloStock> productos) {
		this.vista = vista;
		this.productos = productos;
	}	
	
	public void run() {
		int productos = vista.numeroProductos();
		for (int i=0; i<productos; i++) {
			this.productos.add(vista.solicitarInformacion());
		}
	}
	
}
	
class Calculos implements Runnable {
	private Vista vista;
	private ArrayList<ModeloStock> productos;
	
	public Calculos(Vista vista, ArrayList<ModeloStock> productos) {
		this.vista = vista;
		this.productos = productos;
	}
	
	public void run() {
		ArrayList<Integer> diasRestantes = new ArrayList<>();
		for (int i=0; i<productos.size(); i++) {
			diasRestantes.add(getDiasRestantes(i));
		}
		vista.setInformacion(getGananciaBruta(), getGananciaNeta(), getPerdidaProyectada(), diasRestantes);
	}
	
	public double getGananciaBruta() {
		double output = 0;
		for (int i=0; i<this.productos.size(); i++) {
			output += productos.get(i).getUnidadesVendidas() * productos.get(i).getPrecio();
		}
		return output;
	}
	
	public double getGananciaNeta() {
		return this.getGananciaBruta() * 0.83;
	}
	
	public double getPerdidaProyectada() {
		return this.getGananciaNeta() / 12;
	}
	
	public int getDiasRestantes(int index) {
		int output = (int) ChronoUnit.DAYS.between(productos.get(index).getFechaElaboracion(), productos.get(index).getFechaVencimiento());
		return output;
	}
}

class Output implements Runnable {
	private Vista vista;
	private ArrayList<ModeloStock> productos;
	
	public Output(Vista vista, ArrayList<ModeloStock> productos) {
		this.vista = vista;
		this.productos = productos;
	}
	
	public void run() {		
		Scanner sc = new Scanner(System.in);
		System.out.print("¿Qué información desea obtener?" +
						"\n\t1. Información Stock." +
						"\n\t2. Información Financiera." +
						"\n\t3. Información Stock y Financiera");
		int opcion = sc.nextInt();
		sc.close();
		
		switch (opcion) {
			case 1:
				vista.printStock(this.productos);
				break;
			case 2:
				vista.printFinanciera();
				break;
			case 3:
				vista.printStock(this.productos);
				vista.printFinanciera();
				break;
			default:
				System.err.println("Error: Opción no válida");
		}
		vista.closeScanner();
	}
}
