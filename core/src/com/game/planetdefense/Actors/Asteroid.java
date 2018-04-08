package com.game.planetdefense.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.game.planetdefense.Utils.StaticUtils;

public class Asteroid extends Actor implements Pool.Poolable{
//TODO: Zastanowic sie nad zmiana lotu z poruszania w przod na Action aktora
    private Animation<TextureRegion> animation;
    private Sprite sprite;
    private Rectangle position;
    private Vector2 target;
    private float state_time;

    private float hp;
    private float speed;
    private float money_drop;

    public Asteroid() {
        this.state_time = 0f;
        this.animation = null;
        this.position = new Rectangle(0,0,0,0);
        this.sprite = new Sprite();
        //this.sprite = new Sprite(GraphicManager.meteor_animation.getKeyFrame(state_time,true));
        this.sprite.setBounds( position.getX(), position.getY(), position.getWidth(), position.getHeight());
        this.target = new Vector2(0,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.sprite.draw(batch, parentAlpha);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        state_time += delta;
        sprite.setRegion(animation.getKeyFrame(state_time, true));
        this.moveToTarget(delta);
        this.sprite.setPosition(getX(), getY());

    }

    @Override
    public void reset() {
        this.state_time = 0f;
        this.position.set(0,0,0,0);
        this.sprite.setBounds( position.getX(), position.getY(), position.getWidth(), position.getHeight());
        this.setRotation(0);
        this.target.set(0,0);
        this.animation = null;
        this.hp = 0f;
        this.speed = 0f;
        this.money_drop = 0f;
        this.remove();
    }

    public void setTarget(float x, float y){
        target.set(x,y);
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
        this.position.x += speed * delta * MathUtils.cos((float)((Math.PI / 180) * ( sprite.getRotation())));
        this.position.y += speed * delta * MathUtils.sin((float)((Math.PI / 180) * ( sprite.getRotation())));
    }

    public void setAsteroid(float x, float y){
        this.position.set(x - StaticUtils.ASTEROID_WIDTH/2, y - StaticUtils.ASTEROID_HEIGHT/2, StaticUtils.ASTEROID_WIDTH, StaticUtils.ASTEROID_HEIGHT);
        this.sprite.setBounds(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight());
        this.sprite.setOriginCenter();
        Gdx.app.log("Asteroid position", " " + sprite.getX() + " " + sprite.getY());
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        this.sprite.setRegion(this.animation.getKeyFrame(state_time, true));
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getMoneyDrop() {
        return money_drop;
    }

    public void setMoneyDrop(float money_drop) {
        this.money_drop = money_drop;
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
