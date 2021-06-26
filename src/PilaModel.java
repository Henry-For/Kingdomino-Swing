import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class PilaModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Ficha> fichas;
	
	public PilaModel(int cantJugadores) {
		super(cantJugadores,2);
		this.fichas = new ArrayList<Ficha>(cantJugadores);
	}

	public void insertarFicha(Ficha f) {
		if(this.fichas.size() == this.getRowCount())
			return;
		super.setValueAt(f.getCasilleros()[0],this.fichas.size(),0);
		super.setValueAt(f.getCasilleros()[1],this.fichas.size(),1);
		this.fichas.add(f);
	}
	
	public Ficha getFichaAt(int row,int col) {
		return this.fichas.get(row);
	}
	
	public void limpiar() {
		int i = 0;
		for (Ficha f : fichas) {
			super.setValueAt(f.getCasilleros()[0],i,0);
			super.setValueAt(f.getCasilleros()[1],i++,1);
		}
	}
	
	public void limpiarLista() {
		this.fichas.clear();
	}
}
