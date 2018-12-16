package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Item extends GameObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGHT = 10;
	private static final Color DEFAULT_COLOR = Color.BLUE;

	public Item(int posX, int posY) {
		super(posX + DEFAULT_WIDTH / 2, posY + DEFAULT_HEIGHT / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawString(this.getObjectName(), this.getPosX() - (this.getWidth() / 2),
				this.getPosY() - ((this.getHeight() / 2)) - 2);
		g.fillOval(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}
