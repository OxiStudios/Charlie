package com.oxistudios.charlie.saving_engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class SavingController {
	
	public SavingController() {
		
	}
	
	public String[] read(String location){
		FileHandle file = Gdx.files.external(location);
		String full = file.readString();
		return full.split(",");
		
	}
	
	public void write(Array<String> dictionary, String location, boolean append) {
		FileHandle file = Gdx.files.local(location);
		for(String data: dictionary) {
			file.writeString(data, append);
		}
	}
	
	public void save() {
		
	}
	
}
