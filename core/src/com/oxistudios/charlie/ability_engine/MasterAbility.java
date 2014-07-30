package com.oxistudios.charlie.ability_engine;

public class MasterAbility {
	
	private int length_time;
	private String config_file_location;
	
	public MasterAbility(String config_file_location) {
		this.config_file_location = config_file_location;
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
