package com.oxistudios.charlie.character_engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class StaticCharacters extends Entity{

	public StaticCharacters(Vector2 position, int width, int height) {
		super(position, width, height);
	}
	
	public void createPhysicsObject(Array<Body> movable_bodies, World world) {
		
	}

}
