package com.oxistudios.charlie.character_engine.enemies;

import com.badlogic.gdx.math.Vector2;
import com.oxistudios.charlie.character_engine.EnemySpawn;
import com.oxistudios.charlie.character_engine.MovableCharacters;
import com.oxistudios.charlie.physics_engine.PhysicsController;

public class EnemyCharacter extends MovableCharacters{

	EnemySpawn parent_spawn;
	
	public EnemyCharacter(PhysicsController physics_controller, EnemySpawn parent_spawn, Vector2 position, int width, int height) {
		super(physics_controller, position, width, height);
		// TODO Auto-generated constructor stub
		this.parent_spawn = parent_spawn;
	}

	public EnemyCharacter createNew(Vector2 position, int width, int height) {
		// TODO Auto-generated method stub
		EnemyCharacter c = new EnemyCharacter(physics_controller, parent_spawn, position, width, height);
		c.createPhysicsObject(physics_controller.getMovable_bodies(), physics_controller.get_world());
		return c;
	}

	public Object get_parent_spawn() {
		// TODO Auto-generated method stub
		return parent_spawn;
	}
	
	

}
