import java.util.ArrayList;

public class Modelo {
	private String titulo;
	
	private int espectadores2022;
	private int salas2022;
	private int gananciaBruta2022;
	private int gananciaNeta2022;
	
	private int espectadores2023;
	private int salas2023;
	private int gananciaBruta2023;
	private int gananciaNeta2023;

	public Modelo(String titulo, int espectadores2022, int salas2022, int gananciaBruta2022, int gananciaNeta2022) {
		this.titulo = titulo;
		
		this.espectadores2022 = espectadores2022;
		this.salas2022 = salas2022;
		this.gananciaBruta2022 = gananciaBruta2022;
		this.gananciaNeta2022 = gananciaNeta2022;
		
		this.espectadores2023 = -1;
		this.salas2023 = -1;
		this.gananciaBruta2023 = -1;
		this.gananciaNeta2023 = -1;
	}

	public Modelo(ArrayList<String> pelicula) {
		this.titulo = pelicula.get(0);
		
		this.espectadores2022 = Integer.valueOf(pelicula.get(1));
		this.salas2022 = Integer.valueOf(pelicula.get(2));
		this.gananciaBruta2022 = Integer.valueOf(pelicula.get(3).replace(".", ""));
		this.gananciaNeta2022 = Integer.valueOf(pelicula.get(4).replace(".", ""));
		
		this.espectadores2023 = -1;
		this.salas2023 = -1;
		this.gananciaBruta2023 = -1;
		this.gananciaNeta2023 = -1;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getEspectadores2022() {
		return this.espectadores2022;
	}

	public void setEspectadores2022(int espectadores2022) {
		this.espectadores2022 = espectadores2022;
	}

	public int getSalas2022() {
		return this.salas2022;
	}

	public void setSalas2022(int salas2022) {
		this.salas2022 = salas2022;
	}

	public int getGananciaBruta2022() {
		return this.gananciaBruta2022;
	}

	public void setGananciaBruta2022(int gananciaBruta2022) {
		this.gananciaBruta2022 = gananciaBruta2022;
	}

	public int getGananciaNeta2022() {
		return this.gananciaNeta2022;
	}

	public void setGananciaNeta2022(int gananciaNeta2022) {
		this.gananciaNeta2022 = gananciaNeta2022;
	}
	
	public int getEspectadores2023() {
		return this.espectadores2023;
	}
	
	public void setEspectadores2023(int espectadores2023) {
		this.espectadores2023 = espectadores2023;
	}

	public int getSalas2023() {
		return this.salas2023;
	}

	public void setSalas2023(int salas2023) {
		this.salas2023 = salas2023;
	}

	public int getGananciaBruta2023() {
		return this.gananciaBruta2023;
	}

	public void setGananciaBruta2023(int gananciaBruta2023) {
		this.gananciaBruta2023 = gananciaBruta2023;
	}

	public int getGananciaNeta2023() {
		return this.gananciaNeta2023;
	}

	public void setGananciaNeta2023(int gananciaNeta2023) {
		this.gananciaNeta2023 = gananciaNeta2023;
	}

	@Override
	public String toString() {
		String output = "";

		output += "Título: " + this.getTitulo() + "\n";
		output += "Espectadores 2022: " + this.getEspectadores2022() + "\n";
		output += "Salas 2022: " + this.getSalas2022() + "\n";
		output += "Ganancias Brutas 2022: " + this.getGananciaBruta2022() + "\n";
		output += "Ganancias Netas 2022: " + this.getGananciaNeta2022() + "\n";
		output += "Espectadores 2023: " + this.getEspectadores2023() + "\n";
		output += "Salas 2023: " + this.getSalas2023() + "\n";
		output += "Ganancias Brutas 2023: " + this.getGananciaBruta2023() + "\n";
		output += "Ganancias Netas 2023: " + this.getGananciaNeta2023() + "\n";

		return output;
	}
}
