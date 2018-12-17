package game.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MenuFrame() {
		this.setResizable(false);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setBackground(new Color(0, 0, 0));
		this.setVisible(true);
		this.setSize(300, 200);

		this.addLocalPanel(this);
		this.addCloudPanel(this);
	}

	private void addLocalPanel(MenuFrame menuFrame) {
		MenuPanel localPanel = new MenuPanel();
		JLabel saveLabel = new JLabel("Save Game");
		JButton saveButton = new JButton("Save");
		JLabel loadLabel = new JLabel("Load Save");
		JButton loadButton = new JButton("Load");

		localPanel.add(saveLabel);
		localPanel.add(saveButton);
		localPanel.add(loadLabel);
		localPanel.add(loadButton);
		menuFrame.add(localPanel);
	}

	private void addCloudPanel(MenuFrame menuFrame) {
		MenuPanel cloudPanel = new MenuPanel();
		JLabel cloudSaveLabel = new JLabel("Backup Save to Cloud");
		JButton cloudSaveButton = new JButton("Save (Cloud)");
		JLabel cloudLoadLabel = new JLabel("Load Save from Cloud");
		JButton cloudLoadButton = new JButton("Load (Cloud)");

		cloudPanel.add(cloudSaveLabel);
		cloudPanel.add(cloudSaveButton);
		cloudPanel.add(cloudLoadLabel);
		cloudPanel.add(cloudLoadButton);
		menuFrame.add(cloudPanel);
	}

	private class MenuPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public MenuPanel() {
			this.setPreferredSize(new Dimension(200, 200));
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
	}

	public static void main(String[] args) {
		new MenuFrame();
	}

}
