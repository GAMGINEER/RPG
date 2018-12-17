package game.save;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import game.io.ObjectSave;
import game.object.Player;

public class Payload implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userID;
	private Save save;
	private Player player;

	public Payload(String userID) {
		this.userID = userID;
		try {
			this.save = (Save) ObjectSave.Input("map");
			this.player = (Player) ObjectSave.Input("player");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserID() {
		return this.userID;
	}

	public Save getSave() {
		return this.save;
	}

	public Player getPlayer() {
		return this.player;
	}

}
