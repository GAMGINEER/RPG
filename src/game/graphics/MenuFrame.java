package game.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MenuFrame extends JFrame {
	public MenuFrame() {
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 30));
		this.setPreferredSize(new Dimension(600, 220));
	}
}
