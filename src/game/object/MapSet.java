package game.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class MapSet implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_MAP_NAME = "Whimsyshire";

	private int storyFlag;

	private HashMap<String, Map> mapSet;
	private String currentMapName;

	public MapSet() {
		this.mapSet = new HashMap<>();
		this.storyFlag = 0;
		this.createMap();
	}

	private void createMap() {
		Map tempMap = new Map(DEFAULT_MAP_NAME);
		this.mapSet.put(DEFAULT_MAP_NAME, tempMap);

		String mapName = "enemyField";
		tempMap = null;
		tempMap = new Map(mapName);
		tempMap.addEnemy(new Enemy(500, 500));
		this.mapSet.put(mapName, tempMap);

		mapName = "someWhere";
		tempMap = null;
		tempMap = new Map(mapName);
		tempMap.addEnemy(new Enemy(100, 100));
		tempMap.addEnemy(new Enemy(200, 200));
		tempMap.addEnemy(new Enemy(300, 100));
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

	public int getStoryFlag() {
		return this.storyFlag;
	}

	public void setStoryFlag(int flag) {
		this.storyFlag = flag;
	}

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

}
