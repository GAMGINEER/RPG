package game.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class MapSet implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_MAP_NAME = "defaultField";

	private HashMap<String, Map> mapSet;
	private String currentMapName;

	public MapSet() {
		this.mapSet = new HashMap<>();
		initializeMap();
	}

	private void initializeMap() {
		Map tempMap = new Map(DEFAULT_MAP_NAME);
		tempMap.addItem("item", new Item());
		tempMap.addNPC("npc", new NPC());
		this.mapSet.put(DEFAULT_MAP_NAME, tempMap);

		String mapName = "enemyField";
		tempMap = null;
		tempMap = new Map(mapName);
		tempMap.addEnemy("enemy", new Enemy());
		this.mapSet.put(mapName, tempMap);

		mapName = "someWhere";
		tempMap = null;
		tempMap = new Map(mapName);
		tempMap.addEnemy("enemy", new Enemy());
		tempMap.addEnemy("enemy", new Enemy());
		tempMap.addEnemy("enemy", new Enemy());
		this.mapSet.put(mapName, tempMap);

		this.currentMapName = DEFAULT_MAP_NAME;
	}

	public String getCurrentMapName() {
		return this.currentMapName;
	}

	public Map getCurrentMap() {
		return this.getMap(this.currentMapName);
	}

	public void changeCurrentMap(String nextMap) {
		this.currentMapName = nextMap;
	}

	public Map getMap(String mapName) {
		return this.mapSet.get(mapName);
	}

	// public void flushData() {
	// Iterator<String> mapSetIterator = this.mapSet.keySet().iterator();
	// while (mapSetIterator.hasNext()) {
	// String mapSetKey = (String) mapSetIterator.next();
	// mapSet.get(mapSetKey).flushData();
	// }
	// }

	public String toString() {
		StringBuilder str = new StringBuilder();
		Iterator<String> mapIterator = this.mapSet.keySet().iterator();
		while (mapIterator.hasNext()) {
			String key = (String) mapIterator.next();
			str.append(this.mapSet.get(key));
			if (mapIterator.hasNext()) {
				str.append("\n\n");
			}
		}
		return str.toString();
	}

//
//	public void addMap(Map inputMap) {
//		this.mapSet.put(inputMap.getMapName(), inputMap);
//	}
//
//	public Map getMap(String mapName) {
//		return this.mapSet.get(mapName);
//	}
//
//	public Map getCurrentMap() {
//		return this.mapSet.get(currentMap);
//	}

}
