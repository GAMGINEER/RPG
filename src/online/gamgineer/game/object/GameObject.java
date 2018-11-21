package online.gamgineer.game.object;

import java.awt.Color;
import java.awt.Graphics;

public class GameObject extends Object {

	private int posX; // X 좌표값
	private int posY; // Y 좌표값
	private int width; // 가로 길이값
	private int height; // 세로 길이값
	private Color color; // 색상

	// GameObject 생성자
	public GameObject() {
		this.setPosX(0); // X 좌표 초기값 설정
		this.setPosY(0);// Y 좌표 초기값 설정
		this.setWidth(0);// 가로 길이 초기값 설정
		this.setHeight(0);// 세로 길이 초기값 설정
		this.setColor(Color.BLACK);// 색상 초기값 설정
	}

	// GameObject 특정 생성자
	public GameObject(int posX, int posY, int width, int height, Color color) {
		this.setPosX(posX); // X 좌표 초기값 설정
		this.setPosY(posY); // Y 좌표 초기값 설정
		this.setWidth(width); // 가로 길이 초기값 설정
		this.setHeight(height); // 세로 길이 초기값 설정
		this.setColor(color); // 색상 초기값 설정
	}
	
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}

	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

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

}
