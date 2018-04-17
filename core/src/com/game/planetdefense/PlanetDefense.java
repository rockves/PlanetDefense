package com.game.planetdefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.game.planetdefense.Screens.LoadingScreen;
import com.game.planetdefense.Utils.Managers.AssetsManager;

public class PlanetDefense extends Game {

	public AssetsManager assets_manager;

	@Override
	public void create () {
        //TODO: Make scalable font
		assets_manager = new AssetsManager();
        this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		assets_manager.dispose();
	}

	public void changeScreen(Screen newScreen, Screen thisScreen){
        thisScreen.dispose();
		this.setScreen(newScreen);
	}
}
