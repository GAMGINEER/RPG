package game.control;

import java.awt.Color;
import java.io.Serializable;

import game.graphics.GamePanel;
import game.object.Enemy;
import game.object.Player;

public class EnemyAlgorithm extends Thread implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DELAY = 20;
	private static final int SPEED = 1;

	private GamePanel gamePanel;
	private Player player;
	private Enemy enemy;

	public EnemyAlgorithm(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.player = this.gamePanel.getSave().getPlayer();
		System.out.printf("%s\n", this.player);
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	// KeyMapping에 써져있는 이유로 만듦
	public Player getPlayer() {
		return player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	@Override
	public void run() {
		int FLAG = 0;
		while (true) {
			int dx = this.enemy.getPosX() - this.player.getPosX(); // 적이 오른쪽에 있으면 양수, 내가 오른쪽에 있으면 음수
			int dy = this.enemy.getPosY() - this.player.getPosY(); // 적이 위에 있으면 양수, 내가 위에 있으면 음수
			try {
				if (this.enemy.getHealthPoint() < 0) {
					System.out.println(this.enemy+"이 죽었습니다.");
					this.gamePanel.getSave().getCurrentMap().removeEnemy(this.enemy);
					player.setWidth(Player.DEFAULT_WIDTH);
					player.setHeight(Player.DEFAULT_HEIGHT);
					player.setColor(Player.DEFAULT_COLOR);
					this.enemy.setColor(Color.GRAY);
					gamePanel.repaint();
					return;
				}
				this.follow();
				gamePanel.repaint();
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			}
			if (Math.abs(dx) <= 15 && Math.abs(dy) <= 15) {// 적과 부딪힘 검사
				System.out.println("적과 부딪힙니다!");
				FLAG = 0;
			} else {
				FLAG = 1;
			}
			if (FLAG == 0) {
				if (this.player.getHealthPoint() > 10) { // 체력이 10보다 클 경우
					System.out.println("부딪혔다!");
					System.out.printf(
							this.player.getHealthPoint() + "-10 -> " + (this.player.getHealthPoint() - 10) + "\n");
					this.player.setHealthPoint(this.player.getHealthPoint() - 10);
					knockBack(player, dx, dy);
					FLAG = 1;
				} else {
					System.out.println("부딪혔다!");
					System.out.printf(
							this.player.getHealthPoint() + "-10 -> " + (this.player.getHealthPoint() - 10) + "\n");
					this.player.setHealthPoint(this.player.getHealthPoint() - 10);
					System.out.println("YOU DIE");
					System.exit(0);
				}
			}

		}
	}

	private void follow() {
		int dx = 0;
		int dy = 0;

		if (this.player.getPosX() > this.enemy.getPosX()) {
			dx = SPEED;
		} else if (this.player.getPosX() < this.enemy.getPosX()) {
			dx = -SPEED;
		} else {
			dx = 0;
		}
		if (this.player.getPosY() > this.enemy.getPosY()) {
			dy = SPEED;
		} else if (this.player.getPosY() < this.enemy.getPosY()) {
			dy = -SPEED;
		} else {
			dy = 0;
		}
		this.enemy.move(dx, dy);
	}

	public void knockBack(Object obj, int dx, int dy) {
		int x = 0;
		int y = 0;

		if (dx < 0) {
			x = +KeyMapping.SPEED * 2;
			x = +KeyMapping.SPEED * 2;
		}
		if (dx > 0) {
			x = -KeyMapping.SPEED * 2;
		}

		if (dy > 0) {
			y = -KeyMapping.SPEED * 2;
		}
		if (dy < 0) {
			y = +KeyMapping.SPEED * 2;
		}

		if (this.player.getPosX() + x < 0 || this.player.getPosX() + x > 600 || this.player.getPosY() + y < 0.5
				|| this.player.getPosY() > 600) {
			System.out.println("벽쿵당함");
			this.player.move(-x, -y);
		} else {
			if (dx == 0 && dy == 0) {
				System.out.println("내가 넉백되었다.");
				this.player.move(KeyMapping.SPEED * 2, KeyMapping.SPEED * 2);
			} else if (obj.equals(this.player)) {
				System.out.println("내가 넉백되었다.");
				this.player.move(x, y);
			} else if (obj.equals(this.enemy)) {
				System.out.println("적을 넉백시켰다.");
				this.enemy.move(-x, -y);
			}
		}


	}
}