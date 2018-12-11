package game.object;

import java.awt.Color;
import java.awt.Graphics;

import game.control.EnemyAlgorithm;

public class Enemy extends MovableObject {

	// 직렬화에 따른 시리얼 버전 적용
	private static final long serialVersionUID = 1L;

	// 기본 상수 설정
	private static final int DEFAULT_WIDTH = 15;
	private static final int DEFAULT_HEIGHT = 15;
	private static final Color DEFAULT_COLOR = Color.GREEN;
	private static final String CATEGORY = "enemy";

	private EnemyAlgorithm algorithm;

	/**
	 * 기본 생성자
	 */
	
	public Enemy() {
		super(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
		initial();
	}

	/**
	 * X, Y 좌표 지정 생성자
	 * 
	 * @param posX
	 * @param posY
	 */
	public Enemy(int posX, int posY) {
		super(posX, posY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
		initial();
	}

	/**
	 * X, Y, 너비, 높이 지정 생성자
	 * 
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 */
	public Enemy(int posX, int posY, int width, int height) {
		super(posX, posY, width, height, DEFAULT_COLOR);
		initial();
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
	public Enemy(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height, color);
		initial();
	}

	private void initial() {
		this.setCategory(CATEGORY);
	}

	public EnemyAlgorithm getAlgorithm() {
		return this.algorithm;
	}

	public void setAlgorithm(EnemyAlgorithm algorithm) {
		this.algorithm = algorithm;
		this.algorithm.setEnemy(this);
	}

	public void start() {
		this.algorithm.start();
	}

	public void stop() {
		try {
			this.algorithm.interrupt();
		} catch (NullPointerException e) {
			System.out.printf("%s\n", e);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawString(CATEGORY, this.getPosX() - (this.getWidth()) - 1, this.getPosY() - ((this.getHeight() / 2)) - 2);
		g.fillRect(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}
	
	public EnemyAlgorithm getEnemyAlgorithm() { //이 emeny객체에 사용되는 알고리즘을 리턴
		return algorithm;
	}

}