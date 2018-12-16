package game.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.graphics.GamePanel;

public class Player extends MovableObject {
	// 직렬화에 따른 시리얼 버전 적용
	private static final long serialVersionUID = 1L;

	// 기본 상수 설정
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 20;
	public static final Color DEFAULT_COLOR = Color.RED;
	private static final String CATEGORY = "player";
	private GamePanel gamePanel; //현재 캐릭터의 이미지를 표시하기 위한 gamePanel

	
	public static int moveStatus; //0 : 서있기, 1 : 움직이기, 2 : 공격
	
	
	/**
	 * 기본 생성자
	 */
	
	public void setGamePanel(GamePanel gp) { //GamePanel을 받아온다.
		this.gamePanel = gp;
	}
	
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
		// 이름 표시
		g.setColor(this.getColor());
		g.drawString(CATEGORY, this.getPosX() - (this.getWidth() + 4), this.getPosY() - this.getHeight() - 5);

		// 체력 표시
		// 체력바
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(this.getPosX() - (this.getWidth() + 13), this.getPosY() - (this.getHeight() / 2) - 10, 100 / 2, 8);
		g.setColor(Color.PINK);
		g.fillRect(this.getPosX() - (this.getWidth() + 13), this.getPosY() - (this.getHeight() / 2) - 10,
				this.getHealthPoint() / 2, 8);
		// 체력 수치
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Serif", Font.BOLD, 10));
		g.drawString("HP : " + Integer.toString(this.getHealthPoint()), this.getPosX() - (this.getWidth() + 8),
				this.getPosY() - (this.getHeight() / 2) - 2);
		g.setFont(new Font("굴림", Font.PLAIN, 12));
		g.setColor(this.getColor());

		// 본인 표시
		g.fillRect(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
		
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

}