import javax.swing.JPanel;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javafx.scene.control.Cell;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.table.TableModel;

public class Juego extends JPanel {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable pilaAct;
	private JTable pilaSig;
	/**
	 * Create the panel.
	 */
	public Juego() {
		
		setLayout(null);
		setBounds(100, 100, 1920, 1080);
		
        table = crearTabla(10, 11);
        table_1 = crearTabla(1460, 11);
        table_2 = crearTabla(1460, 560);
        table_3 = crearTabla(10, 560);
        pilaAct = crearPila(720,130);
        pilaSig = crearPila(1020,130);
        
        add(table);
        add(table_1);
        add(table_2);
        add(table_3);
        add(pilaAct);
        add(pilaSig);
        
        JLabel lblNewLabel = new JLabel("PILA DE ROBO ACTUAL");
        lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblNewLabel.setBounds(702, 87, 241, 32);
        add(lblNewLabel);
        
        JLabel lblPilaDeRobo = new JLabel("PILA DE ROBO SIGUIENTE");
        lblPilaDeRobo.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblPilaDeRobo.setBounds(996, 87, 241, 32);
        add(lblPilaDeRobo);
        
    
        
        
        
     
        
        
////		table = new JTable();
////		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
//				System.out.println(table.getSelectedRow()+"-"+table.getSelectedColumn());
//			}
//		});
//		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
//		table.setCellSelectionEnabled(true);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{ "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x"},
//				{ "ficha_x",  "ficha_x",  "ficha_x", "ficha_x",  "ficha_x"},
//				{ "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x"},
//				{ "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x"},
//				{ "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x",  "ficha_x"},
//			},
//			new String[] {
//				"New column", "New column", "New column", "New column", "New column"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				String.class, Object.class, Object.class, Object.class, Object.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
//		table.setBounds(10, 11, 692, 450);
//		table.setRowHeight(90);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		add(table);
//		
//		table_1 = new JTable();
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
	   private JTable crearPila(int x, int y) {
       	DefaultTableModel model = new DefaultTableModel(0,2);
       	JTable table = new JTable(model);
           ImageIcon icon = new ImageIcon(getClass().getResource("1.jpg"));
           Image image = icon.getImage(); // transform it 
           Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
           icon = new ImageIcon(newimg);  // transform it back
           table.setRowSelectionAllowed(false);
           table.setRowHeight(90);
           table.getColumnModel().getColumn(0).setCellRenderer(new ButtonCell());
           table.getColumnModel().getColumn(1).setCellRenderer(new ButtonCell());
           table.getColumnModel().getColumn(0).setPreferredWidth(90);
           table.getColumnModel().getColumn(1).setPreferredWidth(90);
           model.addRow(new Object[]{icon,"test"});
           model.addRow(new Object[]{null,icon});
           model.addRow(new Object[]{icon,"test3"});
           model.addRow(new Object[]{icon,"test3"});
           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           table.setBounds(x, y, 180, 360);/*ATENCION !!! AL CAMBIAR LA INTERFAZ ESTO SE CAMBIA*/
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
    	DefaultTableModel model = new DefaultTableModel(0,5);
    	JTable table = new JTable(model);
        ImageIcon icon = new ImageIcon(getClass().getResource("1.jpg"));
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back
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
        model.addRow(new Object[]{icon,"test", icon});
        model.addRow(new Object[]{null,icon, icon});
        model.addRow(new Object[]{icon,"test3", icon});
        model.addRow(new Object[]{icon,"test3", icon});
        model.addRow(new Object[]{icon,"test3", icon});
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
	
	private class ButtonCell extends AbstractCellEditor implements /*TableCellEditor,*/ TableCellRenderer{

        //private JButton btn;
        private JLabel btn;

        ButtonCell(){
            /*btn = new JButton();
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("clicked");
                }
            });*/
        	btn = new JLabel();
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            if(value instanceof Icon){
                btn.setIcon((Icon) value);
                btn.setText(null);
            } else {
                btn.setIcon(null);
                btn.setText(null);
            }
            return btn;
        }

        /*@Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            if(value instanceof Icon){
                btn.setIcon((Icon) value);
            } else {
                btn.setIcon(null);
            }
            return btn;
        }*/

    }
}
