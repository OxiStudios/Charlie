package com.oxistudios.charlie.ablities;

/*
 * This class will control what abilities are made and send data to the controller
 */

public class AbilityController {

	int ability_one_CD = 0;
	int ability_two_CD = 0;

	boolean ability_one_usuable = true;
	boolean ability_two_usuable = true;

	boolean selected_ability_one = false;
	boolean selected_ability_two = false;

	public AbilityController(int ability_one_id, int ability_two_id) {

	}

	public void render() {

	}

	public void update() {

	}

	// //////////////////////////////////////////////////////

	// gets called in the timer part of the render method in controller
	public void master_timer() {
		ability_cool_down();
	}

	// //////////////////////////////////////////////////////

	public void ability_cool_down() {
		if (ability_one_CD >= 0) {
			ability_one_CD -= 1;
		} else {
			set_ability_one_usable(true);
		}

		if (ability_two_CD >= 0) {
			ability_two_CD -= 1;
		} else {
			set_ability_two_usable(true);
		}
	}

	// //////////////////////////////////////////////////////

	public void set_ability_one_usable(boolean usable) {
		ability_one_usuable = usable;
	}

	public void set_ability_two_usable(boolean usable) {
		ability_two_usuable = usable;
	}

	public boolean get_ability_one_usable() {
		return ability_one_usuable;
	}

	public boolean get_ability_two_usable() {
		return ability_two_usuable;
	}

	// //////////////////////////////////////////////////////

	public void set_selected_ability(int which_one) {
		switch (which_one) {
		case 1:
			selected_ability_one = true;
			selected_ability_two = false;
		case 2:
			selected_ability_two = true;
			selected_ability_one = false;
		}
	}

	public boolean is_ability_one_selected() {
		return selected_ability_one;
	}

	public boolean is_ability_two_selected() {
		return selected_ability_two;
	}

	// //////////////////////////////////////////////////////

	public void set_timer(int which_one, double time) {
		switch (which_one) {
		case 1:
			ability_one_CD = (int) time;
			set_ability_one_usable(false);
		case 2:
			ability_two_CD = (int) time;
			set_ability_two_usable(false);
		}
	}

	public double get_time(int which_one) {
		double time = 5;
		// write some magic here
		return time;
	}

	// //////////////////////////////////////////////////////

	// ScreenHandler.java

	// //////////////////////////////////////////////////////

	public void onTouch() {
		if (!is_ability_one_selected() && !is_ability_two_selected()) {
			// fire normal AutoAttack
		} else if (is_ability_one_selected()) {
			if (get_ability_one_usable()) {
				// fire ability one
				set_timer(1, get_time(1));
			} else {
				// do nothing, still on cooldown
			}
		} else {
			if (get_ability_two_usable()) {
				// fire ability two
				set_timer(2, get_time(2));
			} else {
				// do nothing, still on cooldown
			}
		}
	}

	// //////////////////////////////////////////////////////
}
