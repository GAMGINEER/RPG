package game.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.save.Save;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_FRAME_WIDTH_SIZE = 600;
	public static final int DEFAULT_FRAME_HEIGHT_SIZE = 600;
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	public Save save;

	private int frameWidthSize;
	private int frameHeightSize;

	public GamePanel() {
		this.frameWidthSize = DEFAULT_FRAME_WIDTH_SIZE;
		this.frameHeightSize = DEFAULT_FRAME_HEIGHT_SIZE;
		this.setPreferredSize(new Dimension(DEFAULT_FRAME_WIDTH_SIZE, DEFAULT_FRAME_HEIGHT_SIZE));
		this.setBackground(DEFAULT_BACKGROUND_COLOR);
		initial();
	}

	public GamePanel(int frameWidthSize, int frameHeightSize) {
		this.frameWidthSize = frameWidthSize;
		this.frameHeightSize = frameHeightSize;
		this.setPreferredSize(new Dimension(frameWidthSize, frameHeightSize));
		this.setBackground(DEFAULT_BACKGROUND_COLOR);
		initial();
	}

	public GamePanel(int frameWidthSize, int frameHeightSize, Color backGroundColor) {
		this.frameWidthSize = frameWidthSize;
		this.frameHeightSize = frameHeightSize;
		this.setPreferredSize(new Dimension(frameWidthSize, frameHeightSize));
		this.setBackground(backGroundColor);
		initial();
	}

	private void initial() {
		this.save = new Save();
		this.save.getCurrentMap().startEnemy();
		this.save.setLocation();
		this.save.getCurrentMap().stopEnemy();
		this.save.getCurrentMap().setEnemyAlgorithm(this);
		this.save.getCurrentMap().startEnemy();
		this.repaint();
	}

	public int getFrameWidthSize() {
		return this.frameWidthSize;
	}

	public int getFrameHeightSize() {
		return this.frameHeightSize;
	}

	public Save getSave() {
		return this.save;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObject(g);
	}

	public void drawObject(Graphics g) {
		this.save.getPlayer().draw(g);
		this.save.getCurrentMap().drawObject(g);
	}

}
