package game.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessagePanel extends JPanel {
	private JTextArea txtList;
	private JLabel label;
	private static int line = 0;

	public MessagePanel() {
		this.setBackground(new Color(188, 143, 143));
		this.setPreferredSize(new Dimension(600, 200));

		this.setLayout(new BorderLayout());
		label = new JLabel("System Message", JLabel.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 14));
		this.add("North", label);
		txtList = new JTextArea(10, 35);
		this.add("Center", txtList);
		txtList.setFont(new Font("굴림", Font.PLAIN, 14));
		txtList.setEnabled(false);
		txtList.setDisabledTextColor(Color.BLACK);
		this.addSystemMessage("어떤 세계에 들어왔습니다.");
	}

	public void addSystemMessage(String str) {
		txtList.append("<System> " + str + "\n");
		line++;
		if (line == 10) {
			line = 0;
			txtList.setText("");
		}
	}

	public void addNormalMessage(String who, String str) {
		txtList.append(who + " : " + str + "\n");
		line++;
		if (line == 10) {
			line = 0;
			txtList.setText("");
		}
	}
}
