package com.oxistudios.charlie.level_engine;

import com.oxistudios.charlie.level_engine.types.Puzzle;
import com.oxistudios.charlie.level_engine.types.Runner;
import com.oxistudios.charlie.level_engine.types.SideScroller;


public class LevelController {

	private MasterLevel level;

	public LevelController(int type) {
		switch (type) {
		case 1:
			level = new SideScroller();
		case 2:
			level = new Puzzle();
		case 3:
			level = new Runner();
		}
	}

	public MasterLevel getLevel() {
		return level;
	}

	public void setLevel(MasterLevel level) {
		this.level = level;
	}

}
