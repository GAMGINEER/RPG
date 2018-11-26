package online.gamgineer.game.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import online.gamgineer.game.gui.GamePanel;

/**
 * 게임 창 키 맵핑용 클래스
 */
public class KeyMapping implements KeyListener {

	// 기본 속도 정수 설정
	private static final int DEFAULT_SPEED = 10;

	// 해당 게임 창 (GamePanel) 변수
	private GamePanel gamePanel;

	// 생성자
	public KeyMapping(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	// 키의 Char 값이 존재하는 키를 눌렀을 경우 실행되는 메소드
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.printf("DEBUGGING\tTYPED\t\tID: %s\tKeyText: %s\tKeyChar: %s\n", e.getID(),
				KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());
	}

	// 키가 눌렸을 경우 실행되는 메소드
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.printf("DEBUGGING\tPRESSED\t\tID: %s\tKeyText: %s\tKeyChar: %s\n", e.getID(),
				KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());

		// 방향키 (↑) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			gamePanel.getPlayerObject().move(0, -(DEFAULT_SPEED));
			gamePanel.repaint();
		}

		// 방향키 (↓) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gamePanel.getPlayerObject().move(0, DEFAULT_SPEED);
			gamePanel.repaint();
		}

		// 방향키 (←) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamePanel.getPlayerObject().move(-(DEFAULT_SPEED), 0);
			gamePanel.repaint();
		}

		// 방향키 (→) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamePanel.getPlayerObject().move(DEFAULT_SPEED, 0);
			gamePanel.repaint();
		}
	}

	// 키가 떼어졌을 때 실행되는 메소드
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.printf("DEBUGGING\tRELEASED\tID: %s\tKeyText: %s\tKeyChar: %s\n\n", e.getID(),
				KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());
	}

}
