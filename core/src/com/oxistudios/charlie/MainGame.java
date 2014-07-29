package com.oxistudios.charlie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxistudios.charlie.screens.maingamescreen.MainGameScreen;

public class MainGame extends Game {
	
	//Tyler Liddicoat test commit
	
	
	@Override
	public void create () {
		setScreen(new MainGameScreen(this));
	}

	//Tyler Liddicoat test commit
	
	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
}
