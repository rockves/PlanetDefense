package com.game.planetdefense.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.Utils.Managers.AssetsManager;
import com.game.planetdefense.Utils.Managers.AudioManager;
import com.game.planetdefense.Utils.Singletons.UserData;

public class UpgradeButton extends Button {

    private UpgradeType upgrade_type;
    private AudioManager audio_manager;
    private Animation<TextureRegion> button_animation;
    private Image upgrade_image;
    private Label price_label;
    private TextureRegionDrawable background;
    private float state_time;
    private float price;
    private float how_far_up = 60f;
    private float start_y;
    private float speed = 3f;
    private MoveByAction moveAction;

    public UpgradeButton(AssetsManager assetsManager, UpgradeType upgradeType, float x, float y, float width, float height) {
        super(new TextureRegionDrawable(assetsManager.getUpgrade_button_animation().getKeyFrame(0, true)));
        this.setPosition(x,y);
        this.audio_manager = assetsManager.getAudio_manager();
        start_y = y;
        this.setSize(width,height);
        this.align(Align.top);
        state_time = 0f;
        this.upgrade_type = upgradeType;
        this.button_animation = assetsManager.getUpgrade_button_animation();
        background = new TextureRegionDrawable(button_animation.getKeyFrame(state_time, true));

        //icon
        upgrade_image = new Image(new TextureRegionDrawable(upgradeType.getUpgradeIcon(assetsManager)));
        //upgrade_image.setScaling(Scaling.fit);
        this.add(upgrade_image).center().width(this.getWidth() * 0.6f).height(this.getHeight() * 0.4f).padLeft(this.getWidth() * 0.1f).padTop(this.getWidth() * 0.15f).padRight(this.getWidth() * 0.1f);
        this.row();

        //label
        getPrice(upgradeType);
        price_label = new Label("",new Label.LabelStyle(assetsManager.getGame_font(), Color.WHITE));
        if(upgradeType.getUpgradeLvl() == upgradeType.getUpgradeMaxLvl()){
            price_label.setText("MAX");
        }else {
            price_label.setText("" + (int)price);
        }
        price_label.setAlignment(Align.center);
        float font_scale = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        price_label.setFontScale(font_scale/ 1.5f);
        this.add(price_label).padTop(this.getWidth() * 0.09f);

        if(!(upgradeType.getUpgradeLvl() == upgradeType.getUpgradeMaxLvl())){
            this.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if(UserData.getInstance().getMoney() >= price && !(upgrade_type.getUpgradeLvl() == upgrade_type.getUpgradeMaxLvl())){
                        buy();
                    }
                }
            });
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        state_time += delta;
        background.setRegion(button_animation.getKeyFrame(state_time, true));
        this.getStyle().up = background;
        super.act(delta);
    }

    private void getPrice(UpgradeType upgradeType){
        price = upgradeType.getPrice();
    }

    private void buy(){
        UserData.getInstance().setMoney(UserData.getInstance().getMoney() - upgrade_type.getPrice());
        upgrade_type.addUpgradeLvl();
        UserData.getInstance().addUpgradeNumber();
        if(upgrade_type.getUpgradeLvl() == upgrade_type.getUpgradeMaxLvl()){
            price_label.setText("MAX");
        }
        audio_manager.playBuySound();
        Gdx.app.log("Upgrade", "" + upgrade_type.getUpgradeLvl());
    }
}
