package online.gamgineer.game.object;

import java.awt.Color;

public class MovableObject extends GameObject {

	private static final long serialVersionUID = 1L;

	private int health;
	private int mana;

	public MovableObject(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height, color);
		this.health = 100;
		this.mana = 100;
	}

	public int getHealthPoint() {
		return this.health;
	}

	public int getManaPoint() {
		return this.mana;
	}

	public void setHealthPoint(int health) {
		this.health = health;
	}

	public void setManaPoint(int mana) {
		this.mana = mana;
	}
}
