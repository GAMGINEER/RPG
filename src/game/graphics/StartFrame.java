package game.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 500;
	private static final int FRAME_HEIGHT_SIZE = 300;

	private JPanel imagePanel;
	private JPanel MessagePanel;
	private Image image;
	private JButton startGameButton;
	private JButton exitGameButton;

	public StartFrame() {
		// 기본 설정
		frameConfig();

		// 필드 정의
		initialize();

		// Frame을 반투명하게 만들어준다.
		this.setBackground(new Color(0, 0, 0, 30));
	}

	protected void frameConfig() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫을 때 행동 설정
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE); // 기본 크기 설정
		this.setUndecorated(true); // 타이틀 바가 사라진다.
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected void initialize() {
		startGameButton = new JButton("GAME START!"); // 시작 버튼 생성
		startGameButton.addActionListener(new ActionListener() { // 버튼을 누르면 발생하는 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeFrame();
			}
		});

		exitGameButton = new JButton("EXIT!"); // 종료 버튼 생성
		exitGameButton.addActionListener(new ActionListener() { // 버튼을 누르면 발생하는 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.image = Toolkit.getDefaultToolkit().createImage("res/img/newtitle.gif");
		imagePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null)
					g.drawImage(image, 0, 0, this);
			}
		};

		this.add(imagePanel);
		this.add(startGameButton);
		startGameButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(exitGameButton);
		exitGameButton.setAlignmentX(CENTER_ALIGNMENT);
	}

	protected void ChangeFrame() {
		GameFrame gameFrame = new GameFrame();
		new DebugFrame(gameFrame);
		this.dispose();
	}

}
