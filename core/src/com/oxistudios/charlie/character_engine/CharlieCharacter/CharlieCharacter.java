package com.oxistudios.charlie.character_engine.CharlieCharacter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.oxistudios.charlie.character_engine.MovableCharacters;
import com.oxistudios.charlie.physics_engine.PhysicsController;


public class CharlieCharacter extends MovableCharacters{
	
	public CharlieCharacter(PhysicsController physics_controller, Vector2 position, int width, int height) {
		super(physics_controller, position, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void createPhysicsObject(Array<Body> movable_bodies, World world) {
		
	}

}

