package game.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import game.control.KeyMapping;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel gamePanel;

	public GameFrame() {
		this.setTitle("RPG 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.gamePanel = new GamePanel();
		this.add(gamePanel);
		this.addKeyListener(new KeyMapping(this));

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
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

}
