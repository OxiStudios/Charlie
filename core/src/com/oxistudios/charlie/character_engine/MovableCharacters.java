package com.oxistudios.charlie.character_engine;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class MovableCharacters extends Entity{
	
	protected float delta_x;
	protected float delta_y;
	
	protected Sprite sprite;

	public MovableCharacters(Vector2 position, int width, int height) {
		super(position, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void render(float delta, SpriteBatch b) {
		sprite.draw(b);
		stateTime += delta;
		sprite.setRegion(animation.getKeyFrame(stateTime, true));
		sprite.draw(b);
	}
		
	public void update(float delta) {
		this.position.x += delta_x;
		this.position.y += delta_y;
	}

	public void setDelta_x(float delta_x) {
		this.delta_x = delta_x;
	}

	public void setDelta_y(float delta_y) {
		this.delta_y = delta_y;
	}
	
	public void createPhysicsObject(Array<Body> movable_bodies, World world) {
		
	}

}
