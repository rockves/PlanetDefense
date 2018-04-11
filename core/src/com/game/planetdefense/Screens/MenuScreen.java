package com.game.planetdefense.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.PlanetDefense;
import com.game.planetdefense.Utils.Singletons.UserData;

public class MenuScreen implements Screen {

    private final PlanetDefense planetDefense;
    private final MenuScreen menuScreen;
    private Stage stage;
    private Table table;
    private Table high_score;

    public MenuScreen(final PlanetDefense planetDefense) {

        this.menuScreen = this;
        this.planetDefense = planetDefense;
        this.table = new Table();
        this.high_score = new Table();



        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Image background = new Image(planetDefense.assets_manager.getMenuBackground());
        background.setFillParent(true);
        stage.addActor(background);

        table.setFillParent(true);
        //table.debugAll();
        table.align(Align.top);
        stage.addActor(table);

        high_score.setFillParent(true);
        //high_score.debugAll();
        high_score.setVisible(false);
        stage.addActor(high_score);


        TextButton.TextButtonStyle button_style = new TextButton.TextButtonStyle();
        button_style.font = planetDefense.assets_manager.getGame_font();
        button_style.fontColor = Color.WHITE;

        //Setting menu table
        {
            Image title_image = new Image(planetDefense.assets_manager.getTitleImage());
            title_image.setWidth(stage.getWidth() * 0.6f);
            title_image.setHeight(stage.getHeight() / 3 * 0.8f);
            table.add(title_image).width(title_image.getWidth()).height(title_image.getHeight());
            table.row();

            TextButton new_game_button = new TextButton("NEW GAME", button_style);
            new_game_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (UserData.getInstance().isHasPlaying()) UserData.getInstance().resetUserData();
                    planetDefense.changeScreen(new GameScreen(planetDefense), menuScreen);
                }
            });


            TextButton options_button = new TextButton("OPTIONS", button_style);
            options_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                }
            });
            options_button.setTouchable(Touchable.disabled);
            options_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                }
            });


            TextButton high_score_button = new TextButton("HIGH SCORE", button_style);
            high_score_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    high_score.setVisible(true);
                    table.setVisible(false);
                }
            });


            if (UserData.getInstance().isHasPlaying()) {
                TextButton continue_button = new TextButton("CONTINUE", button_style);
                continue_button.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        planetDefense.changeScreen(new GameScreen(planetDefense), menuScreen);
                    }
                });
                table.add(continue_button).height((stage.getHeight() - title_image.getHeight())/4).width(stage.getWidth() / 4);
                table.row();
            }

            table.add(new_game_button).height((stage.getHeight() - title_image.getHeight())/4).width(stage.getWidth() / 4);
            table.row();
            table.add(options_button).height((stage.getHeight() - title_image.getHeight())/4).width(stage.getWidth() / 4);
            table.row();
            table.add(high_score_button).height((stage.getHeight() - title_image.getHeight())/4).width(stage.getWidth() / 4);
        }


        //Setting high score table
        {
            Label high_score_label = new Label("Top wave: " + UserData.getInstance().getHigh_wave(), new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE));


            TextButton back_to_menu_from_score_button = new TextButton("BACK", button_style);
            back_to_menu_from_score_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    high_score.setVisible(false);
                    table.setVisible(true);
                }
            });


            high_score.add(high_score_label);
            high_score.row();
            high_score.add(back_to_menu_from_score_button).height(stage.getHeight() / 6).width(stage.getWidth() / 4);
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
