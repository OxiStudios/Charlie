package com.oxistudios.charlie.level_engine;

import java.awt.Point;
import java.util.HashMap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.level_engine.MasterLevel;
import com.oxistudios.charlie.level_engine.types.Puzzle;
import com.oxistudios.charlie.level_engine.types.Runner;
import com.oxistudios.charlie.level_engine.types.SideScroller;
import com.oxistudios.charlie.saving_engine.SavingController;

public class LevelController {

	private MasterLevel level;
	private SavingController saving_controller;
	private String master_level_locator = "Hard-coded path to dictionary file";
	private String level_location;
	
	private HashMap<Point, Integer> map_data;
	private HashMap<String, Integer> enemy_data;

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
	
	public void render(OrthographicCamera camera) {
		
	}
	
	public void update() {
		
	}
	
	/**
	 * called at loading screen, creates the level from data file
	 * probably could be optimized
	 */
	public void createLevel() {
		Array<String> header_block;
		Array<String> data_block;
		
		level.readLevel();
		
		header_block = level.getHeaderBlock();
		data_block   = level.getDataBlock();
		
		level.loadSprites(header_block);
		
		enemy_data = level.getEnemyData(data_block);
		map_data   = level.getMapData(data_block);
	}

	public HashMap<Point, Integer> getMap_data() {
		return map_data;
	}

	public HashMap<String, Integer> getEnemy_data() {
		return enemy_data;
	}

	public MasterLevel getLevel() {
		return level;
	}

	public void setLevel(MasterLevel level) {
		this.level = level;
	}
	
	/**
	 * Gets the file location of the level selected
	 * @param level
	 * @return location of level data file
	 */
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
