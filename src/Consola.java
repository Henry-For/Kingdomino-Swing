import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

public class Consola extends JScrollPane{

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	public Consola(int x,int y,JTextArea textArea) {
		
		super(textArea);
		this.textArea = textArea;
		this.textArea.setEditable(false);
		this.textArea.setBounds(x, y, 400, 250);
		this.textArea.setBackground(new ColorUIResource(230, 177, 56));
		this.textArea.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		super.getViewport().setOpaque(false);
		super.setOpaque(false);
		super.setBounds(x, y, 400, 250);
		
	}
	
	public void escribir(String msj) {
		this.textArea.append(msj + "\n");
	}
}
