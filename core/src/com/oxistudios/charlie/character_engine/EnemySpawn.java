package com.oxistudios.charlie.character_engine;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.character_engine.enemies.EnemyCharacter;

public class EnemySpawn {
	
	private Vector2 position;
	private Array<EnemyCharacter> enemy_types;
	private int amount;
	
	private boolean cleared;
	private boolean respawn;
	private Random random;
	private float respawn_time;
	private float current;
	private float start;
	
	int sub_amount_one;
	int sub_amount_two;
	
	private CharacterController character_controller;
	private Array<EnemyCharacter> enemies;
	
	public EnemySpawn(CharacterController character_controller, Vector2 position, Array<EnemyCharacter> enemy_types, int amount) {
		this.character_controller = character_controller;
		this.enemy_types          = enemy_types;
		
		enemies = new Array<EnemyCharacter>();
		random = new Random();
	}
	
	public void remove_enemy(EnemyCharacter enemy) {
		enemies.removeValue(enemy, false);
		if(enemies.size == 0) {
			cleared = true;
			respawn_time = random.nextInt(6) + 4;
			current = 0;
		}
	}
	
	//if all enemies from this spawn are dead, respawn enemies
	public void spawn() {
		if(respawn) {
			
			sub_amount_one = random.nextInt(amount);
			sub_amount_two = amount - sub_amount_one;
			
			for(int i = 0; i < sub_amount_one; i++) {
				enemies.add(enemy_types.get(0).createNew(position, enemy_types.get(0).getWIDTH(), enemy_types.get(0).getHEIGHT()));
			}
			
			for(int i = 0; i < sub_amount_two; i++) {
				enemies.add(enemy_types.get(1).createNew(position, enemy_types.get(1).getWIDTH(), enemy_types.get(1).getHEIGHT()));
			}
			
			respawn = false;
			cleared = false;
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
		
		//start the timer
		if(cleared) {
			start = delta;
		}
		
		//check if timer is done
		if(cleared && current >= respawn_time) {
			
			respawn = true;
			
		}else{
			current += delta - start;
		}
	}

}
	