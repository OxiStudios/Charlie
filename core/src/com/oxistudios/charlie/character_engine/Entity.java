package com.oxistudios.charlie.character_engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

public class Entity {
	
	protected Animation animation;
	protected Texture entity_texture;
	protected TextureRegion[] animation_frames;
	protected TextureRegion current_frame;
	protected float stateTime;
	
	protected int health;
	protected int shield;
	protected double health_regen;
	
	protected int WIDTH;
	protected int HEIGHT;
	protected float p_width;
	protected float p_height;
	
	protected Vector2 position;
	
	protected String ID;
	
	
	public Entity(Vector2 position, int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.position = position;
	}
	
	public void createAnimation(String location, int FRAME_ROWS, int FRAME_COLS) {
		this.entity_texture = new Texture(Gdx.files.internal(location));
		TextureRegion[][] tmp = TextureRegion.split(this.entity_texture, entity_texture.getWidth(), entity_texture.getHeight());
		this.animation_frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for(int i = 0; i < FRAME_ROWS; ++i) {
			for(int j = 0; j < FRAME_COLS; ++j) {
				this.animation_frames[index++] = tmp[i][j];
			}
		}
		
		this.animation = new Animation(0.025f, this.animation_frames);
		this.stateTime = 0f;
	}
	
	public void createPhysicsObject(Array<Body> bodies, World world) {
		//make the shape of the body of fixture could be made in constructor, same for every bullet
		PolygonShape staticShape = new PolygonShape();
		staticShape.setAsBox(this.p_width, this.p_height);

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

		//add body's userdata
		staticBody.setUserData(this);

		//add body to list for position updating and collision detection
		bodies.add(staticBody);

		//delete uneeded shape
		staticShape.dispose();
	}
	
	 //////////////////////////////////////
	//////////////////////////////////////
	
	public void render(float timer, SpriteBatch b) {
		
	}
	
	public String getID() {
		return ID;
	}

	public Vector2 getPosition() {
		return position;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public double getHealth_regen() {
		return health_regen;
	}

	public void setHealth_regen(double d) {
		this.health_regen = d;
	}
	

}
