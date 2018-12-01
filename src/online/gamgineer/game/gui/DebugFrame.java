package online.gamgineer.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DebugFrame extends JFrame {
	public DebugFrame(GameFrame gf) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);

		JButton b1 = new JButton("The cake is lie");

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gf.getpanel().changeMap();
			}
		});
		
		add(b1);
		pack();
	}
}
