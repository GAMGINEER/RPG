package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import online.gamgineer.game.object.GameCharacter;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 600; // (상수) GameFrame 가로 길이
	private static final int FRAME_HEIGHT_SIZE = 600; // (상수) GameFrame 세로 길이

	public GameFrame() {
		this.setTitle("GameFrame");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		GamePanel gp = new GamePanel(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
		this.addKeyListener(new GameControl(gp));

		this.add(gp);
		this.pack();
	}

}

class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int frame_width_size;
	private int frame_height_size;

	private GameCharacter playableCharacter;

	public GamePanel(int frame_width_size, int frame_height_size) {
		this.frame_width_size = frame_width_size;
		this.frame_height_size = frame_height_size;
		this.setPreferredSize(frame_width_size, frame_height_size);
		createObject();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObject(g);
	}

	public void createObject() {
		this.playableCharacter = new GameCharacter(frame_width_size / 2, frame_height_size / 2, 8, 16, Color.RED);
	}

	public void drawObject(Graphics g) {
		this.playableCharacter.draw(g);
	}

	public GameCharacter getPC() {
		return this.playableCharacter;
	}

	public void movePC(int dx, int dy) {
		if (this.playableCharacter.getX() + this.playableCharacter.getW() + dx <= frame_width_size
				&& this.playableCharacter.getX() + dx >= 0) {
			if (this.playableCharacter.getY() + this.playableCharacter.getH() + dy <= frame_height_size
					&& this.playableCharacter.getY() + dy >= 0) {
				this.playableCharacter.move(dx, dy);
			}
		}
		this.repaint();
	}

	private void setPreferredSize(int frameWidthSize, int frameHeightSize) {
		super.setPreferredSize(new Dimension(frameWidthSize, frameHeightSize));
	}

}
