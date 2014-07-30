package com.oxistudios.charlie.level_engine;

import java.awt.Point;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.saving_engine.SavingController;

/**
 * 
 * @author butlernc Used as the Parent Level Has helper methods to load in a
 *         level data file and create the correct object the game will need to
 *         run the level correctly
 * 
 *         order of method calls readLevel() Array<String> getHeaderBlock()
 *         Array<String> getDataBlock() loadSprites(Array<String> header)
 *         HashMap<String, Integer> getEnemyData(Array<String> data)
 *         HashMap<Point, Integer> getMapData(Array<String> data)
 * 
 */

public class MasterLevel {

	private SavingController saving_controller;

	private String level_location;
	private String[] level_data;
	private int place;
	private Array<Sprite> sprites;
	private TextureAtlas texture_atlas;

	public MasterLevel(SavingController saving_controller, String level_location) {
		this.saving_controller = saving_controller;
		this.level_location = level_location;

		place = 0;
		texture_atlas = new TextureAtlas(
				Gdx.files.external("hard coded path here"));
		sprites = new Array<Sprite>();

	}

	/**
	 * This method initializes the file for the current level, populating the
	 * String[] level_data
	 */
	public void readLevel() {
		level_data = saving_controller.read(level_location);
	}

	/**
	 * @return header This method will grab all of the header data in the level
	 *         file, used in LevelController
	 */
	public Array<String> getHeaderBlock() {
		Array<String> header = new Array<String>();

		// get header data for the current level file
		for (String data : level_data) {
			if (data != "END_HEADER") {
				header.add(data);
			} else {
				break;
			}

			++place;
		}

		return header;
	}

	/**
	 * @return data This method will grab the rest of the level file, used in
	 *         LevelController
	 */
	public Array<String> getDataBlock() {
		Array<String> data = new Array<String>();

		// can be optimized
		for (String single_data : level_data) {

			--place;

			if (place != 0 && single_data != "END_DATA") {
				data.add(single_data);
			} else {
				break;
			}

		}

		return data;
	}

	/**
	 * @param header
	 *            grabs correct textures from the texture atlas, used in
	 *            LevelController
	 */
	public void loadSprites(Array<String> header) {
		int i;

		// get to the correct location of the file
		for (i = 0; i < header.size; i++) {
			if (header.get(i) == "TEXTURES") {
				break;
			}
		}

		// create the correct sprites for the given map
		for (int j = i; j < header.size - i; j += 2) {
			if (header.get(j) != "END_TEXTURES") {
				sprites.add(new Sprite(
						texture_atlas.createSprite(header.get(j))));
			}
		}
	}

	/**
	 * @param data
	 * @return enemy_data This method will get the enemy data need for the Enemy
	 *         Controller in the character_engine
	 */

	public HashMap<String, Integer> getEnemyData(Array<String> data) {
		HashMap<String, Integer> enemy_data = new HashMap<String, Integer>();
		int i;

		// get to the correct location of the file
		for (i = 0; i < data.size; ++i) {
			if (data.get(i) == "ENEMY_DATA") {
				break;
			}
		}

		// get data for enemies
		for (int j = i; j < data.size - i; j += 2) {

			// needs to be fixed, this could cause an off by 1 error
			if (data.get(j) != "END_ENEMY" && data.get(j += 1) != "END_ENEMY") {
				enemy_data.put(data.get(j), Integer.parseInt(data.get(j + 1)));
			}
		}

		return enemy_data;
	}

	/**
	 * @param data
	 * @return map_data This method will have all of the map data in a hashmap.
	 *         The key is a point object, the value is the texture id, used in
	 *         LevelController
	 */
	public HashMap<Point, Integer> getMapData(Array<String> data) {
		int i;
		HashMap<Point, Integer> map_data = new HashMap<Point, Integer>();
		for (i = 0; i < data.size; ++i) {
			if (data.get(i) == "MAP_DATA") {
				break;
			}
		}

		// i think this starts off by one
		for (int j = i; j < data.size; j += 3) {

			if (data.get(j) != "END_MAP") {

				Point p = new Point(Integer.parseInt(data.get(j)),
						Integer.parseInt(data.get(j + 1)));
				map_data.put(p, Integer.parseInt(data.get(j + 2)));
			}
		}

		return map_data;
	}
}
