package com.oxistudios.charlie.controls;

import com.oxistudios.charlie.MainGame;
import com.oxistudios.charlie.ablities.AbilityController;
import com.oxistudios.charlie.characters.CharlieCharacter.CharlieCharacter;
import com.oxistudios.charlie.levels.LevelController;
import com.oxistudios.charlie.screens.MasterScreenClass;

public class Controller {
	
	//numbers
	float current;
	
	MainGame game;
	MasterScreenClass screen;
	
	//Charlie aspects
	CharlieCharacter charlie_character;
	AbilityController ability_controller;
	
	//level
	LevelController level_controller;
	public Controller(MainGame game, MasterScreenClass screen, AbilityController ability_controller) {
		this.game               = game;
		this.screen             = screen;
		this.ability_controller = ability_controller;
		init();
	}
	
	public void init() {
		charlie_character = new CharlieCharacter();
		level_controller = new LevelController(1);
		current = 0;
	}
	
	public void render(float delta) {
		
		//master one second timer
		if((delta - current) >= 1 ) {
			
			//reset timer
			current = delta;
			
			//run these methods
			ability_controller.master_timer();
			
		}else{
			
		}
		
		ability_controller.render();
	}
	
	public void update(float delta) {
		
	}
}