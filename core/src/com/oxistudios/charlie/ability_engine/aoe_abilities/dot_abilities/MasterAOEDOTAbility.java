package com.oxistudios.charlie.ability_engine.aoe_abilities.dot_abilities;

import com.oxistudios.charlie.ability_engine.aoe_abilities.MasterAOEAbility;
import com.oxistudios.charlie.saving_engine.SavingController;

public class MasterAOEDOTAbility extends MasterAOEAbility{
	
	public MasterAOEDOTAbility(String config_file_location, SavingController saving_controller) {
		super(config_file_location, saving_controller);
		
	}
	
	public void loadAbilityConfig() {
		this.ability_data = saving_controller.read(this.config_file_location);
	}

}
