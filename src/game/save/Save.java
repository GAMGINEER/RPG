package game.save;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import game.object.Map;
import game.object.MapSet;
import game.object.Player;

public class Save implements Serializable {

	private static final long serialVersionUID = 1L;

	private MapSet mapSet;
	private Player player;

	public Save() {
		initializeData();
	}

	private void initializeData() {
		int FLAG = 0;
		System.out.printf("FLAG: %d\n", FLAG);
		do {
			try {
				FLAG = 1; // 맵 데이터 존재하지 않음을 나타내는 플래그
				this.mapSet = (MapSet) ObjectSave.Input("map");
				System.out.printf("FLAG: %d\n", FLAG);
				FLAG = 2; // 플레이어 데이터 존재하지 않음을 나타내는 플래그
				this.player = (Player) ObjectSave.Input("save");
				System.out.printf("FLAG: %d\n", FLAG);
				if (this.mapSet.equals(null)) {
					if (this.player.equals(null)) {
						FLAG = 3; // 맵 데이터 및 플레이어 데이터 null 값임을 나타내는 플래그
						System.out.printf("FLAG: %d\n", FLAG);
					} else {
						FLAG = 4; // 맵 데이터만 null 값임을 나타내는 플래그
						System.out.printf("FLAG: %d\n", FLAG);
					}
				} else {
					if (this.player.equals(null)) {
						FLAG = 5; // 플레이어 데이터만 null 값임을 나타내는 플래그
						System.out.printf("FLAG: %d\n", FLAG);
					} else {
						FLAG = 0; // 세이브 정상을 나타내는 플래그
						System.out.printf("FLAG: %d\n", FLAG);
					}
				}
			} catch (FileNotFoundException e) {
				System.out.printf("%s\n", e);
				try {
					switch (FLAG) {
					case 1:
						createMapData();
						ObjectSave.Output(this.mapSet, "map");
						break;
					case 2:
						createPlayerData();
						ObjectSave.Output(this.player, "save");
						break;
					}
				} catch (IOException e1) {
					System.out.printf("%s\n", e1);
				}
				System.out.printf("FLAG: %d\n", FLAG);
			} catch (ClassNotFoundException e) {
				System.out.printf("%s\n", e);
			} catch (NullPointerException e) {
				System.out.printf("%s\n", e);
			} catch (IOException e) {
				System.out.printf("%s\n", e);
			}
		} while (FLAG != 0);
	}

	public void outputData() {
		// this.flushData();
		try {
			ObjectSave.Output(this.mapSet, "map");
			ObjectSave.Output(this.player, "save");
			System.out.printf("Saved\n");
		} catch (IOException e) {
			System.out.printf("%s\n", e);
		}
	}

	// private void flushData() {
	// this.mapSet.flushData();
	// }

	private void createMapData() {
		this.mapSet = new MapSet();
	}

	private void createPlayerData() {
		this.player = new Player(0, 0);
	}

	public Player getPlayer() {
		return this.player;
	}

	public MapSet getMapSet() {
		return this.mapSet;
	}

	public Map getMap(String mapName) {
		return this.mapSet.getMap(mapName);
	}

	public Map getCurrentMap() {
		return this.mapSet.getCurrentMap();
	}

	public String getCurrentMapName() {
		return this.mapSet.getCurrentMapName();
	}

	public void setLocation() {
		System.out.printf("%d\n", this.player.getPosX());
		System.out.printf("%d\n", this.player.getPosY());
	}

}
