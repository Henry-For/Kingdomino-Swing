import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Juego extends JPanel {

	/**
	 * Create the panel.
	 */
	public Juego() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("VOLVER SALA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(176, 235, 93, 30);
		add(btnNewButton);

	}

}
