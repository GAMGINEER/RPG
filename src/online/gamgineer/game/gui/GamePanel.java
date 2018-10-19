package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import online.gamgineer.game.object.Player;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int frame_width_size;
	private int frame_height_size;

	private Player playableCharacter;

	public GamePanel(int frame_width_size, int frame_height_size) {
		this.frame_width_size = frame_width_size;
		this.frame_height_size = frame_height_size;
		this.setPreferredSize(new Dimension(frame_width_size, frame_height_size));
		this.setBackground(Color.WHITE);
		createObject();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObject(g);
	}

	public void createObject() {
		this.playableCharacter = new Player(frame_width_size / 2, frame_height_size / 2, 8, 16);
	}

	public void drawObject(Graphics g) {
		this.playableCharacter.draw(g);
	}

	public Player getPC() {
		return this.playableCharacter;
	}

	public void movePC(int dx, int dy) {
		if (this.playableCharacter.getPosX() + this.playableCharacter.getWidth() + dx <= frame_width_size
				&& this.playableCharacter.getPosX() + dx >= 0) {
			if (this.playableCharacter.getPosY() + this.playableCharacter.getHeight() + dy <= frame_height_size
					&& this.playableCharacter.getPosY() + dy >= 0) {
				this.playableCharacter.move(dx, dy);
			}
		}
		this.repaint();
	}

}