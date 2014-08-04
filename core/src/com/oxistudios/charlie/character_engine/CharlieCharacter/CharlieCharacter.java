package com.oxistudios.charlie.character_engine.CharlieCharacter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.oxistudios.charlie.character_engine.MovableCharacters;


public class CharlieCharacter extends MovableCharacters{
	
	private float delta_x;
	private float delta_y;
	Sprite sprite;

	public CharlieCharacter(Vector2 position, int width, int height) {
		super(position, width, height);
		// TODO Auto-generated constructor stub
		
		sprite = new Sprite(new Texture(Gdx.files.external("hard code")));
	}

	public void render(float delta, SpriteBatch b) {
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

}

