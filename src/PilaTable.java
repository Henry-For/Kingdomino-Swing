

import javax.swing.JTable;

public class PilaTable extends JTable{

	private static final long serialVersionUID = 1L;

	public PilaTable(int x, int y) {
		
		super(new PilaModel());
		super.setRowSelectionAllowed(false);
		super.setRowHeight(90);
		super.getColumnModel().getColumn(0).setCellRenderer(new Renderer());
		super.getColumnModel().getColumn(1).setCellRenderer(new Renderer());
		super.getColumnModel().getColumn(0).setPreferredWidth(90);
		super.getColumnModel().getColumn(1).setPreferredWidth(90);
		super.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		super.setBounds(x, y, 180, 360);
	}
	
	@Override
	public boolean editCellAt(int row, int column, java.util.EventObject e) {
        return false;
     }
}
