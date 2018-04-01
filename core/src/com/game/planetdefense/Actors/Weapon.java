package com.game.planetdefense.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Weapon extends Actor {

    private Sprite sprite;

    public Weapon(Stage stage) {
        this.sprite = new Sprite(new Texture(Gdx.files.internal("launcher.png")));
        this.setWidth(100);
        this.setHeight(100);
        this.setPosition(stage.getWidth()/2 - getWidth()/2, stage.getHeight()/2 - getHeight()/2);
        this.setOrigin(getWidth()/2, getHeight()/2);
        this.sprite.setOrigin(this.getOriginX(), this.getOriginY());
        this.sprite.setBounds(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        this.sprite.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
