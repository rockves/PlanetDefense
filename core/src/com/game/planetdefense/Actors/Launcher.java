package com.game.planetdefense.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.planetdefense.Utils.Managers.AssetsManager;
import com.game.planetdefense.Utils.StaticUtils;

public class Launcher extends Actor {

    private Sprite sprite;
    private Rectangle position;
    private float timer;

    public Launcher(AssetsManager assets_manager) {
        position = new Rectangle(0,0,0,0);
        this.sprite = new Sprite(assets_manager.getLauncher_texture());
        this.sprite.setBounds(position.getX(),position.getY(),position.getWidth(),position.getHeight());
        timer = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        this.sprite.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        timer += delta;
        super.act(delta);
    }

    public void setLauncher(float x, float y, float width, float height){
        this.position.set(x,y,width,height);
        this.sprite.setBounds(x,y,width,height);
        this.sprite.setOrigin(width/2, height/2);
    }

    public Missile launchMissile(Missile missile, float x, float y){
        if(timer < StaticUtils.LAUNCHER_DELAY_TIME){
            return null;
        }
        missile.setMissile(this);
        missile.setTarget(x,y);
        missile.rotateToTarget();
        timer = 0;
        return missile;
    }


    @Override
    public float getX() {
        return position.getX();
    }
    @Override
    public float getY() {
        return position.getY();
    }
    @Override
    public float getOriginX() {
        return sprite.getOriginX();
    }
    @Override
    public float getOriginY() {
        return sprite.getOriginY();
    }
}
