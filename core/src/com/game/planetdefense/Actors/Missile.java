package com.game.planetdefense.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.game.planetdefense.Utils.Managers.AssetsManager;
import com.game.planetdefense.Utils.Singletons.UserData;
import com.game.planetdefense.Utils.StaticUtils;

public class Missile extends Actor implements Pool.Poolable {

    private Sprite sprite;
    private Rectangle position;
    private Vector2 target;
    private float damage;
    public float flight_time;


    public Missile(AssetsManager assets_manager) {
        this.damage = 0f;
        this.position = new Rectangle(0,0,0,0);
        this.sprite = new Sprite(assets_manager.getMissile_texture());
        this.sprite.setBounds( position.getX(), position.getY(), position.getWidth(), position.getHeight());
        this.target = new Vector2(0,0);
        flight_time = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch,parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveToTarget(delta);
        this.sprite.setPosition(position.x,position.y);
        flight_time += delta;
    }

    @Override
    public void reset() {
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
        this.sprite.setRotation(angle);
    }

    private void moveToTarget(float delta){
        //TODO: Check performance of this two
        /*Vector2 help = new Vector2(position.getX(), position.getY());
        help.add(new Vector2(StaticUtils.MISSILE_SPEED * Gdx.graphics.getDeltaTime(), 0).rotate(sprite.getRotation()));
        position.setPosition(help);*/
        this.position.x += StaticUtils.MISSILE_SPEED * delta * MathUtils.cos((float)((Math.PI / 180) * ( sprite.getRotation())));
        this.position.y += StaticUtils.MISSILE_SPEED * delta * MathUtils.sin((float)((Math.PI / 180) * ( sprite.getRotation())));
    }

    public void setMissile(Launcher launcher){
        this.position.set(launcher.getX() + launcher.getOriginX() - StaticUtils.MISSILE_WIDTH/2, launcher.getY() + launcher.getOriginY() - StaticUtils.MISSILE_HEIGHT/2, StaticUtils.MISSILE_WIDTH, StaticUtils.MISSILE_HEIGHT);
        this.sprite.setBounds(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight());
        this.sprite.setOriginCenter();
        this.damage = 5f * UserData.getInstance().getBonus_shoot_damage();
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
