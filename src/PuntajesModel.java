import java.util.List;

import javax.swing.table.DefaultTableModel;

public class PuntajesModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	private List<Jugador> jugadores;
	
	public PuntajesModel(List<Jugador> jugadores) {
		super(jugadores.size(),2);

		super.setColumnIdentifiers(new String[]{"Usuario","Puntaje"});
		
		this.jugadores = jugadores;
	}
	
	public void actualizarPuntajes() {
		Jugador.ordenarJugadoresPuntaje(jugadores);
		int i = 0;
		for (Jugador jugador : jugadores) {
			super.setValueAt(jugador.getNickName(), i, 0);
			super.setValueAt(jugador.getPuntaje(), i, 1);
			super.fireTableDataChanged();
			i++;
		}
	}

}
