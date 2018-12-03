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
		while (true) {
			int dx = this.enemy.getPosX() - this.player.getPosX();
			int dy = this.enemy.getPosY() - this.player.getPosY();
//			System.out.println("적의 위치 : (" + this.enemy.getPosX() + ", " + this.enemy.getPosY() + ")");
			System.out.println("내 위치 : (" + this.player.getPosX() + ", " + this.player.getPosY() + ")");
//			System.out.println("dx : " + dx);
//			System.out.println("dy : " + dy);
			

			try {
				this.follow();
				gamePanel.repaint();
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			if (Math.abs(dx) < 15 && Math.abs(dy) < 15) {
				System.out.println("적과 부딪힙니다!");
				FLAG = 0;
			} else {
//				System.out.println("적과의 거리가 아직 있습니다!");
				FLAG = 1;
			}
			
			if (FLAG == 0) {//부딪힘
				
				if (this.gamePanel.getPlayerHP() != 10) {
					//아직 안죽음

					System.out.println("부딪혔다!");
					System.out.printf(this.gamePanel.getPlayerHP()+"-10 -> "+gamePanel.getPlayerHP()+"\n");
					this.gamePanel.setPlayerHP(this.gamePanel.getPlayerHP() - 10);
					
					// TODO KnockBack 구현
					knockBack();

				} else {
					//죽었다
					System.out.printf("부딪혔다!\nHP : 0!\n");
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

	// TODO 넉백수정
	private void knockBack() {
		
//		int dx = 0;
//		int dy = 0;
//		
//		if (this.player.getPosX() >= this.enemy.getPosX()) {
//			//player이 enemy보다 오른쪽에 있을 때
//			dx =+ 5;
//		} else {
//			//player이 enemy보다 왼쪽에 있을 때
//			dx =- 5;
//		}
//		
//		if (this.player.getPosY() >= this.enemy.getPosY()) {
//			//player이 enemy보다 아래에 있을 때
//			dy = -5;
//		} else {
//			//player이 enemy보다 위에 있을 때
//			dy = +5;
//		}
//		
//		this.player.move(player.getPosX()+dx,player.getPosY()+dy);
		
		this.player.move(player.getPosX()+1, player.getPosY()+1);
		System.out.println("getposx : "+player.getPosX());
		System.out.println("getposy : "+player.getPosY());
		
		System.out.println("넉백되었다.");
	}
}