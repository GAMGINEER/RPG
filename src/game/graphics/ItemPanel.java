package game.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ItemPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ItemPanel() {
		this.setBackground(new Color(112, 128, 144));
		this.setPreferredSize(new Dimension(250, 600));
	}
}
