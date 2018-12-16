
package game.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MessagePanel() {
		this.setBackground(new Color(188, 143, 143));
		this.setPreferredSize(new Dimension(600, 200));
	}
}
