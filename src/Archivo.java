import java.io.File;
import java.util.Scanner;

public class Archivo {
	private String nombre;
	
	public Archivo(String nombre) {
		this.nombre = nombre;
	}
	
	public Mazo generarMazo() {
		
		File archivo = new File(this.nombre);
		try {
			if(!archivo.exists()) {
				System.err.println("Error al abrir el archivo");
		}
			Scanner sc = new Scanner(archivo);
			Mazo mazo = new Mazo();
			Ficha ficha;
			
			while(sc.hasNext()) {
				
				String linea = sc.nextLine();
				String valoresLinea[] = linea.split(" ");
				
				Posicion pos = null;
				Posicion pos2 = null;
				
				Casillero casillero = new Casillero(valoresLinea[1], Integer.valueOf(valoresLinea[2]), pos);
				Casillero casillero2 = new Casillero(valoresLinea[3], Integer.valueOf(valoresLinea[4]), pos2);
				
				Casillero [] casilleros = {casillero, casillero2};
				
				ficha = new Ficha(Integer.valueOf(valoresLinea[0]), casilleros);
				mazo.agregarFicha(ficha);	
			}
			
			sc.close();
			return mazo;
			
		} catch (Exception e) {
			return null;
		}
			
	}
}