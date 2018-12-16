package game.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSave {

	private static String DefaultPath = "save/";
	private static FileInputStream fis = null;
	private static FileOutputStream fos = null;
	private static ObjectInputStream ois = null;
	private static ObjectOutputStream oos = null;

	public static Object Input(String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException {
		Object object_dump = null;
		fis = new FileInputStream(new File(String.format("%s.object", DefaultPath + fileName)));
		ois = new ObjectInputStream(fis);
		object_dump = (Object) ois.readObject();
		StreamReset();
		return object_dump;
	}

	public static void Output(Object outputObject, String fileName) throws IOException {
		try {
			fos = new FileOutputStream(String.format("%s.object", DefaultPath + fileName));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(outputObject);
		} catch (FileNotFoundException e) {
			new File(DefaultPath).mkdir();
			fos = new FileOutputStream(String.format("%s.object", DefaultPath + fileName));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(outputObject);
		}
		StreamReset();
	}

	public static void Delete(String fileName) {
		String fileLocation = String.format("%s.object", DefaultPath + fileName);
		File selectedFile = new File(fileLocation);
		if (selectedFile.delete()) {
			System.out.printf("Save Deleted\n");
		} else {
			System.out.printf("Save Not Deleted\n");
		}
	}

	private static void StreamReset() {
		fis = null;
		fos = null;
		ois = null;
		oos = null;
	}

}
