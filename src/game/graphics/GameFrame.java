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
	private ChatFrame chatFrame;

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
		this.addKeyListener(new KeyMapping(this));

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

		chatFrame = new ChatFrame(this); // 채팅창 열기

		class tutorialThread extends Thread {

			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				messagePanel.addSystemMessage("[시끄러운 소리]");
				messagePanel.addNormalMessage("주민1", "적들이 쳐들어 온다!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				messagePanel.addNormalMessage("주민2", "도망쳐!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				messagePanel.addNormalMessage("주민3", "으아아아아아아악!!!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				gamePanel.save.getMapSet().getCurrentMap().addEnemy(new Enemy(500, 500));
				gamePanel.save.getMapSet().getCurrentMap().addEnemy(new Enemy(500, 500));
				gamePanel.save.getMapSet().getCurrentMap().addEnemy(new Enemy(500, 500));
				gamePanel.save.getMapSet().getCurrentMap().setEnemyAlgorithm(gamePanel);
			}
		}

		new tutorialThread().start();

	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

}
