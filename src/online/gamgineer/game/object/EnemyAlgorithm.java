package online.gamgineer.game.object;

import java.awt.Color;

import online.gamgineer.game.control.KeyMapping;
import online.gamgineer.game.gui.GamePanel;

public class EnemyAlgorithm extends Thread {

	private static final int DELAY = 100;
	private static final int SPEED = 5;

	private GamePanel gamePanel;
	private Player player;
	private Enemy enemy;

	public EnemyAlgorithm(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.player = (Player) this.gamePanel.getCurrentMap().getGameObject().get("player");
		this.enemy = (Enemy) this.gamePanel.getCurrentMap().getGameObject().get("enemy");
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

			try {// 적이 따라감

				if (gamePanel.getEnemyHP() < 0) {
					System.out.println("적이 죽었습니다.");
					enemy.setColor(Color.gray);

					player.setColor(Color.RED);
					player.setHeight(15);
					player.setWidth(15);

					this.gamePanel.getCurrentMap().addObject("item",
							new Item(this.enemy.getPosX(), this.enemy.getPosY()));
					Item item = (Item) this.gamePanel.getCurrentMap().getGameObject().get("item0");
					item.setColor(Color.pink);
					/// item이 null임
					gamePanel.repaint();

					return;
				}

				this.follow();
//				System.out.println("dx, dy : "+dx+", "+dy);
//				System.out.println("real dy, dy : "+(this.enemy.getPosX() - this.player.getPosX())+", "+(this.enemy.getPosY() - this.player.getPosY()));
				player.setColor(Color.RED);
				player.setHeight(15);
				player.setWidth(15);
				gamePanel.repaint();
				Thread.sleep(DELAY);

			} catch (InterruptedException e) {
				System.out.println(e);
			}

			if (Math.abs(dx) <= 15 && Math.abs(dy) <= 15) {// 적과 부딪힘 검사
				System.out.println("적과 부딪힙니다!");
				FLAG = 0;
			} else {
				FLAG = 1;
			}

			if (FLAG == 0) {// 부딪혔을 경우

				if (this.gamePanel.getPlayerHP() > 10) { // 체력이 10보다 클 경우

					System.out.println("부딪혔다!");
					System.out.printf(this.gamePanel.getPlayerHP() + "-10 -> " + (gamePanel.getPlayerHP() - 10) + "\n");
					this.gamePanel.setPlayerHP(this.gamePanel.getPlayerHP() - 10);

					knockBack(player, dx, dy);
					FLAG = 1;

				} else {
					System.out.println("부딪혔다!");
					System.out.printf(this.gamePanel.getPlayerHP() + "-10 -> " + (gamePanel.getPlayerHP() - 10) + "\n");
					this.gamePanel.setPlayerHP(this.gamePanel.getPlayerHP() - 10);
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
//		System.out.println("dx, dy : " + dx + ", " + dy);
		int x = 0;
		int y = 0;

		if (dx < 0) {
			// player이 enemy보다 오른쪽에 있을 때
//			System.out.println("내가 적보다 오른쪽에 있음");
			x = +KeyMapping.SPEED * 2;
			x = +KeyMapping.SPEED * 2;
		}
		if (dx > 0) {
			// player이 enemy보다 왼쪽에 있을 때
//			System.out.println("내가 적보다 왼쪽에 있음");
			x = -KeyMapping.SPEED * 2;
		}

		if (dy > 0) {
			// player이 enemy보다 아래에 있을 때
			y = -KeyMapping.SPEED * 2;
		}
		if (dy < 0) {
			// player이 enemy보다 위에 있을 때
			y = +KeyMapping.SPEED * 2;
		}

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

//		System.out.println("getposx : " + player.getPosX());
//		System.out.println("getposy : " + player.getPosY());

	}
}