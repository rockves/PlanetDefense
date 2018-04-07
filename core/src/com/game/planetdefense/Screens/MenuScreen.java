package com.game.planetdefense.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.PlanetDefense;

public class MenuScreen implements Screen {

    private final PlanetDefense planetDefense;
    private final MenuScreen menuScreen;
    private Stage stage;
    private VerticalGroup vertical_group;

    public MenuScreen(final PlanetDefense planetDefense) {
        this.menuScreen = this;
        this.planetDefense = planetDefense;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        vertical_group = new VerticalGroup();
        vertical_group.debugAll();
        vertical_group.setHeight(stage.getHeight()/2);
        vertical_group.setWidth(stage.getWidth()/3);
        vertical_group.setOrigin(vertical_group.getWidth()/2, vertical_group.getHeight()/2);
        vertical_group.setPosition(stage.getWidth()/2 - vertical_group.getOriginX(), stage.getHeight()/2 - vertical_group.getOriginY());
        stage.addActor(vertical_group);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = PlanetDefense.font;
        style.fontColor = Color.BLACK;
        //Continue button

        //Start new game button
        TextButton start_new_game = new TextButton("Start new game",style);
        start_new_game.debug();
        start_new_game.setHeight(vertical_group.getHeight()/3);
        start_new_game.setWidth(vertical_group.getWidth());
        start_new_game.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                planetDefense.changeScreen(new GameScreen(planetDefense), menuScreen);
            }
        });
        vertical_group.addActor(start_new_game);
        //Options button
        TextButton options_button = new TextButton("Options", style);
        options_button.debug();
        options_button.setHeight(vertical_group.getHeight()/3);
        options_button.setWidth(vertical_group.getWidth());
        options_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        options_button.setDisabled(true);
        vertical_group.addActor(options_button);
        //Exit button
        TextButton exit_button = new TextButton("Exit", style);
        exit_button.debug();
        exit_button.setHeight(vertical_group.getHeight()/3);
        exit_button.setWidth(vertical_group.getWidth());
        exit_button.getLabel().setFillParent(true);
        exit_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menuScreen.dispose();
                planetDefense.dispose();
                System.exit(0);
            }
        });
        vertical_group.addActor(exit_button);
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
        stage.dispose();
    }
}
