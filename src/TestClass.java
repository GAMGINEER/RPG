import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import online.gamgineer.game.gui.GamePanel;
import online.gamgineer.game.io.DataBaseSave;

public class TestClass {

	public static void main(String[] args) {

		GamePanel gp = null;

		try {
			gp = (GamePanel) DataBaseSave.Input("TestSave");
		} catch (FileNotFoundException e) {
			gp = new GamePanel(600, 600);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JFrame jp = new JFrame();
		jp.setVisible(true);
		jp.add(gp);
		try {
			DataBaseSave.Output((Object) gp, "TestSave");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
