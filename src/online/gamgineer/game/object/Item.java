package online.gamgineer.game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Item extends GameObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public Item(int posX, int posY) {
		super(posX, posY, 15, 15, Color.blue);
	}
	public Item(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height, color);
	}

	@Override
	public void draw(Graphics g) {
		g.drawString("item", this.getPosX()-3, this.getPosY()-6);
		g.setColor(this.getColor());
		g.fillOval(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}
