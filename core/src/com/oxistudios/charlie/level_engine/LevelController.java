package com.oxistudios.charlie.level_engine;

import java.awt.Point;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.saving_engine.SavingController;

public class LevelController {

	private MasterLevel level;
	private SavingController saving_controller;
	private String master_level_locator = "Hard-coded path to dictionary file";
	private String level_location;
	private TextureAtlas texture_atlas;
	
	private HashMap<String, Integer> map_data;
	private HashMap<String, Integer> static_data;
	private HashMap<String, Integer> enemy_data;
	private HashMap<Integer, Texture> map_tiles;
	
	private int x;
	private int y;
	private int MAP_SIZE_X;
	private int MAP_SIZE_Y;
	private float SCREEN_X = Gdx.graphics.getWidth();
	private float SCREEN_Y = Gdx.graphics.getHeight();

	public LevelController(int type, SavingController saving_controller,
			String level_selected) {
		
		this.saving_controller = saving_controller;
		level_location = getFileLocation(level_selected);
		
		
		//switch (type) {
		//case 1:
		//	level = new SideScroller(saving_controller, level_location);
		//case 2:
		//	level = new Puzzle(saving_controller, level_location);
		//case 3:
		//	level = new Runner(saving_controller, level_location);
		//}
	}
	
	//might slow this down so it doesn't render 60 times a second.
	public void render(float delta, SpriteBatch b, OrthographicCamera camera) {
		x = 0;
		y = 0;
		//render the area of the map seen by the camera, right now this renders the whole map.
		for(int i = 0; i < MAP_SIZE_X; ++i) {
			for(int j = 0; j < MAP_SIZE_Y; ++j) {
				x = 64 * i;
				y = 64 * j;
				b.draw(map_tiles.get(map_data.get("" + i +"," + j)), x, y, 64, 64);
			}
		}
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
		texture_atlas = level.getTextureAtlas(header_block);
		map_tiles = level.loadTextures(header_block, texture_atlas);
		
		enemy_data  = level.getEnemyData(data_block);
		map_data    = level.getMapData(data_block);
		static_data = level.getStaticData(data_block);
	}

	public HashMap<String, Integer> getStatic_data() {
		return static_data;
	}

	public HashMap<String, Integer> getMap_data() {
		return map_data;
	}

	public HashMap<String, Integer> getEnemy_data() {
		return enemy_data;
	}

	public MasterLevel getLevel() {
		return level;
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
