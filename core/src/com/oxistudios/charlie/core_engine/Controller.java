package com.oxistudios.charlie.core_engine;

import com.oxistudios.charlie.MainGame;
import com.oxistudios.charlie.ability_engine.AbilityController;
import com.oxistudios.charlie.character_engine.CharlieCharacter.CharlieCharacter;
import com.oxistudios.charlie.level_engine.LevelController;
import com.oxistudios.charlie.navigation_engine.MasterScreenClass;
import com.oxistudios.charlie.saving_engine.SavingController;

public class Controller {
	
	//numbers
	private float current;
	
	//Game
	private MainGame game;
	private MasterScreenClass screen;
	
	//Charlie aspects
	private CharlieCharacter charlie_character;
	private AbilityController ability_controller;
	
	//Saving Controller
	private SavingController saving_controller;
	
	//Level Controller
	private LevelController level_controller;
	private String level_selected;
	
	public Controller(MainGame game, MasterScreenClass screen, AbilityController ability_controller, SavingController saving_controller, String level_selected) {
		this.game               = game;
		this.screen             = screen;
		this.ability_controller = ability_controller;
		this.saving_controller  = saving_controller;
		this.level_selected     = level_selected;
		init();
	}
	
	public void init() {
		charlie_character = new CharlieCharacter();
		level_controller = new LevelController(1, saving_controller, level_selected);
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