package game.object;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends MovableObject {
	// 직렬화에 따른 시리얼 버전 적용
	private static final long serialVersionUID = 1L;

	// 기본 상수 설정
	private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGHT = 20;
	private static final Color DEFAULT_COLOR = Color.RED;
	private static final String CATEGORY = "player";

	/**
	 * 기본 생성자
	 */
	public Player() {
		super(0 + DEFAULT_WIDTH / 2, 0 + DEFAULT_HEIGHT / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
		this.setCategory(CATEGORY);
	}

	/**
	 * X, Y 좌표 지정 생성자
	 * 
	 * @param posX
	 * @param posY
	 */
	public Player(int posX, int posY) {
		super(posX + DEFAULT_WIDTH / 2, posY + DEFAULT_HEIGHT / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
		this.setCategory(CATEGORY);
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
		super(posX + DEFAULT_WIDTH / 2, posY + DEFAULT_HEIGHT / 2, width, height, DEFAULT_COLOR);
		this.setCategory(CATEGORY);
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
		super(posX + DEFAULT_WIDTH / 2, posY + DEFAULT_HEIGHT / 2, width, height, color);
		this.setCategory(CATEGORY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawString(CATEGORY, this.getPosX() - (this.getWidth() / 2), this.getPosY() - ((this.getHeight() / 2)) - 2);
		g.fillRect(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}