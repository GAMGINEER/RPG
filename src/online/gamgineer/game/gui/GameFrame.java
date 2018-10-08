package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 600; // (상수) GameFrame 가로 길이
	private static final int FRAME_HEIGHT_SIZE = 600; // (상수) GameFrame 세로 길이

	public GameFrame() {
		setTitle("GameFrame");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		GamePanel gp = new GamePanel(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);

		addKeyListener(new GameControl(gp));

		add(gp);

	}

}

class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Graphics g;

	private int frame_width_size;
	private int frame_height_size;

	private GameObject playableCharacter;

	public GamePanel(int frame_width_size, int frame_height_size) {
		this.frame_width_size = frame_width_size;
		this.frame_height_size = frame_height_size;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		createObject();
		drawObject(g);
		this.g = g;
	}

	public void createObject() {
		this.playableCharacter = new GameObject(frame_width_size / 2, frame_height_size / 2, 8, 16, Color.RED);
	}

	public void drawObject(Graphics g) {
		this.playableCharacter.draw(g);
	}

	public GameObject getPC() {
		return this.playableCharacter;
	}

	public void updatePC(int dx, int dy) {
		this.playableCharacter.move(dx, dy);
//		this.drawObject(g);
		this.repaint();
	}

}

class GameObject {

	private Graphics g;
	private int obj_pos_x; // 게임 객체 X 좌표값
	private int obj_pos_y; // 게임 객체 Y 좌표값
	private int obj_width; // 게임 객체 가로 길이
	private int obj_height; // 게임 객체 세로 길이
	private Color obj_color;

	public GameObject(int obj_pos_x, int obj_pos_y, int obj_width, int obj_height, Color obj_color) {
		this.obj_pos_x = obj_pos_x;
		this.obj_pos_y = obj_pos_y;
		this.obj_width = obj_width;
		this.obj_height = obj_height;
		this.obj_color = obj_color;
	}

	public void draw(Graphics g) {
		g.setColor(this.obj_color);
		g.fillRect(obj_pos_x, obj_pos_y, obj_width, obj_height);
		this.g = g;
	}

	public void move(int dx, int dy) {
		this.obj_pos_x = this.obj_pos_x + dx;
		this.obj_pos_y = this.obj_pos_y + dy;
	}

}