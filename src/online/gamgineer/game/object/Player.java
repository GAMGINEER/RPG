package online.gamgineer.game.object;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	// 직렬화에 따른 시리얼 버전 적용
	private static final long serialVersionUID = 1L;

	// 기본 상수 설정
	private static final int DEFAULT_WIDTH = 15;
	private static final int DEFAULT_HEIGHT = 15;
	private static final Color DEFAULT_COLOR = Color.RED;

	/**
	 * 기본 생성자
	 */
	public Player() {
		super(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
	}

	/**
	 * X, Y 좌표 지정 생성자
	 * 
	 * @param posX
	 * @param posY
	 */
	public Player(int posX, int posY) {
		super(posX, posY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
	}

	/**
	 * X, Y, 너비, 높이 지정 생성자
	 * 
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 */
	public Player(int posX, int posY, int width, int height) {
		super(posX, posY, width, height, DEFAULT_COLOR);
	}

	/**
	 * X, Y, 너비, 높이, 색상 지정 생성자
	 * 
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 * @param color
	 */
	public Player(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height, color);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawString("me", this.getPosX()-(this.getWidth()/2), this.getPosY()-((this.getHeight()/2))-2);
		g.fillRect(this.getPosX()-(this.getWidth()/2), this.getPosY()-(this.getHeight()/2), this.getWidth(), this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}