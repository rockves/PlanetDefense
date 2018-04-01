package com.game.planetdefense.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Missile extends Actor {

    private final int missile_speed = 500;
    private Sprite sprite;
    private Vector2 position;


    public Missile(Weapon launcher, float x, float y) {
        this.sprite = new Sprite(new Texture(Gdx.files.internal("missile.png")));
        this.setWidth(100);
        this.setHeight(50);
        this.setOrigin(getWidth()/2, getHeight()/2);
        this.position = new Vector2(launcher.getX() + launcher.getOriginX() - getOriginX(), launcher.getY() + launcher.getOriginY() - getOriginY());
        this.setPosition(position.x, position.y);
        this.sprite.setBounds( getX(), getY(), getWidth(), getHeight());
        this.sprite.setOrigin(getOriginX(),getOriginY());

        rotateToPoint(x,y);
        Gdx.app.log("New object", "Missile");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveToTarget();
        this.sprite.setPosition(position.x,position.y);
    }

    private void rotateToPoint(float x, float y){
        Vector2 temp = new Vector2(0,0);
        float angle = temp.set(x,y).sub(position.x + getOriginX(),position.y + getOriginY()).angle();
        this.sprite.setOriginCenter();
        this.setOrigin(this.sprite.getOriginX(), this.sprite.getOriginY());
        this.sprite.setRotation(angle);
        this.setRotation(angle);
    }

    private void moveToTarget(){
        position.add(new Vector2(missile_speed *Gdx.graphics.getDeltaTime(), 0).rotate(this.getRotation()));
    }
}
