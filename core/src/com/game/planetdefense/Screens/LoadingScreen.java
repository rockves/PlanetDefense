package com.game.planetdefense.Screens;

import com.badlogic.gdx.Screen;
import com.game.planetdefense.PlanetDefense;

public class LoadingScreen implements Screen {

    private PlanetDefense planetDefense;

    public LoadingScreen(PlanetDefense planetDefense) {
        this.planetDefense = planetDefense;
        planetDefense.assets_manager.setAssetsQueue();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(planetDefense.assets_manager.update()){
            planetDefense.assets_manager.getAssets();
            planetDefense.changeScreen(new MenuScreen(planetDefense), this);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
