package online.gamgineer.game.server;

import java.io.Serializable;

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
	String str;
	
	public Data(String str) {
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
}
