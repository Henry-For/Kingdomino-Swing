import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mazo implements Cloneable {
	private static final int CARTAS_A_ENTREGAR = 4;
	private ArrayList<Ficha> fichas = new ArrayList<Ficha>(48);
	private int indice= 0;

	public Mazo() {
	}
	
	public Mazo(ArrayList<Ficha> fichas) {
		this.fichas = fichas;
	}
	
	public void agregarFicha(Ficha f){
		this.fichas.add(f);
	}

	public void mezclarMazo() {
		Collections.shuffle(fichas, new Random());
	}
	
	public List<Ficha> devolverFichas() {
		return fichas.subList(indice, indice += CARTAS_A_ENTREGAR);
	}
	
	@Override
	public String toString() {
		return "Mazo : "+ fichas;
	}
}
