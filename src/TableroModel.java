
import javax.swing.table.DefaultTableModel;

public class TableroModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;

	private Object [][] casilleros =
		{
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null},
				{null,null,null,null,null}
		};
//		{
//				{new Casillero(1),new Casillero(2),new Casillero(3),new Casillero(4),new Casillero(5)},
//				{new Casillero(6),new Casillero(7),new Casillero(8),new Casillero(9),new Casillero(10)},
//				{new Casillero(11),new Casillero(12),new Casillero(13),new Casillero(14),new Casillero(15)},
//				{new Casillero(15),new Casillero(16),new Casillero(17),new Casillero(18),new Casillero(19)},
//				{new Casillero(20),new Casillero(21),new Casillero(22),new Casillero(23),new Casillero(24)}
//		};
//	private Object[] columns = new Object[] {
//			"New column", "New column", "New column", "New column", "New column"
//		};
	public TableroModel() {
	}
	
	@Override
	public int getRowCount() {
		return 5;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return casilleros[rowIndex][columnIndex];
	}
	
//	public Class<? extends Object> getColumnClass(int c) {
//        return getValueAt(0, c).getClass();
//    }
//	
	@Override
    public void setValueAt(Object value, int row, int col) {
        casilleros[row][col] = value;
    }

}