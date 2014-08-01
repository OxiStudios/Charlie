package com.oxistudios.charlie.character_engine;

import com.badlogic.gdx.math.Vector2;

public class Entity {
	
	protected int health;
	protected int shield;
	protected double health_regen;
	
	protected int WIDTH;
	protected int HEIGHT;
	
	protected Vector2 position;
	
	protected String ID;
	
	
	public Entity(Vector2 position, int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.position = position;
	}
	
	public void createPhysicsObject() {
		
	}
	
	 //////////////////////////////////////
	//////////////////////////////////////
	
	public void render(float timer) {
		
	}
	
	public String getID() {
		return ID;
	}

	public Vector2 getPosition() {
		return position;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
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
