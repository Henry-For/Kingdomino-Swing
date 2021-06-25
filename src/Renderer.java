import java.awt.Component;
import java.awt.Image;

import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class Renderer extends AbstractCellEditor implements TableCellRenderer {

	private JLabel btn;

	Renderer() {
		btn = new JLabel();
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Icon) {
			btn.setIcon((Icon) value);
			btn.setText(null);
		} else if (value instanceof Casillero) {
			ImageIcon icon = escalarImagen(((Casillero) value).getImagen(), 90, 90);
			btn.setIcon(icon);
			btn.setText(null);
		} else {
			btn.setIcon(null);
			btn.setText(null);
		}
		return btn;
	}
	
	private ImageIcon escalarImagen(Image imagen, int width, int heigth) {
		return new ImageIcon(imagen.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH));
	}
}