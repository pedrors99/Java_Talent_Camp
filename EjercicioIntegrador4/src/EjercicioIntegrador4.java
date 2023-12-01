/*
 * 
 * Nombre: Pedro Ramos Suárez
 * Debemos desarrollar una importante aplicación para una empresa de Finanzas que se encarga de contabilizar la ganancia de las 10 mejores peliculas de la historia. Para esto tienen en cuenta
 * los siguientes criterios: 
 * - Cantidad de espectadores globalmente
 * - Ganancia bruta (distinta de la ganancia neta)
 * - Cantidad de salas donde se proyecto la pelicula
 * Para esto comparan el año actual vs lo que paso el año anterior. Haciendo de información historica para llegar a conclusiones. 
 * 
 * Titulo                                        Cantidad de espectadores 2022          Cantidad de salas 2022              Ganancia Bruta 2022            Ganancia Neta 2022 (G. Bruta * 0.80)
 * Avatar	                                                100000                              10000                          1.000.000.000                            800.000.000
 * Avengers: Endgame	                                      90000                               9000                            810.000.000                             648.000.000
 * Avatar: The Way of Water                                80000                               8000                            640.000.000                             512.000.000
 * Titanic                                                 70000                               7000                            490.000.000                             392.000.000               
 * Star Wars: Episode VII - The Force Awakens              60000                               6000                            360.000.000                             288.000.000
 * Avengers: Infinity War                                  50000                               5000                            250.000.000                             200.000.000
 * Spider-Man: No Way Home	                                40000                               4000                            160.000.000                             128.000.000
 * Jurassic World	                                        30000                               3000                            90.000.000                              72.000.000
 * The Lion King	                                          20000                               2000                            40.000.000                              32.000.000
 * The Avengers                                            10000                               1000                            10.000.000                              8.000.000
 * 
 * En base a los datos de 2022 se debe proyectar cuales son los estimados de 2023 entendiendo que los espectadores de 2023 seran la mitad de los espectadores de 2022 y que la cantidad de salas de 2023
 * sera la mitad, tambien, de las que hubo en 2022. En base a esto calcular la Ganancia Bruta de 2023 y la Ganancia Neta de 2023. 
 * 
 * SECCION JENKINS:
 * Generar un Jenkinsfile que contenga la información de 2023.
 * 
 * ENTREGABLES:
 * - Link del Repositorio
 * - Captura de pantalla de la consola de Jenkins.
*/

public class EjercicioIntegrador4 {

	public static void main(String[] args) {
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista);
		controlador.run();
	}
}
