package com.oxistudios.charlie.level_engine;

import com.oxistudios.charlie.level_engine.types.Puzzle;
import com.oxistudios.charlie.level_engine.types.Runner;
import com.oxistudios.charlie.level_engine.types.SideScroller;
import com.oxistudios.charlie.saving_engine.SavingController;

public class LevelController {

	private MasterLevel level;
	private SavingController saving_controller;
	private String master_level_locator = "Hard-coded path to dictionary file";
	private String level_location;

	public LevelController(int type, SavingController saving_controller,
			String level_selected) {
		
		this.saving_controller = saving_controller;
		level_location = getFileLocation(level_selected);
		switch (type) {
		case 1:
			level = new SideScroller(saving_controller, level_location);
		case 2:
			level = new Puzzle(saving_controller, level_location);
		case 3:
			level = new Runner(saving_controller, level_location);
		}
	}

	public MasterLevel getLevel() {
		return level;
	}

	public void setLevel(MasterLevel level) {
		this.level = level;
	}

	private String getFileLocation(String level) {
		int count = 0;
		String[] file_data = saving_controller.read(master_level_locator);
		for (String level_local : file_data) {
			++count;
			
			if(level == level_local) {
				++count;
				return file_data[count];
			}
		}
		
		return null;
	}

}
