package game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public abstract class GameObject extends Object implements Serializable {

	private static final long serialVersionUID = 1L;

	private int posX; // X 좌표값
	private int posY; // Y 좌표값
	private int width; // 가로 길이값
	private int height; // 세로 길이값
	private Color color; // 색상
	private String objectName; // 객체 이름

	// // GameObject 생성자
	// public GameObject() {
	// this.setPosX(0); // X 좌표 초기값 설정
	// this.setPosY(0);// Y 좌표 초기값 설정
	// this.setWidth(0);// 가로 길이 초기값 설정
	// this.setHeight(0);// 세로 길이 초기값 설정
	// this.setColor(Color.BLACK);// 색상 초기값 설정
	// }

	// GameObject 특정 생성자
	public GameObject(int posX, int posY, int width, int height, Color color) {
		this.setPosX(posX); // X 좌표 초기값 설정
		this.setPosY(posY); // Y 좌표 초기값 설정
		this.setWidth(width); // 가로 길이 초기값 설정
		this.setHeight(height); // 세로 길이 초기값 설정
		this.setColor(color); // 색상 초기값 설정
		Random random = new Random();
		this.objectName = String.valueOf(random.nextLong());
	}

	/**
	 * 객체 draw 메소드
	 * 
	 * @param g
	 */
	public abstract void draw(Graphics g);

	/**
	 * 객체 move 메소드
	 * 
	 * @param dx
	 * @param dy
	 */
	public abstract void move(int dx, int dy);

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getObjectName() {
		return this.objectName;
	}

	@Override
	public String toString() {
		return String.format("objectName: %s\nposX: %d, posY: %d\nwidth: %d, height: %d", this.objectName, this.posX,
				this.posY, this.width, this.height);
	}

}
