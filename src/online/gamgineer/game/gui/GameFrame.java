package online.gamgineer.game.gui;

import javax.swing.JFrame;

import online.gamgineer.game.control.KeyMapping;

public class GameFrame extends JFrame {

	// 직렬화에 따른 시리얼 버전 적용
	private static final long serialVersionUID = 1L;

	// 기본 상수 설정
	private static final int DEFAULT_FRAME_WIDTH_SIZE = 600;
	private static final int DEFAULT_FRAME_HEIGHT_SIZE = 600;

	public GameFrame() {

		// 게임창 제목
		this.setTitle("GameFrame");

		// 게임창 설정
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창을 닫을 때 프로그램 종료가 아니라 창만 사라지게 함
		this.setResizable(false); // 창 크기 조절기능 해제

		// 게임 패널 설정
		GamePanel gp = new GamePanel(DEFAULT_FRAME_WIDTH_SIZE, DEFAULT_FRAME_HEIGHT_SIZE); // 게임 패널 추가
		this.addKeyListener(new KeyMapping(gp)); // 게임 패널에 입력키 리스너 추가
		this.add(gp); // 게임 창에 게임 패널 추가
		this.pack(); // 게임창 크기 조정

		this.setLocationRelativeTo(null); // 창 위치 초기화
		this.setVisible(true); // 창 보이기
	}

}
