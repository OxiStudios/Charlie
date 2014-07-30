package com.oxistudios.charlie.level_engine;

import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.saving_engine.SavingController;

public class MasterLevel {

	SavingController saving_controller;

	String level_location;
	String[] level_data;
	int place;

	public MasterLevel(SavingController saving_controller, String level_location) {
		this.saving_controller = saving_controller;
		this.level_location = level_location;
		place = 0;
	}

	public void readLevel() {
		level_data = saving_controller.read(level_location);
	}

	public Array<String> getHeaderBlock() {
		Array<String> header = new Array<String>();

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

	public Array<String> getDataBlock() {
		Array<String> data = new Array<String>();

		for (String single_data : level_data) {

			--place;

			if (place == 0 && single_data != "END_DATA") {
				data.add(single_data);
			} else {
				break;
			}

		}

		return data;
	}
	
	public HashMap<String, Integer> getEnemyData(Array<String> data) {
		boolean read = false;
		HashMap<String, Integer> enemy_data = new HashMap<String, Integer>();
		int i;
		for(i = 0; i < data.size; ++i) {
			if(data.get(i) == "ENEMY_DATA"){
				read = true;
			}
		}
		
		for(int j = i; j < data.size - i; j += 2) {

			if(read) {
				enemy_data.put(data.get(j), Integer.parseInt(data.get(j += 1)));
			}
		}
		
		return enemy_data;
	}

}
