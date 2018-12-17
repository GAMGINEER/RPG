package game.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ItemPanel extends JPanel {
	private JTextArea txtList;
	private JLabel label;
	
	private static final long serialVersionUID = 1L;

	public ItemPanel(GameFrame gf) {
		
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
		
		class ItemThread extends Thread {
			public void run() {
				Iterator<String> itemIterator = gf.getGamePanel().getSave().getMapSet().getCurrentMap().getItemSet().keySet().iterator();
				while (itemIterator.hasNext()) {
					String key = (String) itemIterator.next();
					String itemName = gf.getGamePanel().getSave().getMapSet().getCurrentMap().getItemSet().get(key).getObjectName();
					txtList.append(itemName+"/n");
					System.out.println(itemName+"/n");
				}
			}
		}
		
	}
	
}
