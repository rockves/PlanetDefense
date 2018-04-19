package com.game.planetdefense.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.PlanetDefense;

public class SplashScreen implements Screen {

    private PlanetDefense planetDefense;
    private Stage stage;
    private float timer = 1f;
    private float timer_y = 0.7f;
    private boolean isTimer_y = false;
    private Image title_image;

    public SplashScreen(PlanetDefense planetDefense) {
        this.planetDefense = planetDefense;
        this.stage = new Stage(new ScreenViewport());
        Image background = new Image(planetDefense.assets_manager.getStarBackground());
        background.setFillParent(true);
        stage.addActor(background);
        title_image = new Image(planetDefense.assets_manager.getTitleImage());
        title_image.setWidth(stage.getWidth() * 0.6f);
        title_image.setHeight(stage.getHeight() / 3 * 0.8f);
        title_image.setPosition(stage.getWidth()/2 - title_image.getWidth()/2, stage.getHeight());
        stage.addActor(title_image);
        title_image.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.parallel(Actions.fadeIn(1.5f), Actions.moveTo(title_image.getX(),stage.getHeight()/2 - title_image.getHeight()/2, 1.5f))));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
        if(title_image.getActions().size == 0 && !isTimer_y){
            timer -= delta;
        }else if(isTimer_y){
            timer_y -= delta;
        }
        if(timer <= 0){
            isTimer_y = true;
            title_image.addAction(Actions.moveTo(title_image.getX(), stage.getHeight() - title_image.getHeight() - 15f, timer_y));
        }
        if(timer_y <= 0){
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
