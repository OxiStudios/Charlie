package com.oxistudios.charlie.character_engine;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.character_engine.enemies.EnemyCharacter;

public class EnemySpawn {
	
	private Vector2 position;
	private EnemyCharacter enemy;
	private int amount;
	private int timer;
	
	private boolean cleared;
	private boolean respawn;
	private Random random;
	private int respawn_time;
	
	private CharacterController character_controller;
	private Array<EnemyCharacter> enemies;
	
	public EnemySpawn(CharacterController character_controller, Vector2 position, EnemyCharacter enemy, int amount) {
		this.character_controller = character_controller;
		
		enemies = new Array<EnemyCharacter>();
	
		timer = 0;
		
		random = new Random();
	}
	
	public void remove_enemy(EnemyCharacter enemy) {
		enemies.removeValue(enemy, false);
		if(enemies.size == 0) {
			cleared = true;
			respawn_time = random.nextInt(6) + 4;
			timer = 0;
		}
	}
	
	//if all enemies from this spawn are dead, respawn enemies
	public void spawn() {
		if(respawn) {
			for(int i = 0; i < amount; i++) {
				enemies.add(enemy.createNew(position, enemy.getWIDTH(), enemy.getHEIGHT()));
			}
			
			respawn = false;
		}
	}
	
	public void render(float delta, SpriteBatch b) {
		for(EnemyCharacter enemy : enemies) {
			enemy.render(delta, b);
		}
	}
	
	public void update(float delta) {
		for(EnemyCharacter enemy : enemies) {
			enemy.update(delta);
		}
		
		if(cleared && timer >= respawn_time) {
			respawn = true;
		}
	}

}
	