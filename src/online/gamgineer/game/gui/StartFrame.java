package online.gamgineer.game.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 100; // (상수) GameFrame 가로 길이
	private static final int FRAME_HEIGHT_SIZE = 100; // (상수) GameFrame 세로 길이

	public StartFrame() {
		this.setTitle("StartFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
		this.setUndecorated(true);// 타이틀 바가 사라진다.

		JButton startGameButton = new JButton("START NGD!"); // 버튼 생성
		startGameButton.addActionListener(new ActionListener() { // 버튼을 누르면 발생하는 이벤트

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeFrame();
			}
		});

		this.add(startGameButton, BorderLayout.SOUTH);
		this.pack();
		
		//애니메이션이 있는 gif 삽입
		URL url = null;
		try {
			url = new URL("http://smashinghub.com/wp-content/uploads/2014/08/cool-loading-animated-gif-1.gif");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Icon icon = new ImageIcon(url);
		JLabel label = new JLabel(icon);
		
		this.getContentPane().add(label);
		this.setVisible(true);
		this.pack();

		System.out.println("!!\tDEBUGGING...");

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void ChangeFrame() {
		new GameFrame();
		this.dispose();
	}

}
