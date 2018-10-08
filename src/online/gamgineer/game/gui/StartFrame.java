package online.gamgineer.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 100; // (상수) GameFrame 가로 길이
	private static final int FRAME_HEIGHT_SIZE = 100; // (상수) GameFrame 세로 길이

	public StartFrame() {
		this.setTitle("StartFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		JButton startGameButton = new JButton("START GAME");
		startGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeFrame();
			}
		});
		this.add(startGameButton);
		this.pack();
	}

	public void ChangeFrame() {
		new GameFrame();
		this.dispose();
	}

}
