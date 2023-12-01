import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Vista {

	ArrayList<ArrayList<String>> readFile(String fileName) {
		ArrayList<ArrayList<String>> info = new ArrayList<>();
		File file = new File(fileName);
		try {
			Scanner reader = new Scanner(file);
			String data = "";
		      while (reader.hasNextLine()) {
		        data += reader.nextLine() + "\n";
		      }
		      reader.close();
		      
		      ArrayList<String> lines = new ArrayList<>(Arrays.asList(data.split("\n")));
		      
		      for (int i=1; i<lines.size(); i++) {
		    	  String line = lines.get(i).trim();//.replaceAll("\\s+", " ");
		    	  info.add(new ArrayList<String>(Arrays.asList(line.split("\\s\\s+"))));
	    	  }
		} catch (IOException e) {
			System.err.println("Error al leer el fichero");
			e.printStackTrace();
		}

		return info;
	}
	
	public void imprimeInformacion(ArrayList<Modelo> peliculas) {
		for (Modelo pelicula: peliculas) {
			System.out.println(pelicula);
		}
	}
	
	public String toJenkisFile(ArrayList<Modelo> peliculas) {
		StringBuilder jenkinsfile = new StringBuilder();
		
		jenkinsfile.append("pipeline {\n");
		jenkinsfile.append("\tagent any\n");
		jenkinsfile.append("\tstages {\n");
		
		for(Modelo pelicula: peliculas) {
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
		
		return jenkinsfile.toString();
	}
}
