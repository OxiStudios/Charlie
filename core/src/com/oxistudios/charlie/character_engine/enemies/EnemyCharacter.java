package com.oxistudios.charlie.character_engine.enemies;

import com.badlogic.gdx.math.Vector2;
import com.oxistudios.charlie.character_engine.EnemySpawn;
import com.oxistudios.charlie.character_engine.MovableCharacters;

public class EnemyCharacter extends MovableCharacters{

	EnemySpawn parent_spawn;
	
	public EnemyCharacter(EnemySpawn parent_spawn, Vector2 position, int width, int height) {
		super(position, width, height);
		// TODO Auto-generated constructor stub
		this.parent_spawn = parent_spawn;
	}

	public EnemyCharacter createNew(Vector2 position, int width, int height) {
		// TODO Auto-generated method stub
		return new EnemyCharacter(parent_spawn, position, width, height);
	}

	public Object get_parent_spawn() {
		// TODO Auto-generated method stub
		return parent_spawn;
	}
	
	

}
