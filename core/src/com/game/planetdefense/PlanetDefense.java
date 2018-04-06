package com.game.planetdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.Screens.GameScreen;
import com.sun.xml.internal.bind.v2.TODO;

public class PlanetDefense extends Game {

	public static BitmapFont font;

	@Override
	public void create () {
        //TODO: Make scalable font
		font = new BitmapFont();
        this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
