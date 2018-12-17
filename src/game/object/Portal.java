package game.object;

import java.awt.Color;
import java.awt.Graphics;

public class Portal extends GameObject {

	public Portal(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height, color);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillOval(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		
	}

}
