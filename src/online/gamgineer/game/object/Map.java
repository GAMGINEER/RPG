package online.gamgineer.game.object;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mapName;

	private HashMap<String, GameObject> gameObject;

	public Map(String name) {
		mapName = name;
	}

	public void addObject(String category, GameObject obj) {
		Iterator<String> it = this.gameObject.keySet().iterator();
		int cnt = 0;

		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.contains(category)) {
				cnt++;
			}
		}
		category += cnt; //category의 이름 뒤에 cnt를 붙여준다.

		this.gameObject.put(category, obj);
	}

	public GameObject getGameObject(Object key) {
		return this.gameObject.get(key);
	}

	public void drawObject(Graphics g) {
		Iterator<String> it = this.gameObject.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			this.gameObject.get(key).draw(g);
		}
	}

	public String nameGetter() {
		return mapName;
	}

}
