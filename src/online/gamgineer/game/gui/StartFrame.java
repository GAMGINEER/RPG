package online.gamgineer.game.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
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

		/*
		 * JButton startGameButton = new JButton("START GAME"); //버튼 생성
		 * startGameButton.addActionListener(new ActionListener() { //버튼을 누르면 발생하는 이벤트
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { ChangeFrame(); }
		 * });
		 * 
		 * this.add(startGameButton); this.pack();
		 */

		ImageIcon ic = new ImageIcon("res/overactionRabbit.png");
		System.out.println("토끼를 클릭하세요!");
		JLabel lbImage1 = new JLabel(ic);
		lbImage1.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				ChangeFrame();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			} // 이미지를 누르면 발생하는 이벤트
		});

		this.add(lbImage1);
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
