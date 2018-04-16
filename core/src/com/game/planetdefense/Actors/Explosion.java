package com.game.planetdefense.Actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.game.planetdefense.Utils.Managers.AssetsManager;

public class Explosion extends Actor implements Pool.Poolable {

    private Animation<TextureRegion> explosion_animation;
    private Sprite sprite;
    private float state_time;

    public Explosion(AssetsManager assetsManager) {
        explosion_animation = assetsManager.getExplosion_animation();
        sprite = new Sprite(explosion_animation.getKeyFrame(0, false));
        sprite.setBounds(0,0,0,0);
        state_time = 0f;
    }

    @Override
    public void reset() {
        sprite.setBounds(0,0,0,0);
        sprite.setRegion(explosion_animation.getKeyFrame(0, false));
        state_time = 0f;
        this.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        state_time += delta;
        sprite.setRegion(explosion_animation.getKeyFrame(state_time, false));
        super.act(delta);
    }

    public void setExplosion(float x, float y, float width, float height){
        sprite.setBounds(x,y,width,height);
        state_time = 0f;
    }

    public boolean isAnimationEnd(){
        return explosion_animation.isAnimationFinished(state_time);
    }
}
