package com.oxistudios.charlie.character_engine.static_map_locations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.character_engine.StaticCharacters;
import com.oxistudios.charlie.physics_engine.PhysicsController;

public class Safehouse extends StaticCharacters{
	
	public Safehouse(PhysicsController physics_controller, Vector2 position, int width, int height) {
		super(physics_controller, position, width, height);
		this.ID = "0001";
		this.p_height = .35f;
		this.p_width = .6f;
	}
	
	
	

}
