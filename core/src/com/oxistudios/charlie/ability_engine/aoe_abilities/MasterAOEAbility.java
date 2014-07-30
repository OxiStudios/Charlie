package com.oxistudios.charlie.ability_engine.aoe_abilities;

import com.oxistudios.charlie.ability_engine.MasterAbility;
import com.oxistudios.charlie.saving_engine.SavingController;

public class MasterAOEAbility extends MasterAbility{
	
	float radius;
	float damage;
	
	public MasterAOEAbility(String config_file_location, SavingController saving_controller) {
		super(config_file_location, saving_controller);
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
