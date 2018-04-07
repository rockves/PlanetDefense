package com.game.planetdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.game.planetdefense.Screens.GameScreen;

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
