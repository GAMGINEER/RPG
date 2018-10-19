package online.gamgineer.game.gui;

import javax.swing.JFrame;

import online.gamgineer.game.control.KeyMapping;

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
		this.addKeyListener(new KeyMapping(gp)); // 게임 패널에 입력키 리스너 추가
		this.add(gp); // 게임 창에 게임 패널 추가

		// Container gameFrameContentPane = this.getContentPane();
		// gameFrameContentPane.setBackground(Color.BLACK);

		this.pack(); // 게임창 크기 조정
	}

}
