package com.oxistudios.charlie.character_engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.character_engine.enemies.EnemyCharacter;
import com.oxistudios.charlie.level_engine.LevelController;

public class CharacterController {

	private LevelController level_controller;
	private Array<EnemySpawn> enemy_spawns;

	public CharacterController(LevelController level_controller) {
		this.level_controller = level_controller;
		
		enemy_spawns = level_controller.getEnemy_spawns();

	}
	
	public void removeEnemy(EnemyCharacter enemy) {
		for(EnemySpawn spawn : enemy_spawns) {
			if(enemy.get_parent_spawn().equals(spawn)) {
				spawn.remove_enemy(enemy);
			}
			
		}
	}
	
	public void add_spawn(EnemySpawn spawn) {
		this.enemy_spawns.add(spawn);
	}
	
	public void remove_spawn(EnemySpawn spawn) {
		this.enemy_spawns.removeValue(spawn, false);
	}

	// not checking if they are in view yet
	public void render(float delta, SpriteBatch b) {
		for(EnemySpawn spawn : enemy_spawns) {
			spawn.render(delta, b);
		}
	}

	public void update(float delta) {
		for(EnemySpawn spawn : enemy_spawns) {
			spawn.spawn();
			spawn.update(delta);
		}
	}

}
