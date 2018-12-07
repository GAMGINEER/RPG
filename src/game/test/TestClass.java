package game.test;

import game.save.Save;

public class TestClass {

	public static void main(String[] args) {
		Save save = new Save();
		System.out.printf("%s\n", save.getPlayer());
		System.out.printf("%s\n", save.getCurrentMap());
		save.outputData();
	}

}
