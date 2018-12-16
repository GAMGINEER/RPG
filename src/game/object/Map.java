package game.object;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import game.graphics.GamePanel;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	private String mapName;

	private HashMap<String, Enemy> enemySet;
	private HashMap<String, Item> itemSet;

	public Map(String name) {
		mapName = name;
		this.enemySet = new HashMap<>();
		this.itemSet = new HashMap<>();
	}

	public void addEnemy(Enemy enemyObject) {
		this.enemySet.put(enemyObject.getObjectName(), enemyObject);
	}

	public void addItem(Item itemObject) {
		this.itemSet.put(itemObject.getObjectName(), itemObject);
	}

	public void draw(Graphics g) {
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
	}

	public String getMapName() {
		return this.mapName;
	}

	public HashMap<String, Enemy> getEnemySet() {
		return this.enemySet;
	}

	public HashMap<String, Item> getItemSet() {
		return this.itemSet;
	}

	public void setEnemyAlgorithm(GamePanel gamePanel) {
		Iterator<String> enemyIterator = this.enemySet.keySet().iterator();
		while (enemyIterator.hasNext()) {
			String key = (String) enemyIterator.next();
			this.enemySet.get(key).setEnemyAlgorithm(gamePanel);
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

	public void removeEnemy(Enemy enemy) {
		this.addItem(new Item(enemy.getPosX(), enemy.getPosY()));
		this.enemySet.values().remove(enemy);
	}

	@Override
	public String toString() {
		return String.format("mapName: %s\n\nenemySet\n%s\n\nitemSet\n%s\n", this.mapName, this.enemySet, this.itemSet);
	}

}
