package online.gamgineer.game.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH_SIZE = 100;
	private static final int FRAME_HEIGHT_SIZE = 100;

	public StartFrame() {
		// 기본 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫을 때 행동 설정
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HEIGHT_SIZE); // 기본 크기 설정
		// this.setUndecorated(true); // 타이틀 바가 사라진다.
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);

		JButton startGameButton = new JButton("START NGD!"); // 버튼 생성
		startGameButton.addActionListener(new ActionListener() { // 버튼을 누르면 발생하는 이벤트
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangeFrame();
			}
		});
		this.add(startGameButton, BorderLayout.SOUTH); // 버튼 추가

		// // 애니메이션이 있는 gif 삽입
		// URL url = null;
		// try {
		// url = new
		// URL("http://smashinghub.com/wp-content/uploads/2014/08/cool-loading-animated-gif-1.gif");
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }
		// Icon icon = new ImageIcon(url);
		// JLabel label = new JLabel(icon);
		// this.getContentPane().add(label);

		this.pack();
	}

	protected void ChangeFrame() {
		new GameFrame();
		this.dispose();
	}

}
