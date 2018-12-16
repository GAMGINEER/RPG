package game.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player extends MovableObject {
	// 직렬화에 따른 시리얼 버전 적용
	private static final long serialVersionUID = 1L;

	// 기본 상수 설정
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 20;
	public static final Color DEFAULT_COLOR = Color.RED;

	public Player(int posX, int posY) {
		super(posX + DEFAULT_WIDTH / 2, posY + DEFAULT_HEIGHT / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
	}

	public void resetPlayer() {
		this.setWidth(Player.DEFAULT_WIDTH);
		this.setHeight(Player.DEFAULT_HEIGHT);
		this.setColor(Player.DEFAULT_COLOR);
	}

	public void attackedPlayer() {
		this.setWidth(30);
		this.setHeight(30);
		this.setColor(Color.CYAN);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawString("PLAYER", this.getPosX() - (this.getWidth() * 2), this.getPosY() - this.getHeight() - 5);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(this.getPosX() - (this.getWidth() + 13), this.getPosY() - (this.getHeight() / 2) - 10, 100 / 2, 8);
		g.setColor(Color.PINK);
		g.fillRect(this.getPosX() - (this.getWidth() + 13), this.getPosY() - (this.getHeight() / 2) - 10,
				this.getHealthPoint() / 2, 8);
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Serif", Font.BOLD, 10));
		g.drawString("HP : " + Integer.toString(this.getHealthPoint()), this.getPosX() - (this.getWidth() + 8),
				this.getPosY() - (this.getHeight() / 2) - 2);
		g.setFont(new Font("굴림", Font.PLAIN, 12));
		g.setColor(this.getColor());
		g.fillRect(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}