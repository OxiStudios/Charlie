package com.oxistudios.charlie.physics_engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class PhysicsController {
	
	private static final float SCALING_FACTOR = .05f;
	private static final float BOX_STEP = 1/60f;
	private static final int BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;
	
	private Array<Body> static_bodies;
	private Array<Body> movable_bodies;
	
	private World world;
	private float accumulator;
	public PhysicsController() {
		
		
		world = new World(new Vector2(0,0), true);
		
		static_bodies  = new Array<Body>();
		movable_bodies = new Array<Body>();
		
		setCollisionListener();
		
	}
	
	public void update(float delta) {
		
		//update objects
		
		accumulator += delta;
		while(accumulator > BOX_STEP) {
			world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
			accumulator -= BOX_STEP; 
		}
		
		//safe to remove bodies here
	}
	
	private void setCollisionListener() {
		world.setContactListener(new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				Body bodyA = contact.getFixtureA().getBody();
				Body bodyB = contact.getFixtureB().getBody();
				
				//get user data for the objects
			}
		});
	}
	
	public Array<Body> getStatic_bodies() {
		return static_bodies;
	}
	
	public Array<Body> getMovable_bodies() {
		return movable_bodies;
	}
}
