package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import online.gamgineer.game.object.GameCharacter;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 600; // (상수) GameFrame 가로 길이
	private static final int FRAME_HEIGHT_SIZE = 600; // (상수) GameFrame 세로 길이

	public GameFrame() {

		// 게임창 제목
		this.setTitle("GameFrame");

		// 게임창 설정
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때 프로그램 종료가 아니라 창만 사라지게 함
		this.setLocationRelativeTo(null); // 창 위치 초기화
		this.setResizable(false); // 창 크기 조절기능 해제
		this.setVisible(true); // 창 보이기
		// this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);

		// 게임 패널 설정
		GamePanel gp = new GamePanel(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE); // 게임 패널 추가
		this.addKeyListener(new GameControl(gp)); // 게임 패널에 입력키 리스너 추가
		this.add(gp); // 게임 창에 게임 패널 추가

		// Container gameFrameContentPane = this.getContentPane();
		// gameFrameContentPane.setBackground(Color.BLACK);

		this.pack(); // 게임창 크기 조정
	}

}

class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int frame_width_size;
	private int frame_height_size;

	private GameCharacter playableCharacter;

	public GamePanel(int frame_width_size, int frame_height_size) {
		this.frame_width_size = frame_width_size;
		this.frame_height_size = frame_height_size;
		this.setPreferredSize(new Dimension(frame_width_size, frame_height_size));
		this.setBackground(Color.WHITE);
		createObject();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObject(g);
	}

	public void createObject() {
		this.playableCharacter = new GameCharacter(frame_width_size / 2, frame_height_size / 2, 8, 16, Color.RED);
	}

	public void drawObject(Graphics g) {
		this.playableCharacter.draw(g);
	}

	public GameCharacter getPC() {
		return this.playableCharacter;
	}

	public void movePC(int dx, int dy) {
		if (this.playableCharacter.getX() + this.playableCharacter.getW() + dx <= frame_width_size
				&& this.playableCharacter.getX() + dx >= 0) {
			if (this.playableCharacter.getY() + this.playableCharacter.getH() + dy <= frame_height_size
					&& this.playableCharacter.getY() + dy >= 0) {
				this.playableCharacter.move(dx, dy);
			}
		}
		this.repaint();
	}

}
