package online.gamgineer.game.object;

import online.gamgineer.game.gui.GamePanel;

public class EnemyAlgorithm extends Thread {

	private static final int DELAY = 200;
	private static final int SPEED = 15;

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

	@Override
	public void run() {
		int FLAG = 0;
		do {
			if ((this.player.getPosX() == this.enemy.getPosX()) && (this.player.getPosY() == this.enemy.getPosY())) {
				FLAG = 0;
			} else {
				FLAG = 1;
			}
			try {
				this.follow();
				gamePanel.repaint();
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		} while (FLAG != 0);
		if (FLAG == 0) {
			System.exit(0);
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

}