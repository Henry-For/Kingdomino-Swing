import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ImageRenderer extends DefaultTableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbl = new JLabel();	
	//private ImageIcon casillero = new ImageIcon(getClass().getResource("\\Recorte-Fichas\\1.jpg"));
    
	
	public ImageRenderer() { super(); };

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
    	      boolean hasFocus, int row, int column) {
	    	lbl.setIcon(((Icon)value));
    	    //lbl.setText((String) "hola");
    	    return lbl;
    	  }
}
