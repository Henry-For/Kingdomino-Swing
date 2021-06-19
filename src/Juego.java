import javax.swing.JPanel;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Component;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Juego extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTable table4;
	private JTable pilaAct;
	private JTable pilaSig;
	private Integer cantJugadores;

	public Juego(Integer cantJugadores) {
		this.cantJugadores = cantJugadores;
		System.out.println(cantJugadores);

		setLayout(null);
		setBounds(100, 100, 1920, 1080);
	}

	private JTable crearPila(int x, int y) {
		DefaultTableModel model = new DefaultTableModel(0, 2);
		JTable table = new JTable(model);
		ImageIcon icon = new ImageIcon(getClass().getResource("1.jpg"));
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back
		table.setRowSelectionAllowed(false);
		table.setRowHeight(90);
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		model.addRow(new Object[] { icon, "test" });
		model.addRow(new Object[] { null, icon });
		model.addRow(new Object[] { icon, "test3" });
		model.addRow(new Object[] { icon, "test3" });
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(x, y, 180, 360);/* ATENCION !!! AL CAMBIAR LA INTERFAZ ESTO SE CAMBIA */
		table.enable(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int row = table.rowAtPoint(event.getPoint());
				int col = table.columnAtPoint(event.getPoint());
				System.out.println(row + " " + col);
				System.out.println(table.getValueAt(row, col));
			}
		});

		return table;
	}

	private JTable crearTabla(int x, int y) {
		DefaultTableModel model = new DefaultTableModel(0, 5);
		JTable table = new JTable(model);
		ImageIcon icon = new ImageIcon(getClass().getResource("1.jpg"));
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back
		table.setRowSelectionAllowed(false);
		table.setRowHeight(90);
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(4).setCellRenderer(new ButtonCell());
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		model.addRow(new Object[] { icon, "test", icon });
		model.addRow(new Object[] { null, icon, icon });
		model.addRow(new Object[] { icon, "test3", icon });
		model.addRow(new Object[] { icon, "test3", icon });
		model.addRow(new Object[] { icon, "test3", icon });
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(x, y, 450, 450);
		table.enable(false);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int row = table.rowAtPoint(event.getPoint());
				int col = table.columnAtPoint(event.getPoint());
				System.out.println(row + " " + col);
				System.out.println(table.getValueAt(row, col));
			}
		});

		return table;
	}

	private class ButtonCell extends AbstractCellEditor implements /* TableCellEditor, */ TableCellRenderer {

		private JLabel btn;

		ButtonCell() {
			btn = new JLabel();
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof Icon) {
				btn.setIcon((Icon) value);
				btn.setText(null);
			} else {
				btn.setIcon(null);
				btn.setText(null);
			}
			return btn;
		}
	}

	private void crearTablas() {
		System.out.println(this.cantJugadores);
		table1 = crearTabla(10, 11);
		add(table1);
		
		table2 = crearTabla(1460, 11);
		add(table2);

		if (this.cantJugadores >= 3) {
			table3 = crearTabla(1460, 560);
			add(table3);
		}
		
		if (this.cantJugadores == 4) {
			table4 = crearTabla(10, 560);
			add(table4);
		}
		
		pilaAct = crearPila(720, 130);
		pilaSig = crearPila(1020, 130);

		add(pilaAct);
		add(pilaSig);
	}
	
	public void crearJuego() {
		crearTablas();

		JLabel lblNewLabel = new JLabel("PILA DE ROBO ACTUAL");
		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblNewLabel.setBounds(702, 87, 241, 32);
		add(lblNewLabel);

		JLabel lblPilaDeRobo = new JLabel("PILA DE ROBO SIGUIENTE");
		lblPilaDeRobo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
		lblPilaDeRobo.setBounds(996, 87, 241, 32);
		add(lblPilaDeRobo);
	}
	
	
}
