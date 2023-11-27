/*
 * Nombre: Pedro Ramos Suárez
 * 
 * Ejercicio integrador N° 3: 
 * Desarrollar un programa que implemente los siguientes componentes:
 * - Hilos
 * - MVC 
 * - Funciones
 * 
 * Nos contrataron de la empresa Pepito Y Asociados (Venta de vehiculos) para diseñar un software a medida. El mismo esta orientado a la información contable de los clientes. Donde se lleva un registro
 * de toda la data financiera de la empresa. Ademas de esto, el sistema debe poder almacenar información asociada al stock de los productos.
 * 
 * (Modelo - Hilo 1)
 * Modulo de stock: 
 * - Detalle del producto
 * - Color
 * - Peso
 * - Precio
 * - Unidades vendidas
 * - Fecha de elaboración
 * - Fecha de Vencimiento
 * 
 * (Controlador - Hilo 2)
 * Modulo de Financiera:
 * - Ganancias Bruta = Suma de las unidades vendidas
 * - Ganancia Neta = Ganancia bruta * 0.83
 * - Perdida proyectada = Ganancia neta / 12
 * - Diferencia de días entre F. Elaboración - F. Vencimiento 
 * 
 * (Vista - Hilo 3):
 * La información del Stock debe ser ingresada por consola (para al menos 5 productos) y la información de Financiera se arma en base al Stock. 
 * Mostrar por consola la información del modulo de Financiera
 * 
 * PUNTOS EXTRA:
 * - Que el usuario pueda ver la información por consola y pueda elegir. 
 * ¿Que información queres ver?
 * 1. Stock
 * 2. Información financiera.
 * 
 * Entregable:
 * - Link del repositorio.
 */
public class EjercicioIntegrador3 {
	public static void main (String[] args) {
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista);
		controlador.Ejercicio();
		
	}
}
