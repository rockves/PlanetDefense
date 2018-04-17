package com.game.planetdefense.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.PlanetDefense;
import com.game.planetdefense.Utils.Managers.AudioManager;
import com.game.planetdefense.Utils.Singletons.UserData;
import com.game.planetdefense.Utils.StaticUtils;
import com.game.planetdefense.Utils.UpgradeButton;


public class UpgradeScreen implements Screen {

    private final PlanetDefense planetDefense;
    private final UpgradeScreen upgradeScreen;
    private AudioManager audio_manager;
    private Stage stage;
    private Label money_label;
    private Image shop_guy_image;
    private float state_time;

    public UpgradeScreen(final PlanetDefense planetDefense) {
        this.planetDefense = planetDefense;
        this.upgradeScreen = this;
        this.audio_manager = planetDefense.assets_manager.getAudio_manager();
        this.state_time = 0;
        Gdx.input.setCatchBackKey(true);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Image background = new Image(planetDefense.assets_manager.getStarBackground());
        background.setFillParent(true);
        stage.addActor(background);


        //Set money counter
        Image counter_image = new Image(planetDefense.assets_manager.getMoneyCounterTexture());
        counter_image.setSize(stage.getWidth()/3.5f, stage.getHeight()/3.5f);
        counter_image.setPosition(0, stage.getHeight() - counter_image.getHeight());


        money_label = new Label("" + MathUtils.floor(UserData.getInstance().getMoney()), new Label.LabelStyle(planetDefense.assets_manager.getGame_font(), Color.WHITE));
        money_label.setSize(counter_image.getWidth() * 0.5f, counter_image.getHeight() * 0.3f);
        money_label.setPosition(counter_image.getX() + (counter_image.getWidth() - money_label.getWidth() - counter_image.getWidth() * 0.1f), counter_image.getY() + counter_image.getHeight() - money_label.getHeight() - (counter_image.getHeight() * 0.1f));

        stage.addActor(counter_image);
        stage.addActor(money_label);


        //set shop guy
        shop_guy_image = new Image(planetDefense.assets_manager.getShopGuyAnimation().getKeyFrame(state_time, true));
        shop_guy_image.setSize(stage.getWidth() * 0.25f * 1.4f, stage.getHeight() * 0.25f * 1.4f);
        shop_guy_image.setPosition(stage.getWidth()/2 - shop_guy_image.getWidth()/2, stage.getHeight() * 0.95f - shop_guy_image.getHeight());
        stage.addActor(shop_guy_image);

        //set exit button
        ImageButton exit_game_button = new ImageButton(new TextureRegionDrawable(planetDefense.assets_manager.getButton_exit()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_exit_hover()));
        exit_game_button.setSize(StaticUtils.ACTION_BUTTON_SIZE, StaticUtils.ACTION_BUTTON_SIZE);
        exit_game_button.setPosition(stage.getWidth() * 0.02f, stage.getWidth() * 0.02f);
        exit_game_button.getImageCell().size(exit_game_button.getWidth(), exit_game_button.getHeight());
        exit_game_button.getImage().setScaling(Scaling.stretch);
        exit_game_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                planetDefense.changeScreen(new MenuScreen(planetDefense), upgradeScreen);
            }
        });
        //exit_game_button.debug();
        stage.addActor(exit_game_button);

        //set start button
        ImageButton start_game_button = new ImageButton(new TextureRegionDrawable(planetDefense.assets_manager.getButton_continue()), new TextureRegionDrawable(planetDefense.assets_manager.getButton_continue_hover()));
        start_game_button.setSize(stage.getWidth() * 0.1f, stage.getWidth() * 0.1f);
        start_game_button.setPosition(stage.getWidth() - start_game_button.getWidth() - (stage.getWidth() * 0.02f), stage.getWidth() * 0.02f);
        start_game_button.getImageCell().size(start_game_button.getWidth(), start_game_button.getHeight());
        start_game_button.getImage().setScaling(Scaling.stretch);
        start_game_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                planetDefense.changeScreen(new GameScreen(planetDefense), upgradeScreen);
            }
        });
        //start_game_button.debug();
        stage.addActor(start_game_button);


        {//upgrades
            float pad = StaticUtils.UPGRADE_BUTTON_SIZE * 0.1f;
            float button_size = StaticUtils.UPGRADE_BUTTON_SIZE;
            float pos_x = stage.getWidth()/2 - button_size - pad/2;
            float pos_y = stage.getHeight()/3;

            UpgradeButton dmgUpgrade = new UpgradeButton(planetDefense.assets_manager, UpgradeType.DmgBonus, pos_x, pos_y, button_size, button_size);
            //dmgUpgrade.debugAll();
            stage.addActor(dmgUpgrade);

            pos_x += button_size + pad;

            UpgradeButton laserUpgrade = new UpgradeButton(planetDefense.assets_manager, UpgradeType.LaserUpgrade, pos_x, pos_y, button_size, button_size);
            //dmgUpgrade.debugAll();
            stage.addActor(laserUpgrade);
            pos_y -= button_size + pad;
            UpgradeButton stageUpgrade = new UpgradeButton(planetDefense.assets_manager, UpgradeType.StageBonus, pos_x, pos_y, button_size, button_size);
            //dmgUpgrade.debugAll();
            stage.addActor(stageUpgrade);

            pos_x -= button_size + pad;
            UpgradeButton shieldUpgrade = new UpgradeButton(planetDefense.assets_manager, UpgradeType.ShieldBonus, pos_x, pos_y, button_size, button_size);
            //dmgUpgrade.debugAll();
            stage.addActor(shieldUpgrade);
        }

    }

    @Override
    public void show() {
        audio_manager.playUpgradeMenuMusic();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        money_label.setText("" + MathUtils.floor(UserData.getInstance().getMoney()));
        shop_guy_image.setDrawable(new TextureRegionDrawable(planetDefense.assets_manager.getShopGuyAnimation().getKeyFrame(state_time, true)));
        stage.draw();
        state_time += delta;
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
        UserData.getInstance().updateUserData();
        audio_manager.stopUpgradeMenuMusic();
        stage.dispose();
    }
}
