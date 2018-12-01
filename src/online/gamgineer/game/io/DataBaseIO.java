package online.gamgineer.game.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataBaseIO {
	public static Object LoadData() {
		try {
			return DataBaseSave.Input("savedata");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void SaveData() {
		
	}
	
	public static Object LoadServerData() {
		return -1;
	}
	
	public static void SaveServerData() {
		
	}
}
