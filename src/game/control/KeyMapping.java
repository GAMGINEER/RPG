package game.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;

import game.graphics.GameFrame;
import game.graphics.GamePanel;
import game.object.Enemy;
import game.object.Item;
import game.object.Player;

public class KeyMapping extends KeyAdapter {

	public static final int SPEED = 10;

	private GameFrame gameFrame;
	private Player player;

	public KeyMapping(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.player = this.gameFrame.getGamePanel().getSave().getPlayer();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.player.resetPlayer();
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (this.isBlocked(0, -(SPEED)) == false) {
				this.player.move(0, -(SPEED));
				this.gameFrame.getGamePanel().repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (this.isBlocked(0, SPEED) == false) {
				this.player.move(0, SPEED);
				this.gameFrame.getGamePanel().repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (this.isBlocked(-(SPEED), 0) == false) {
				this.player.move(-(SPEED), 0);
				this.gameFrame.getGamePanel().repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (this.isBlocked(SPEED, 0) == false) {
				this.player.move(SPEED, 0);
				this.gameFrame.getGamePanel().repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_F4) {
			this.gameFrame.getGamePanel().getSave().save();
		}
		if (e.getKeyCode() == KeyEvent.VK_F5) {
			String currentMapName = this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMapName();
			if (currentMapName.equals("defaultField")) {
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().stopEnemy();
				this.gameFrame.getGamePanel().getSave().getMapSet().changeCurrentMap("enemyField");
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap()
						.setEnemyAlgorithm(this.gameFrame.getGamePanel());
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().startEnemy();
				this.gameFrame.getGamePanel().repaint();
			} else if (currentMapName.equals("enemyField")) {
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().stopEnemy();
				this.gameFrame.getGamePanel().getSave().getMapSet().changeCurrentMap("someWhere");
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap()
						.setEnemyAlgorithm(this.gameFrame.getGamePanel());
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().startEnemy();
				this.gameFrame.getGamePanel().repaint();
			} else if (currentMapName.equals("someWhere")) {
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().stopEnemy();
				this.gameFrame.getGamePanel().getSave().getMapSet().changeCurrentMap("defaultField");
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap()
						.setEnemyAlgorithm(this.gameFrame.getGamePanel());
				this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().startEnemy();
				this.gameFrame.getGamePanel().repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.printf("SYSTEM >> TRYING ATTACK\n");
			HashMap<String, Enemy> enemySet = this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap()
					.getEnemySet();
			Iterator<String> iterator = enemySet.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				Enemy enemy = enemySet.get(key);
				int dx = enemy.getPosX() - this.player.getPosX();
				int dy = enemy.getPosY() - this.player.getPosY();
				if (Math.abs(dx) <= 30 && Math.abs(dy) <= 30) {
					enemy.setHealthPoint(enemy.getHealthPoint() - 34);
					System.out.printf("SYSTEM >> ATTACKED PLAYER\n");
					this.player.attackedPlayer();
					this.gameFrame.getGamePanel().repaint();
					enemySet.get(key).getEnemyAlgorithm().knockBack(enemySet.get(key), dx, dy);
					System.out.printf("SYSTEM >> Enemy \"%s\"'s HP : %d\n", enemy.getObjectName(),
							enemy.getHealthPoint());
				}
			}
			System.out.printf("SYSTEM >> ATTACK FINISHED\n");
			System.out.printf("SYSTEM >> RESET PLAYER\n");
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			if (!this.player.getItemSet().isEmpty()) {
				Iterator<Item> iterator = this.player.getItemSet().values().iterator();
				iterator.next();
				int healthPoint = this.player.getHealthPoint();
				if (healthPoint <= 90) {
					this.player.setHealthPoint(healthPoint + 10);
					iterator.remove();
					System.out.printf("SYSTEM >> HEAL\n");
				} else if (healthPoint < 100) {
					this.player.setHealthPoint(100);
					iterator.remove();
					System.out.printf("SYSTEM >> HEAL\n");
				} else {
					System.out.printf("SYSTEM >> HP MAXIMUM\n");
				}
				this.gameFrame.getGamePanel().repaint();
			} else {
				System.out.printf("SYSTEM >> NO ITEM\n");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			System.out.printf("SYSTEM >> TRYING PICK UP ITEM\n");
			Iterator<Item> iterator = this.gameFrame.getGamePanel().getSave().getMapSet().getCurrentMap().getItemSet()
					.values().iterator();
			while (iterator.hasNext()) {
				Item value = (Item) iterator.next();
				int dx = value.getPosX() - player.getPosX();
				int dy = value.getPosY() - player.getPosY();
				if (Math.abs(dx) <= 20 && Math.abs(dy) <= 20) {
					this.player.addItem(value);
					iterator.remove();
					System.out.printf("SYSTEM >> ITEM GET\n");
					this.gameFrame.getGamePanel().repaint();
				}
			}
			System.out.printf("SYSTEM >> PICK UP FINISHED\n");
			this.gameFrame.getGamePanel().repaint();
		}
	}

	private boolean isBlocked(int dx, int dy) {
		boolean dxBlocked = this.player.getPosX() + this.player.getWidth() + dx <= GamePanel.DEFAULT_FRAME_WIDTH_SIZE
				&& this.player.getPosX() + dx >= 0;
		boolean dyBlocked = this.player.getPosY() + this.player.getHeight() + dy <= GamePanel.DEFAULT_FRAME_HEIGHT_SIZE
				&& this.player.getPosY() + dy >= 0;
		if (dxBlocked && dyBlocked) {
			return false;
		} else {
			return true;
		}
	}

}
