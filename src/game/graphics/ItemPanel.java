package game.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.object.Item;

public class ItemPanel extends JPanel {
	private JTextArea txtList;
	private JLabel label;
	private GameFrame gf;

	private static final long serialVersionUID = 1L;

	public ItemPanel(GameFrame gf) {
		this.gf = gf;

		this.setBackground(new Color(112, 128, 144));
		this.setPreferredSize(new Dimension(250, 600));

		this.setLayout(new BorderLayout());
		label = new JLabel("Item", JLabel.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 14));
		this.add("North", label);
		txtList = new JTextArea(10, 35);
		this.add("Center", txtList);
		txtList.setFont(new Font("굴림", Font.PLAIN, 14));
		txtList.setEnabled(false);
		txtList.setDisabledTextColor(Color.BLACK);

	}

	public void drawList() {
		this.txtList.setText("");
		Iterator<Item> iterator = this.gf.getGamePanel().getSave().getPlayer().getItemSet().values().iterator();
		while (iterator.hasNext()) {
			this.txtList.append(iterator.next().getObjectName() + "\n");
		}
	}

}
