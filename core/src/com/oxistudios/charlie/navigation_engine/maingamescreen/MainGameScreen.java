package com.oxistudios.charlie.navigation_engine.maingamescreen;


import com.oxistudios.charlie.MainGame;
import com.oxistudios.charlie.ability_engine.AbilityController;
import com.oxistudios.charlie.core_engine.Controller;
import com.oxistudios.charlie.navigation_engine.MasterScreenClass;

public class MainGameScreen extends MasterScreenClass{
	
	MainGame game;
	Controller controller;
	AbilityController ability_controller;
	public MainGameScreen(MainGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		controller.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		if(ability_controller == null) {
			ability_controller = new AbilityController(0, 1);
		}
		if(controller == null) {
			controller = new Controller(game, this, ability_controller);
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		controller = null;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
