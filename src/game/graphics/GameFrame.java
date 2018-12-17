package game.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.control.KeyMapping;
import game.object.Enemy;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel gamePanel;
	private MessagePanel messagePanel;
	private ItemPanel itemPanel;
	private KeyMapping keyMapping;

	public ItemPanel getItemPanel() {
		return this.itemPanel;
	}

	public GameFrame() {
		this.setTitle("Tutorial - 어느 알록달록한 산골");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

		JPanel tmpPanel = new JPanel();
		tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

		// 게임 패널 설정
		this.gamePanel = new GamePanel();
		tmpPanel.add(gamePanel);
		keyMapping = new KeyMapping(this);
		this.addKeyListener(keyMapping);

		// 메세지 패널 설정
		this.messagePanel = new MessagePanel();
		tmpPanel.add(messagePanel);
		messagePanel.addSystemMessage("어느 알록달록한 산골에 도착했습니다.");

		// 아이템 패널 설정
		this.itemPanel = new ItemPanel();

		this.add(tmpPanel);
		this.add(itemPanel);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.printf("DEBUG >> WINDOW CLOSED\n");
			}

			@Override
			public void windowClosing(WindowEvent e) {
				gamePanel.getSave().save();
			}
		});

		this.pack();

		this.setLocationRelativeTo(null); // 창 위치 초기화
		this.setVisible(true); // 창 보이기

		new ChatFrame(this); // 채팅창 열기

		class tutorialThread extends Thread {

			public void run() {
				this.sleep();
				messagePanel.addSystemMessage("[시끄러운 소리]");
				this.sleep();
				messagePanel.addNormalMessage("주민1", "적들이 쳐들어 온다!");
				this.sleep();
				messagePanel.addNormalMessage("주민2", "도망쳐!");
				this.sleep();
				messagePanel.addNormalMessage("주민3", "으아아아아아아악!!!");
				this.sleep();
				// 여기서 주민들이 잔뜩 지나가면 좋겠지만 아직 무리

				messagePanel.addSystemMessage("적들이 몰려옵니다.");
				this.sleep();
				gamePanel.killedEnemy = 0;
				for (int i = 0; i < 10; i++) {
					getGamePanel().getSave().getMapSet().getCurrentMap().stopEnemy();
					getGamePanel().getSave().getMapSet().getCurrentMap().addEnemy(new Enemy(i * 20, i * 20));
					getGamePanel().getSave().getMapSet().getCurrentMap().setEnemyAlgorithm(getGamePanel());
					getGamePanel().getSave().getMapSet().getCurrentMap().startEnemy();
				}

				while (true) {
					this.sleep();
					if (gamePanel.killedEnemy >= 10) {
						messagePanel.addSystemMessage("모든 적을 처치했습니다.");
						messagePanel.addSystemMessage("마을로 가는 포탈이 열립니다.");
						// 이제 포탈이 열린다.

						keyMapping.youCanGo = 1;
						System.out.println("youcango==1");
						// 이제 포탈이 열린다.
						break;
					}
				}
			}

			private void sleep() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		new tutorialThread().start();

	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

}
