package com.game.planetdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.game.planetdefense.Screens.MenuScreen;

public class PlanetDefense extends Game {

	public static BitmapFont font;

	@Override
	public void create () {
        //TODO: Make scalable font
		font = new BitmapFont();
        this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		font.dispose();
	}

	public void changeScreen(Screen newScreen, Screen thisScreen){
        thisScreen.dispose();
		this.setScreen(newScreen);
	}
}
