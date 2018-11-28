package online.gamgineer.game.creator;

import java.awt.Color;
import java.util.Scanner;

import online.gamgineer.game.object.GameObject;
import online.gamgineer.game.object.Map;

public class MapCreator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("만들 맵 이름을 입력하시오 : ");
		String name = sc.next();
		Map map = new Map(name);
		while (true) {
			System.out.print("맵에 새로운 오브젝트를 추가하시겠습니까?(yes : 0, no : 다른숫자) : ");
			if (sc.nextInt() != 0) {
				System.out.println("맵 생성 완료. 종료합니다");
				return;
			}
			System.out.print("카테고리 이름을 입력하세요 : ");
			String category = sc.next();
			System.out.print("기본으로 만드시겠습니까?(yes : 0, no : 다른숫자) : ");
			if (sc.nextInt() == 0) {
				map.addObject(category, new GameObject());
				System.out.println("map " + name + "에 " + category + "를 그냥 추가했습니다.");
			} else {
				int posX, posY, width, height;
				Color color;
				System.out.println("x좌표 입력 : ");
				posX = sc.nextInt();
				System.out.println("y좌표 입력 : ");
				posY = sc.nextInt();
				System.out.println("넓이 : ");
				width = sc.nextInt();
				System.out.println("높이 : ");
				height = sc.nextInt();
				System.out.println("색깔 : ");
				color = Color.decode(sc.next());
				map.addObject(category, new GameObject(posX, posY, width, height, color));
				System.out.println("map "+name+"에 "+category+"를 뭔가 설정해서 추가했습니다.");
			}
		}
	}
}
