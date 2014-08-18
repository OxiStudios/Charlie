package com.oxistudios.charlie.level_engine;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.character_engine.EnemySpawn;
import com.oxistudios.charlie.character_engine.Entity;
import com.oxistudios.charlie.character_engine.static_map_locations.Safehouse;
import com.oxistudios.charlie.saving_engine.SavingController;

/**
 * 
 * @author butlernc Used as the Parent Level Has helper methods to load in a
 *         level data file and create the correct object the game will need to
 *         run the level correctly
 * 
 *         order of method calls readLevel() Array<String> getHeaderBlock()
 *         Array<String> getDataBlock() TextureAtlas getTextureAtlas()
 *         loadTextures(Array<String> header) HashMap<String, Integer>
 *         getEnemyData(Array<String> data) HashMap<String, Integer>
 *         getMapData(Array<String> data) HashMap<String, Integer>
 *         getStaticData(Array<String> data)
 *         
 *         
 *         this is all getting rewritten
 * 
 */

public class MasterLevel {

	private SavingController saving_controller;

	private String level_location;
	private String[] level_data;
	private int place;

	public MasterLevel(SavingController saving_controller, String level_location) {
		this.saving_controller = saving_controller;
		this.level_location = level_location;

		place = 0;
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
	 * used to get the texture atlas for the method loadSprites()
	 * 
	 * @return
	 */
	public TextureAtlas getTextureAtlas(Array<String> header) {
		for (int i = 0; i < header.size; i++) {
			if (header.get(i) == "TEXTURE_ATLAS_LOCATION") {
				return new TextureAtlas(Gdx.files.external(header.get(i + 1)));
			}
		}

		return null;

	}

	/**
	 * @param header
	 * @return sprites - the correct sprites and their id's needed for the base
	 *         of the map grabs correct textures from the texture atlas, used in
	 *         LevelController
	 */
	public HashMap<Integer, Texture> loadTextures(Array<String> header,
			TextureAtlas texture_atlas) {
		HashMap<Integer, Texture> textures = new HashMap<Integer, Texture>();
		int i;

		// get to the correct location of the file
		for (i = 0; i < header.size; i++) {
			if (header.get(i) == "TEXTURES") {
				break;
			}
		}

		// create the correct sprites for the given map
		for (int j = i; j < header.size - i; j++) {
			if (header.get(j) != "END_TEXTURES") {
				textures.put(Integer.parseInt(header.get(j)), new Sprite(
						texture_atlas.createSprite(header.get(j))).getTexture());
			}
		}

		return textures;
	}

	/**
	 * @param data
	 * @return enemy_data This method will get the enemy data need for the Enemy
	 *         Controller in the character_engine
	 * 
	 *         needs work
	 */

	public Array<EnemySpawn> getEnemyData(Array<String> data) {
		Array<EnemySpawn> enemy_spawns = new Array<EnemySpawn>();
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
				enemy_spawns.add((EnemySpawn) saving_controller.create_object_json(EnemySpawn.class, data.get(i)));
			}
		}

		return enemy_spawns;
	}

	/**
	 * @param data
	 *            - the raw map data
	 * @return map_data, <x, y coords, sprite id> This method will have all of
	 *         the map data in a hashmap. The key is a point object, the value
	 *         is the texture id, used in LevelController
	 */
	public HashMap<String, Integer> getMapData(Array<String> data) {
		int x = 0;
		int y = 0;
		int current_x = 0;
		int current_y = 0;
		int i;
		HashMap<String, Integer> map_data = new HashMap<String, Integer>();
		for (i = 0; i < data.size; ++i) {
			if (data.get(i) == "MAP_DATA") {
				// get how big the map is and put it as the first point in the
				// hashmap
				i++;
				x = Integer.parseInt(data.get(i));
				i++;
				y = Integer.parseInt(data.get(i));
				// add the x limit and y limit to the hashmap
				map_data.put("" + x + "," + y, 000);
				// get the the next for loop to loop over the actual map data
				break;
			}
		}

		// i think this starts off by one
		for (int j = i; j < data.size; ++j) {

			if (data.get(j) != "END_MAP") {

				map_data.put("" + current_x + "," + current_y,
						Integer.parseInt(data.get(j)));

				// check to see if we need to start next row
				if (current_x == x) {
					current_x = 0;
					++current_y;
				} else {// we dont need to start next row yet
					++current_x;
				}
			}
		}

		return map_data;
	}

	/**
	 * @param data
	 *            - the raw map data
	 * @return static_data, Array(Entity) (x, y coords, h, w dimensions, sprite
	 *         id) This method will have all of the map's static data in a the
	 *         objects.
	 */
	public Array<Entity> getStaticData(Array<String> data) {

		// HashMap<String, Integer> static_data = new HashMap<String,
		// Integer>();
		Array<Entity> static_entity = new Array<Entity>();
		int i;
		for (i = 0; i < data.size; ++i) {
			if (data.get(i) == "STATIC_DATA") {
				break;
			}
		}

		// i think this starts off by one
		for (int j = i; j < data.size; j += 5) {

			// if (data.get(j) != "END_STATIC") {
			// static_data.put(data.get(j + 1) + "," + data.get(j + 2),
			// Integer.parseInt(data.get(j)));
			// }
		}

		return static_entity;
	}
}
