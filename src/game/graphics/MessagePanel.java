package game.graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public MessagePanel(int width, int height) {
		this.setSize(width, height);
		JLabel label = new JLabel("Hello World!");
		JButton button = new JButton("Hello Button!");
	}
}
