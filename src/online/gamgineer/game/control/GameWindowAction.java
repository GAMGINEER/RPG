package online.gamgineer.game.control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import online.gamgineer.game.gui.GamePanel;

/**
 * 게임 창 닫을 때 이벤트 처리기
 */
public class GameWindowAction extends WindowAdapter {

	private GamePanel gp; // 해당하는 게임 창 (GamePanel) 변수

	// 생성자
	public GameWindowAction(GamePanel gp) {
		this.gp = gp;
	}

	// 창이 닫히고 나서 실행되는 메소드
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("!!\tCLOSED");
	}

	// 창이 닫힐 때 실행되는 메소드
	@Override
	public void windowClosing(WindowEvent we) {
//		Scanner inputStreamScanner = new Scanner(System.in);
//		System.out.println("!!\tCLOSING");
//
//		// 세이브 데이터 처리
//		try {
//			@SuppressWarnings("unused")
//			GamePanel saveData = (GamePanel) DataBaseSave.Input("SaveData");
//			System.out.println("!!\t세이브 데이터가 존재합니다.");
//			System.out.print("!!\t덮어쓰기 하시겠습니까?\n>> ");
//			if (inputStreamScanner.next().toLowerCase().equals("y")) {
//				DataBaseSave.Output(gp, "SaveData");
//			} else {
//				System.out.println("!!\t저장되지 않았습니다.");
//			}
//		} catch (FileNotFoundException e) {
//			System.out.print("!!\t저장하시겠습니까?\n>> ");
//			if (inputStreamScanner.next().toLowerCase().equals("y")) {
//				try {
//					DataBaseSave.Output((Object) gp, "SaveData");
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			} else {
//				System.out.println("!!\t저장되지 않았습니다.");
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		inputStreamScanner.close();
	}

}
