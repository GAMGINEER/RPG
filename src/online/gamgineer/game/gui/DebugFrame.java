package online.gamgineer.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DebugFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public DebugFrame(GameFrame gf) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);

		JButton b1 = new JButton("The cake is lie");

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gf.resetKeyListener();
				gf.getPanel().changeMap();
				gf.setKeyListener();
			}
		});

		add(b1);
		pack();
	}

}
