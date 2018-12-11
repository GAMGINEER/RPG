package game.object;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import game.control.EnemyAlgorithm;
import game.graphics.GamePanel;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mapName;

	private HashMap<String, Enemy> enemySet;
	private HashMap<String, Item> itemSet;
	private HashMap<String, NPC> npcSet;

	public Map(String name) {
		mapName = name;
		this.enemySet = new HashMap<>();
		this.itemSet = new HashMap<>();
		this.npcSet = new HashMap<>();
	}

	public void addEnemy(String category, Enemy enemyObject) {
		Iterator<String> it = this.enemySet.keySet().iterator();
		int count = 0;

		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.contains(category)) {
				count++;
			}
		}
		category += count;

		this.enemySet.put(category, enemyObject);
	}

	public void addItem(String category, Item itemObject) {
		Iterator<String> it = this.itemSet.keySet().iterator();
		int count = 0;

		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.contains(category)) {
				count++;
			}
		}
		category += count;

		this.itemSet.put(category, itemObject);
	}

	public void addNPC(String category, NPC npcObject) {
		Iterator<String> it = this.npcSet.keySet().iterator();
		int count = 0;

		while (it.hasNext()) {
			String key = (String) it.next();
			if (key.contains(category)) {
				count++;
			}
		}
		category += count;

		this.npcSet.put(category, npcObject);
	}

	public void drawObject(Graphics g) {
		Iterator<String> enemyIterator = this.enemySet.keySet().iterator();
		while (enemyIterator.hasNext()) {
			String key = (String) enemyIterator.next();
			this.enemySet.get(key).draw(g);
		}
		Iterator<String> itemIterator = this.itemSet.keySet().iterator();
		while (itemIterator.hasNext()) {
			String key = (String) itemIterator.next();
			this.itemSet.get(key).draw(g);
		}
		Iterator<String> npcIterator = this.npcSet.keySet().iterator();
		while (npcIterator.hasNext()) {
			String key = (String) npcIterator.next();
			this.npcSet.get(key).draw(g);
		}
	}

	public String getMapName() {
		return this.mapName;
	}

	public HashMap<String, Enemy> getEnemySet() {
		return this.enemySet;
	}
	
	public HashMap<String, Item> getItemSet(){
		return this.itemSet;
	}
	
	public void setEnemyAlgorithm(GamePanel gamePanel) {
		Iterator<String> enemyIterator = this.enemySet.keySet().iterator();
		while (enemyIterator.hasNext()) {
			String key = (String) enemyIterator.next();
			this.enemySet.get(key).setAlgorithm(new EnemyAlgorithm(gamePanel));
		}
	}

	public void startEnemy() {
		Iterator<String> enemyIterator = this.enemySet.keySet().iterator();
		while (enemyIterator.hasNext()) {
			String key = (String) enemyIterator.next();
			this.enemySet.get(key).start();
		}
	}

	public void stopEnemy() {
		Iterator<String> enemyIterator = this.enemySet.keySet().iterator();
		while (enemyIterator.hasNext()) {
			String key = (String) enemyIterator.next();
			this.enemySet.get(key).stop();
		}
	}

	// public void flushData() {
	// Iterator<String> enemyIterator = this.enemySet.keySet().iterator();
	// while (enemyIterator.hasNext()) {
	// String key = (String) enemyIterator.next();
	// this.enemySet.get(key).setAlgorithm(null);
	// }
	// }

	@SuppressWarnings("unlikely-arg-type")
	public void removeEnemy(Enemy enemy) {
		this.addItem("item", new Item(enemy.getPosX(), enemy.getPosY()));
		this.enemySet.remove(enemy);
	}

	@Override
	public String toString() {
		return String.format("mapName: %s\n\nenemySet\n%s\n\nitemSet\n%s\n\nnpcSet\n%s\n", this.mapName, this.enemySet,
				this.itemSet, this.npcSet);
	}

}
