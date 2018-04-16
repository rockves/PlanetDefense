package com.game.planetdefense.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.game.planetdefense.Enums.LaserType;
import com.game.planetdefense.Enums.UpgradeType;
import com.game.planetdefense.Utils.Managers.AssetsManager;

public class Missile extends Actor implements Pool.Poolable {

    private LaserType laserType;
    private Sprite sprite;
    private Rectangle position;
    private Vector2 target;
    private float damage;
    public float flight_time;


    public Missile(AssetsManager assets_manager) {
        this.laserType = null;
        this.damage = 0f;
        this.position = new Rectangle(0,0,0,0);
        this.sprite = new Sprite();
        this.sprite.setBounds( position.getX(), position.getY(), position.getWidth(), position.getHeight());
        this.target = new Vector2(0,0);
        flight_time = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch,parentAlpha);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        moveToTarget(delta);
        this.sprite.setPosition(position.x,position.y);
        flight_time += delta;
        super.act(delta);
    }

    @Override
    public void reset() {
        this.laserType = null;
        this.position.set(0,0,0,0);
        this.sprite.setBounds( position.getX(), position.getY(), position.getWidth(), position.getHeight());
        this.setRotation(0);
        this.target.set(0,0);
        this.flight_time = 0;
        this.remove();
    }

    public void setTarget(float x, float y){
        target.set(x,y);
    }

    public void rotateToPoint(float x, float y){
        Vector2 temp = new Vector2(0,0);
        float angle = temp.set(x,y).sub(position.getX() + sprite.getOriginX(), position.getY() + sprite.getOriginY()).angle();
        this.sprite.setRotation(angle);
    }

    public void rotateToTarget(){
        Vector2 temp = new Vector2(0,0);
        float angle = temp.set(target.x,target.y).sub(position.getX() + sprite.getOriginX(), position.getY() + sprite.getOriginY()).angle();
        this.sprite.setRotation(angle - 90f);
    }

    private void moveToTarget(float delta){
        //TODO: Check performance of this two
        /*Vector2 help = new Vector2(position.getX(), position.getY());
        help.add(new Vector2(StaticUtils.MISSILE_SPEED * Gdx.graphics.getDeltaTime(), 0).rotate(sprite.getRotation()));
        position.setPosition(help);*/
        this.position.x += laserType.getSpeed() * delta * MathUtils.cos((float)((Math.PI / 180) * ( sprite.getRotation() + 90)));
        this.position.y += laserType.getSpeed() * delta * MathUtils.sin((float)((Math.PI / 180) * ( sprite.getRotation() + 90)));
    }

    public void moveForward(float distance){
        this.position.x += distance * MathUtils.cos((float)((Math.PI / 180) * ( sprite.getRotation() + 90)));
        this.position.y += distance * MathUtils.sin((float)((Math.PI / 180) * ( sprite.getRotation() + 90)));
    }

    public void setMissile(float x, float y, LaserType laser_type, TextureRegion laser_texture){
        this.laserType = laser_type;
        this.sprite.setRegion(laser_texture);
        this.position.set(x, y, laserType.getWidth(), laserType.getHeight());
        this.sprite.setBounds(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight());
        this.sprite.setOrigin(this.sprite.getWidth()/2, 0);
        rotateToTarget();
        this.damage = 5f + (UpgradeType.DmgBonus.getUpgradeLvl() * UpgradeType.DmgBonus.getUpgradeValue());
        Gdx.app.log("DMG", ""+damage);
        Gdx.app.log("Missile position", " " + sprite.getX() + " " + sprite.getY());
    }

    public float getDamage() {
        return damage;
    }
    public void setDamage(float damage) {
        this.damage = damage;
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
    public Rectangle getRectangle(){
        return position;
    }
}
