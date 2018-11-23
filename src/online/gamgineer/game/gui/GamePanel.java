package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import online.gamgineer.game.object.Enemy;
import online.gamgineer.game.object.Item;
import online.gamgineer.game.object.Player;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_FRAME_WIDTH_SIZE = 600;
	private static final int DEFAULT_FRAME_HEIGHT_SIZE = 600;
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	private int frameWidthSize;
	private int frameHeightSize;
	private Player playerObject;
	private Enemy enemyObject;
	private Item itemObject;

	public GamePanel() {
		this.frameWidthSize = GamePanel.DEFAULT_FRAME_WIDTH_SIZE;
		this.frameHeightSize = GamePanel.DEFAULT_FRAME_HEIGHT_SIZE;
		this.setPreferredSize(new Dimension(GamePanel.DEFAULT_FRAME_WIDTH_SIZE, GamePanel.DEFAULT_FRAME_HEIGHT_SIZE));
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
		this.playerObject = new Player(frameWidthSize / 2, frameHeightSize / 2, 8, 16);
		this.enemyObject = new Enemy(0, 0, 8, 16, Color.GREEN);
		this.itemObject = new Item(100, 100, 8, 16, Color.CYAN);
	}

	public int getFrameWidthSize() {
		return this.frameWidthSize;
	}

	public int getFrameHeightSize() {
		return this.frameHeightSize;
	}

	public Player getPlayerObject() {
		return this.playerObject;
	}

	public Enemy getEnemyObject() {
		return this.enemyObject;
	}

	public Item getItemObject() {
		return this.itemObject;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObject(g);
	}

	private void drawObject(Graphics g) {
		this.playerObject.draw(g);
		this.enemyObject.draw(g);
		this.itemObject.draw(g);
	}

}
