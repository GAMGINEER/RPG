package online.gamgineer.game.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessagePanel extends JPanel {
	public MessagePanel() {
		this.setSize(450, 120);

		BoxLayout layout = new BoxLayout(this.getRootPane(), BoxLayout.Y_AXIS);
		this.setLayout(layout);

		JTextArea chatting = new JTextArea();
		chatting.setText("Hello World!");
		JScrollPane scrollPane = new JScrollPane(chatting, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(4, 4, 340, 330);
		this.add(scrollPane);

		this.setVisible(true); // 창이 보이게 한다
	}
}
