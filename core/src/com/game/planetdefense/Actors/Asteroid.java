package com.game.planetdefense.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.game.planetdefense.StaticUtils;

public class Asteroid extends Actor implements Pool.Poolable{
//TODO: Zastanowic sie nad zmiana lotu z poruszania w przod na Action aktora
    private Sprite sprite;
    private Rectangle position;
    private Vector2 target;

    public Asteroid() {
        this.position = new Rectangle(0,0,0,0);
        this.sprite = new Sprite(new Texture(Gdx.files.internal("rock.png")));
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
        this.moveToTarget(delta);
        this.sprite.setPosition(getX(), getY());

    }

    @Override
    public void reset() {
        this.position.set(0,0,0,0);
        this.sprite.setBounds( position.getX(), position.getY(), position.getWidth(), position.getHeight());
        this.setRotation(0);
        this.target.set(0,0);
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
        this.position.x += StaticUtils.ASTEROID_SPEED * delta * MathUtils.cos((float)((Math.PI / 180) * ( sprite.getRotation())));
        this.position.y += StaticUtils.ASTEROID_SPEED * delta * MathUtils.sin((float)((Math.PI / 180) * ( sprite.getRotation())));
    }

    public void setAsteroid(float x, float y){
        this.position.set(x - StaticUtils.ASTEROID_WIDTH/2, y - StaticUtils.ASTEROID_HEIGHT/2, StaticUtils.ASTEROID_WIDTH, StaticUtils.ASTEROID_HEIGHT);
        this.sprite.setBounds(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight());
        this.sprite.setOriginCenter();
        Gdx.app.log("Asteroid position", " " + sprite.getX() + " " + sprite.getY());
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
