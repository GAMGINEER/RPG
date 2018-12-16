package game.save;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import game.io.ObjectSave;
import game.object.MapSet;
import game.object.Player;

public class Save implements Serializable {

	private static final long serialVersionUID = 1L;

	private MapSet mapSet;
	private Player player;

	public Save() {
		this.load();
	}

	private void load() {
		int FLAG = 0;
		do {
			try {
				FLAG = 1; // 맵 데이터 존재하지 않음을 나타내는 플래그
				this.mapSet = (MapSet) ObjectSave.Input("map");
				FLAG = 2; // 플레이어 데이터 존재하지 않음을 나타내는 플래그
				this.player = (Player) ObjectSave.Input("player");
				if (this.mapSet.equals(null)) {
					if (this.player.equals(null)) {
						FLAG = 3; // 맵 데이터 및 플레이어 데이터 null 값임을 나타내는 플래그
					} else {
						FLAG = 4; // 맵 데이터만 null 값임을 나타내는 플래그
					}
				} else {
					if (this.player.equals(null)) {
						FLAG = 5; // 플레이어 데이터만 null 값임을 나타내는 플래그
					} else {
						FLAG = 0; // 세이브 정상을 나타내는 플래그
					}
				}
			} catch (FileNotFoundException e) {
				System.out.printf("%s\n", e);
				try {
					switch (FLAG) {
					case 1:
						createMap();
						ObjectSave.Output(this.mapSet, "map");
						break;
					case 2:
						createPlayer();
						ObjectSave.Output(this.player, "player");
						break;
					}
				} catch (IOException e1) {
					System.out.printf("%s\n", e1);
				}
			} catch (ClassNotFoundException e) {
				System.out.printf("%s\n", e);
			} catch (NullPointerException e) {
				System.out.printf("%s\n", e);
			} catch (IOException e) {
				System.out.printf("%s\n", e);
			}
		} while (FLAG != 0);
	}

	public void save() {
		try {
			ObjectSave.Output(this.mapSet, "map");
			System.out.printf("SYSTEM >> MAP SAVED\n");
			ObjectSave.Output(this.player, "player");
			System.out.printf("SYSTEM >> PLAYER SAVED\n");
		} catch (IOException e) {
			System.out.printf("%s\n", e);
		}
	}

	private void createMap() {
		this.mapSet = new MapSet();
	}

	private void createPlayer() {
		this.player = new Player(300, 300);
	}

	public Player getPlayer() {
		return this.player;
	}

	public MapSet getMapSet() {
		return this.mapSet;
	}

}
