package online.gamgineer.game.control;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import online.gamgineer.game.gui.GamePanel;
import online.gamgineer.game.object.Enemy;
import online.gamgineer.game.object.EnemyAlgorithm;
import online.gamgineer.game.object.Player;

/**
 * 게임 창 키 맵핑용 클래스
 */
public class KeyMapping extends KeyAdapter {

	// 기본 속도 정수 설정
	public static final int SPEED = 15;

	// 변수
	private GamePanel gamePanel;
	private Player player;

	// 적과 내 캐릭터가 대체 어디에 저장됐고 어디서 불러와야하는지 감이안잡혀서 일단 여기서 불러옴
	private EnemyAlgorithm ea;

	// 생성자
	public KeyMapping(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		// player = (Player)
		// gamePanel.getMap().get("map0").getGameObject().get("player");
		player = (Player) gamePanel.getCurrentMap().getGameObject().get("player");
	}

//
//	// 키의 Char 값이 존재하는 키를 눌렀을 경우 실행되는 메소드
//	@Override
//	public void keyTyped(KeyEvent e) {
//		System.out.printf("DEBUGGING\tTYPED\t\tID: %s\tKeyText: %s\tKeyChar: %s\n", e.getID(),
//				KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());
//	}
//
	// 키가 눌렸을 경우 실행되는 메소드
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.printf("DEBUGGING\tPRESSED\t\tID: %s\tKeyText: %s\tKeyChar: %s\n", e.getID(),
//				KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());

		// 방향키 (↑) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (isBlocked(0, -(SPEED)) == false) {
				player.move(0, -(SPEED));
				gamePanel.repaint();
			}
		}

		// 방향키 (↓) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (isBlocked(0, SPEED) == false) {
				player.move(0, SPEED);
				gamePanel.repaint();
			}
		}

		// 방향키 (←) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (isBlocked(-(SPEED), 0) == false) {
				player.move(-(SPEED), 0);
				gamePanel.repaint();
			}
		}

		// 방향키 (→) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (isBlocked(SPEED, 0) == false) {
				player.move(SPEED, 0);
				gamePanel.repaint();
			}
		}

		// TODO 스페이스바를 눌렀을 때 dx, dy를 계산하고 거리가 가까울 시 enemy의 HP가 50줄어들고 공격 메세지를 출력
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("공격을 시도한다!!");

			Player player = (Player) this.gamePanel.getCurrentMap().getGameObject().get("player");
			Enemy enemy = (Enemy) this.gamePanel.getCurrentMap().getGameObject().get("enemy");

			int dx = enemy.getPosX() - player.getPosX();
			int dy = enemy.getPosY() - player.getPosY();
			
			if (Math.abs(dx) <= 30 && Math.abs(dy) <= 30) {
				gamePanel.setEnemyHP(gamePanel.getEnemyHP() - 34);
				player.setColor(Color.CYAN);
				player.setHeight(30);
				player.setWidth(30);
				gamePanel.repaint();
				
				this.gamePanel.getEA().knockBack(enemy, dx, dy);
				System.out.println("적을 공격했다! 적의 체력 : " + gamePanel.getEnemyHP());
			}
		}
	}

	// 키가 떼어졌을 때 실행되는 메소드
	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.printf("DEBUGGING\tRELEASED\tID: %s\tKeyText: %s\tKeyChar: %s\n\n", e.getID(),
//				KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());
	}

	private boolean isBlocked(int dx, int dy) {
		boolean dxBlocked = this.player.getPosX() + this.player.getWidth() + dx <= this.gamePanel.getFrameWidthSize()
				&& this.player.getPosX() + dx >= 0;
		boolean dyBlocked = this.player.getPosY() + this.player.getHeight() + dy <= this.gamePanel.getFrameHeightSize()
				&& this.player.getPosY() + dy >= 0;

		if (dxBlocked && dyBlocked) {
			return false;
		} else {
			return true;
		}
	}

}
