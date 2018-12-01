package online.gamgineer.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;

import online.gamgineer.game.object.Enemy;
import online.gamgineer.game.object.GameObject;
import online.gamgineer.game.object.Map;
import online.gamgineer.game.object.Player;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_FRAME_WIDTH_SIZE = 600;
	private static final int DEFAULT_FRAME_HEIGHT_SIZE = 600;
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	private HashMap<String, Map> map;
	private String currentMap;
	private int frameWidthSize;
	private int frameHeightSize;

	public GamePanel() {
		this.frameWidthSize = GamePanel.DEFAULT_FRAME_WIDTH_SIZE;
		this.frameHeightSize = GamePanel.DEFAULT_FRAME_HEIGHT_SIZE;
		this.setPreferredSize(new Dimension(GamePanel.DEFAULT_FRAME_WIDTH_SIZE, GamePanel.DEFAULT_FRAME_HEIGHT_SIZE));
		this.setBackground(DEFAULT_BACKGROUND_COLOR);
		initial();
	}

	public GamePanel(int frameWidthSize, int frameHeightSize) {
		this.frameWidthSize = frameWidthSize;
		this.frameHeightSize = frameHeightSize;
		this.setPreferredSize(new Dimension(frameWidthSize, frameHeightSize));
		this.setBackground(DEFAULT_BACKGROUND_COLOR);
		initial();
	}

	public GamePanel(int frameWidthSize, int frameHeightSize, Color backGroundColor) {
		this.frameWidthSize = frameWidthSize;
		this.frameHeightSize = frameHeightSize;
		this.setPreferredSize(new Dimension(frameWidthSize, frameHeightSize));
		this.setBackground(backGroundColor);
		initial();
	}

	private void initial() {
//		this.gameObject = new HashMap<String, GameObject>();
//		this.gameObject.put("player", new Player(frameWidthSize / 2, frameHeightSize / 2));
//		this.gameObject.put("enemy", new Enemy());
//		this.gameObject.put("enemy", new Enemy());
//		Enemy enemy = (Enemy) this.gameObject.get("enemy");
//		enemy.setAlgorithm(new EnemyAlgorithm(this));
//		Thread t = new Thread(enemy.getAlgorithm());
//		t.start();

		// this.gameObject 대신 Map객체의 gameObject를 써야함
		map = new HashMap<>();
		
		
		HashMap<String, GameObject> tempHashMap = new HashMap<>();
		String defaultMapName = "map0";
		Map tempMap = new Map(defaultMapName);
		this.currentMap = defaultMapName;
		tempHashMap = tempMap.getGameObject();
		tempHashMap.put("enemy", new Enemy());
		tempHashMap.put("player", new Player(150, 150));
		tempMap.setGameObject(tempHashMap);
		map.put(tempMap.getMapName(), tempMap);
		
		tempHashMap = new HashMap<>();
		defaultMapName = "map1";
		tempMap = new Map(defaultMapName);
		tempHashMap = tempMap.getGameObject();
		tempHashMap.put("enemy", new Enemy());
		tempHashMap.put("player", new Player(300, 300));
		tempMap.setGameObject(tempHashMap);
		map.put(tempMap.getMapName(), tempMap);
		
//		new EnemyAlgorithm(this).start();
	}


	public int getFrameWidthSize() {
		return this.frameWidthSize;
	}

	public int getFrameHeightSize() {
		return this.frameHeightSize;
	}

	public Map getCurrentMap() {
		return this.getMap().get(this.currentMap);
	}

	public HashMap<String, Map> getMap() {
		return this.map;
	}
	
	public void setCurrentMap(String newMapName) {
		this.currentMap = newMapName;
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObject(g);
	}

	private void drawObject(Graphics g) {
		Iterator<String> it = this.getCurrentMap().getGameObject().keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			this.getCurrentMap().getGameObject().get(key).draw(g);
		}
	}
	
	public void changeMap() {
		//String currentMapName = this.getCurrentMap().getMapName();
		String newMapName = "map1";
		this.setCurrentMap(newMapName);
		this.repaint();
	}
		

}