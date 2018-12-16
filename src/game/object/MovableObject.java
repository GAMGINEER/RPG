package game.object;

import java.awt.Color;

public abstract class MovableObject extends GameObject {

	private static final long serialVersionUID = 1L;

	// 체력 (HP)
	private int healthPoint;
	/*
	 * 힘 (ATK)
	 * 
	 * 공격 시 일정 비율로 줄어든다. 5초간 정지함으로써 회복을 시작할 수 있으며, 아이템을 통해 회복할 수 있다.
	 */
	private int attackPoint;

	public MovableObject(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height, color);
		this.healthPoint = 100;
		this.attackPoint = 100;
	}

	// 체력 값 가져오기
	public int getHealthPoint() {
		return this.healthPoint;
	}

	// 힘 값 가져오기
	public int getAttackPoint() {
		return this.attackPoint;
	}

	// 체력 값 설정
	public void setHealthPoint(int health) {
		this.healthPoint = health;
	}

	// 힘 값 설정
	public void setAttackPoint(int attack) {
		this.attackPoint = attack;
	}

	public String toString() {
		return String.format("%s\nHP: %d, ATK: %d", super.toString(), this.healthPoint, this.attackPoint);
	}

}
