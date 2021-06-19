import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Juego extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Create the panel.
	 */
	public Juego() {
		DefaultTableModel model = new TableroModel();
		setLayout(null);
		setBounds(100, 100, 1920, 1080);
		table = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ImageIcon icon = new ImageIcon(getClass().getResource("Recorte-Fichas\\1.jpg"));
				
				model.setValueAt(icon,table.getSelectedRow(),table.getSelectedColumn());
				model.fireTableCellUpdated(table.getSelectedRow(), table.getSelectedColumn());
				
				System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				System.out.println(table.getSelectedRow()+"-"+table.getSelectedColumn());
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setCellSelectionEnabled(true);
		table.setDefaultRenderer(Object.class,new ImageRenderer());
		table.setBounds(10, 11, 450, 450);
		table.setRowHeight(90);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		
		
		
		//model.setValueAt(icon, ERROR, ABORT);
		//model.addRow(new Object[]{null,null,null});
		
//		model.setValueAt(icon,0,1);
//		model.setValueAt(new ImageIcon(getClass().getResource("Recorte-Fichas\\2.jpg")),0,0);
		
		//img.getTableCellRendererComponent(table,"lol", getFocusTraversalKeysEnabled(), isFocusOwner(), 0,0);
		
		add(table);
		
		
		
		
		
//		table_1 = new JTable();
//		table_1.setDefaultRenderer(getClass(), new ImageRenderer());
//		table_1.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column"
//			}
//		));
//		table_1.setRowHeight(90);
//		table_1.setCellSelectionEnabled(true);
//		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table_1.setBounds(1218, 11, 692, 450);
//		add(table_1);
//		
//		table_2 = new JTable();
//		table_2.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column"
//			}
//		));
//		table_2.setRowHeight(90);
//		table_2.setCellSelectionEnabled(true);
//		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table_2.setBounds(1218, 560, 692, 450);
//		add(table_2);
//		
//		table_3 = new JTable();
//		table_3.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
//		table_3.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column"
//			}
//		));
//		table_3.setRowHeight(90);
//		table_3.setCellSelectionEnabled(true);
//		table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table_3.setBounds(10, 564, 692, 450);
//		add(table_3);
//
	}
}
