package online.gamgineer.game.gui.deprecated;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

import online.gamgineer.game.object.Enemy;
import online.gamgineer.game.object.GameObject;
import online.gamgineer.game.object.Player;

/**
 *
 * @author JiMyoungHa
 *
 */
public class GamePanel extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int frame_width_size;
	private int frame_height_size;

	private Player playableCharacter;
	private Enemy enemyObject;
	private GameObject item1;

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
		this.enemyObject = new Enemy(0, 0, 8, 16, Color.GREEN);
		this.item1 = new GameObject(100, 100, 8, 16, Color.CYAN);
	}

	public void recreateObject() {
		this.playableCharacter = new Player(this.playableCharacter.getWidth(), this.playableCharacter.getHeight(), 8,
				16);
		this.enemyObject = new Enemy(this.enemyObject.getWidth(), this.enemyObject.getHeight(), 8, 16, Color.GREEN);
	}

	public void drawObject(Graphics g) {
		this.enemyObject.draw(g);
		this.item1.draw(g);
		this.playableCharacter.draw(g);
	}

	public Player getPC() {
		return this.playableCharacter;
	}

	public boolean isColide(Object a, Object b) {
		return false;

	}

	public void movePC(int dx, int dy) {
		this.playableCharacter.test();
		if (this.playableCharacter.getPosX() + this.playableCharacter.getWidth() + dx <= frame_width_size
				&& this.playableCharacter.getPosX() + dx >= 0) {
			if (this.playableCharacter.getPosY() + this.playableCharacter.getHeight() + dy <= frame_height_size
					&& this.playableCharacter.getPosY() + dy >= 0) {
				this.playableCharacter.move(dx, dy);
			}
		}
		this.repaint();
	}

	public void moveEnemy(int dx, int dy) {
		if (this.enemyObject.getPosX() + this.enemyObject.getWidth() + dx <= frame_width_size
				&& this.enemyObject.getPosX() + dx >= 0) {
			if (this.enemyObject.getPosY() + this.enemyObject.getHeight() + dy <= frame_height_size
					&& this.enemyObject.getPosY() + dy >= 0) {
				this.enemyObject.move(dx, dy);
			}
		}
		this.repaint();
	}
}