package com.oxistudios.charlie.screens.maingamescreen;

import com.badlogic.gdx.InputProcessor;
import com.oxistudios.charlie.ablities.AbilityController;

public class MainGameScreenInput implements InputProcessor{
	
	AbilityController ab_con;
	
	public MainGameScreenInput(AbilityController ability_controller) {
		// TODO Auto-generated constructor stub
		this.ab_con = ability_controller;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (!ab_con.is_ability_one_selected() && !ab_con.is_ability_two_selected()) {
			// fire normal AutoAttack
		} else if (ab_con.is_ability_one_selected()) {
			if (ab_con.get_ability_one_usable()) {
				// fire ability one
				ab_con.set_timer(1, ab_con.get_time(1));
			} else {
				// do nothing, still on cooldown
			}
		} else {
			if (ab_con.get_ability_two_usable()) {
				// fire ability two
				ab_con.set_timer(2, ab_con.get_time(2));
			} else {
				// do nothing, still on cooldown
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
