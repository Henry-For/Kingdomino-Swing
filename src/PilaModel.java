import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class PilaModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Ficha> fichas;
	//private Casillero[][] casilleros;
	
	public PilaModel(int cantJugadores) {
		//super(cantJugadores,2);
		super(4,2);
		this.fichas = new ArrayList<Ficha>(cantJugadores);
		//this.casilleros = new Casillero[5][5];
	}

	/*
	@Override
	public Object getValueAt(int row,int col) {
		return this.casilleros[row][col];
	}*/
	/*
	@Override
	public void setValueAt(Object casillero,int row,int col) {
		super.setValueAt(casillero, row, col);
		//this.casilleros[row][col] = (Casillero)casillero;
	}
	*/
	public void insertarFicha(Ficha f) {
		if(this.fichas.size() == this.getRowCount())
			return;
		super.setValueAt(f.getCasilleros()[0],this.fichas.size(),0);
		super.setValueAt(f.getCasilleros()[1],this.fichas.size(),1);
		this.fichas.add(f);
		//if(this.fichas.size() == 4)
			//this.fichas.clear();
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
