package com.oxistudios.charlie.characters;

public class Entity {
	
	private int health;
	private int shield;
	private double health_regen;
	
	public Entity() {
		
	}
	
	public void render(float timer) {
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public double getHealth_regen() {
		return health_regen;
	}

	public void setHealth_regen(double d) {
		this.health_regen = d;
	}
	

}
