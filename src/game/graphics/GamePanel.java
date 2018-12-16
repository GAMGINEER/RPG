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
	public int killedEnemy = 0;

	public GamePanel() {
		this.save = new Save();
		this.initial();
	}

	public void initial() {
		this.setPreferredSize(new Dimension(DEFAULT_FRAME_WIDTH_SIZE, DEFAULT_FRAME_HEIGHT_SIZE));
		this.setBackground(DEFAULT_BACKGROUND_COLOR);
		this.save = new Save();
		this.save.getMapSet().getCurrentMap().startEnemy();
		this.save.getMapSet().getCurrentMap().stopEnemy();
		this.save.getMapSet().getCurrentMap().setEnemyAlgorithm(this);
		this.save.getMapSet().getCurrentMap().startEnemy();
		this.repaint();
	}

	public Save getSave() {
		return this.save;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.drawSave(g);
	}

	private void drawSave(Graphics g) {
		this.save.getPlayer().draw(g);
		this.save.getMapSet().getCurrentMap().draw(g);
	}

}