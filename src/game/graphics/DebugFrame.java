package game.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import game.object.Enemy;

public class DebugFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_WEIGHT = 500;

	public DebugFrame(GameFrame gf) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(DEFAULT_HEIGHT, DEFAULT_WEIGHT);
		this.setVisible(true);

		JButton b1 = new JButton("The cake is lie (맵 변경)");

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gf.resetKeyListener();
				String currentMapName = gf.getPanel().getSave().getCurrentMapName();
				if (currentMapName.equals("defaultField")) {
					gf.getPanel().getSave().getCurrentMap().stopEnemy();
					gf.getPanel().getSave().getMapSet().changeCurrentMap("enemyField");
					gf.getPanel().getSave().getCurrentMap().setEnemyAlgorithm(gf.getPanel());
					gf.getPanel().getSave().getCurrentMap().startEnemy();
					gf.getPanel().repaint();
				} else if (currentMapName.equals("enemyField")) {
					gf.getPanel().getSave().getCurrentMap().stopEnemy();
					gf.getPanel().getSave().getMapSet().changeCurrentMap("defaultField");
					gf.getPanel().getSave().getCurrentMap().setEnemyAlgorithm(gf.getPanel());
					gf.getPanel().getSave().getCurrentMap().startEnemy();
					gf.getPanel().repaint();
				}
				gf.setKeyListener();
			}
		});

		JButton b2 = new JButton("It's Dangerous to Go Alone! Take This (적 생성)");

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < 10; i++) {
						gf.getPanel().getSave().getCurrentMap().stopEnemy();
						gf.getPanel().getSave().getCurrentMap().addEnemy("enemy", new Enemy(i * 10, i * 10));
						gf.getPanel().getSave().getCurrentMap().setEnemyAlgorithm(gf.getPanel());
						gf.getPanel().getSave().getCurrentMap().startEnemy();
					}
			}
		});
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		add(b1);
		add(b2);
		pack();
	}

}
