package online.gamgineer.game.object;

import java.awt.Color;
import java.awt.Graphics;

public class GameCharacter {

	private int obj_pos_x; // 게임 객체 X 좌표값
	private int obj_pos_y; // 게임 객체 Y 좌표값
	private int obj_width; // 게임 객체 가로 길이
	private int obj_height; // 게임 객체 세로 길이
	private Color obj_color;

	public GameCharacter(int obj_pos_x, int obj_pos_y, int obj_width, int obj_height, Color obj_color) {
		this.obj_pos_x = obj_pos_x;
		this.obj_pos_y = obj_pos_y;
		this.obj_width = obj_width;
		this.obj_height = obj_height;
		this.obj_color = obj_color;
	}

	public void draw(Graphics g) {
		g.setColor(this.obj_color);
		g.fillRect(obj_pos_x, obj_pos_y, obj_width, obj_height);
	}

	public void move(int dx, int dy) {
		this.obj_pos_x = this.obj_pos_x + dx;
		this.obj_pos_y = this.obj_pos_y + dy;
	}

	public int getX() {
		return this.obj_pos_x;
	}

	public int getY() {
		return this.obj_pos_y;
	}

	public int getW() {
		return this.obj_width;
	}

	public int getH() {
		return this.obj_height;
	}

}