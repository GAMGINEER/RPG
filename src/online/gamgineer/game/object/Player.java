package online.gamgineer.game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Player extends GameObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public Player(int posX, int posY, int width, int height) {
		super(posX, posY, width, height, Color.RED);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}
