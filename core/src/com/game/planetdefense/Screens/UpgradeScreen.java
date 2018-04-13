package com.game.planetdefense.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.PlanetDefense;


public class UpgradeScreen implements Screen {

    private final PlanetDefense planetDefense;
    private final UpgradeScreen upgradeScreen;
    private Stage stage;
    private Table table;

    public UpgradeScreen(final PlanetDefense planetDefense) {
        this.planetDefense = planetDefense;
        this.upgradeScreen = this;
        Gdx.input.setCatchBackKey(true);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.debugAll();
        stage.addActor(table);

        {//Laser category
            Table laser_category = new Table();
            laser_category.debugAll();
            laser_category.setBounds(0,0,stage.getWidth()/3,stage.getHeight());
            laser_category.align(Align.left);
            Label laser_title = new Label("Laser upgrade",new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.BLACK));
            laser_category.add(laser_title).expandX().setActorHeight(20f);
            table.addActor(laser_category);
        }

        {//Base category
            Table base_category = new Table();
            base_category.debugAll();
            base_category.setBounds(stage.getWidth()/3,0,stage.getWidth()/3,stage.getHeight());
            base_category.align(Align.left);
            Label base_title = new Label("Base upgrade",new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.BLACK));
            base_category.add(base_title).expandX().setActorHeight(20f);
            table.addActor(base_category);
        }

        {//Rest
            Table rest_category = new Table();
            rest_category.debugAll();
            rest_category.setBounds(stage.getWidth()/3,0,stage.getWidth()/3,stage.getHeight());
            rest_category.align(Align.left);
            Label rest_title = new Label("Rest upgrades",new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.BLACK));
            rest_category.add(rest_title).expandX().setActorHeight(20f);
        }
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
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) planetDefense.changeScreen(new MenuScreen(planetDefense), this);
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
