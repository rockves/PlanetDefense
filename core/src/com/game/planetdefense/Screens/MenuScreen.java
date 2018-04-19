package com.game.planetdefense.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.PlanetDefense;
import com.game.planetdefense.Utils.Managers.AudioManager;
import com.game.planetdefense.Utils.Singletons.UserData;
import com.game.planetdefense.Utils.StaticUtils;

public class MenuScreen implements Screen {

    private final PlanetDefense planetDefense;
    private final MenuScreen menuScreen;
    private Stage stage;
    private Table table;
    private Table buttons_table;
    private Table options_table;
    private ImageButton isSoundOn;
    private ImageButton isMusicOn;
    private Table high_score;
    private Table credits_table;
    private ImageButton back_button;
    private AudioManager audioManager;

    public MenuScreen(final PlanetDefense planetDefense) {

        this.menuScreen = this;
        this.planetDefense = planetDefense;
        this.table = new Table();
        this.buttons_table = new Table();
        this.options_table = new Table();
        this.high_score = new Table();
        this.credits_table = new Table();
        this.audioManager = planetDefense.assets_manager.getAudio_manager();


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Image background = new Image(planetDefense.assets_manager.getStarBackground());
        background.setFillParent(true);
        stage.addActor(background);

        table.setFillParent(true);
        //table.debugAll();
        table.align(Align.top);
        stage.addActor(table);


        buttons_table.setBackground(new TextureRegionDrawable(planetDefense.assets_manager.getButtonsBackground()));
        //buttons_table.debugAll();
        buttons_table.setSize(stage.getWidth(), stage.getWidth() * 0.15f);
        buttons_table.setPosition(0, stage.getHeight()/2 - buttons_table.getHeight()/2);
        buttons_table.align(Align.center);
        stage.addActor(buttons_table);

        options_table.setFillParent(true);
        options_table.setVisible(false);
        options_table.debugAll();
        stage.addActor(options_table);

        high_score.setFillParent(true);
        //high_score.debugAll();
        high_score.setVisible(false);
        stage.addActor(high_score);

        credits_table.setFillParent(true);
        //credits_table.debugAll();
        credits_table.setVisible(false);
        stage.addActor(credits_table);

        TextButton.TextButtonStyle button_style = new TextButton.TextButtonStyle();
        button_style.font = planetDefense.assets_manager.getGame_font();
        button_style.fontColor = Color.WHITE;

        //Setting menu table
        {
            Image title_image = new Image(planetDefense.assets_manager.getTitleImage());
            title_image.setWidth(stage.getWidth() * 0.6f);
            title_image.setHeight(stage.getHeight() / 3 * 0.8f);
            table.add(title_image).width(title_image.getWidth()).height(title_image.getHeight()).padTop(15f);

            float size_width = StaticUtils.MENU_BUTTON_SIZE;
            float size_height = StaticUtils.MENU_BUTTON_SIZE;

            final ImageButton options_button = new ImageButton(new TextureRegionDrawable(planetDefense.assets_manager.getButton_options()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_options_hover()));
            options_button.setSize(size_width, size_height);
            options_button.getImageCell().width(size_width).height(size_height);
            options_button.getImage().setScaling(Scaling.stretch);
            options_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    options_table.setVisible(true);
                    table.setVisible(false);
                    buttons_table.setVisible(false);
                    back_button.setVisible(true);
                }
            });


            ImageButton high_score_button = new ImageButton(new TextureRegionDrawable(planetDefense.assets_manager.getButton_highscore()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_highscore_hover()));
            high_score_button.setSize(size_width, size_height);
            high_score_button.getImageCell().width(size_width).height(size_height);
            high_score_button.getImage().setScaling(Scaling.stretch);
            high_score_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    high_score.setVisible(true);
                    table.setVisible(false);
                    buttons_table.setVisible(false);
                    back_button.setVisible(true);
                }
            });

            ImageButton credits_button = new ImageButton(new TextureRegionDrawable(planetDefense.assets_manager.getButton_about()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_about_hover()));
            credits_button.setSize(size_width, size_height);
            credits_button.getImageCell().width(size_width).height(size_height);
            credits_button.getImage().setScaling(Scaling.stretch);
            credits_button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    credits_table.setVisible(true);
                    table.setVisible(false);
                    buttons_table.setVisible(false);
                    back_button.setVisible(true);
                }
            });
                ImageButton continue_button = new ImageButton(new TextureRegionDrawable(planetDefense.assets_manager.getButton_continue()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_continue_hover()));
                continue_button.setSize(size_width, size_height);
                continue_button.getImageCell().width(size_width).height(size_height);
                continue_button.getImage().setScaling(Scaling.stretch);
                continue_button.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        planetDefense.changeScreen(new GameScreen(planetDefense), menuScreen);
                    }
                });
                buttons_table.add(continue_button).height(size_height).width(size_width).padRight(size_height * 0.2f);

            buttons_table.add(options_button).height(size_height).width(size_width).padRight(size_height * 0.2f);
            buttons_table.add(high_score_button).height(size_height).width(size_width).padRight(size_height * 0.2f);
            buttons_table.add(credits_button).height(size_height).width(size_width).padRight(size_height * 0.2f);
        }

        //setting option table
        {
            ImageButton.ImageButtonStyle style_settings = new ImageButton.ImageButtonStyle();
            style_settings.imageChecked = new TextureRegionDrawable(planetDefense.assets_manager.getButton_checked());
            style_settings.up = new TextureRegionDrawable(planetDefense.assets_manager.getButon_unchecked());
            if(UserData.getInstance().getIsMusicOn()){
                isMusicOn = new ImageButton(style_settings);
                isMusicOn.setChecked(true);
            }else{
                isMusicOn = new ImageButton(style_settings);
            }
            isMusicOn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    UserData.getInstance().setMusicOn(!UserData.getInstance().getIsMusicOn());
                }
            });



            TextButton.TextButtonStyle text_style = new TextButton.TextButtonStyle();
            text_style.fontColor = Color.WHITE;
            text_style.font = planetDefense.assets_manager.getGame_font();
            TextButton reset = new TextButton("Reset save", text_style);
            reset.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (UserData.getInstance().isHasPlaying()) UserData.getInstance().resetUserData();
                }
            });
            reset.setSize(StaticUtils.MENU_BUTTON_SIZE, StaticUtils.MENU_BUTTON_SIZE);
            options_table.add(reset).height(reset.getHeight());
        }

        //Setting high score table
        {
            Label high_score_label = new Label("Top wave: " + UserData.getInstance().getHigh_wave(), new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE));
            Label upgrades_number = new Label("Upgrades bought: " + UserData.getInstance().getUpgradeNumber(), new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE));

            high_score.add(high_score_label).padBottom(10f);
            high_score.row();
            high_score.add(upgrades_number).padBottom(10f);
        }

        //setting credits table
        {
            Label credits_label = new Label(
                    "Code: Milosz Leszko\n" +
                            "Graphic and design: Sebastian Urbanski\n" +
                            "Music: \"8-bit March\" and \"Digital Voyage\"\nby Twin Musicom (twinmusicom.org)"
                    , new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE));
            credits_label.setFontScale(0.8f);
            Container<Label> label_container = new Container<Label>(credits_label);
            label_container.setBackground(new TextureRegionDrawable(planetDefense.assets_manager.getButtonsBackground()));
            credits_table.add(label_container).width(stage.getWidth());
        }

        back_button = new ImageButton( new TextureRegionDrawable(planetDefense.assets_manager.getButton_exit()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_exit_hover()));
        back_button.setBounds(stage.getWidth() * 0.03f, stage.getWidth() * 0.03f,stage.getWidth()/10, stage.getWidth()/10);
        back_button.getImageCell().width(stage.getWidth()/10).height(stage.getWidth()/10);
        back_button.getImage().setScaling(Scaling.stretch);
        back_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(high_score.isVisible()) {
                    high_score.setVisible(false);
                    table.setVisible(true);
                    buttons_table.setVisible(true);
                    back_button.setVisible(false);
                }else if(credits_table.isVisible()){
                    credits_table.setVisible(false);
                    table.setVisible(true);
                    buttons_table.setVisible(true);
                    back_button.setVisible(false);
                }else{
                    options_table.setVisible(false);
                    table.setVisible(true);
                    buttons_table.setVisible(true);
                    back_button.setVisible(false);
                }
            }
        });
        back_button.setVisible(false);
        stage.addActor(back_button);
    }

    @Override
    public void show() {
        audioManager.playMenuMusic();
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
        audioManager.stopMenuMusic();
        stage.dispose();
    }
}
