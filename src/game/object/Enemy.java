package game.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import game.control.KeyMapping;
import game.graphics.GamePanel;

public class Enemy extends MovableObject {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_WIDTH = 15;
	private static final int DEFAULT_HEIGHT = 15;
	private static final Color DEFAULT_COLOR = Color.GREEN;

	private EnemyAlgorithm enemyAlgorithm;

	public Enemy(int posX, int posY) {
		super(posX, posY, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
		initial();
	}

	public void initial() {
		this.enemyAlgorithm = new EnemyAlgorithm(this);
	}

	public EnemyAlgorithm getEnemyAlgorithm() {
		return this.enemyAlgorithm;
	}

	public void setEnemyAlgorithm(GamePanel gamePanel) {
		this.initial();
		this.enemyAlgorithm.intialize(gamePanel);
	}

	public void start() {
		this.enemyAlgorithm.start();
	}

	public void stop() {
		try {
			this.enemyAlgorithm.interrupt();
		} catch (NullPointerException e) {
			System.out.printf("%s\n", e);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawString(this.getObjectName(), this.getPosX() - (this.getWidth()) - 1,
				this.getPosY() - this.getHeight() - 5);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(this.getPosX() - (this.getWidth() + 10), this.getPosY() - (this.getHeight() / 2) - 10, 100 / 2, 8);
		g.setColor(new Color(144, 238, 144));
		g.fillRect(this.getPosX() - (this.getWidth() + 10), this.getPosY() - (this.getHeight() / 2) - 10,
				this.getHealthPoint() / 2, 8);
		// 체력 수치
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Serif", Font.BOLD, 10));
		g.drawString("HP : " + Integer.toString(this.getHealthPoint()), this.getPosX() - this.getWidth() - 5,
				this.getPosY() - (this.getHeight() / 2) - 3); // 체력 숫자로 표시

		g.setFont(new Font("굴림", Font.PLAIN, 12));
		g.setColor(this.getColor());
		g.fillRect(this.getPosX() - (this.getWidth() / 2), this.getPosY() - (this.getHeight() / 2), this.getWidth(),
				this.getHeight());
	}

	@Override
	public void move(int dx, int dy) {
		this.setPosX(this.getPosX() + dx);
		this.setPosY(this.getPosY() + dy);
	}

	public class EnemyAlgorithm extends Thread implements Serializable {

		private static final long serialVersionUID = 1L;

		private static final int DELAY = 20;
		private static final int SPEED = 1;

		private GamePanel gamePanel;
		private Player player;
		private Enemy enemy;

		public EnemyAlgorithm(Enemy enemy) {
			this.enemy = enemy;
		}

		public void intialize(GamePanel gamePanel) {
			this.gamePanel = gamePanel;
			this.player = this.gamePanel.getSave().getPlayer();
		}

		public void resetPlayer() {
			this.player = this.gamePanel.getSave().getPlayer();
		}

		@Override
		public void run() {
			int FLAG = 0;
			while (true) {
				int dx = this.enemy.getPosX() - this.player.getPosX();
				int dy = this.enemy.getPosY() - this.player.getPosY();
				try {
					if (this.enemy.getHealthPoint() < 0) {
						System.out.printf("SYSTEM >> ENEMY %s DIED\n", this.enemy.getObjectName());
						this.enemy.setColor(Color.GRAY);
						this.player.resetPlayer();
						this.gamePanel.getSave().getMapSet().getCurrentMap().removeEnemy(this.enemy);
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
					System.out.printf("SYSTEM >> COLLIDING WITH ENEMY %s\n", this.enemy.getObjectName());
					FLAG = 0;
				} else {
					FLAG = 1;
				}
				if (FLAG == 0) {
					System.out.printf("SYSTEM >> ENEMY %s COLLIDED\n", this.enemy.getObjectName());
					if (this.player.getHealthPoint() > 10) { // 체력이 10보다 클 경우
						System.out.printf(
								this.player.getHealthPoint() + "-10 -> " + (this.player.getHealthPoint() - 10) + "\n");
						this.player.setHealthPoint(this.player.getHealthPoint() - 10);
						knockBack(player, dx, dy);
						FLAG = 1;
					} else {
						System.out.printf(
								this.player.getHealthPoint() + "-10 -> " + (this.player.getHealthPoint() - 10) + "\n");
						this.player.setHealthPoint(this.player.getHealthPoint() - 10);
						System.out.printf("SYSTEM >> PLAYER DIED\n");
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
				System.out.printf("SYSTEM >> COLLIDED TO WALL\n");
				this.player.move(-x, -y);
			} else {
				if (dx == 0 && dy == 0) {
					System.out.printf("SYSTEM >> PLAYER KNOCKBACKED BY ENEMY %s\n", this.enemy.getObjectName());
					this.player.move(KeyMapping.SPEED * 2, KeyMapping.SPEED * 2);
				} else if (obj.equals(this.player)) {
					System.out.printf("SYSTEM >> PLAYER KNOCKBACKED BY ENEMY %s\n", this.enemy.getObjectName());
					;
					this.player.move(x, y);
				} else if (obj.equals(this.enemy)) {
					System.out.printf("SYSTEM >> PLAYER KNOCKBACKED ENEMY %s\n", this.enemy.getObjectName());
					this.enemy.move(-x, -y);
				}
			}

		}

	}
}