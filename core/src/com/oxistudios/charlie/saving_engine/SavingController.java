package com.oxistudios.charlie.saving_engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class SavingController {
	
	private Json json;
	private FileHandle file;
	
	public SavingController() {
		json = new Json();
	}
	
	public String[] read(String location){
		file = Gdx.files.external(location);
		String full = file.readString();
		return full.split(",");
		
	}
	
	public void write(Array<String> dictionary, String location, boolean append) {
		file = Gdx.files.local(location);
		for(String data: dictionary) {
			file.writeString(data, append);
		}
	}
	
	public void save() {
		
	}
	
	public <T> Object create_object_json(Class<T> obj, String location) {
		
		
		file = Gdx.files.external(location);
		String raw_data = file.readString();
		Object o = json.fromJson(obj, raw_data);
		
		return o;
	}
	
	public void save_object_json(Object obj, String location) {
		String raw_data = json.toJson(obj);
		file = Gdx.files.external(location);
		file.writeString(raw_data, false);
	}
	
}
