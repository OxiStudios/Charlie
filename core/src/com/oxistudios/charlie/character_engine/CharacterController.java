package com.oxistudios.charlie.character_engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.level_engine.LevelController;

public class CharacterController {
	
	LevelController level_controller;
	Array<Entity> enemies;
	
	public CharacterController(LevelController level_controller) {
		this.level_controller = level_controller;
		
		enemies = new Array<Entity>();
		//fix enemy data in level controller
		createEnemies(level_controller.getEnemy_data());
	}
	
	public void createEnemies(Array<String> all_enemy_data) {
		for(String Enemy_data : all_enemy_data) {
			switch(Enemy_data) {
				case "00-000":
					//enemies.add(new FirstEnemy());
					break;
				case "00-001":
					//enemies.add(new SecondEnemy());
					break;
			}
		}
	}
	
	//not checking if they are in view yet
	public void render(float delta, SpriteBatch b) {
		for(Entity enemy: enemies) {
			enemy.render(delta, b);
		}
	}
	
	public void update(float delta) {
		
	}

}
