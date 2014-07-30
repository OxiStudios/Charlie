package com.oxistudios.charlie.ability_engine;


import com.oxistudios.charlie.saving_engine.SavingController;

public class MasterAbility {
	
	private int length_time;
	protected String config_file_location;
	protected SavingController saving_controller;
	protected String[] ability_data;
	
	public MasterAbility(String config_file_location, SavingController saving_controller) {
		this.config_file_location = config_file_location;
		this.saving_controller    = saving_controller;
	}
	/**
	 * parse the config file in to the given values, will be different for the different types of abilities
	 */
	public void loadAbilityConfig() {
		
	}
	
	public void render() {
		
	}
	
	public void update() {
		
	}


	public int getLength_time() {
		return length_time;
	}


	public void setLength_time(int length_time) {
		this.length_time = length_time;
	}
	
}
