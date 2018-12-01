package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 100;
	private static final int FRAME_HEIGHT_SIZE = 100;

	public StartFrame() {
		// 기본 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫을 때 행동 설정
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE); // 기본 크기 설정
		this.setUndecorated(true); // 타이틀 바가 사라진다.
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JButton startGameButton = new JButton("GAME START!"); // 시작 버튼 생성
		startGameButton.addActionListener(new ActionListener() { // 버튼을 누르면 발생하는 이벤트
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeFrame();
			}
		});

		JButton exitGameButton = new JButton("EXIT!"); // 종료 버튼 생성
		exitGameButton.addActionListener(new ActionListener() { // 버튼을 누르면 발생하는 이벤트
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		class StartPanel extends JPanel {
			private static final long serialVersionUID = 1L;
			private Image image;

			public StartPanel() throws MalformedURLException {
				image = Toolkit.getDefaultToolkit().createImage("res/loadingCat.gif");
			}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null)
					g.drawImage(image, 0, 0, this);
			}
		}

		//

		try {
			StartPanel p = new StartPanel();
			add(p);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		add(startGameButton); // 시작 버튼 추가
		startGameButton.setAlignmentX(CENTER_ALIGNMENT);
		add(exitGameButton); // 종료 버튼 추가
		exitGameButton.setAlignmentX(CENTER_ALIGNMENT);

		setBackground(new Color(0, 0, 0, 30));// Frame을 반투명하게 만들어준다.

		this.setSize(500, 300);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected void ChangeFrame() {
		GameFrame gf = new GameFrame();
		new DebugFrame(gf);
		this.dispose();
	}

}
