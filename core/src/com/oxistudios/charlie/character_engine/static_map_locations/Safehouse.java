package com.oxistudios.charlie.character_engine.static_map_locations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Safehouse extends StaticCharacters{
	
	public Safehouse(Vector2 position, int width, int height) {
		super(position, width, height);
		this.ID = "0001";
	}
	
	public void createPhysicsObject(Array<Body> static_bodies, World world) {
		//make the shape of the body of fixture could be made in constructor, same for every bullet
		PolygonShape staticShape = new PolygonShape();
		staticShape.setAsBox(.35f, .6f);

		//make a body to add to the world
		BodyDef staticBodyDef = new BodyDef();
		staticBodyDef.type = BodyType.DynamicBody; //not sure this is the right body type
		staticBodyDef.position.set(this.position); //still need to convert correctly

		//add bodydef to a world body, this is where it will probably add to the arraylist
		Body staticBody = world.createBody(staticBodyDef);

		//make a fixture for the body and shape to it
		FixtureDef bulletFixture = new FixtureDef();
		bulletFixture.shape = staticShape;
		bulletFixture.isSensor = true;

		//add fixture to the world body 
		staticBody.createFixture(bulletFixture);

		//add speed
		staticBody.setLinearVelocity(0.0f, 30.0f);

		//add body's userdata
		staticBody.setUserData(this);

		//add body to list for position updating and collision detection
		static_bodies.add(staticBody);

		//delete uneeded shape
		staticShape.dispose();
	}
	

}
