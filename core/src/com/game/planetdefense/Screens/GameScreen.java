package com.game.planetdefense.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.GameStage;
import com.game.planetdefense.PlanetDefense;
import com.game.planetdefense.Utils.Singletons.UserData;

public class GameScreen implements Screen {

    private GameStage stage;
    private PlanetDefense planet_defense;
    //private FPSLogger fps;

    public GameScreen(PlanetDefense planet_defense) {
        //fps = new FPSLogger();
        this.stage = new GameStage(new ScreenViewport(), planet_defense);
        Gdx.input.setInputProcessor(stage);
        this.planet_defense = planet_defense;

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        //fps.log();
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
        UserData.getInstance().updateUserData();
        stage.dispose();
    }
}
