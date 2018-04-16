package com.game.planetdefense.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.Utils.Managers.AssetsManager;
import com.game.planetdefense.Utils.Singletons.UserData;

public class UpgradeButton extends Button {

    private UpgradeType upgrade_type;
    private Animation<TextureRegion> button_animation;
    private Label price_label;
    private TextureRegionDrawable background;
    private float state_time;
    private float price;

    public UpgradeButton(AssetsManager assetsManager, UpgradeType upgradeType, float width, float height) {
        super(new TextureRegionDrawable(assetsManager.getUpgrade_button_animation().getKeyFrame(0, true)));
        this.setSize(width,height);
        this.align(Align.bottom);
        state_time = 0f;
        this.upgrade_type = upgradeType;
        this.button_animation = assetsManager.getUpgrade_button_animation();
        background = new TextureRegionDrawable(button_animation.getKeyFrame(state_time, true));

        //label
        getPrice(upgradeType);
        price_label = new Label("",new Label.LabelStyle(assetsManager.getGame_font(), Color.WHITE));
        price_label.setText("" + (int)price);
        price_label.setAlignment(Align.center);
        float font_scale = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        price_label.setFontScale(font_scale/ 1.5f);
        this.add(price_label).padBottom(this.getHeight() * 0.2f).height(this.getHeight() * 0.15f);

        this.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(UserData.getInstance().getMoney() >= price){
                    buy();
                }
            }
        });
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
        //this.setStyle(this.getStyle());
        super.act(delta);
    }

    private void getPrice(UpgradeType upgradeType){
        price = upgradeType.getPrice();
    }

    private void buy(){
        UserData.getInstance().setMoney(UserData.getInstance().getMoney() - upgrade_type.getPrice());
        upgrade_type.addUpgradeLvl();
        Gdx.app.log("Upgrade", "" + upgrade_type.getUpgradeLvl());
    }
}
