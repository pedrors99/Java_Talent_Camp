import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
	
	public void toJenkinsFile(String jenkinsfile) {
		String fileName = "Jenkinsfile";
		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file, false);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(jenkinsfile);
	          
	        bw.close();
	        fw.close();
		} catch (IOException e) {
			System.err.println("Error al escribir a fichero.");
			e.printStackTrace();
		}
	}
}
