package game.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.control.KeyMapping;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GamePanel gamePanel;
	private MessagePanel messagePanel;
	private ItemPanel itemPanel;
	private ChatFrame chatFrame;

	public GameFrame() {
		chatFrame = new ChatFrame(this); //채팅창 열기
		
		this.setTitle("RPG 게임");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel tmpPanel = new JPanel();
		tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));

		//게임 패널 설정
		this.gamePanel = new GamePanel();
		tmpPanel.add(gamePanel);
		this.addKeyListener(new KeyMapping(this));
		
		//메세지 패널 설정
		this.messagePanel = new MessagePanel();
		tmpPanel.add(messagePanel);
		
		//아이템 패널 설정
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

	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

}
