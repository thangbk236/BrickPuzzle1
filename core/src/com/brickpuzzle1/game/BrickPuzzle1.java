package com.brickpuzzle1.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.brickpuzzle1.game.Assets.GameAsset;
import com.brickpuzzle1.game.Assets.GameJson;
import com.brickpuzzle1.game.Screen.MainMenuScreen;

public class BrickPuzzle1 extends Game {
	GameAsset gameAsset;
	public static PlayServices playservices;
	public BrickPuzzle1(PlayServices playservices) {
		this.playservices = playservices;
	}

	@Override
	public void create () {
		gameAsset = new GameAsset();
		GameJson.load();

		this.setScreen(MainMenuScreen.getInstance(this,true));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
