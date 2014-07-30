package com.oxistudios.charlie.ability_engine.aoe_abilities;

import com.oxistudios.charlie.ability_engine.MasterAbility;

public class MasterAOEAbility extends MasterAbility{
	
	float radius;
	float damage;
	
	public MasterAOEAbility(String config_file_location) {
		super(config_file_location);
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
