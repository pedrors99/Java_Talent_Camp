import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controlador {
	private Vista vista;
	private ArrayList<Modelo> peliculas;
	private String peliculasFile;
	
	public Controlador(Vista vista) {
		this.vista = vista;
		this.peliculas = new ArrayList<>();
		this.peliculasFile = "peliculas2022.txt";
	}
	
	public void run() {
		this.input();
		this.addInformacion2023();
		this.imprimeInformacion();
		this.toJenkinsFile();
	}
	
	public void input() {
		ArrayList<ArrayList<String>> info = this.vista.readFile(this.peliculasFile);
		for (ArrayList<String> pelicula: info) {
			this.peliculas.add(new Modelo(pelicula));
		}
	}
	
	private void addInformacion2023() {		
		for (Modelo pelicula: this.peliculas) {
			pelicula.setEspectadores2023(this.espectadores2023(pelicula.getEspectadores2022()));
			pelicula.setSalas2023(this.salas2023(pelicula.getSalas2022()));
			pelicula.setGananciaBruta2023(this.gananciaBruta2023(pelicula.getGananciaBruta2022()));
			pelicula.setGananciaNeta2023(this.gananciaNeta2023(pelicula.getGananciaBruta2023()));
		}
	}
	
	private int espectadores2023(int espectadores2022) {
		return espectadores2022/2;
	}
	
	private int salas2023(int salas2022) {
		return salas2022/2;
	}
	
	private int gananciaBruta2023(int gananciaBruta2022) {
		return gananciaBruta2022/2;
	}
	
	private int gananciaNeta2023(int gananciaBruta2023) {
		return ((int) (gananciaBruta2023 * 0.8));
	}
	
	public void imprimeInformacion() {
		this.vista.imprimeInformacion(this.peliculas);
	}
	
	private void toJenkinsFile() {
		StringBuilder jenkinsfile = new StringBuilder();
		
		jenkinsfile.append("pipeline {\n");
		jenkinsfile.append("\tagent any\n");
		jenkinsfile.append("\tstages {\n");
		
		for(Modelo pelicula: this.peliculas) {
			jenkinsfile.append("\t\tstage(\"" + pelicula.getTitulo() + "\") {\n");
			jenkinsfile.append("\t\t\tsteps {\n");
			for (String line: pelicula.toString().split("\n")) {
				jenkinsfile.append("\t\t\t\tprint \"" + line + "\"\n");
			}
			jenkinsfile.append("\t\t\t}\n");
			jenkinsfile.append("\t\t}\n");
		}
		
		jenkinsfile.append("\t}\n");
		jenkinsfile.append("}\n");
		
		this.vista.toJenkinsFile(jenkinsfile.toString());
	}

}
