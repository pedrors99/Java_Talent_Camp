import java.time.LocalDate;

public class ModeloStock {
	private String detalles;
	private String color;
	private double peso;
	private double precio;
	private int unidadesVendidas;
	private LocalDate fechaElaboracion;
	private LocalDate fechaVencimiento;

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	public String getDetalles() {
		return this.detalles;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public void setUnidadesVendidas(int unidadesVendidas) {
		this.unidadesVendidas = unidadesVendidas;
	}

	public int getUnidadesVendidas() {
		return this.unidadesVendidas;
	}
	
	public void setFechaElaboracion(LocalDate fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}
	
	public void setFechaElaboracion(int dia, int mes, int ano) {
		this.fechaElaboracion = LocalDate.of(ano, mes, dia);
	}
	
	public LocalDate getFechaElaboracion() {
		return this.fechaElaboracion;
	}
	
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public void setFechaVencimiento(int dia, int mes, int ano) {
		this.fechaVencimiento = LocalDate.of(ano, mes, dia);
	}
	
	public LocalDate getFechaVencimiento() {
		return this.fechaVencimiento;
	}
}
