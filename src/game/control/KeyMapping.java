package game.control;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;

import game.graphics.GameFrame;
import game.graphics.GamePanel;
import game.object.Enemy;
import game.object.Item;
import game.object.Player;

/**
 * 게임 창 키 맵핑용 클래스
 */
public class KeyMapping extends KeyAdapter {

	// 기본 속도 정수 설정
	public static final int SPEED = 10;

	// 변수
	private GamePanel gamePanel;
	private Player player;
	private GameFrame gf;
	
	public void setGameFrame(GameFrame gf) {
		this.gf = gf;
	}
	
	// 생성자
	public KeyMapping(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		// player = (Player)
		// gamePanel.getMap().get("map0").getGameObject().get("player");
		player = this.gamePanel.getSave().getPlayer();
	}

	// // 키의 Char 값이 존재하는 키를 눌렀을 경우 실행되는 메소드
	// @Override
	// public void keyTyped(KeyEvent e) {
	// System.out.printf("DEBUGGING\tTYPED\t\tID: %s\tKeyText: %s\tKeyChar: %s\n",
	// e.getID(),
	// KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());
	// }

	// 키가 눌렸을 경우 실행되는 메소드
	
	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.printf("DEBUGGING\tPRESSED\t\tID: %s\tKeyText: %s\tKeyChar: %s\n",
		// e.getID(),
		// KeyEvent.getKeyText(e.getKeyCode()), e.getKeyChar());

		// 방향키 (↑) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (isBlocked(0, -(SPEED)) == false) {
				player.moveStatus = 1;
				player.move(0, -(SPEED));
				gamePanel.repaint();
			}
			
			if(this.canGoNextMap(this.player.getPosX(), this.player.getPosY())) {
				String currentMapName = gf.getPanel().getSave().getCurrentMapName();
				if (currentMapName.equals("defaultField")) {
					System.out.println("enemyField로 이동합니다.");
					gf.getPanel().getSave().getCurrentMap().stopEnemy();
					gf.getPanel().getSave().getMapSet().changeCurrentMap("enemyField");
					gf.getPanel().getSave().getCurrentMap().setEnemyAlgorithm(gf.getPanel());
					gf.getPanel().getSave().getCurrentMap().startEnemy();
					gf.getPanel().repaint();
				} else if (currentMapName.equals("enemyField")) {
					System.out.println("someWhere로 이동합니다.");
					gf.getPanel().getSave().getCurrentMap().stopEnemy();
					gf.getPanel().getSave().getMapSet().changeCurrentMap("someWhere");
					gf.getPanel().getSave().getCurrentMap().setEnemyAlgorithm(gf.getPanel());
					gf.getPanel().getSave().getCurrentMap().startEnemy();
					gf.getPanel().repaint();
				} else if (currentMapName.equals("someWhere")) {
					System.out.println("defaultField로 이동합니다.");
					gf.getPanel().getSave().getCurrentMap().stopEnemy();
					gf.getPanel().getSave().getMapSet().changeCurrentMap("defaultField");
					gf.getPanel().getSave().getCurrentMap().setEnemyAlgorithm(gf.getPanel());
					gf.getPanel().getSave().getCurrentMap().startEnemy();
					gf.getPanel().repaint();
				}
			}
			
		}

		// 방향키 (↓) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (isBlocked(0, SPEED) == false) {
				player.moveStatus = 1;
				player.move(0, SPEED);
				gamePanel.repaint();
			}
		}

		// 방향키 (←) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (isBlocked(-(SPEED), 0) == false) {
				player.moveStatus = 1;
				player.move(-(SPEED), 0);
				gamePanel.repaint();
			}
		}

		// 방향키 (→) 이벤트
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (isBlocked(SPEED, 0) == false) {
				player.moveStatus = 1;
				player.move(SPEED, 0);
				gamePanel.repaint();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_F4) {
			gamePanel.getSave().outputData();
		}

		if (e.getKeyCode() == KeyEvent.VK_Q) { // Q : 체력 회복 아이템 사용
			// if 물약 아이템이 하나 이상 있다면,
			// 물약 아이템 수 --
			if (this.player.getHealthPoint() < 100) {
				if(this.player.getHealthPoint()<90)
					this.player.setHealthPoint(this.player.getHealthPoint() + 10);
				else
					this.player.setHealthPoint(100);
				System.out.println("체력을 회복했습니다.");
			} else {
				System.out.println("체력이 100보다 많습니다!");
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) { // 스페이스 바 : 공격
			System.out.println("공격을 시도한다!!");
 
			HashMap<String, Enemy> enemySet = this.gamePanel.getSave().getCurrentMap().getEnemySet();

			Iterator<String> iterator = enemySet.keySet().iterator();

			while (iterator.hasNext()) { // 현재 맵에 있는 적 하나하나마다 이 알고리즘을 실행
				String key = (String) iterator.next();
				Enemy enemy = enemySet.get(key);
				int dx = enemy.getPosX() - player.getPosX();
				int dy = enemy.getPosY() - player.getPosY();

				if (Math.abs(dx) <= 30 && Math.abs(dy) <= 30) { // 공격 가능 범위인지 검사
					enemy.setHealthPoint(enemy.getHealthPoint() - 34);
					player.setColor(Color.CYAN);
					player.setHeight(30);
					player.setWidth(30);
					gamePanel.repaint();

					enemySet.get(key).getAlgorithm().knockBack(enemySet.get(key), dx, dy);
					System.out.println("적을 공격했다! 적의 체력 : " + enemy.getHealthPoint());
					player.setColor(player.getColor());
					player.setHeight(Player.DEFAULT_HEIGHT);
					player.setWidth(Player.DEFAULT_WIDTH);
					player.setColor(Player.DEFAULT_COLOR);
				}

			}
			System.out.println("모든 적에 대한 공격을 완료했다.");

		}

		if (e.getKeyCode() == KeyEvent.VK_Z) { // Z키 : 아이템 줍기
			System.out.println("아이템 줍기를 시도한다!!");

			HashMap<String, Item> itemSet = this.gamePanel.getSave().getCurrentMap().getItemSet();
			Iterator<String> iterator = itemSet.keySet().iterator();

			while (iterator.hasNext()) { // 현재 맵에 있는 아이템 하나하나마다 이 알고리즘을 실행
				String key = iterator.next();
				Item item = itemSet.get(key);

				int dx = item.getPosX() - player.getPosX();
				int dy = item.getPosY() - player.getPosY();

				if (Math.abs(dx) <= 20 && Math.abs(dy) <= 20) { // 아이템 줍기 가능한 거리인지 검사
					this.gamePanel.getSave().getCurrentMap().removeItem(item);
					;
					item.move(1000, 1000);// item을 맵에서 사라지게 한다
					System.out.println("아이템을 먹었습니다.");
					this.gamePanel.repaint();
				}
			}
			System.out.println("모든 아이템에 대해 줍기를 완료했다.");
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
	
	private boolean canGoNextMap(int dx, int dy) {
		boolean x = this.player.getPosX()<530 && this.player.getPosX()>470;
		boolean y = this.player.getPosY()<530 && this.player.getPosY()>470;
		if (x && y) {
			return true;
		} else {
			return false;
		}
	}

}
